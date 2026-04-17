package br.com.fiap.mecanica.domain.entity.factory;

import br.com.fiap.mecanica.domain.entity.Proprietario;
import lombok.RequiredArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@RequiredArgsConstructor(access = PRIVATE)
public final class ProprietarioFactory {

    private final Proprietario.ProprietarioBuilder<?, ?> builder;

    public static ProprietarioFactory criarProprietarioDirector() {
        return new ProprietarioFactory(Proprietario.builder());
    }

    public Proprietario.ProprietarioBuilder<?, ?> comTodosOsCampos() {
        return builder;
    }
}