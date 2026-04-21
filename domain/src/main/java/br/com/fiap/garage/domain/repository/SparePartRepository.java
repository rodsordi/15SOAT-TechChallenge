package br.com.fiap.garage.domain.repository;

import br.com.fiap.garage.domain.entity.SparePart;
import org.springframework.core.annotation.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

@Order(3)
public interface SparePartRepository extends CrudRepository<SparePart, UUID> {

    Page<SparePart> findAll(Specification<SparePart> filter, Pageable pageable);
}
