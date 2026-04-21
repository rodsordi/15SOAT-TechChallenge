package br.com.fiap.garage.application.v1.mapper;

import br.com.fiap.garage.application.v1.dto.InventoryDto;
import br.com.fiap.garage.domain.entity.Inventory;
import org.mapstruct.Mapper;

import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

@Mapper(nullValuePropertyMappingStrategy = IGNORE)
public interface InventoryDtoMapper {

    InventoryDto.Representation convertToRepresentation(Inventory source);
}
