package br.com.fiap.garage.application.v1.mapper;

import br.com.fiap.garage.application.v1.dto.InventoryDto;
import br.com.fiap.garage.application.v1.dto.ShopSupplyDto;
import br.com.fiap.garage.application.v1.dto.SparePartDto;
import br.com.fiap.garage.domain.entity.Inventory;
import br.com.fiap.garage.domain.entity.ShopSupply;
import br.com.fiap.garage.domain.entity.SparePart;
import org.mapstruct.Mapper;

import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

@Mapper(nullValuePropertyMappingStrategy = IGNORE)
public interface InventoryDtoMapper {

    SparePart convert(SparePartDto.Request source);

    ShopSupply convert(ShopSupplyDto.Request source);

    InventoryDto.Response convert(Inventory source);

    InventoryDto.Representation convertToRepresentation(Inventory source);
}
