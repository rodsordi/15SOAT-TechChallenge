package br.com.fiap.garage.domain.use_case;

import br.com.fiap.commons.exception.ResourceNotFoundException;
import br.com.fiap.garage.domain.entity.WorkOrder;
import br.com.fiap.garage.domain.repository.WorkOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class WorkOrderUpdateUseCase {

    private final WorkOrderRepository repository;

    public WorkOrder update(UUID id) {
        var foundWorkOrder = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(WorkOrder.class, "id", id));
        return repository.save(foundWorkOrder);
    }
}
