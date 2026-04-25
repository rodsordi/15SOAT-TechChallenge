package br.com.fiap.garage.application.v1.mapper;

import br.com.fiap.garage.application.v1.dto.ServiceDto;
import br.com.fiap.garage.domain.entity.Material;
import br.com.fiap.garage.domain.entity.Service;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.UUID;

import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

@Mapper(nullValuePropertyMappingStrategy = IGNORE)
public interface ServiceDtoMapper {

    @Mapping(target = "materials", source = "materialsIds")
    Service convert(ServiceDto.Request source);

    ServiceDto.Response convert(Service source);

    ServiceDto.Representation convertToRepresentation(Service source);

    default Material mapMaterial(UUID materialId) {
        if (materialId == null)
            return null;
        return Material.builder()
                .id(materialId)
                .build();
    }

}
