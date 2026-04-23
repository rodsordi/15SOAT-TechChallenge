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

import static br.com.fiap.garage.application.v1.dto.factory.OwnerDtoFactory.create_OwnerDto_Request;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static java.text.MessageFormat.format;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@ActiveProfiles("int_test")
@SpringBootTest(webEnvironment = RANDOM_PORT)
@ContextConfiguration(classes = GarageApplication.class)
@Testcontainers
public class OwnerResourceTest extends GarageIntegrationTest {

    @DisplayName("When creating a new owner")
    @Nested
    class Create {

        @DisplayName("Then should execute successfully")
        @Nested
        class Success {

            @DisplayName("Given an owner with all fields")
            @Test
            void test1() {
                //Given
                var requestBody = create_OwnerDto_Request()
                        .withAllFields();
                //When
                var response = given()
                        .log().all()
                        .header("Authorization", authorization)
                        .contentType(JSON)
                        .body(json.writeValueAsString(requestBody))
                        .post("/v1/owners")
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

    @DisplayName("When finding an owner by id")
    @Nested
    class FindById {

        @DisplayName("Then should execute successfully")
        @Nested
        class Success {

            @DisplayName("Given a valid owner id, in scenario with saved owner")
            @Test
            void test1() {
                //Scenario
                var scenarioResponse = given()
                        .log().all()
                        .header("Authorization", authorization)
                        .contentType(JSON)
                        .body(json.writeValueAsString(create_OwnerDto_Request().withAllFields()))
                        .post("/v1/owners")
                        .then()
                        .log().all()
                        .extract()
                        .response();
                //Given
                var ownerId = scenarioResponse.jsonPath().getString("id");
                //When
                var response = given()
                        .log().all()
                        .header("Authorization", authorization)
                        .get(format("/v1/owners/{0}", ownerId))
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

    @DisplayName("When finding all owners")
    @Nested
    class FindAll {

        @DisplayName("Then should execute successfully")
        @Nested
        class Success {

            @DisplayName("Given no query params, in scenario with saved owner")
            @Test
            void test1() {
                //Scenario
                given()
                        .log().all()
                        .header("Authorization", authorization)
                        .contentType(JSON)
                        .body(json.writeValueAsString(create_OwnerDto_Request().withAllFields()))
                        .post("/v1/owners")
                        .then()
                        .log().all()
                        .extract()
                        .response();
                //When
                var response = given()
                        .log().all()
                        .header("Authorization", authorization)
                        .get("/v1/owners")
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
