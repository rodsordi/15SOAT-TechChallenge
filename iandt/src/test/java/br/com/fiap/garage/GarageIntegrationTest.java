package br.com.fiap.garage;

import br.com.fiap.commons.iandt.setup.LocalStackSetup;
import br.com.fiap.commons.iandt.setup.PostgresSetup;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.env.Environment;
import org.springframework.data.repository.CrudRepository;
import tools.jackson.databind.json.JsonMapper;

import java.util.List;

import static java.lang.String.format;
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

    @BeforeEach
    void beforeEach() {
        RestAssured.baseURI = format("http://localhost:%s/api", port);

        if (env.acceptsProfiles(of("int_test"))) {
            repositories.forEach(repository -> {
                System.out.println(repository.getClass().getGenericInterfaces()[0]);
                repository.deleteAll();
            });
        }
    }
}
