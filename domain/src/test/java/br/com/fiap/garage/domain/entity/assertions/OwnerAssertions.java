package br.com.fiap.garage.domain.entity.assertions;

import br.com.fiap.garage.domain.entity.Owner;
import lombok.RequiredArgsConstructor;

import static br.com.fiap.commons.util.ReflectionUtil.assertThatObject;
import static lombok.AccessLevel.PRIVATE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.spy;

@RequiredArgsConstructor(access = PRIVATE)
public final class OwnerAssertions {

    private final Owner actual;

    public static OwnerAssertions assertThat_Owner(Owner actual) {
        assertThat(actual).isNotNull();
        return new OwnerAssertions(spy(actual));
    }

    /**
     * @see OwnerFactory
     * .withAllFields()
     */
    public void wasConvertedFrom_OwnerDto_Request() {
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