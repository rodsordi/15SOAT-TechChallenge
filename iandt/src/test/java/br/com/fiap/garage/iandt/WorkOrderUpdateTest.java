package br.com.fiap.garage.iandt;

import br.com.fiap.garage.GarageIntegrationTest;
import br.com.fiap.garage.application.GarageApplication;
import br.com.fiap.garage.domain.enums.WorkOrderStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.testcontainers.junit.jupiter.Testcontainers;

import static br.com.fiap.garage.application.v1.dto.factory.WorkOrderDtoFactory.create_WorkOrderDto_Request;
import static br.com.fiap.garage.domain.enums.WorkOrderStatus.EXECUTING;
import static br.com.fiap.garage.iandt.WorkOrderCreationTest.createWorkOrder;
import static io.restassured.RestAssured.given;
import static java.time.Duration.ofSeconds;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.awaitility.Awaitility.await;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@ActiveProfiles("int_test")
@SpringBootTest(webEnvironment = RANDOM_PORT)
@ContextConfiguration(classes = GarageApplication.class)
@Testcontainers
public class WorkOrderUpdateTest extends GarageIntegrationTest {

    @DisplayName("When creating a new workOrder")
    @Nested
    class Create {

        @DisplayName("Then should execute successfully")
        @Nested
        class Success {

            @DisplayName("Given an workOrder with all fields")
            @Test
            void test1() {
                //Given
                var requestBody = create_WorkOrderDto_Request().valid();
                //When
                var response = createWorkOrder(authorization, json, requestBody);
                //Then
                assertThat(response.statusCode())
                        .isEqualTo(201);
                var workOrderId = response.jsonPath().getString("id");
                await()
                        .atMost(ofSeconds(3))
                        .pollInterval(ofSeconds(1))
                        .untilAsserted(() -> {
                            searchNotificationByWorkOrderId(workOrderId);
                            updateWorkOrder(EXECUTING);
                        });
            }
        }
    }

    private void searchNotificationByWorkOrderId(String workOrderId) {
        //When
        var notificationResponse = given()
                .log().all()
                .header("Authorization", authorization)
                .param("externalId", workOrderId)
                .get("/v1/notifications")
                .then()
                .log().all()
                .extract()
                .response();
        //Then
        assertThat(notificationResponse.statusCode())
                .isEqualTo(200);
    }

    private void updateWorkOrder(WorkOrderStatus workOrderStatus) {

    }
}