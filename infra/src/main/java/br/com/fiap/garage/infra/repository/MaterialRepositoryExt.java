package br.com.fiap.garage.infra.repository;

import br.com.fiap.garage.domain.repository.MaterialRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Repository;

@Repository
@Primary
@Order(4)
public interface MaterialRepositoryExt extends MaterialRepository {
    
}
