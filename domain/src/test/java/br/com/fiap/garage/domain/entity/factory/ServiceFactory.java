package br.com.fiap.garage.domain.entity.factory;

import br.com.fiap.garage.domain.entity.Service;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.util.Set;

import static br.com.fiap.commons.util.DateUtil.newDateTime;
import static br.com.fiap.commons.util.ReflectionUtil.assertThatObject;
import static java.util.UUID.fromString;
import static lombok.AccessLevel.PRIVATE;

@RequiredArgsConstructor(access = PRIVATE)
public final class ServiceFactory {

    private final Service.ServiceBuilder<?, ?> builder;

    public static ServiceFactory create_Service() {
        return new ServiceFactory(Service.builder());
    }

    public Service withAllFields() {
        var result = builder
                // Self
                .id(fromString("f47ac10b-58cc-4372-a567-0e02b2c3d479"))
                .name("Complete Engine Overhaul")
                .description("Comprehensive engine inspection, repair, and parts replacement")
                .amount(new BigDecimal("3500.00"))
                // Composition
                .estimatedMaterials(Set.of("79ee04c1-da47-45fb-869f-876be2ab0ef2"))
                // Inheritance (AuditableEntity)
                .createdAt(newDateTime("30/12/2024 23:59:59"))
                .updatedAt(newDateTime("31/12/2024 23:59:59"))
                .build();
        // And
        assertThatObject(result)
                .hasNoEmptyFields();
        return result;
    }

    public Service withAllFieldsExceptDB() {
        withAllFields();
        return builder
                .id(null)
                .build();
    }

    public Service valid() {
        return builder
                .name("Complete Engine Overhaul")
                .description("Comprehensive engine inspection, repair, and parts replacement")
                .amount(new BigDecimal("3500.00"))
                .estimatedMaterials(Set.of("79ee04c1-da47-45fb-869f-876be2ab0ef2"))
                .build();
    }

    public Service initiatedEmpty() {
        return builder
                .clearEstimatedMaterials()
                .estimatedMaterials(Set.of("79ee04c1-da47-45fb-869f-876be2ab0ef2"))
                .build();
    }
}