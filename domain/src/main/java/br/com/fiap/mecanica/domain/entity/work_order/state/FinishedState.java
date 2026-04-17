package br.com.fiap.mecanica.domain.entity.work_order.state;

import br.com.fiap.mecanica.domain.entity.WorkOrder;
import br.com.fiap.mecanica.domain.entity.enums.WorkOrderStatus;
import br.com.fiap.mecanica.domain.entity.work_order.WorkOrderState;

import static br.com.fiap.mecanica.domain.entity.enums.WorkOrderStatus.RELEASED;

public class FinishedState extends WorkOrderState {

    public FinishedState(WorkOrder workOrder) {
        super(workOrder);
    }

    @Override
    public WorkOrderStatus release() {
        return RELEASED;
    }
}
