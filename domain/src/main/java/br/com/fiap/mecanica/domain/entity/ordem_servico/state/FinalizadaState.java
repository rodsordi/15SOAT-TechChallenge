package br.com.fiap.mecanica.domain.entity.ordem_servico.state;

import br.com.fiap.mecanica.domain.entity.OrdemDeServico;
import br.com.fiap.mecanica.domain.entity.enums.OrdemDeServicoStatus;
import br.com.fiap.mecanica.domain.entity.ordem_servico.OrdemDeServicoState;

import static br.com.fiap.mecanica.domain.entity.enums.OrdemDeServicoStatus.ENTREGUE;

public class FinalizadaState extends OrdemDeServicoState {

    public FinalizadaState(OrdemDeServico ordemDeServico) {
        super(ordemDeServico);
    }

    @Override
    public OrdemDeServicoStatus entregar() {
        return ENTREGUE;
    }
}
