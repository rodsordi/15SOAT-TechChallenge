package br.com.fiap.garage.domain.use_case;

import br.com.fiap.garage.domain.entity.Owner;
import br.com.fiap.garage.domain.repository.OwnerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OwnerCreation {

    private final OwnerRepository repository;

    public Owner create(Owner owner) {
        return repository.save(owner);
    }
}
