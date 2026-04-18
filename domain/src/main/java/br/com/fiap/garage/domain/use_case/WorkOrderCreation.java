package br.com.fiap.garage.domain.use_case;

import br.com.fiap.garage.domain.repository.WorkOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WorkOrderCreation {

    private final WorkOrderRepository repository;
}
