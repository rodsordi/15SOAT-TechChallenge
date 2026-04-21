package br.com.fiap.garage.application.v1.mapper;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static br.com.fiap.garage.application.v1.dto.assertions.ShopSupplyDtoAssertions.Response.assertThat_ShopSupplyDto_Response;
import static br.com.fiap.garage.application.v1.dto.factory.ShopSupplyDtoFactory.Request.create_ShopSupplyDto_Request;
import static br.com.fiap.garage.domain.entity.assertions.ShopSupplyAssertions.assertThat_ShopSupply;
import static br.com.fiap.garage.domain.entity.factory.ShopSupplyFactory.create_ShopSupply;
import static org.mapstruct.factory.Mappers.getMapper;

class ShopSupplyDtoMapperTest {

    private static final ShopSupplyDtoMapper MAPPER = getMapper(ShopSupplyDtoMapper.class);

    @DisplayName("When converting ShopSupplyDto.Request to ShopSupply")
    @Nested
    class Convert1 {

        @DisplayName("Then should execute successfully")
        @Nested
        class Success {

            @DisplayName("Given a ShopSupplyDto.Request with all fields")
            @Test
            void test1() {
                //Given
                var source = create_ShopSupplyDto_Request()
                        .withAllFields();
                //When
                var actual = MAPPER.convert(source);
                //Then
                assertThat_ShopSupply(actual)
                        .wasConvertedFrom_ShopSupplyDto_Request();
            }
        }
    }

    @DisplayName("When converting ShopSupply to ShopSupplyDto.Response")
    @Nested
    class Convert2 {

        @DisplayName("Then should execute successfully")
        @Nested
        class Success {

            @DisplayName("Given a ShopSupply with all fields")
            @Test
            void test1() {
                //Given
                var source = create_ShopSupply()
                        .withAllFields();
                //When
                var actual = MAPPER.convert(source);
                //Then
                assertThat_ShopSupplyDto_Response(actual)
                        .wasConvertedFrom_ShopSupply();
            }
        }
    }
}