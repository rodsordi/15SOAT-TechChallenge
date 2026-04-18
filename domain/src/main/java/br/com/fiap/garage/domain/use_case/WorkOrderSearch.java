package br.com.fiap.garage.domain.use_case;

import br.com.fiap.garage.domain.entity.WorkOrder;
import br.com.fiap.garage.domain.filter.WorkOrderFilter;
import br.com.fiap.garage.domain.repository.WorkOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class WorkOrderSearch {

    private final WorkOrderRepository repository;

    public WorkOrder findById(UUID id) {
        return repository.findById(id)
                .orElse(null);
    }

    public Page<WorkOrder> findAll(WorkOrderFilter filter) {
        return repository.findAll(filter, filter.buildPageRequest());
    }
}
