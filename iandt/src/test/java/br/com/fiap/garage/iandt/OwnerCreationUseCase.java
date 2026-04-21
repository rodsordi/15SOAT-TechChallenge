package br.com.fiap.garage.iandt;

import br.com.fiap.garage.application.GarageApplication;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.testcontainers.junit.jupiter.Testcontainers;

import static br.com.fiap.garage.application.v1.dto.factory.OwnerDtoFactory.Request.create_OwnerDto_Request;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@ActiveProfiles("int_test")
@SpringBootTest(webEnvironment = RANDOM_PORT)
@ContextConfiguration(classes = GarageApplication.class)
@Testcontainers
public class OwnerCreationUseCase extends BaseIntegrationTest {

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

                //Then

            }
        }
    }
}
