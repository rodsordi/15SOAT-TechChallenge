package br.com.fiap.mecanica.domain.entity.enums;

import br.com.fiap.mecanica.domain.entity.WorkOrder;
import br.com.fiap.mecanica.domain.entity.work_order.WorkOrderState;
import br.com.fiap.mecanica.domain.entity.work_order.state.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.function.Function;

@Getter
@RequiredArgsConstructor
public enum WorkOrderStatus {

    RECEIVED(ReceivedState::new),
    DIAGNOSING(DiagnosingState::new),
    WAITING_FOR_APPROVAL(WaitingForApprovalState::new),
    EXECUTING(ExecutingState::new),
    FINISHED(FinishedState::new),
    RELEASED(ReleasedState::new);

    private final Function<WorkOrder, WorkOrderState> state;
}
