package br.com.fiap.garage.infra.repository;

import br.com.fiap.garage.domain.repository.OwnerRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

@Primary
@Repository
public interface OwnerRepositoryExt extends OwnerRepository {

}
