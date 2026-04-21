package br.com.fiap.garage.application.v1.mapper;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static br.com.fiap.garage.application.v1.dto.assertions.OwnerDtoAssertions.Representation.assertThat_OwnerDto_Representation;
import static br.com.fiap.garage.application.v1.dto.assertions.OwnerDtoAssertions.Response.assertThat_OwnerDto_Response;
import static br.com.fiap.garage.application.v1.dto.factory.OwnerDtoFactory.Request.create_OwnerDto_Request;
import static br.com.fiap.garage.domain.entity.assertions.OwnerAssertions.assertThat_Owner;
import static br.com.fiap.garage.domain.entity.factory.OwnerFactory.create_Owner;
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
                var source = create_OwnerDto_Request()
                        .withAllFields();
                //When
                var actual = MAPPER.convert(source);
                //Then
                assertThat_Owner(actual)
                        .wasConvertedFrom_OwnerDto_Request();
            }
        }
    }

    @DisplayName("When converting Owner to OwnerDto.Response")
    @Nested
    class Convert2 {

        @DisplayName("Then should execute successfully")
        @Nested
        class Success {

            @DisplayName("Given a Owner with all fields")
            @Test
            void test1() {
                //Given
                var source = create_Owner()
                        .withAllFields();
                //When
                var actual = MAPPER.convert(source);
                //Then
                assertThat_OwnerDto_Response(actual)
                        .wasConvertedFrom_Owner();
            }
        }
    }

    @DisplayName("When converting Owner to OwnerDto.Representation")
    @Nested
    class ConvertToRepresentation {

        @DisplayName("Then should execute successfully")
        @Nested
        class Success {

            @DisplayName("Given a Owner with all fields")
            @Test
            void test1() {
                //Given
                var source = create_Owner()
                        .withAllFields();
                //When
                var actual = MAPPER.convertToRepresentation(source);
                //Then
                assertThat_OwnerDto_Representation(actual)
                        .wasConvertedFrom_Owner();
            }
        }
    }
}