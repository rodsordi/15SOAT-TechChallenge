package br.com.fiap.mecanica.domain.entity.work_order.state;

import br.com.fiap.mecanica.domain.entity.WorkOrder;
import br.com.fiap.mecanica.domain.entity.enums.WorkOrderStatus;
import br.com.fiap.mecanica.domain.entity.work_order.WorkOrderState;

import static br.com.fiap.mecanica.domain.entity.enums.WorkOrderStatus.WAITING_FOR_APPROVAL;

public class DiagnosingState extends WorkOrderState {

    public DiagnosingState(WorkOrder workOrder) {
        super(workOrder);
    }

    @Override
    public WorkOrderStatus waitForApproval() {
        return WAITING_FOR_APPROVAL;
    }
}
