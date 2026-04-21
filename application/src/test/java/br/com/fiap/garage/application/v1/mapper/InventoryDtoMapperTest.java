package br.com.fiap.garage.application.v1.mapper;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static br.com.fiap.garage.application.v1.dto.assertions.InventoryDtoAssertions.Representation.assertThat_InventoryDto_Representation;
import static br.com.fiap.garage.domain.entity.factory.ShopSupplyFactory.create_ShopSupply;
import static br.com.fiap.garage.domain.entity.factory.SparePartFactory.create_SparePart;
import static org.mapstruct.factory.Mappers.getMapper;

class InventoryDtoMapperTest {

    private static final InventoryDtoMapper MAPPER = getMapper(InventoryDtoMapper.class);

    @DisplayName("When converting Inventory to InventoryDto.Representation")
    @Nested
    class ConvertToRepresentation {

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
                var actual = MAPPER.convertToRepresentation(source);
                //Then
                assertThat_InventoryDto_Representation(actual)
                        .wasConvertedFrom_SparePart();
            }

            @DisplayName("Given a ShopSupply with all fields")
            @Test
            void test2() {
                //Given
                var source = create_ShopSupply()
                        .withAllFields();
                //When
                var actual = MAPPER.convertToRepresentation(source);
                //Then
                assertThat_InventoryDto_Representation(actual)
                        .wasConvertedFrom_ShopSupply();
            }
        }
    }
}