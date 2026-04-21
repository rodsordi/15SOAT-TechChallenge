package br.com.fiap.garage.domain.entity.factory;

import br.com.fiap.garage.domain.entity.SparePart;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

import static br.com.fiap.commons.util.ReflectionUtil.assertThatObject;
import static java.util.UUID.fromString;
import static lombok.AccessLevel.PRIVATE;

@RequiredArgsConstructor(access = PRIVATE)
public final class SparePartFactory {

    private final SparePart.SparePartBuilder<?, ?> builder;

    public static SparePartFactory createSparePart() {
        return new SparePartFactory(SparePart.builder());
    }

    public SparePart withAllFields() {
        var result = builder
                // Self
                .id(fromString("4f9e8d2a-1c5b-4a32-9d8e-7f6a5b4c3d2e"))
                .name("Synthetic Oil 5W-30")
                .price(new BigDecimal("45.90"))
                .quantityInStock(150)
                .build();

        // And
        assertThatObject(result)
                .hasNoEmptyFields();
        return result;
    }

    public SparePart withAllFieldsExceptDB() {
        withAllFields();
        return builder
                .id(null)
                .build();
    }

    public SparePart valid() {
        return builder
                .name("Brake Pad Set")
                .price(new BigDecimal("120.00"))
                .quantityInStock(20)
                .build();
    }

    public SparePart initiatedEmpty() {
        return builder.build();
    }
}