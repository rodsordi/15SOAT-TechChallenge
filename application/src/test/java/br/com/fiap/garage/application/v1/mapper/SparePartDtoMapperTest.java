package br.com.fiap.garage.application.v1.mapper;

import br.com.fiap.garage.domain.entity.assertions.SparePartAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static br.com.fiap.garage.application.v1.dto.assertions.SparePartDtoAssertions.Response.assertThat_SparePartDto_Response;
import static br.com.fiap.garage.application.v1.dto.factory.SparePartDtoFactory.create_SparePartDto_Request;
import static br.com.fiap.garage.domain.entity.factory.SparePartFactory.create_SparePart;
import static org.mapstruct.factory.Mappers.getMapper;

class SparePartDtoMapperTest {

    private static final SparePartDtoMapper MAPPER = getMapper(SparePartDtoMapper.class);

    @DisplayName("When converting SparePartDto.Request to SparePart")
    @Nested
    class Convert1 {

        @DisplayName("Then should execute successfully")
        @Nested
        class Success {


            @DisplayName("Given a SparePartDto.Request with all fields")
            @Test
            void test1() {
                //Given
                var source = create_SparePartDto_Request()
                        .withAllFields();
                //When
                var actual = MAPPER.convert(source);
                //Then
                SparePartAssertions.assertThat_SparePart(actual)
                        .wasConvertedFrom_SparePartDto_Request();
            }
        }
    }

    @DisplayName("When converting SparePart to SparePartDto.Response")
    @Nested
    class Convert2 {

        @DisplayName("Then should execute successfully")
        @Nested
        class Success {


            @DisplayName("Given a SparePart with all fields")
            @Test
            void test1() {
                //Given
                var source = create_SparePart()
                        .withAllFields();
                //When
                var actual = MAPPER.convert(source);
                //Then
                assertThat_SparePartDto_Response(actual)
                        .wasConvertedFrom_SparePart();
            }
        }
    }
}