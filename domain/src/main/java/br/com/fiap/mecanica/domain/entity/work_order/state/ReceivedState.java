package br.com.fiap.mecanica.domain.entity.work_order.state;

import br.com.fiap.mecanica.domain.entity.WorkOrder;
import br.com.fiap.mecanica.domain.entity.enums.WorkOrderStatus;
import br.com.fiap.mecanica.domain.entity.work_order.WorkOrderState;

import static br.com.fiap.mecanica.domain.entity.enums.WorkOrderStatus.DIAGNOSING;

public class ReceivedState extends WorkOrderState {

    public ReceivedState(WorkOrder workOrder) {
        super(workOrder);
    }

    @Override
    public WorkOrderStatus diagnose() {
        return DIAGNOSING;
    }
}
