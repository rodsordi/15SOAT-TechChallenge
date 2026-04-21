package br.com.fiap.garage.domain.entity.assertions;

import br.com.fiap.garage.domain.entity.ShopSupply;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

import static br.com.fiap.commons.util.ReflectionUtil.assertThatObject;
import static lombok.AccessLevel.PRIVATE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.spy;

@RequiredArgsConstructor(access = PRIVATE)
public final class ShopSupplyAssertions {

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

        //Inheritance (AuditableEntity)
        assertThat(actual.getCreatedAt())
                .isNull();
        assertThat(actual.getUpdatedAt())
                .isNull();

        // And
        assertThatObject(actual)
                .hasAllGetMethodsVerifiedOnceAtLeast();
    }
}