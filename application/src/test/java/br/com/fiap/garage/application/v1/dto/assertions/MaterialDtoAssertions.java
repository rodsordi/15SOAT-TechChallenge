package br.com.fiap.garage.application.v1.dto.assertions;

import br.com.fiap.garage.application.v1.dto.MaterialDto;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

import static br.com.fiap.commons.util.DateUtil.newDateTime;
import static br.com.fiap.commons.util.ReflectionUtil.assertThatObject;
import static br.com.fiap.garage.domain.enums.MaterialType.SHOP_SUPPLY;
import static lombok.AccessLevel.PRIVATE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.spy;

@RequiredArgsConstructor(access = PRIVATE)
public final class MaterialDtoAssertions {

    public static Response assertThat_MaterialDto_Response(MaterialDto.Response actual) {
        assertThat(actual).isNotNull();
        return new Response(spy(actual));
    }

    @RequiredArgsConstructor(access = PRIVATE)
    public static final class Response {

        private final MaterialDto.Response actual;

        /**
         * @see br.com.fiap.garage.domain.entity.factory.MaterialFactory
         * .withAllFields()
         */
        public void wasConvertedFrom_Material() {
            // Self
            assertThat(actual.getId())
                    .hasToString("73ffaed5-ebc3-4c28-901d-b0240c30b639");
            assertThat(actual.getType())
                    .isEqualTo(SHOP_SUPPLY);
            assertThat(actual.getName())
                    .isEqualTo("Engine Oil");
            assertThat(actual.getDescription())
                    .isEqualTo("Synthetic 5W-30 motor oil");
            assertThat(actual.getAmount())
                    .isEqualTo(new BigDecimal("150.00"));

            //Inheritance (AuditableTable)
            assertThat(actual.getCreatedAt())
                    .isEqualTo(newDateTime("13/12/2026 23:59:59"));
            assertThat(actual.getUpdatedAt())
                    .isEqualTo(newDateTime("14/12/2026 23:59:59"));

            // And
            assertThatObject(actual)
                    .hasAllGetMethodsVerifiedOnceAtLeast();
        }
    }

    public static Representation assertThat_MaterialDto_Representation(MaterialDto.Representation actual) {
        assertThat(actual).isNotNull();
        return new Representation(spy(actual));
    }

    @RequiredArgsConstructor(access = PRIVATE)
    public static final class Representation {

        private final MaterialDto.Representation actual;

        /**
         * @see br.com.fiap.garage.domain.entity.factory.MaterialFactory
         * .withAllFields()
         */
        public void wasConvertedFrom_Material() {
            // Self
            assertThat(actual.getId())
                    .hasToString("73ffaed5-ebc3-4c28-901d-b0240c30b639");
            assertThat(actual.getType())
                    .isEqualTo(SHOP_SUPPLY);
            assertThat(actual.getName())
                    .isEqualTo("Engine Oil");
            assertThat(actual.getAmount())
                    .isEqualTo(new BigDecimal("150.00"));

            // Composition
            assertThat(actual.getLinks())
                    .isNullOrEmpty();

            //Inheritance (AuditableTable)
            assertThat(actual.getCreatedAt())
                    .isEqualTo(newDateTime("13/12/2026 23:59:59"));
            assertThat(actual.getUpdatedAt())
                    .isEqualTo(newDateTime("14/12/2026 23:59:59"));

            // And
            assertThatObject(actual)
                    .hasAllGetMethodsVerifiedOnceAtLeast();
        }
    }
}