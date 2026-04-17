package br.com.fiap.mecanica.domain.entity.factory;

import br.com.fiap.mecanica.domain.entity.Funcionario;
import lombok.RequiredArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@RequiredArgsConstructor(access = PRIVATE)
public final class FuncionarioFactory {

    private final Funcionario.FuncionarioBuilder<?, ?> builder;

    public static FuncionarioFactory criarFuncionarioDirector() {
        return new FuncionarioFactory(Funcionario.builder());
    }

    public Funcionario.FuncionarioBuilder<?, ?> comTodosOsCampos() {
        return builder;
    }
}