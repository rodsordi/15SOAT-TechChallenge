package br.com.fiap.garage.domain.use_case;

import br.com.fiap.commons.exception.NotFoundException;
import br.com.fiap.garage.domain.entity.Inventory;
import br.com.fiap.garage.domain.filter.InventoryFilter;
import br.com.fiap.garage.domain.repository.InventoryRepository;
import br.com.fiap.garage.domain.repository.ShopSupplyRepository;
import br.com.fiap.garage.domain.repository.SparePartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class InventorySearchUseCase {

    private final InventoryRepository inventoryRepository;

    private final SparePartRepository sparePartRepository;

    private final ShopSupplyRepository shopSupplyRepository;

    public Inventory findById(UUID id) {
        var sparePartOpt = sparePartRepository.findById(id);
        if (sparePartOpt.isPresent())
            return sparePartOpt.get();

        var shopSupplyOpt = shopSupplyRepository.findById(id);
        if (shopSupplyOpt.isPresent())
            return shopSupplyOpt.get();

        throw new NotFoundException(Inventory.class, "id", id);
    }

    public Page<Inventory> findAll(InventoryFilter filter) {
        var foundInventories = inventoryRepository.findAll(filter, filter.buildPageRequest());
        if (foundInventories.isEmpty())
            throw new NotFoundException(Inventory.class);
        return foundInventories;
    }
}
