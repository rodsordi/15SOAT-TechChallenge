package br.com.fiap.garage.domain.use_case;

import br.com.fiap.garage.domain.entity.Vehicle;
import br.com.fiap.garage.domain.repository.VehicleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static br.com.fiap.garage.domain.entity.factory.VehicleFactory.create_Vehicle;
import static java.util.UUID.fromString;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.util.ReflectionTestUtils.setField;

@ExtendWith(MockitoExtension.class)
class VehicleCreationUseCaseTest {

    @InjectMocks
    private VehicleCreationUseCase vehicleCreationUseCase;

    @Mock
    private VehicleRepository repository;

    @DisplayName("When creating Vehicle")
    @Nested
    class Create {

        @DisplayName("Then should execute successfully")
        @Nested
        class Success {

            @BeforeEach
            void beforeEach() {
                when(repository.save(any()))
                        .thenAnswer(invocationOnMock -> {
                            Vehicle vehicle = invocationOnMock.getArgument(0);
                            setField(vehicle, "id", fromString("c0a1f176-d3e6-4910-8fba-9a6c31bc5577"));
                            return vehicle;
                        });
            }

            @DisplayName("Given a Vehicle with all fields")
            @Test
            void test1() {
                //Given
                var vehicle = create_Vehicle()
                        .withAllFields();
                //When
                var actual = vehicleCreationUseCase.create(vehicle);
                //Then
                assertThat(actual.getId())
                        .hasToString("c0a1f176-d3e6-4910-8fba-9a6c31bc5577");
            }
        }
    }
}