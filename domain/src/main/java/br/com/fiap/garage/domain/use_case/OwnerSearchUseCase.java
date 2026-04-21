package br.com.fiap.garage.domain.use_case;

import br.com.fiap.commons.exception.NotFoundException;
import br.com.fiap.garage.domain.entity.Owner;
import br.com.fiap.garage.domain.filter.OwnerFilter;
import br.com.fiap.garage.domain.repository.OwnerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OwnerSearchUseCase {

    private final OwnerRepository repository;

    public Owner findById(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException(Owner.class, "id", id));
    }

    public Page<Owner> findAll(OwnerFilter filter) {
        return repository.findAll(filter, filter.buildPageRequest());
    }
}
