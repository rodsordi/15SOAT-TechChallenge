package br.com.fiap.garage.application.v1.dto.assertions;

import br.com.fiap.garage.application.v1.dto.InventoryMaterialDto;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

import static br.com.fiap.commons.util.DateUtil.newDateTime;
import static br.com.fiap.commons.util.ReflectionUtil.assertThatObject;
import static lombok.AccessLevel.PRIVATE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.spy;

@RequiredArgsConstructor(access = PRIVATE)
public final class InventoryDtoAssertions {

    public static Representation assertThat_InventoryDto_Representation(InventoryMaterialDto.Representation actual) {
        assertThat(actual).isNotNull();
        return new Representation(spy(actual));
    }

    @RequiredArgsConstructor(access = PRIVATE)
    public static final class Representation {

        private final InventoryMaterialDto.Representation actual;

        /**
         * @see br.com.fiap.garage.domain.entity.factory.SparePartFactory
         * .withAllFields()
         */
        public void wasConvertedFrom_SparePart() {
            // Self
            assertThat(actual.getId())
                    .hasToString("4f9e8d2a-1c5b-4a32-9d8e-7f6a5b4c3d2e");
            assertThat(actual.getName())
                    .isEqualTo("Engine");
            assertThat(actual.getAmount())
                    .isEqualTo(new BigDecimal("10000.99"));
            assertThat(actual.getQuantityInStock())
                    .isEqualTo(1);

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

        /**
         * @see br.com.fiap.garage.domain.entity.factory.ShopSupplyFactory
         * .withAllFields()
         */
        public void wasConvertedFrom_ShopSupply() {
            // Self
            assertThat(actual.getId())
                    .hasToString("d341007b-7d1f-406e-aabf-37db3ddbdb8e");
            assertThat(actual.getName())
                    .isEqualTo("Synthetic Oil 5W-30");
            assertThat(actual.getAmount())
                    .isEqualTo(new BigDecimal("45.90"));
            assertThat(actual.getQuantityInStock())
                    .isEqualTo(111);

            //Inheritance (AuditableTable)
            assertThat(actual.getCreatedAt())
                    .isEqualTo(newDateTime("13/12/2027 23:59:59"));
            assertThat(actual.getUpdatedAt())
                    .isEqualTo(newDateTime("14/12/2027 23:59:59"));
            assertThat(actual.getLinks())
                    .isNullOrEmpty();

            // And
            assertThatObject(actual)
                    .hasAllGetMethodsVerifiedOnceAtLeast();
        }
    }
}