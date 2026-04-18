package br.com.fiap.garage.domain.use_case;

import br.com.fiap.garage.domain.entity.Owner;
import br.com.fiap.commons.domain.exception.NotFoundException;
import br.com.fiap.garage.domain.repository.OwnerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OwnerUpdate {

    private final OwnerRepository repository;

    public Owner update(Owner owner) {
        var fetchedOwner = repository.findById(owner.getId())
                .orElseThrow(() -> new NotFoundException(Owner.class, "id", owner.getId()));
        return repository.save(fetchedOwner);
    }
}
