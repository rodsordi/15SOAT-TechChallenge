package br.com.fiap.garage.application.v1.mapper;

import br.com.fiap.garage.application.v1.dto.OwnerDto;
import br.com.fiap.garage.domain.entity.Owner;
import org.mapstruct.Mapper;

import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

@Mapper(nullValuePropertyMappingStrategy = IGNORE)
public interface OwnerDtoMapper {

    Owner convert(OwnerDto.Request source);

    OwnerDto.Response convert(Owner source);

    OwnerDto.Representation convertToRepresentation(Owner source);
}
