package br.com.fiap.garage.iandt.flow;

import br.com.fiap.garage.GarageIntegrationTest;
import br.com.fiap.garage.application.GarageApplication;
import br.com.fiap.garage.application.v1.dto.WorkOrderDto;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.UUID;

import static br.com.fiap.garage.application.v1.dto.factory.WorkOrderDtoFactory.create_WorkOrderDto_Request;
import static br.com.fiap.garage.domain.enums.WorkOrderStatus.*;
import static br.com.fiap.garage.iandt.WorkOrderCreationTest.createWorkOrder;
import static br.com.fiap.garage.iandt.WorkOrderUpdateTest.updateWorkOrder;
import static io.restassured.RestAssured.given;
import static java.lang.Thread.sleep;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@ActiveProfiles("int_test")
@SpringBootTest(webEnvironment = RANDOM_PORT)
@ContextConfiguration(classes = GarageApplication.class)
@Testcontainers
public class CompleteFlowTest extends GarageIntegrationTest {

    @DisplayName("When executing the complete work flow")
    @Nested
    class Create {

        @DisplayName("Then should execute successfully")
        @Nested
        class Success {

            @DisplayName("Given an workOrder with all fields")
            @Test
            void test1() {

                System.out.println("====== Creating a new work order ====");

                //Given
                var requestBody1 = create_WorkOrderDto_Request().valid();
                //When
                var response1 = createWorkOrder(authorization, json, requestBody1);
                //Then
                assertThat(response1.statusCode()).isEqualTo(201);
                var workOrderId = response1.jsonPath().getString("id");

                System.out.println("\n====== Sending to the mechanic to diagnose the problems of the customer's vehicle ======\n");

                //Given
                var requestBody2 = WorkOrderDto.PatchRequest.builder()
                        .status(DIAGNOSING)
                        .build();
                //When
                var response2 = updateWorkOrder(authorization, json, workOrderId, requestBody2);
                //Then
                assertThat(response2.statusCode()).isEqualTo(200);
                assertThat(response2.jsonPath().getString("status")).isEqualTo("DIAGNOSING");

                System.out.println("\n====== Finished the diagnose, waiting for the customer's approval ======\n");

                //Given
                var requestBody3 = WorkOrderDto.PatchRequest.builder()
                        .status(WAITING_FOR_APPROVAL)
                        .build();
                //When
                var response3 = updateWorkOrder(authorization, json, workOrderId, requestBody3);
                //Then
                assertThat(response3.statusCode()).isEqualTo(200);
                assertThat(response3.jsonPath().getString("status")).isEqualTo("WAITING_FOR_APPROVAL");
                try {
                    sleep(3000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }


                System.out.println("\n====== Checking the e-mail notification informing the service estimation ======\n");

                //When
                var response4 = searchNotificationByWorkOrderId(workOrderId);
                //Then
                assertThat(response4.statusCode()).isEqualTo(200);

                System.out.println("\n====== Customer approval in website simulation ======\n");

                //Given
                var requestBody5 = WorkOrderDto.PatchRequest.builder()
                        .status(EXECUTING)
                        .build();
                //When
                var response5 = updateWorkOrder(authorization, json, workOrderId, requestBody5);
                //Then
                assertThat(response5.statusCode()).isEqualTo(200);
                assertThat(response5.jsonPath().getString("status")).isEqualTo("EXECUTING");
                var serviceId = response5.jsonPath().getString("estimatedServices[0].id");

                System.out.println("\n====== Finishing the service estimation ======\n");

                //Given
                var requestBody6 = WorkOrderDto.PatchRequest.builder()
                        .finishedServiceId(UUID.fromString(serviceId))
                        .build();
                //When
                var response6 = updateWorkOrder(authorization, json, workOrderId, requestBody6);
                //Then
                assertThat(response6.statusCode()).isEqualTo(200);
                assertThat(response6.jsonPath().getString("estimatedServices[0].finishedAt")).isNotBlank();

                System.out.println("\n====== Finished the work order ======\n");

                //Given
                var requestBody7 = WorkOrderDto.PatchRequest.builder()
                        .status(FINISHED)
                        .build();
                //When
                var response7 = updateWorkOrder(authorization, json, workOrderId, requestBody7);
                //Then
                assertThat(response7.statusCode()).isEqualTo(200);
                assertThat(response7.jsonPath().getString("status")).isEqualTo("FINISHED");

                System.out.println("\n====== Released the vehicle to the customer ======\n");

                //Given
                var requestBody8 = WorkOrderDto.PatchRequest.builder()
                        .status(RELEASED)
                        .build();
                //When
                var response8 = updateWorkOrder(authorization, json, workOrderId, requestBody8);
                //Then
                assertThat(response8.statusCode()).isEqualTo(200);
                assertThat(response8.jsonPath().getString("status")).isEqualTo("RELEASED");

                System.out.println("\n====== Simulate routine started of Services avg time calculation  ======\n");

                //When
                var response9 = given()
                        .log().all()
                        .header("Authorization", authorization)
                        .get("/v1/services/calculateAverageTime")
                        .then()
                        .log().all()
                        .extract()
                        .response();
                //Then
                assertThat(response9.statusCode()).isEqualTo(204);

                System.out.println("\n====== Query services average time ======\n");

                //When
                var response10 = given()
                        .log().all()
                        .header("Authorization", authorization)
                        .pathParam("serviceId", serviceId)
                        .get("/v1/services/{serviceId}")
                        .then()
                        .log().all()
                        .extract()
                        .response();
                //Then
                assertThat(response10.statusCode()).isEqualTo(200);
                assertThat(response10.jsonPath().getLong("averageTimeInMinutes")).isZero();
            }
        }
    }

    private Response searchNotificationByWorkOrderId(String workOrderId) {
        return given()
                .log().all()
                .header("Authorization", authorization)
                .param("externalId", workOrderId)
                .get("/v1/notifications")
                .then()
                .log().all()
                .extract()
                .response();
    }
}