package br.com.fiap.mecanica.domain.entity;

import br.com.fiap.mecanica.domain.entity.enums.OrdemDeServicoStatus;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.util.UUID;

import static br.com.fiap.mecanica.domain.entity.enums.OrdemDeServicoStatus.RECEBIDA;
import static jakarta.persistence.CascadeType.MERGE;
import static jakarta.persistence.CascadeType.PERSIST;
import static jakarta.persistence.EnumType.STRING;
import static lombok.AccessLevel.PROTECTED;

@Getter
@NoArgsConstructor(access = PROTECTED)
@SuperBuilder
@EqualsAndHashCode(callSuper = false, exclude = "id")
@MappedSuperclass
public class OrdemDeServico extends AuditableEntity implements Serializable {

    @Id
    @GeneratedValue
    @Column(comment = "Id da ordem de serviço. Dono: postgres")
    private UUID id;

    @Enumerated(STRING)
    @Column(comment = "Situação da ordem de serviço. Dono: eu-mesmo")
    private OrdemDeServicoStatus status = RECEBIDA;

    @ManyToOne(cascade = {MERGE, PERSIST}) //VO
    @JoinColumn(updatable = false, comment = "Id do orçamento. Dono: postgres")
    @Valid
    private Orcamento orcamento;

    public void diagnosticar() {
        status = status.getState()
                .apply(this)
                .diagnosticar();
    }

    public void aguardarAprovacao() {
        status = status.getState()
                .apply(this)
                .aguardarAprovacao();
    }

    public void executar() {
        status = status.getState()
                .apply(this)
                .executar();
    }

    public void finalizar() {
        status = status.getState()
                .apply(this)
                .finalizar();
    }

    public void entregar() {
        status = status.getState()
                .apply(this)
                .entregar();
    }
}
