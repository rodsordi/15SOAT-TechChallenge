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

import static br.com.fiap.garage.application.v1.dto.factory.SparePartDtoFactory.create_SparePartDto_Request;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@ActiveProfiles("int_test")
@SpringBootTest(webEnvironment = RANDOM_PORT)
@ContextConfiguration(classes = GarageApplication.class)
@Testcontainers
public class InventoryResourceTest extends GarageIntegrationTest {

    @DisplayName("When finding all inventorys")
    @Nested
    class FindAll {

        @DisplayName("Then should execute successfully")
        @Nested
        class Success {

            @DisplayName("Given no query params, in scenario with saved inventory")
            @Test
            void test1() {
                //Scenario
                given()
                        .log().all()
                        .header("Authorization", authorization)
                        .contentType(JSON)
                        .body(json.writeValueAsString(create_SparePartDto_Request().withAllFields()))
                        .post("/v1/spare-parts")
                        .then()
                        .log().all()
                        .extract()
                        .response();
                //When
                var response = given()
                        .log().all()
                        .header("Authorization", authorization)
                        .get("/v1/inventories")
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
