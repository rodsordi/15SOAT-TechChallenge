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

import java.util.Set;
import java.util.UUID;

import static br.com.fiap.garage.application.v1.dto.factory.ServiceDtoFactory.create_ServiceDto_Request;
import static br.com.fiap.garage.domain.entity.factory.InventoryMaterialFactory.create_InventoryMaterial;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.test.util.ReflectionTestUtils.setField;

@ActiveProfiles("int_test")
@SpringBootTest(webEnvironment = RANDOM_PORT)
@ContextConfiguration(classes = GarageApplication.class)
@Testcontainers
class ServiceCreationTest extends GarageIntegrationTest {

    @DisplayName("When creating a new service")
    @Nested
    class Create {

        @DisplayName("Then should execute successfully")
        @Nested
        class Success {

            @DisplayName("Given a valid service, in scenario with a registered inventory material")
            @Test
            void test1() {
                //Scenario
                var materialId = createInventoryMaterial();
                //Given
                var requestBody = create_ServiceDto_Request()
                        .valid();
                setField(requestBody, "materialsIds", Set.of(materialId));
                //When
                var response = given()
                        .log().all()
                        .header("Authorization", authorization)
                        .contentType(JSON)
                        .body(json.writeValueAsString(requestBody))
                        .post("/v1/services")
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

    private UUID createInventoryMaterial() {
        //Scenario
        given()
                .log().all()
                .header("Authorization", authorization)
                .contentType(JSON)
                .body(json.writeValueAsString(create_InventoryMaterial().withAllFields()))
                .post("/v1/inventory-materials")
                .then()
                .log().all()
                .extract()
                .response();
        //When
        var response = given()
                .log().all()
                .header("Authorization", authorization)
                .get("/v1/inventory-materials")
                .then()
                .log().all()
                .extract()
                .response();
        //Then
        assertThat(response.statusCode())
                .isEqualTo(200);
        var id = response.body().jsonPath().getString("content.id");
        return UUID.fromString(id);
    }
}
