package br.com.fiap.garage.domain.entity.factory;

import br.com.fiap.garage.domain.entity.WorkOrder;
import lombok.RequiredArgsConstructor;

import static br.com.fiap.garage.domain.entity.enums.WorkOrderStatus.RECEIVED;
import static br.com.fiap.garage.domain.entity.factory.EstimateFactory.createEstimate;
import static br.com.fiap.commons.util.DateUtil.newDateTime;
import static br.com.fiap.commons.util.ReflectionUtil.assertThatObject;
import static java.util.UUID.fromString;
import static lombok.AccessLevel.PRIVATE;

@RequiredArgsConstructor(access = PRIVATE)
public final class WorkOrderFactory {

    private final WorkOrder.WorkOrderBuilder<?, ?> builder;

    public static WorkOrderFactory createWorkOrder() {
        return new WorkOrderFactory(WorkOrder.builder());
    }

    public WorkOrder withAllFields() {
        var result = builder
                // Self
                .id(fromString("e48ad20c-69dd-4382-b567-0e02b2c3d480"))
                .status(RECEIVED)
                // Composition
                .estimate(createEstimate().withAllFields())
                // Inheritance (AuditableEntity)
                .createdAt(newDateTime("21/04/2026 10:00:00"))
                .updatedAt(newDateTime("21/04/2026 15:30:00"))
                .build();

        // And
        assertThatObject(result)
                .hasNoEmptyFields();
        return result;
    }

    public WorkOrder withAllFieldsExceptDB() {
        withAllFields();
        return builder
                .id(null)
                .estimate(createEstimate().withAllFieldsExceptDB())
                .createdAt(null)
                .updatedAt(null)
                .build();
    }

    public WorkOrder valid() {
        return builder
                .status(RECEIVED)
                .estimate(createEstimate().valid())
                .build();
    }

    public WorkOrder initiatedEmpty() {
        return builder
                .estimate(createEstimate().initiatedEmpty())
                .build();
    }
}