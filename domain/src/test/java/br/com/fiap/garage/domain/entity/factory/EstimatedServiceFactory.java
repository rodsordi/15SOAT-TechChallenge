package br.com.fiap.garage.domain.entity.factory;

import br.com.fiap.garage.domain.entity.EstimatedMaterial;
import br.com.fiap.garage.domain.entity.EstimatedService;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import static br.com.fiap.commons.util.DateUtil.newDateTime;
import static br.com.fiap.commons.util.ReflectionUtil.assertThatObject;
import static br.com.fiap.garage.domain.entity.factory.EstimatedMaterialFactory.create_EstimatedMaterial;
import static br.com.fiap.garage.domain.entity.factory.ServiceFactory.create_Service;
import static java.util.UUID.fromString;
import static lombok.AccessLevel.PRIVATE;

@RequiredArgsConstructor(access = PRIVATE)
public final class EstimatedServiceFactory {

    private final EstimatedService.EstimatedServiceBuilder<?, ?> builder;

    public static EstimatedServiceFactory create_EstimatedService() {
        return new EstimatedServiceFactory(EstimatedService.builder());
    }

    public EstimatedService withAllFields() {
        var result = builder
                // Self
                .id(fromString("9d1b9b7c-bd7c-4f5f-a747-0b1f63aac409"))
                .name("Engine Overhaul")
                .description("Complete disassembly and rebuilding of the engine block.")
                .cost(new BigDecimal("4500.00"))
                .finishedAt(newDateTime("31/12/2025 18:00:00"))
                // Composition
                .estimatedMaterial(create_EstimatedMaterial().withAllFields())
                // Inheritance (AuditableEntity)
                .createdAt(newDateTime("13/12/2026 23:59:59"))
                .updatedAt(newDateTime("14/12/2026 23:59:59"))
                .build();

        // And
        assertThatObject(result)
                .hasNoEmptyFields();
        return result;
    }

    public EstimatedService withAllFieldsExceptDB() {
        withAllFields();
        return builder
                .id(null)
                .clearEstimatedMaterials()
                .estimatedMaterials(new HashSet<>(Set.of(create_EstimatedMaterial().withAllFieldsExceptDB())))
                .build();
    }

    public EstimatedService valid() {
        return builder
                .name("Engine Overhaul")
                .cost(new BigDecimal("4500.00"))
                .estimatedMaterials(new HashSet<>(Set.of(create_EstimatedMaterial().valid())))
                .build();
    }

    public EstimatedService initiatedEmpty() {
        return builder
                .estimatedMaterials(new HashSet<>(Set.of(EstimatedMaterial.builder().build())))
                .build();
    }
}