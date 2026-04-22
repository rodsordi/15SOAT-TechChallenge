package br.com.fiap.garage;

import br.com.fiap.commons.iandt.setup.LocalStackSetup;
import br.com.fiap.commons.iandt.setup.PostgresSetup;
import br.com.fiap.garage.application.v1.dto.factory.EmployeeDtoFactory;
import br.com.fiap.garage.domain.entity.factory.EmployeeFactory;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.env.Environment;
import org.springframework.data.repository.CrudRepository;
import tools.jackson.databind.json.JsonMapper;

import java.text.MessageFormat;
import java.util.List;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static java.lang.String.format;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.core.env.Profiles.of;

public abstract class GarageIntegrationTest implements PostgresSetup, LocalStackSetup {

    @Autowired
    private Environment env;

    @LocalServerPort
    private Integer port;

    @Autowired
    private List<CrudRepository<?, ?>> repositories;

    @Autowired
    protected JsonMapper json;

    protected String authorization;

    @BeforeEach
    void beforeEach() {
        RestAssured.baseURI = format("http://localhost:%s/api", port);

        if (env.acceptsProfiles(of("int_test"))) {
            repositories.forEach(repository -> {
                System.out.println(repository.getClass().getGenericInterfaces()[0]);
                repository.deleteAll();
            });
        }

        authenticate();
    }

    private void authenticate() {
        var employee = EmployeeDtoFactory.create_EmployeeDto_Request()
                .withAllFields();

        var response = given()
                .log().all()
                .contentType(JSON)
                .body("""
                        {
                            "username": "admin",
                            "password": "admin"
                        }
                        """)
                .post("/v1/employees")
                .then()
                .log().all()
                .extract()
                .response();
        assertThat(response.statusCode()).isEqualTo(200);

        response = given()
                .log().all()
                .contentType(JSON)
                .body("""
                        {
                            "username": "admin",
                            "password": "admin"
                        }
                        """)
                .post("/auth/login")
                .then()
                .log().all()
                .extract()
                .response();
        assertThat(response.statusCode()).isEqualTo(200);
        authorization = MessageFormat.format("Bearer {0}", response.jsonPath().getString("token"));
    }
}
