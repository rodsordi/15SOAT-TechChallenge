package br.com.fiap.garage.domain.use_case;

import br.com.fiap.commons.exception.BusinessException;
import br.com.fiap.garage.domain.entity.Inventory;
import br.com.fiap.garage.domain.entity.ShopSupply;
import br.com.fiap.garage.domain.entity.SparePart;
import br.com.fiap.garage.domain.repository.ShopSupplyRepository;
import br.com.fiap.garage.domain.repository.SparePartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InventoryCreationUseCase {

    private final SparePartRepository sparePartRepository;

    private final ShopSupplyRepository shopSupplyRepository;

    public Inventory create(Inventory inventory) {
        if (inventory instanceof SparePart sparePart)
            return create(sparePart);

        if (inventory instanceof ShopSupply shopSupply)
            return create(shopSupply);

        throw new BusinessException("Inventory type not found");
    }

    private SparePart create(SparePart sparePart) {
        return sparePartRepository.save(sparePart);
    }

    private ShopSupply create(ShopSupply shopSupply) {
        return shopSupplyRepository.save(shopSupply);
    }
}
