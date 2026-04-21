package br.com.fiap.garage.application.v1.mapper;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static br.com.fiap.garage.application.v1.dto.factory.VehicleDtoFactory.Request.createVehicleDto_Request;
import static br.com.fiap.garage.domain.entity.assertions.VehicleAssertions.assertThat_Vehicle;
import static org.mapstruct.factory.Mappers.getMapper;

class VehicleDtoMapperTest {

    private static final VehicleDtoMapper MAPPER = getMapper(VehicleDtoMapper.class);

    @DisplayName("When converting VehicleDto.Request to Vehicle")
    @Nested
    class Convert1 {

        @DisplayName("Then should execute successfully")
        @Nested
        class Success {

            @DisplayName("Given a VehicleDto.Request with all fields")
            @Test
            void test1() {
                //Given
                var source = createVehicleDto_Request()
                        .withAllFields();
                //When
                var actual = MAPPER.convert(source);
                //Then
                assertThat_Vehicle(actual)
                        .wasConvertedFrom_VehicleDto_Request();
            }
        }
    }

}