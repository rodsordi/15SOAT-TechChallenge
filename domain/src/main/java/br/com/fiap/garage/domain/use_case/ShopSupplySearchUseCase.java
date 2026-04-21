package br.com.fiap.garage.domain.use_case;

import br.com.fiap.commons.exception.NotFoundException;
import br.com.fiap.garage.domain.entity.ShopSupply;
import br.com.fiap.garage.domain.repository.ShopSupplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ShopSupplySearchUseCase {

    private final ShopSupplyRepository sparePartRepository;

    public ShopSupply findById(UUID id) {
        return sparePartRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(ShopSupply.class, "id", id));
    }
}
