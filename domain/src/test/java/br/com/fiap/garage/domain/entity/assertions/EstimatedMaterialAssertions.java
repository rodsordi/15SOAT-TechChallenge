package br.com.fiap.garage.domain.entity.assertions;

import br.com.fiap.garage.domain.entity.EstimatedMaterial;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

import static br.com.fiap.commons.util.DateUtil.newDateTime;
import static br.com.fiap.commons.util.ReflectionUtil.assertThatObject;
import static lombok.AccessLevel.PRIVATE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.spy;

@RequiredArgsConstructor(access = PRIVATE)
public final class EstimatedMaterialAssertions {

    private final EstimatedMaterial actual;

    public static EstimatedMaterialAssertions assertThat_EstimatedMaterial(EstimatedMaterial actual) {
        assertThat(actual).isNotNull();
        return new EstimatedMaterialAssertions(spy(actual));
    }

    /**
     * @see br.com.fiap.garage.domain.entity.factory.EstimatedMaterialFactory
     * .withAllFields()
     */
    public void isEqualTo_EstimatedMaterial() {
        // Self
        assertThat(actual.getId())
                .hasToString("9d1b9b7c-bd7c-4f5f-a747-0b1f63aac409");
        assertThat(actual.getName())
                .isEqualTo("Synthetic Oil 5W30");
        assertThat(actual.getDescription())
                .isEqualTo("High-performance synthetic motor oil");
        assertThat(actual.getCost())
                .isEqualTo(new BigDecimal("85.50"));

        // Inheritance (AuditableEntity)
        assertThat(actual.getCreatedAt())
                .isEqualTo(newDateTime("30/12/2024 23:59:59"));
        assertThat(actual.getUpdatedAt())
                .isEqualTo(newDateTime("31/12/2024 23:59:59"));

        // And
        assertThatObject(actual)
                .hasAllGetMethodsVerifiedOnceAtLeast();
    }
}