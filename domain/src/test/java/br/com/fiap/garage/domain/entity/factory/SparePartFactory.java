package br.com.fiap.garage.domain.entity.factory;

import br.com.fiap.garage.domain.entity.SparePart;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

import static br.com.fiap.commons.util.DateUtil.newDateTime;
import static br.com.fiap.commons.util.ReflectionUtil.assertThatObject;
import static java.util.UUID.fromString;
import static lombok.AccessLevel.PRIVATE;

@RequiredArgsConstructor(access = PRIVATE)
public final class SparePartFactory {

    private final SparePart.SparePartBuilder<?, ?> builder;

    public static SparePartFactory create_SparePart() {
        return new SparePartFactory(SparePart.builder());
    }

    public SparePart withAllFields() {
        var result = builder
                // Self
                .id(fromString("4f9e8d2a-1c5b-4a32-9d8e-7f6a5b4c3d2e"))
                .name("Engine")
                .price(new BigDecimal("10000.99"))
                .quantityInStock(1)
                .createdAt(newDateTime("13/12/2026 23:59:59"))
                .updatedAt(newDateTime("14/12/2026 23:59:59"))
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