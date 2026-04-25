package br.com.fiap.garage.domain.use_case;

import br.com.fiap.garage.domain.entity.Service;
import br.com.fiap.garage.domain.repository.ServiceRepository;
import lombok.RequiredArgsConstructor;

@org.springframework.stereotype.Service
@RequiredArgsConstructor
public class ServiceCreationUseCase {

    private final ServiceRepository repository;

    public Service create(Service service) {
        return repository.save(service);
    }
}
