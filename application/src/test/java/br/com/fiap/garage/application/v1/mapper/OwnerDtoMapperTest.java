package br.com.fiap.garage.application.v1.mapper;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static br.com.fiap.garage.application.v1.dto.factory.OwnerDtoFactory.Request.createOwnerDto_Request;
import static br.com.fiap.garage.domain.entity.assertions.OwnerAssertions.assertThat_Owner;
import static org.mapstruct.factory.Mappers.getMapper;

class OwnerDtoMapperTest {

    private static final OwnerDtoMapper MAPPER = getMapper(OwnerDtoMapper.class);

    @DisplayName("When converting OwnerDto.Request to Owner")
    @Nested
    class Convert1 {

        @DisplayName("Then should execute successfully")
        @Nested
        class Success {

            @DisplayName("Given a OwnerDto.Request with all fields")
            @Test
            void test1() {
                //Given
                var source = createOwnerDto_Request()
                        .withAllFields();
                //When
                var actual = MAPPER.convert(source);
                //Then
                assertThat_Owner(actual)
                        .wasConvertedFrom_OwnerDto_Request();
            }
        }
    }

}