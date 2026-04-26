package br.com.fiap.garage.domain.mapper;

import br.com.fiap.garage.domain.entity.EstimatedService;
import br.com.fiap.garage.domain.entity.Service;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

@Mapper(nullValuePropertyMappingStrategy = IGNORE)
public interface ServiceMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    EstimatedService convert(Service source);
}
