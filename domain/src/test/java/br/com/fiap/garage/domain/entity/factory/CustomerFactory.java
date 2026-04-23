package br.com.fiap.garage.domain.entity.factory;

import br.com.fiap.garage.domain.entity.Customer;
import lombok.RequiredArgsConstructor;

import static br.com.fiap.commons.util.DateUtil.newDateTime;
import static br.com.fiap.commons.util.ReflectionUtil.assertThatObject;
import static br.com.fiap.garage.domain.entity.factory.AuthorityFactory.create_Authority;
import static java.util.UUID.fromString;
import static lombok.AccessLevel.PRIVATE;

@RequiredArgsConstructor(access = PRIVATE)
public final class CustomerFactory {

    private final Customer.CustomerBuilder<?, ?> builder;

    public static CustomerFactory create_Customer() {
        return new CustomerFactory(Customer.builder());
    }

    public Customer withAllFields() {
        var result = builder
                // Self
                .id(fromString("f47ac10b-58cc-4372-a567-0e02b2c3d479"))
                .username("jack.doe@company.com")
                .password("4321abcd")
                .name("John Doe")
                .email("john.doe@fiap.com.br")
                // Composition
                .authority(create_Authority().withAllFields())
                // Inheritance (AuditableEntity)
                .createdAt(newDateTime("21/04/2026 10:00:00"))
                .updatedAt(newDateTime("21/04/2026 15:30:00"))
                .build();
        // And
        assertThatObject(result)
                .hasNoEmptyFields();
        return result;
    }

    public Customer withAllFieldsExceptDB() {
        withAllFields();
        return builder
                .id(null)
                .clearAuthorities()
                .authority(create_Authority().withAllFieldsExceptDB())
                .createdAt(null)
                .updatedAt(null)
                .build();
    }

    public Customer valid() {
        return builder
                .name("Jane Smith")
                .email("jane.smith@fiap.com.br")
                .build();
    }

    public Customer initiatedEmpty() {
        return builder.build();
    }
}