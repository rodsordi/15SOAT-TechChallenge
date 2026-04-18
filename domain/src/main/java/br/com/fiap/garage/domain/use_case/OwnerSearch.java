package br.com.fiap.garage.domain.use_case;

import br.com.fiap.garage.domain.entity.Owner;
import br.com.fiap.garage.domain.filter.OwnerFilter;
import br.com.fiap.garage.domain.repository.OwnerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OwnerSearch {

    private final OwnerRepository repository;

    public Owner findById(UUID id) {
        return repository.findById(id)
                .orElse(null);
    }

    public Page<Owner> findAll(OwnerFilter filter) {
        return repository.findAll(filter, filter.buildPageRequest());
    }
}
