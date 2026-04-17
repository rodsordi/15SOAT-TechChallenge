package br.com.fiap.mecanica.domain.entity.ordem_servico.state;

import br.com.fiap.mecanica.domain.entity.OrdemDeServico;
import br.com.fiap.mecanica.domain.entity.enums.OrdemDeServicoStatus;
import br.com.fiap.mecanica.domain.entity.ordem_servico.OrdemDeServicoState;

import static br.com.fiap.mecanica.domain.entity.enums.OrdemDeServicoStatus.EM_EXECUCAO;

public class AguardandoAprovacaoState extends OrdemDeServicoState {

    public AguardandoAprovacaoState(OrdemDeServico ordemDeServico) {
        super(ordemDeServico);
    }

    @Override
    public OrdemDeServicoStatus executar() {
        return EM_EXECUCAO;
    }
}
