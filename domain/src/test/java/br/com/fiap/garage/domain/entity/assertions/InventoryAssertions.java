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

    private final Inventory actual;

    public static InventoryAssertions assertThat_Inventory(Inventory actual) {
        assertThat(actual).isNotNull();
        return new InventoryAssertions(spy(actual));
    }

    /**
     * @see br.com.fiap.garage.application.v1.dto.factory.InventoryDtoFactory
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