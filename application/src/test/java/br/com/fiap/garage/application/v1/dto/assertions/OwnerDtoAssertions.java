package br.com.fiap.garage.application.v1.dto.assertions;

import br.com.fiap.garage.application.v1.dto.OwnerDto;
import lombok.RequiredArgsConstructor;

import static br.com.fiap.commons.util.DateUtil.newDateTime;
import static br.com.fiap.commons.util.ReflectionUtil.assertThatObject;
import static lombok.AccessLevel.PRIVATE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.spy;

@RequiredArgsConstructor(access = PRIVATE)
public final class OwnerDtoAssertions {

    @RequiredArgsConstructor(access = PRIVATE)
    public static final class Response {
        
        private final OwnerDto.Response actual;

        public static Response assertThat_OwnerDto_Response(OwnerDto.Response actual) {
            assertThat(actual).isNotNull();
            return new Response(spy(actual));
        }

        /**
         * @see br.com.fiap.garage.domain.entity.factory.OwnerFactory
         * .withAllFields()
         */
        public void wasConvertedFrom_Owner() {
            // Self
            assertThat(actual.getId())
                    .hasToString("f47ac10b-58cc-4372-a567-0e02b2c3d479");
            assertThat(actual.getUsername())
                    .isEqualTo("jack.doe@company.com");
            assertThat(actual.getName())
                    .isEqualTo("John Doe");
            assertThat(actual.getEmail())
                    .isEqualTo("john.doe@fiap.com.br");

            //Inheritance (AuditableTable)
            assertThat(actual.getCreatedAt())
                    .isEqualTo(newDateTime("21/04/2026 10:00:00"));
            assertThat(actual.getUpdatedAt())
                    .isEqualTo(newDateTime("21/04/2026 15:30:00"));

            // And
            assertThatObject(actual)
                    .hasAllGetMethodsVerifiedOnceAtLeast();
        }
    }

    @RequiredArgsConstructor(access = PRIVATE)
    public static final class Representation {

        private final OwnerDto.Representation actual;

        public static Representation assertThat_OwnerDto_Representation(OwnerDto.Representation actual) {
            assertThat(actual).isNotNull();
            return new Representation(spy(actual));
        }

        /**
         * @see br.com.fiap.garage.domain.entity.factory.OwnerFactory
         * .withAllFields()
         */
        public void wasConvertedFrom_Owner() {
            // Self
            assertThat(actual.getId())
                    .hasToString("f47ac10b-58cc-4372-a567-0e02b2c3d479");
            assertThat(actual.getUsername())
                    .isEqualTo("jack.doe@company.com");
            assertThat(actual.getName())
                    .isEqualTo("John Doe");
            assertThat(actual.getEmail())
                    .isEqualTo("john.doe@fiap.com.br");

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