package br.com.fiap.garage.application.v1.dto.assertions;

import br.com.fiap.garage.application.v1.dto.InventoryMaterialDto;
import lombok.RequiredArgsConstructor;

import static br.com.fiap.commons.util.DateUtil.newDateTime;
import static br.com.fiap.commons.util.ReflectionUtil.assertThatObject;
import static br.com.fiap.garage.application.v1.dto.assertions.MaterialDtoAssertions.assertThat_MaterialDto_Representation;
import static lombok.AccessLevel.PRIVATE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.spy;

@RequiredArgsConstructor(access = PRIVATE)
public final class InventoryMaterialDtoAssertions {

    public static Representation assertThat_InventoryMaterialDto_Representation(InventoryMaterialDto.Representation actual) {
        assertThat(actual).isNotNull();
        return new Representation(spy(actual));
    }

    @RequiredArgsConstructor(access = PRIVATE)
    public static final class Representation {

        private final InventoryMaterialDto.Representation actual;

        /**
         * @see br.com.fiap.garage.domain.entity.factory.InventoryMaterialFactory
         * .withAllFields()
         */
        public void wasConvertedFrom_InventoryMaterial() {
            // Self
            assertThat(actual.getId())
                    .hasToString("9d1b9b7c-bd7c-4f5f-a747-0b1f63aac409");

            // Composition
            assertThat_MaterialDto_Representation(actual.getMaterial())
                    .wasConvertedFrom_Material();
            assertThat(actual.getLinks())
                    .isNullOrEmpty();

            //Inheritance (AuditableTable)
            assertThat(actual.getCreatedAt())
                    .isEqualTo(newDateTime("13/12/2026 23:59:59"));
            assertThat(actual.getUpdatedAt())
                    .isEqualTo(newDateTime("14/12/2026 23:59:59"));
            assertThat(actual.getLinks())
                    .isNullOrEmpty();

            // And
            assertThatObject(actual)
                    .hasAllGetMethodsVerifiedOnceAtLeast();
        }
    }
}