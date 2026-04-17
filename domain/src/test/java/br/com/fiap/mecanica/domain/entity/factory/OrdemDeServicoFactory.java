package br.com.fiap.mecanica.domain.entity.factory;

import br.com.fiap.mecanica.domain.entity.OrdemDeServico;
import lombok.RequiredArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@RequiredArgsConstructor(access = PRIVATE)
public final class OrdemDeServicoFactory {

    private final OrdemDeServico.OrdemDeServicoBuilder<?, ?> builder;

    public static OrdemDeServicoFactory criarOrdemDeServicoDirector() {
        return new OrdemDeServicoFactory(OrdemDeServico.builder());
    }

    public OrdemDeServico.OrdemDeServicoBuilder<?, ?> comTodosOsCampos() {
        return builder;
    }
}