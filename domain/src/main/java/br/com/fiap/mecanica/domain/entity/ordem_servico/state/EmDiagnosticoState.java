package br.com.fiap.mecanica.domain.entity.ordem_servico.state;

import br.com.fiap.mecanica.domain.entity.OrdemDeServico;
import br.com.fiap.mecanica.domain.entity.enums.OrdemDeServicoStatus;
import br.com.fiap.mecanica.domain.entity.ordem_servico.OrdemDeServicoState;

import static br.com.fiap.mecanica.domain.entity.enums.OrdemDeServicoStatus.AGUARDANDO_APROVACAO;

public class EmDiagnosticoState extends OrdemDeServicoState {

    public EmDiagnosticoState(OrdemDeServico ordemDeServico) {
        super(ordemDeServico);
    }

    @Override
    public OrdemDeServicoStatus aguardarAprovacao() {
        return AGUARDANDO_APROVACAO;
    }
}
