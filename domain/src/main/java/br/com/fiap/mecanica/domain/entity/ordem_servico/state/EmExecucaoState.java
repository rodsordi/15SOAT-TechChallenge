package br.com.fiap.mecanica.domain.entity.ordem_servico.state;

import br.com.fiap.mecanica.domain.entity.OrdemDeServico;
import br.com.fiap.mecanica.domain.entity.enums.OrdemDeServicoStatus;
import br.com.fiap.mecanica.domain.entity.ordem_servico.OrdemDeServicoState;

import static br.com.fiap.mecanica.domain.entity.enums.OrdemDeServicoStatus.FINALIZADA;

public class EmExecucaoState extends OrdemDeServicoState {

    public EmExecucaoState(OrdemDeServico ordemDeServico) {
        super(ordemDeServico);
    }

    @Override
    public OrdemDeServicoStatus finalizar() {
        return FINALIZADA;
    }
}
