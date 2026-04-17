package br.com.fiap.mecanica.domain.entity.ordem_servico;

import br.com.fiap.mecanica.domain.entity.OrdemDeServico;
import br.com.fiap.mecanica.domain.entity.enums.OrdemDeServicoStatus;
import br.com.fiap.mecanica.domain.exception.BusinessException;
import lombok.RequiredArgsConstructor;

import static br.com.fiap.mecanica.domain.entity.enums.OrdemDeServicoStatus.*;
import static java.lang.String.format;
import static lombok.AccessLevel.PROTECTED;

@RequiredArgsConstructor(access = PROTECTED)
public abstract class OrdemDeServicoState {

    private static final String MSG = "Ordem de Serviço com situação %s, não pode ser atualizada para situação %s";

    protected final OrdemDeServico ordemDeServico;

    public OrdemDeServicoStatus diagnosticar() {
        throw new BusinessException(format(MSG, ordemDeServico.getStatus(), EM_DIAGNOSTICO));
    }

    public OrdemDeServicoStatus aguardarAprovacao() {
        throw new BusinessException(format(MSG, ordemDeServico.getStatus(), AGUARDANDO_APROVACAO));
    }

    public OrdemDeServicoStatus executar() {
        throw new BusinessException(format(MSG, ordemDeServico.getStatus(), EM_EXECUCAO));
    }

    public OrdemDeServicoStatus finalizar() {
        throw new BusinessException(format(MSG, ordemDeServico.getStatus(), FINALIZADA));
    }

    public OrdemDeServicoStatus entregar() {
        throw new BusinessException(format(MSG, ordemDeServico.getStatus(), ENTREGUE));
    }
}
