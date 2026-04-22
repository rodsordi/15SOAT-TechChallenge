package br.com.fiap.garage.domain.repository;

import br.com.fiap.garage.domain.entity.Inventory;
import org.springframework.core.annotation.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

@Order(3)
public interface InventoryRepository extends CrudRepository<Inventory, UUID> {

    Page<Inventory> findAll(Specification<Inventory> filter, Pageable pageable);
}
