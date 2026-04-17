package br.com.fiap.garage.domain.entity.factory;

import br.com.fiap.garage.domain.entity.Owner;
import lombok.RequiredArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@RequiredArgsConstructor(access = PRIVATE)
public final class OwnerFactory {

    private final Owner.OwnerBuilder<?, ?> builder;

    public static OwnerFactory createOwner() {
        return new OwnerFactory(Owner.builder());
    }

    public Owner.OwnerBuilder<?, ?> withAllFields() {
        return builder;
    }
}