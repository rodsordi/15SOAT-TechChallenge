package br.com.fiap.mecanica.domain.entity.factory;

import br.com.fiap.mecanica.domain.entity.Veiculo;
import lombok.RequiredArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@RequiredArgsConstructor(access = PRIVATE)
public final class VeiculoFactory {

    private final Veiculo.VeiculoBuilder<?, ?> builder;

    public static VeiculoFactory criarVeiculoDirector() {
        return new VeiculoFactory(Veiculo.builder());
    }

    public Veiculo.VeiculoBuilder<?, ?> comTodosOsCampos() {
        return builder;
    }
}