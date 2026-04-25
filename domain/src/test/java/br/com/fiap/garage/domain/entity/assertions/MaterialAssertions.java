package br.com.fiap.garage.domain.entity.assertions;

import br.com.fiap.garage.domain.entity.Material;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

import static br.com.fiap.commons.util.DateUtil.newDateTime;
import static br.com.fiap.commons.util.ReflectionUtil.assertThatObject;
import static br.com.fiap.garage.domain.enums.MaterialType.SHOP_SUPPLY;
import static lombok.AccessLevel.PRIVATE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.spy;

@RequiredArgsConstructor(access = PRIVATE)
public final class MaterialAssertions {

    private final Material actual;

    public static MaterialAssertions assertThat_Material(Material actual) {
        assertThat(actual).isNotNull();
        return new MaterialAssertions(spy(actual));
    }

    /**
     * @see br.com.fiap.garage.domain.entity.factory.MaterialFactory
     * .withAllFields()
     */
    public void isEqualTo_Material() {
        // Self
        assertThat(actual.getName())
                .isEqualTo("Engine Oil");
        assertThat(actual.getType())
                .isEqualTo(SHOP_SUPPLY);
        assertThat(actual.getDescription())
                .isEqualTo("Synthetic 5W-30 motor oil");
        assertThat(actual.getAmount())
                .isEqualTo(new BigDecimal("150.00"));

        // Inheritance (AuditableEntity)
        assertThat(actual.getCreatedAt())
                .isEqualTo(newDateTime("25/04/2026 10:00:00"));
        assertThat(actual.getUpdatedAt())
                .isEqualTo(newDateTime("25/04/2026 15:30:00"));

        // And
        assertThatObject(actual)
                .hasAllGetMethodsVerifiedOnceAtLeast();
    }

    /**
     * @see br.com.fiap.garage.application.v1.dto.factory.MaterialDtoFactory
     * .withAllFields()
     */
    public void wasConvertedFrom_MaterialDto_Request() {
        // Self
        assertThat(actual.getId())
                .isNull();
        assertThat(actual.getType())
                .isEqualTo(SHOP_SUPPLY);
        assertThat(actual.getName())
                .isEqualTo("Synthetic Oil 5W-30");
        assertThat(actual.getDescription())
                .isEqualTo("Oil 5W-30");
        assertThat(actual.getAmount())
                .isEqualTo(new BigDecimal("85.50"));

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