package br.com.fiap.mecanica.domain.entity.factory;

import br.com.fiap.mecanica.domain.entity.Orcamento;
import lombok.RequiredArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@RequiredArgsConstructor(access = PRIVATE)
public final class OrcamentoFactory {

    private final Orcamento.OrcamentoBuilder<?, ?> builder;

    public static OrcamentoFactory criarOrcamentoDirector() {
        return new OrcamentoFactory(Orcamento.builder());
    }

    public Orcamento.OrcamentoBuilder<?, ?> comTodosOsCampos() {
        return builder;
    }
}