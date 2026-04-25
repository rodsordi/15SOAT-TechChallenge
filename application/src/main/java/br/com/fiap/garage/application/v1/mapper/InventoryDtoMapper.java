package br.com.fiap.garage.application.v1.mapper;

import br.com.fiap.garage.application.v1.dto.InventoryMaterialDto;
import br.com.fiap.garage.domain.entity.InventoryMaterial;
import org.mapstruct.Mapper;

import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

@Mapper(nullValuePropertyMappingStrategy = IGNORE)
public interface InventoryDtoMapper {

    InventoryMaterialDto.Representation convertToRepresentation(InventoryMaterial source);
}
