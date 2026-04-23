package br.com.fiap.garage.application.v1.dto.assertions;

import br.com.fiap.garage.application.v1.dto.EmployeeDto;
import lombok.RequiredArgsConstructor;

import static br.com.fiap.commons.util.DateUtil.newDateTime;
import static br.com.fiap.commons.util.ReflectionUtil.assertThatObject;
import static lombok.AccessLevel.PRIVATE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.spy;

@RequiredArgsConstructor(access = PRIVATE)
public final class EmployeeDtoAssertions {

    @RequiredArgsConstructor(access = PRIVATE)
    public static final class Response {
        
        private final EmployeeDto.Response actual;

        public static Response assertThat_EmployeeDto_Response(EmployeeDto.Response actual) {
            assertThat(actual).isNotNull();
            return new Response(spy(actual));
        }

        /**
         * @see br.com.fiap.garage.domain.entity.factory.EmployeeFactory
         * .withAllFields()
         */
        public void wasConvertedFrom_Employee() {
            // Self
            assertThat(actual.getId())
                    .hasToString("a1b2c3d4-e5f6-4a5b-8c9d-0e1f2a3b4c5d");
            assertThat(actual.getUsername())
                    .isEqualTo("john.doe@garage.com");
            assertThat(actual.getName())
                    .isEqualTo("John Doe");
            assertThat(actual.getEmail())
                    .isEqualTo("john.doe@garage.com");

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

        private final EmployeeDto.Representation actual;

        public static Representation assertThat_EmployeeDto_Representation(EmployeeDto.Representation actual) {
            assertThat(actual).isNotNull();
            return new Representation(spy(actual));
        }

        /**
         * @see br.com.fiap.garage.domain.entity.factory.EmployeeFactory
         * .withAllFields()
         */
        public void wasConvertedFrom_Employee() {
            // Self
            assertThat(actual.getId())
                    .hasToString("a1b2c3d4-e5f6-4a5b-8c9d-0e1f2a3b4c5d");
            assertThat(actual.getUsername())
                    .isEqualTo("john.doe@garage.com");
            assertThat(actual.getName())
                    .isEqualTo("John Doe");
            assertThat(actual.getEmail())
                    .isEqualTo("john.doe@garage.com");

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