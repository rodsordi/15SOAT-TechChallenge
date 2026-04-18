package br.com.fiap.garage.domain.repository;

import br.com.fiap.garage.domain.entity.SparePartAndShopSupply;
import org.springframework.core.annotation.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

@Order(3)
public interface SparePartAndShopSupplyRepository extends CrudRepository<SparePartAndShopSupply, UUID> {

    Page<SparePartAndShopSupply> findAll(Specification<SparePartAndShopSupply> filter, Pageable pageable);
}
