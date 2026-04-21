package br.com.fiap.garage.domain.entity.assertions;

import br.com.fiap.garage.domain.entity.Inventory;
import br.com.fiap.garage.domain.entity.ShopSupply;
import br.com.fiap.garage.domain.entity.SparePart;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

import static br.com.fiap.commons.util.ReflectionUtil.assertThatObject;
import static lombok.AccessLevel.PRIVATE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.spy;

@RequiredArgsConstructor(access = PRIVATE)
public final class InventoryAssertions {

    @RequiredArgsConstructor(access = PRIVATE)
    public static final class ShopSupplyAssertions {

        private final ShopSupply actual;

        public static ShopSupplyAssertions assertThat_ShopSupply(ShopSupply actual) {
            assertThat(actual).isNotNull();
            return new ShopSupplyAssertions(spy(actual));
        }

        /**
         * @see InventoryFactory
         * .withAllFields()
         */
        public void wasConvertedFrom_ShopSupplyDto_Request() {
            // Self
            assertThat(actual.getId())
                    .isNull();
            assertThat(actual.getName())
                    .isEqualTo("Synthetic Oil 5W-30");
            assertThat(actual.getPrice())
                    .isEqualTo(new BigDecimal("85.50"));
            assertThat(actual.getQuantityInStock())
                    .isEqualTo(6);

            // And
            assertThatObject(actual)
                    .hasAllGetMethodsVerifiedOnceAtLeast();
        }
    }

    @RequiredArgsConstructor(access = PRIVATE)
    public static final class SparePartAssertions {

        private final SparePart actual;

        public static SparePartAssertions assertThat_SparePart(SparePart actual) {
            assertThat(actual).isNotNull();
            return new SparePartAssertions(spy(actual));
        }

        /**
         * @see InventoryFactory
         * .withAllFields()
         */
        public void wasConvertedFrom_SparePartDto_Request() {
            // Self
            assertThat(actual.getId())
                    .isNull();
            assertThat(actual.getName())
                    .isEqualTo("Brake Pad");
            assertThat(actual.getPrice())
                    .isEqualTo(new BigDecimal("120.50"));
            assertThat(actual.getQuantityInStock())
                    .isEqualTo(10);

            // And
            assertThatObject(actual)
                    .hasAllGetMethodsVerifiedOnceAtLeast();
        }
    }
}