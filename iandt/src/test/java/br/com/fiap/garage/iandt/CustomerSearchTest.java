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

import static br.com.fiap.garage.application.v1.dto.factory.CustomerDtoFactory.create_CustomerDto_Request;
import static br.com.fiap.garage.iandt.CustomerCreationTest.createCustomer;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static java.text.MessageFormat.format;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.test.util.ReflectionTestUtils.setField;

@ActiveProfiles("int_test")
@SpringBootTest(webEnvironment = RANDOM_PORT)
@ContextConfiguration(classes = GarageApplication.class)
@Testcontainers
public class CustomerSearchTest extends GarageIntegrationTest {

    @DisplayName("When finding an customer by id")
    @Nested
    class FindById {

        @DisplayName("Then should execute successfully")
        @Nested
        class Success {

            @DisplayName("Given a valid customer id, in scenario with registered customer")
            @Test
            void test1() {
                //Scenario
                var registeredCustomer = create_CustomerDto_Request().withAllFields();
                var scenarioResponse = createCustomer(authorization, json, registeredCustomer);
                //Given
                var customerId = scenarioResponse.jsonPath().getString("id");
                //When
                var response = given()
                        .log().all()
                        .header("Authorization", authorization)
                        .get(format("/v1/customers/{0}", customerId))
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

    @DisplayName("When finding all customers")
    @Nested
    class FindAll {

        @DisplayName("Then should execute successfully")
        @Nested
        class Success {

            @DisplayName("Given no query params, in scenario with registered customer")
            @Test
            void test1() {
                //Scenario
                var scenarioRequestBody = create_CustomerDto_Request().withAllFields();
                createCustomer(authorization, json, scenarioRequestBody);
                //When
                var response = given()
                        .log().all()
                        .header("Authorization", authorization)
                        .get("/v1/customers")
                        .then()
                        .log().all()
                        .extract()
                        .response();
                //Then
                assertThat(response.statusCode())
                        .isEqualTo(200);
            }

            @DisplayName("Given a valid document query param, in scenario with registered customer")
            @Test
            void test2() {
                //Scenario
                var scenarioRequestBody = create_CustomerDto_Request().withAllFields();
                setField(scenarioRequestBody, "document", "54.662.770/0001-29");
                createCustomer(authorization, json, scenarioRequestBody);
                //When
                var response = given()
                        .log().all()
                        .header("Authorization", authorization)
                        .param("document", "54662770000129")
                        .get("/v1/customers")
                        .then()
                        .log().all()
                        .extract()
                        .response();
                //Then
                assertThat(response.statusCode())
                        .isEqualTo(200);
                assertThat(response.body().jsonPath().getList("content"))
                        .hasSize(1);
                assertThat(response.body().jsonPath().getString("content.[0].document"))
                        .isEqualTo("54.662.770/0001-29");
            }
        }
    }
}
