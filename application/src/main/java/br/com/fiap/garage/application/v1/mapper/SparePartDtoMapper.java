package br.com.fiap.garage.application.v1.mapper;

import br.com.fiap.garage.application.v1.dto.SparePartDto;
import br.com.fiap.garage.domain.entity.SparePart;
import org.mapstruct.Mapper;

import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

@Mapper(nullValuePropertyMappingStrategy = IGNORE)
public interface SparePartDtoMapper {

    SparePart convert(SparePartDto.Request source);
    SparePartDto.Response convert(SparePart source);
}
