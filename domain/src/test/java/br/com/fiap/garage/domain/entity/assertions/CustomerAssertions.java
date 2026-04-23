package br.com.fiap.garage.domain.entity.assertions;

import br.com.fiap.garage.domain.entity.Customer;
import lombok.RequiredArgsConstructor;

import static br.com.fiap.commons.util.ReflectionUtil.assertThatObject;
import static lombok.AccessLevel.PRIVATE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.spy;

@RequiredArgsConstructor(access = PRIVATE)
public final class CustomerAssertions {

    private final Customer actual;

    public static CustomerAssertions assertThat_Customer(Customer actual) {
        assertThat(actual).isNotNull();
        return new CustomerAssertions(spy(actual));
    }

    /**
     * @see br.com.fiap.garage.application.v1.dto.factory.CustomerDtoFactory
     * .withAllFields()
     */
    public void wasConvertedFrom_CustomerDto_Request() {
        // Self
        assertThat(actual.getId())
                .isNull();
        assertThat(actual.getUsername())
                .isEqualTo("john.doe@example.com");
        assertThat(actual.getPassword())
                .isEqualTo("1234asdl");
        assertThat(actual.getName())
                .isEqualTo("John Doe");
        assertThat(actual.getEmail())
                .isEqualTo("john.doe@example.com");
        assertThat(actual.getDocument())
                .isEqualTo("00123456000190");
        assertThat(actual.getAuthorities())
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