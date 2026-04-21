package br.com.fiap.garage.domain.use_case;

import br.com.fiap.garage.domain.entity.ShopSupply;
import br.com.fiap.garage.domain.repository.ShopSupplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ShopSupplyCreationUseCase {

    private final ShopSupplyRepository repository;

    public ShopSupply create(ShopSupply shopSupply) {
        return repository.save(shopSupply);
    }
}
