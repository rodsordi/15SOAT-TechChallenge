package br.com.fiap.garage.application.v1.mapper;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static br.com.fiap.garage.application.v1.dto.factory.ShopSupplyDtoFactory.Request.createShopSupplyDto_Request;
import static br.com.fiap.garage.application.v1.dto.factory.SparePartDtoFactory.Request.createSparePartDto_Request;
import static br.com.fiap.garage.domain.entity.assertions.InventoryAssertions.ShopSupplyAssertions.assertThat_ShopSupply;
import static br.com.fiap.garage.domain.entity.assertions.InventoryAssertions.SparePartAssertions.assertThat_SparePart;
import static org.mapstruct.factory.Mappers.getMapper;

class InventoryDtoMapperTest {

    private static final InventoryDtoMapper MAPPER = getMapper(InventoryDtoMapper.class);

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
                var source = createShopSupplyDto_Request()
                        .withAllFields();
                //When
                var actual = MAPPER.convert(source);
                //Then
                assertThat_ShopSupply(actual)
                        .wasConvertedFrom_ShopSupplyDto_Request();
            }
        }
    }

    @DisplayName("When converting SparePartDto.Request to SparePart")
    @Nested
    class Convert2 {

        @DisplayName("Then should execute successfully")
        @Nested
        class Success {

            @DisplayName("Given a SparePartDto.Request with all fields")
            @Test
            void test1() {
                //Given
                var source = createSparePartDto_Request()
                        .withAllFields();
                //When
                var actual = MAPPER.convert(source);
                //Then
                assertThat_SparePart(actual)
                        .wasConvertedFrom_SparePartDto_Request();
            }
        }
    }
}