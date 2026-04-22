package br.com.fiap.garage.iandt;

import br.com.fiap.garage.GarageIntegrationTest;
import br.com.fiap.garage.application.GarageApplication;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.testcontainers.junit.jupiter.Testcontainers;

import static br.com.fiap.garage.application.v1.dto.factory.WorkOrderDtoFactory.Request.create_WorkOrderDto_Request;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static java.text.MessageFormat.format;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@ActiveProfiles("int_test")
@SpringBootTest(webEnvironment = RANDOM_PORT)
@ContextConfiguration(classes = GarageApplication.class)
@Testcontainers
public class WorkOrderResourceTest extends GarageIntegrationTest {

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
                var requestBody = create_WorkOrderDto_Request()
                        .withAllFields();
                //When
                var response = given()
                        .log().all()
                        .contentType(JSON)
                        .body(json.writeValueAsString(requestBody))
                        .post("/v1/work-orders")
                        .then()
                        .log().all()
                        .extract()
                        .response();
                //Then
                assertThat(response.statusCode())
                        .isEqualTo(201);
            }
        }
    }

    @DisplayName("When finding an workOrder by id")
    @Nested
    class FindById {

        @DisplayName("Then should execute successfully")
        @Nested
        class Success {

            @DisplayName("Given a valid workOrder id, in scenario with saved workOrder")
            @Test
            void test1() {
                //Scenario
                var scenarioResponse = given()
                        .log().all()
                        .contentType(JSON)
                        .body(json.writeValueAsString(create_WorkOrderDto_Request().withAllFields()))
                        .post("/v1/work-orders")
                        .then()
                        .log().all()
                        .extract()
                        .response();
                //Given
                var workOrderId = scenarioResponse.jsonPath().getString("id");
                //When
                var response = given()
                        .log().all()
                        .get(format("/v1/work-orders/{0}", workOrderId))
                        .then()
                        .log().all()
                        .extract()
                        .response();
                //Then
                assertThat(response.statusCode())
                        .isEqualTo(200);
            }
        }
    }

    @DisplayName("When finding all workOrders")
    @Nested
    class FindAll {

        @DisplayName("Then should execute successfully")
        @Nested
        class Success {

            @DisplayName("Given no query params, in scenario with saved workOrder")
            @Test
            void test1() {
                //Scenario
                given()
                        .log().all()
                        .contentType(JSON)
                        .body(json.writeValueAsString(create_WorkOrderDto_Request().withAllFields()))
                        .post("/v1/work-orders")
                        .then()
                        .log().all()
                        .extract()
                        .response();
                //When
                var response = given()
                        .log().all()
                        .get("/v1/work-orders")
                        .then()
                        .log().all()
                        .extract()
                        .response();
                //Then
                assertThat(response.statusCode())
                        .isEqualTo(200);
            }
        }
    }
}
