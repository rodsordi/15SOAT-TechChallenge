package br.com.fiap.garage.domain.entity.factory;

import br.com.fiap.garage.domain.entity.Authority;
import lombok.RequiredArgsConstructor;

import static br.com.fiap.commons.util.DateUtil.newDateTime;
import static br.com.fiap.commons.util.ReflectionUtil.assertThatObject;
import static java.util.UUID.fromString;
import static lombok.AccessLevel.PRIVATE;

@RequiredArgsConstructor(access = PRIVATE)
public final class AuthorityFactory {

    private final Authority.AuthorityBuilder<?, ?> builder;

    public static AuthorityFactory create_Authority() {
        return new AuthorityFactory(Authority.builder());
    }

    public Authority withAllFields() {
        var result = builder
                // Self
                .id(fromString("9f2c6680-d47f-4a91-9680-8d64b514e5f8"))
                .authority("ROLE_ADMIN")
                // Inheritance (AuditableEntity)
                .createdAt(newDateTime("21/04/2026 10:00:00"))
                .updatedAt(newDateTime("21/04/2026 15:30:00"))
                .build();

        // Validation check
        assertThatObject(result)
                .hasNoEmptyFields();
        return result;
    }

    public Authority withAllFieldsExceptDB() {
        withAllFields();
        return builder
                .id(null)
                .createdAt(null)
                .updatedAt(null)
                .build();
    }

    public Authority valid() {
        return builder
                .authority("ROLE_USER")
                .build();
    }

    public Authority initiatedEmpty() {
        return builder.build();
    }
}