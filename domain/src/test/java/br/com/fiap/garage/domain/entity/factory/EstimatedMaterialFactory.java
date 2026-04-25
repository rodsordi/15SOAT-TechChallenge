package br.com.fiap.garage.domain.entity.factory;

import br.com.fiap.garage.domain.entity.EstimatedMaterial;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

import static br.com.fiap.commons.util.DateUtil.newDateTime;
import static br.com.fiap.commons.util.ReflectionUtil.assertThatObject;
import static java.util.UUID.fromString;
import static lombok.AccessLevel.PRIVATE;

@RequiredArgsConstructor(access = PRIVATE)
public final class EstimatedMaterialFactory {

    private final EstimatedMaterial.EstimatedMaterialBuilder<?, ?> builder;

    public static EstimatedMaterialFactory create_EstimatedMaterial() {
        return new EstimatedMaterialFactory(EstimatedMaterial.builder());
    }

    public EstimatedMaterial withAllFields() {
        var result = builder
                // Self
                .id(fromString("f47ac10b-58cc-4372-a567-0e02b2c3d479"))
                .name("Ceramic Brake Pads")
                .description("High-performance front ceramic brake pads")
                .amount(new BigDecimal("150.00"))
                // Inheritance (AuditableEntity)
                .createdAt(newDateTime("30/12/2024 23:59:59"))
                .updatedAt(newDateTime("31/12/2024 23:59:59"))
                .build();

        // And
        assertThatObject(result)
                .hasNoEmptyFields();
        return result;
    }

    public EstimatedMaterial withAllFieldsExceptDB() {
        withAllFields();
        return builder
                .id(null)
                .createdAt(null)
                .updatedAt(null)
                .build();
    }

    public EstimatedMaterial valid() {
        return builder
                .name("Synthetic Motor Oil")
                .amount(new BigDecimal("45.50"))
                .build();
    }

    public EstimatedMaterial initiatedEmpty() {
        return builder.build();
    }
}