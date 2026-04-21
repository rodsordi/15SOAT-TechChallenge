package br.com.fiap.garage.domain.use_case;

import br.com.fiap.commons.exception.NotFoundException;
import br.com.fiap.garage.domain.entity.Inventory;
import br.com.fiap.garage.domain.filter.InventoryFilter;
import br.com.fiap.garage.domain.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InventorySearchUseCase {

    private final InventoryRepository inventoryRepository;

    public Page<Inventory> findAll(InventoryFilter filter) {
        var foundInventories = inventoryRepository.findAll(filter, filter.buildPageRequest());
        if (foundInventories.isEmpty())
            throw new NotFoundException(Inventory.class);
        return foundInventories;
    }
}
