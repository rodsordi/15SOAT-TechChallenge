package br.com.fiap.mecanica.domain.entity.enums;

import br.com.fiap.mecanica.domain.entity.OrdemDeServico;
import br.com.fiap.mecanica.domain.entity.ordem_servico.OrdemDeServicoState;
import br.com.fiap.mecanica.domain.entity.ordem_servico.state.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.function.Function;

@Getter
@RequiredArgsConstructor
public enum OrdemDeServicoStatus {

    RECEBIDA(RecebidaState::new),
    EM_DIAGNOSTICO(EmDiagnosticoState::new),
    AGUARDANDO_APROVACAO(AguardandoAprovacaoState::new),
    EM_EXECUCAO(EmExecucaoState::new),
    FINALIZADA(FinalizadaState::new),
    ENTREGUE(EntregueState::new);

    private final Function<OrdemDeServico, OrdemDeServicoState> state;
}
