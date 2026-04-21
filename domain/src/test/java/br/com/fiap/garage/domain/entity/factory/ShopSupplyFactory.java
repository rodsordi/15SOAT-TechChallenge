package br.com.fiap.garage.domain.entity.factory;

import br.com.fiap.garage.domain.entity.ShopSupply;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

import static br.com.fiap.commons.util.DateUtil.newDateTime;
import static br.com.fiap.commons.util.ReflectionUtil.assertThatObject;
import static java.util.UUID.fromString;
import static lombok.AccessLevel.PRIVATE;

@RequiredArgsConstructor(access = PRIVATE)
public final class ShopSupplyFactory {

    private final ShopSupply.ShopSupplyBuilder<?, ?> builder;

    public static ShopSupplyFactory create_ShopSupply() {
        return new ShopSupplyFactory(ShopSupply.builder());
    }

    public ShopSupply withAllFields() {
        var result = builder
                // Self
                .id(fromString("d341007b-7d1f-406e-aabf-37db3ddbdb8e"))
                .name("Synthetic Oil 5W-30")
                .price(new BigDecimal("45.90"))
                .quantityInStock(111)
                .createdAt(newDateTime("13/12/2027 23:59:59"))
                .updatedAt(newDateTime("14/12/2027 23:59:59"))
                .build();

        // And
        assertThatObject(result)
                .hasNoEmptyFields();
        return result;
    }

    public ShopSupply withAllFieldsExceptDB() {
        withAllFields();
        return builder
                .id(null)
                .build();
    }

    public ShopSupply valid() {
        return builder
                .name("Brake Pad Set")
                .price(new BigDecimal("120.00"))
                .quantityInStock(20)
                .build();
    }

    public ShopSupply initiatedEmpty() {
        return builder.build();
    }
}