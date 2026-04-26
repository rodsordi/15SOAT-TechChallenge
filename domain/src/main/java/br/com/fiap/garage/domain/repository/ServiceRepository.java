package br.com.fiap.garage.domain.repository;

import br.com.fiap.garage.domain.entity.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface ServiceRepository extends CrudRepository<Service, UUID> {

    Page<Service> findAll(Specification<Service> filter, Pageable pageable);
}
