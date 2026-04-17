package br.com.fiap.mecanica.domain.entity;

import br.com.fiap.mecanica.domain.entity.enums.WorkOrderStatus;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.util.UUID;

import static br.com.fiap.mecanica.domain.entity.enums.WorkOrderStatus.RECEIVED;
import static jakarta.persistence.CascadeType.MERGE;
import static jakarta.persistence.CascadeType.PERSIST;
import static jakarta.persistence.EnumType.STRING;
import static lombok.AccessLevel.PROTECTED;

@Getter
@NoArgsConstructor(access = PROTECTED)
@SuperBuilder
@EqualsAndHashCode(callSuper = false, exclude = "id")
@MappedSuperclass
public class WorkOrder extends AuditableEntity implements Serializable {

    @Id
    @GeneratedValue
    @Column(comment = "WorkOrder id. Owner: postgres")
    private UUID id;

    @Enumerated(STRING)
    @Column(comment = "WorkOrder status. Owner: self")
    private WorkOrderStatus status = RECEIVED;

    @ManyToOne(cascade = {MERGE, PERSIST}) //VO
    @JoinColumn(updatable = false, comment = "Estimate id. Owner: postgres")
    @Valid
    private Estimate estimate;

    public void diagnose() {
        status = status.getState()
                .apply(this)
                .diagnose();
    }

    public void waitForApproval() {
        status = status.getState()
                .apply(this)
                .waitForApproval();
    }

    public void execute() {
        status = status.getState()
                .apply(this)
                .execute();
    }

    public void finish() {
        status = status.getState()
                .apply(this)
                .finish();
    }

    public void release() {
        status = status.getState()
                .apply(this)
                .release();
    }
}
