package br.com.fiap.garage.application.v1.dto.assertions;

import br.com.fiap.garage.application.v1.dto.EstimateDto;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

import static br.com.fiap.commons.util.DateUtil.newDateTime;
import static br.com.fiap.commons.util.ReflectionUtil.assertThatObject;
import static lombok.AccessLevel.PRIVATE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.spy;

@RequiredArgsConstructor(access = PRIVATE)
public final class EstimateDtoAssertions {

    @RequiredArgsConstructor(access = PRIVATE)
    public static final class Response {
        
        private final EstimateDto.Response actual;

        public static Response assertThat_EstimateDto_Response(EstimateDto.Response actual) {
            assertThat(actual).isNotNull();
            return new Response(spy(actual));
        }

        /**
         * @see br.com.fiap.garage.domain.entity.factory.EstimateFactory
         * .withAllFields()
         */
        public void wasConvertedFrom_Estimate() {
            // Self
            assertThat(actual.getId())
                    .hasToString("f47ac10b-58cc-4372-a567-0e02b2c3d479");
            assertThat(actual.getAmount())
                    .isEqualTo(new BigDecimal("1500.00"));

            //Inheritance (AuditableTable)
            assertThat(actual.getCreatedAt())
                    .isEqualTo(newDateTime("21/04/2026 10:00:00"));
            assertThat(actual.getUpdatedAt())
                    .isEqualTo(newDateTime("21/04/2026 10:00:00"));

            // And
            assertThatObject(actual)
                    .hasAllGetMethodsVerifiedOnceAtLeast();
        }
    }

    @RequiredArgsConstructor(access = PRIVATE)
    public static final class Representation {

        private final EstimateDto.Representation actual;

        public static Representation assertThat_EstimateDto_Representation(EstimateDto.Representation actual) {
            assertThat(actual).isNotNull();
            return new Representation(spy(actual));
        }

        /**
         * @see br.com.fiap.garage.domain.entity.factory.EstimateFactory
         * .withAllFields()
         */
        public void wasConvertedFrom_Estimate() {
            // Self
            assertThat(actual.getId())
                    .hasToString("9f8792ea-cf8f-43d1-824f-9f7bc433e404");
            assertThat(actual.getAmount())
                    .isEqualTo(new BigDecimal("1.00"));

            //Inheritance (AuditableTable)
            assertThat(actual.getCreatedAt())
                    .isEqualTo(newDateTime("21/04/2026 10:00:00"));
            assertThat(actual.getUpdatedAt())
                    .isEqualTo(newDateTime("21/04/2026 15:30:00"));
            assertThat(actual.getLinks())
                    .isNullOrEmpty();

            // And
            assertThatObject(actual)
                    .hasAllGetMethodsVerifiedOnceAtLeast();
        }
    }
}