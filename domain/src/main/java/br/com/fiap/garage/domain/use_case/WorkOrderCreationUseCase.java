package br.com.fiap.garage.domain.use_case;

import br.com.fiap.garage.domain.entity.Vehicle;
import br.com.fiap.garage.domain.entity.WorkOrder;
import br.com.fiap.garage.domain.repository.WorkOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WorkOrderCreationUseCase {

    private final WorkOrderRepository repository;

    public WorkOrder create(WorkOrder workOrder) {
        return repository.save(workOrder);
    }
}
