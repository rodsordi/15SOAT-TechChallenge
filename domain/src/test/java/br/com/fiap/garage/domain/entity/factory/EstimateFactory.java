package br.com.fiap.garage.domain.entity.factory;

import br.com.fiap.garage.domain.entity.Estimate;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

import static br.com.fiap.commons.util.DateUtil.newDateTime;
import static br.com.fiap.commons.util.ReflectionUtil.assertThatObject;
import static java.util.UUID.fromString;
import static lombok.AccessLevel.PRIVATE;

@RequiredArgsConstructor(access = PRIVATE)
public final class EstimateFactory {

    private final Estimate.EstimateBuilder<?, ?> builder;

    public static EstimateFactory create_Estimate() {
        return new EstimateFactory(Estimate.builder());
    }

    public Estimate withAllFields() {
        var result = builder
                // Self
                .id(1L)
                .amount(new BigDecimal("1500.00"))
                // Inheritance (AuditableEntity)
                .createdAt(newDateTime("21/04/2026 10:00:00"))
                .updatedAt(newDateTime("21/04/2026 10:00:00"))
                .build();

        // And
        assertThatObject(result)
                .hasNoEmptyFields();
        return result;
    }

    public Estimate withAllFieldsExceptDB() {
        withAllFields();
        return builder
                .id(null)
                .createdAt(null)
                .updatedAt(null)
                .build();
    }

    public Estimate valid() {
        return builder
                .amount(new BigDecimal("500.50"))
                .build();
    }

    public Estimate initiatedEmpty() {
        return builder.build();
    }
}