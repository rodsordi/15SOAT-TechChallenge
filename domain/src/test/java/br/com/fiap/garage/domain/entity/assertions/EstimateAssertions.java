package br.com.fiap.garage.domain.entity.assertions;

import br.com.fiap.garage.domain.entity.Estimate;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

import static br.com.fiap.commons.util.DateUtil.newDateTime;
import static br.com.fiap.commons.util.ReflectionUtil.assertThatObject;
import static lombok.AccessLevel.PRIVATE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.spy;

@RequiredArgsConstructor(access = PRIVATE)
public final class EstimateAssertions {

    private final Estimate actual;

    public static EstimateAssertions assertThat_Estimate(Estimate actual) {
        assertThat(actual).isNotNull();
        return new EstimateAssertions(spy(actual));
    }

    /**
     * @see EstimateFactory
     * .withAllFields()
     */
    public void wasConvertedFrom_EstimateDto_Request() {
        // Self
        assertThat(actual.getId())
                .isNull();
        assertThat(actual.getAmount())
                .isEqualTo(new BigDecimal("1500.00"));

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