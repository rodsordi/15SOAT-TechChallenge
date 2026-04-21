package br.com.fiap.garage.domain.entity.factory;

import br.com.fiap.garage.domain.entity.Owner;
import lombok.RequiredArgsConstructor;

import static br.com.fiap.commons.util.DateUtil.newDateTime;
import static br.com.fiap.commons.util.ReflectionUtil.assertThatObject;
import static java.util.UUID.fromString;
import static lombok.AccessLevel.PRIVATE;

@RequiredArgsConstructor(access = PRIVATE)
public final class OwnerFactory {

    private final Owner.OwnerBuilder<?, ?> builder;

    public static OwnerFactory create_Owner() {
        return new OwnerFactory(Owner.builder());
    }

    public Owner withAllFields() {
        var result = builder
                // Self
                .id(fromString("f47ac10b-58cc-4372-a567-0e02b2c3d479"))
                .name("John Doe")
                .email("john.doe@fiap.com.br")
                // Inheritance (AuditableEntity)
                .createdAt(newDateTime("21/04/2026 10:00:00"))
                .updatedAt(newDateTime("21/04/2026 15:30:00"))
                .build();
        // And
        assertThatObject(result)
                .hasNoEmptyFields();
        return result;
    }

    public Owner withAllFieldsExceptDB() {
        withAllFields();
        return builder
                .id(null)
                .createdAt(null)
                .updatedAt(null)
                .build();
    }

    public Owner valid() {
        return builder
                .name("Jane Smith")
                .email("jane.smith@fiap.com.br")
                .build();
    }

    public Owner initiatedEmpty() {
        return builder.build();
    }
}