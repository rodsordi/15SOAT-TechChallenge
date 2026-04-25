package br.com.fiap.garage.domain.entity.assertions;

import br.com.fiap.garage.domain.entity.EstimatedService;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

import static br.com.fiap.commons.util.DateUtil.newDateTime;
import static br.com.fiap.commons.util.ReflectionUtil.assertThatObject;
import static br.com.fiap.garage.domain.entity.assertions.EstimatedMaterialAssertions.assertThat_EstimatedMaterial;
import static br.com.fiap.garage.domain.entity.assertions.ServiceAssertions.assertThat_Service;
import static lombok.AccessLevel.PRIVATE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.spy;

@RequiredArgsConstructor(access = PRIVATE)
public final class EstimatedServiceAssertions {

    private final EstimatedService actual;

    public static EstimatedServiceAssertions assertThat_EstimatedService(EstimatedService actual) {
        assertThat(actual).isNotNull();
        return new EstimatedServiceAssertions(spy(actual));
    }

    /**
     * @see br.com.fiap.garage.application.v1.dto.factory.ServiceDtoFactory
     * .withAllFields()
     */
    public void wasConvertedFrom_ServiceDto_Request() {
        // Self
        assertThat(actual.getId())
                .hasToString("9d1b9b7c-bd7c-4f5f-a747-0b1f63aac409");
        assertThat(actual.getName())
                .isEqualTo("Engine Maintenance");
        assertThat(actual.getDescription())
                .isEqualTo("Complete engine diagnostic and oil change");
        assertThat(actual.getAmount())
                .isEqualTo(new BigDecimal("500.00"));
        assertThat(actual.getFinishedAt())
                .isEqualTo(newDateTime("25/04/2026 14:00:00"));

        // Composition
        assertThat_EstimatedMaterial(actual.getEstimatedMaterials().stream().findFirst().orElseThrow())
                .isEqualTo_EstimatedMaterial();

        // Inheritance (AuditableEntity)
        assertThat(actual.getCreatedAt())
                .isEqualTo(newDateTime("24/04/2026 10:00:00"));
        assertThat(actual.getUpdatedAt())
                .isEqualTo(newDateTime("25/04/2026 09:00:00"));

        // And
        assertThatObject(actual)
                .hasAllGetMethodsVerifiedOnceAtLeast();
    }

    /**
     * @see br.com.fiap.garage.application.v1.dto.factory.WorkOrderDtoFactory
     * .withAllFields()
     */
    public void wasConvertedFrom_WorkOrderDto_Request() {
        // Self
        assertThat(actual.getId())
                .isNull();
        assertThat(actual.getName())
                .isNull();
        assertThat(actual.getDescription())
                .isNull();
        assertThat(actual.getAmount())
                .isNull();
        assertThat(actual.getFinishedAt())
                .isNull();

        // Composition
        assertThat_Service(actual.getService())
                .wasConvertedFrom_WorkOrderDto();
        assertThat(actual.getEstimatedMaterials())
                .isNullOrEmpty();

        // Inheritance (AuditableEntity)
        assertThat(actual.getCreatedAt())
                .isNull();
        assertThat(actual.getUpdatedAt())
                .isNull();

        // And
        assertThatObject(actual)
                .hasAllGetMethodsVerifiedOnceAtLeast();
    }
}