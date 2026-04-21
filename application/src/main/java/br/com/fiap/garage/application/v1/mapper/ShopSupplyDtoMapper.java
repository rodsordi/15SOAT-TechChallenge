package br.com.fiap.garage.application.v1.mapper;

import br.com.fiap.garage.application.v1.dto.ShopSupplyDto;
import br.com.fiap.garage.domain.entity.ShopSupply;
import org.mapstruct.Mapper;

import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

@Mapper(nullValuePropertyMappingStrategy = IGNORE)
public interface ShopSupplyDtoMapper {

    ShopSupply convert(ShopSupplyDto.Request source);
    ShopSupplyDto.Response convert(ShopSupply source);
}
