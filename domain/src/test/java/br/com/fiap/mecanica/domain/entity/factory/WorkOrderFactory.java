package br.com.fiap.mecanica.domain.entity.factory;

import br.com.fiap.mecanica.domain.entity.WorkOrder;
import lombok.RequiredArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@RequiredArgsConstructor(access = PRIVATE)
public final class WorkOrderFactory {

    private final WorkOrder.WorkOrderBuilder<?, ?> builder;

    public static WorkOrderFactory createWorkOrder() {
        return new WorkOrderFactory(WorkOrder.builder());
    }

    public WorkOrder.WorkOrderBuilder<?, ?> withAllFields() {
        return builder;
    }
}