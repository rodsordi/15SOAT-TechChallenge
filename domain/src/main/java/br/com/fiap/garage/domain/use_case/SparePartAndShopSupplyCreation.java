package br.com.fiap.garage.domain.use_case;

import br.com.fiap.garage.domain.entity.SparePartAndShopSupply;
import br.com.fiap.garage.domain.repository.SparePartAndShopSupplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SparePartAndShopSupplyCreation {

    private final SparePartAndShopSupplyRepository repository;

    public SparePartAndShopSupply create(SparePartAndShopSupply sparePartAndShopSupply) {
        return repository.save(sparePartAndShopSupply);
    }
}
