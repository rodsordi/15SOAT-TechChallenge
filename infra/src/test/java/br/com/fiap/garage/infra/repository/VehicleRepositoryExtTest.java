package br.com.fiap.garage.infra.repository;

import br.com.fiap.commons.config.JpaConfig;
import br.com.fiap.garage.domain.entity.Vehicle;
import br.com.fiap.garage.domain.filter.VehicleFilter;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jpa.test.autoconfigure.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

import static br.com.fiap.garage.domain.entity.factory.VehicleFactory.create_Vehicle;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.util.ReflectionTestUtils.setField;

@ActiveProfiles("test")
@DataJpaTest
@ContextConfiguration(classes = JpaConfig.class)
class VehicleRepositoryExtTest {

    @Autowired
    private VehicleRepositoryExt repository;

    @Autowired
    private TestEntityManager em;

    @DisplayName("When finding all vehicles")
    @Nested
    class FindAll {

        @DisplayName("Then should execute successfully")
        @Nested
        class Success {

            @DisplayName("Given a valid filter, in scenario with registers")
            @Test
            void test1() {
                //Scenario
                var vehicle = create_Vehicle().withAllFieldsExceptDB();
                setField(vehicle, "make", "BMW");
                em.merge(vehicle);
                em.flush();
                //Given
                var filter = new VehicleFilter();
                //When
                var actual = repository.findAll(filter, filter.buildPageRequest());
                //Then
                assertThat(actual)
                        .hasSize(1)
                        .extracting(Vehicle::getMake)
                        .containsExactly("BMW");
            }
        }
    }
}