package br.com.fiap.garage.domain.entity.assertions;

import br.com.fiap.garage.domain.entity.SparePart;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

import static br.com.fiap.commons.util.ReflectionUtil.assertThatObject;
import static lombok.AccessLevel.PRIVATE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.spy;

@RequiredArgsConstructor(access = PRIVATE)
public final class SparePartAssertions {

    private final SparePart actual;

    public static SparePartAssertions assertThat_SparePart(SparePart actual) {
        assertThat(actual).isNotNull();
        return new SparePartAssertions(spy(actual));
    }

    /**
     * @see br.com.fiap.garage.application.v1.dto.factory.SparePartDtoFactory
     * .withAllFields()
     */
    public void wasConvertedFrom_SparePartDto_Request() {
        // Self
        assertThat(actual.getId())
                .isNull();
        assertThat(actual.getName())
                .isEqualTo("Brake Pad");
        assertThat(actual.getAmount())
                .isEqualTo(new BigDecimal("120.50"));
        assertThat(actual.getQuantityInStock())
                .isEqualTo(10);

        //Inheritance (AuditableEntity)
        assertThat(actual.getCreatedAt())
                .isNull();
        assertThat(actual.getUpdatedAt())
                .isNull();

        // And
        assertThatObject(actual)
                .hasAllGetMethodsVerifiedOnceAtLeast();
    }
}