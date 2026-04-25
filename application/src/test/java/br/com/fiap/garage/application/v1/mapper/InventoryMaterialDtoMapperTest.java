package br.com.fiap.garage.application.v1.mapper;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static br.com.fiap.garage.application.v1.dto.assertions.InventoryMaterialDtoAssertions.assertThat_InventoryMaterialDto_Representation;
import static br.com.fiap.garage.domain.entity.factory.InventoryMaterialFactory.create_InventoryMaterial;
import static org.mapstruct.factory.Mappers.getMapper;

class InventoryMaterialDtoMapperTest {

    private static final InventoryMaterialDtoMapper MAPPER = getMapper(InventoryMaterialDtoMapper.class);

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
                var source = create_InventoryMaterial()
                        .withAllFields();
                //When
                var actual = MAPPER.convertToRepresentation(source);
                //Then
                assertThat_InventoryMaterialDto_Representation(actual)
                        .wasConvertedFrom_InventoryMaterial();
            }

            @DisplayName("Given a ShopSupply with all fields")
            @Test
            void test2() {
                //Given
                var source = create_InventoryMaterial()
                        .withAllFields();
                //When
                var actual = MAPPER.convertToRepresentation(source);
                //Then
                assertThat_InventoryMaterialDto_Representation(actual)
                        .wasConvertedFrom_InventoryMaterial();
            }
        }
    }
}