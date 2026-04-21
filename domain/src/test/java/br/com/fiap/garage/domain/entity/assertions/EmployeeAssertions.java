package br.com.fiap.garage.domain.entity.assertions;

import br.com.fiap.garage.domain.entity.Employee;
import lombok.RequiredArgsConstructor;

import static br.com.fiap.commons.util.DateUtil.newDateTime;
import static br.com.fiap.commons.util.ReflectionUtil.assertThatObject;
import static lombok.AccessLevel.PRIVATE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.spy;

@RequiredArgsConstructor(access = PRIVATE)
public final class EmployeeAssertions {

    private final Employee actual;

    public static EmployeeAssertions assertThat_Employee(Employee actual) {
        assertThat(actual).isNotNull();
        return new EmployeeAssertions(spy(actual));
    }

    /**
     * @see EmployeeFactory
     * .withAllFields()
     */
    public void wasConvertedFrom_EmployeeDto_Request() {
        // Self
        assertThat(actual.getId())
                .isNull();
        assertThat(actual.getName())
                .isEqualTo("John");

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