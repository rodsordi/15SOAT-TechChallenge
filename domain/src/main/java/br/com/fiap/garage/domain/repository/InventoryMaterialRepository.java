package br.com.fiap.garage.domain.repository;

import br.com.fiap.garage.domain.entity.InventoryMaterial;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface InventoryMaterialRepository extends CrudRepository<InventoryMaterial, UUID> {

    Page<InventoryMaterial> findAll(Specification<InventoryMaterial> filter, Pageable pageable);
}
