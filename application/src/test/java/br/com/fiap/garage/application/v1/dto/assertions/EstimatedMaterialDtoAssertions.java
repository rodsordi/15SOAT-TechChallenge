package br.com.fiap.garage.application.v1.dto.assertions;

import br.com.fiap.garage.application.v1.dto.EstimatedMaterialDto;
import br.com.fiap.garage.domain.enums.MaterialType;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

import static br.com.fiap.commons.util.DateUtil.newDateTime;
import static br.com.fiap.commons.util.ReflectionUtil.assertThatObject;
import static br.com.fiap.garage.domain.enums.MaterialType.SHOP_SUPPLY;
import static lombok.AccessLevel.PRIVATE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.spy;

@RequiredArgsConstructor(access = PRIVATE)
public final class EstimatedMaterialDtoAssertions {

    public static Response assertThat_EstimatedMaterialDto_Response(EstimatedMaterialDto.Response actual) {
        assertThat(actual).isNotNull();
        return new Response(spy(actual));
    }

    @RequiredArgsConstructor(access = PRIVATE)
    public static final class Response {

        private final EstimatedMaterialDto.Response actual;

        /**
         * @see br.com.fiap.garage.domain.entity.factory.EstimatedMaterialFactory
         * .withAllFields()
         */
        public void wasConvertedFrom_EstimatedMaterial() {
            // Self
            assertThat(actual.getId())
                    .hasToString("f47ac10b-58cc-4372-a567-0e02b2c3d479");
            assertThat(actual.getType())
                    .isEqualTo(SHOP_SUPPLY);
            assertThat(actual.getName())
                    .isEqualTo("Ceramic Brake Pads");
            assertThat(actual.getDescription())
                    .isEqualTo("High-performance front ceramic brake pads");
            assertThat(actual.getCost())
                    .isEqualTo(new BigDecimal("150.00"));

            // Inheritance (AuditableEntity)
            assertThat(actual.getCreatedAt())
                    .isEqualTo(newDateTime("13/12/2026 23:59:59"));
            assertThat(actual.getUpdatedAt())
                    .isEqualTo(newDateTime("14/12/2026 23:59:59"));

            // And
            assertThatObject(actual)
                    .hasAllGetMethodsVerifiedOnceAtLeast();
        }
    }

    public static Representation assertThat_EstimatedMaterialDto_Representation(EstimatedMaterialDto.Representation actual) {
        assertThat(actual).isNotNull();
        return new Representation(spy(actual));
    }

    @RequiredArgsConstructor(access = PRIVATE)
    public static final class Representation {

        private final EstimatedMaterialDto.Representation actual;

        /**
         * @see br.com.fiap.garage.domain.entity.factory.EstimatedMaterialFactory
         * .withAllFields()
         */
        public void wasConvertedFrom_EstimatedMaterial() {
            // Self
            assertThat(actual.getId())
                    .hasToString("9d1b9b7c-bd7c-4f5f-a747-0b1f63aac409");
            assertThat(actual.getType())
                    .isEqualTo(SHOP_SUPPLY);
            assertThat(actual.getName())
                    .isEqualTo("Galvanized Steel Pipe");
            assertThat(actual.getCost())
                    .isEqualTo(new BigDecimal("150.75"));

            // Inheritance (AuditableEntity)
            assertThat(actual.getCreatedAt())
                    .isEqualTo(newDateTime("25/04/2026 10:00:00"));
            assertThat(actual.getUpdatedAt())
                    .isEqualTo(newDateTime("25/04/2026 18:30:00"));

            // HATEOAS
            assertThat(actual.getLinks())
                    .isNullOrEmpty();

            // And
            assertThatObject(actual)
                    .hasAllGetMethodsVerifiedOnceAtLeast();
        }
    }
}