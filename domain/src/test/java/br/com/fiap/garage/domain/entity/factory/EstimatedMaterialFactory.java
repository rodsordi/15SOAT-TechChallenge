package br.com.fiap.garage.domain.entity.factory;

import br.com.fiap.garage.domain.entity.EstimatedMaterial;
import br.com.fiap.garage.domain.enums.MaterialType;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

import static br.com.fiap.commons.util.DateUtil.newDateTime;
import static br.com.fiap.commons.util.ReflectionUtil.assertThatObject;
import static br.com.fiap.garage.domain.entity.factory.MaterialFactory.create_Material;
import static br.com.fiap.garage.domain.enums.MaterialType.SHOP_SUPPLY;
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
                .type(SHOP_SUPPLY)
                .name("Ceramic Brake Pads")
                .description("High-performance front ceramic brake pads")
                .cost(new BigDecimal("150.00"))
                // Inheritance (AuditableEntity)
                .createdAt(newDateTime("13/12/2026 23:59:59"))
                .updatedAt(newDateTime("14/12/2026 23:59:59"))
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
                .type(SHOP_SUPPLY)
                .name("Synthetic Motor Oil")
                .cost(new BigDecimal("45.50"))
                .build();
    }

    public EstimatedMaterial initiatedEmpty() {
        return builder.build();
    }
}