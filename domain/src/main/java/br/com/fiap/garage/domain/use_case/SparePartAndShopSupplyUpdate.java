package br.com.fiap.garage.domain.use_case;

import br.com.fiap.commons.domain.exception.NotFoundException;
import br.com.fiap.garage.domain.entity.SparePartAndShopSupply;
import br.com.fiap.garage.domain.repository.SparePartAndShopSupplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SparePartAndShopSupplyUpdate {

    private final SparePartAndShopSupplyRepository repository;

    public SparePartAndShopSupply update(SparePartAndShopSupply sparePartAndShopSupply) {
        var fetchedSparePartAndShopSupply = repository.findById(sparePartAndShopSupply.getId())
                .orElseThrow(() -> new NotFoundException(SparePartAndShopSupply.class, "id", sparePartAndShopSupply.getId()));
        return repository.save(fetchedSparePartAndShopSupply);
    }
}
