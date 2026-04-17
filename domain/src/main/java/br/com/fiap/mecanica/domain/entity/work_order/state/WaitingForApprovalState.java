package br.com.fiap.mecanica.domain.entity.work_order.state;

import br.com.fiap.mecanica.domain.entity.WorkOrder;
import br.com.fiap.mecanica.domain.entity.enums.WorkOrderStatus;
import br.com.fiap.mecanica.domain.entity.work_order.WorkOrderState;

import static br.com.fiap.mecanica.domain.entity.enums.WorkOrderStatus.EXECUTING;

public class WaitingForApprovalState extends WorkOrderState {

    public WaitingForApprovalState(WorkOrder workOrder) {
        super(workOrder);
    }

    @Override
    public WorkOrderStatus execute() {
        return EXECUTING;
    }
}
