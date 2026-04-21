package br.com.fiap.garage.domain.entity;

import br.com.fiap.commons.entity.AuditableEntity;
import br.com.fiap.garage.domain.entity.enums.WorkOrderStatus;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.util.UUID;

import static br.com.fiap.garage.domain.entity.enums.WorkOrderStatus.RECEIVED;
import static jakarta.persistence.CascadeType.MERGE;
import static jakarta.persistence.CascadeType.PERSIST;
import static jakarta.persistence.EnumType.STRING;
import static lombok.AccessLevel.PROTECTED;

@Getter
@NoArgsConstructor(access = PROTECTED)
@SuperBuilder
@EqualsAndHashCode(callSuper = false, exclude = "id")
@Entity
@Table(schema = "garage")
public class WorkOrder extends AuditableEntity implements Serializable {

    @Id
    @GeneratedValue
    @Column(comment = "WorkOrder id. Owner: postgres")
    private UUID id;

    @Builder.Default
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
