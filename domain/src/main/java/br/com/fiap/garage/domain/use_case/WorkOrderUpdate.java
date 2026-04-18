package br.com.fiap.garage.domain.use_case;

import br.com.fiap.garage.domain.entity.WorkOrder;
import br.com.fiap.commons.domain.exception.NotFoundException;
import br.com.fiap.garage.domain.repository.WorkOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WorkOrderUpdate {

    private final WorkOrderRepository repository;

    public WorkOrder update(WorkOrder workOrder) {
        var fetchedWorkOrder = repository.findById(workOrder.getId())
                .orElseThrow(() -> new NotFoundException(WorkOrder.class, "id", workOrder.getId()));
        return repository.save(fetchedWorkOrder);
    }
}
