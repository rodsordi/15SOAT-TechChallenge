package br.com.fiap.garage.infra.repository;

import br.com.fiap.garage.domain.repository.InventoryMaterialRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Repository;

@Repository
@Primary
@Order(3)
public interface InventoryMaterialRepositoryExt extends InventoryMaterialRepository {
    
}
