package br.com.fiap.garage.domain.use_case;

import br.com.fiap.garage.domain.entity.Owner;
import br.com.fiap.garage.domain.repository.OwnerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OwnerCreationUseCase {

    private final OwnerRepository repository;

    private final PasswordEncoder passwordEncoder;

    public Owner create(Owner owner) {
        owner.encodePassword(passwordEncoder);
        return repository.save(owner);
    }
}
