package br.com.fiap.garage.domain.repository;

import br.com.fiap.garage.domain.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends CrudRepository<User, UUID> {

    Optional<User> findByEmail(String username);
}
