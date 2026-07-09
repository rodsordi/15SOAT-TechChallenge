package br.com.fiap.garage.manual;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.assertj.core.api.Assertions.assertThat;

public class ManualTest {

    @BeforeEach
    void beforeEach() {
        RestAssured.baseURI = "http://localhost:8080/api";
    }

    @DisplayName("When creating a new customer")
    @Nested
    class Create {

        @DisplayName("Then should execute successfully")
        @Nested
        class Success {

            @DisplayName("Given an customer with all fields")
            @Test
            void test1() {
                var response = given()
                        .log().all()
                        .contentType(JSON)
                        .body("""
                        {
                          "username":"employee_1_odoqo@garage.com",
                          "password":"abcd1234",
                          "name":"John Employee",
                          "email":"employee_1_mypbo@garage.com",
                          "cpf":"651.885.891-57"
                        }
                        """)
                        .post("/v1/employees")
                        .then()
                        .log().all()
                        .extract()
                        .response();
                assertThat(response.statusCode())
                        .isEqualTo(201);
            }
        }
    }
}
