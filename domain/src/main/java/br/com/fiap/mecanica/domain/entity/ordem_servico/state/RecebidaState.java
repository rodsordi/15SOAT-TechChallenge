package br.com.fiap.mecanica.domain.entity.ordem_servico.state;

import br.com.fiap.mecanica.domain.entity.OrdemDeServico;
import br.com.fiap.mecanica.domain.entity.enums.OrdemDeServicoStatus;
import br.com.fiap.mecanica.domain.entity.ordem_servico.OrdemDeServicoState;

import static br.com.fiap.mecanica.domain.entity.enums.OrdemDeServicoStatus.EM_DIAGNOSTICO;

public class RecebidaState extends OrdemDeServicoState {

    public RecebidaState(OrdemDeServico ordemDeServico) {
        super(ordemDeServico);
    }

    @Override
    public OrdemDeServicoStatus diagnosticar() {
        return EM_DIAGNOSTICO;
    }
}
