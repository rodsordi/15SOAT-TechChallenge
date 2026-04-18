package br.com.fiap.garage.domain.use_case;

import br.com.fiap.garage.domain.entity.SparePartAndShopSupply;
import br.com.fiap.garage.domain.filter.SparePartAndShopSupplyFilter;
import br.com.fiap.garage.domain.repository.SparePartAndShopSupplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SparePartAndShopSupplySearch {

    private final SparePartAndShopSupplyRepository repository;

    public SparePartAndShopSupply findById(UUID id) {
        return repository.findById(id)
                .orElse(null);
    }

    public Page<SparePartAndShopSupply> findAll(SparePartAndShopSupplyFilter filter) {
        return repository.findAll(filter, filter.buildPageRequest());
    }
}
