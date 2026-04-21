package br.com.fiap.garage.application.v1.mapper;

import br.com.fiap.garage.application.v1.dto.WorkOrderDto;
import br.com.fiap.garage.domain.entity.WorkOrder;
import org.mapstruct.Mapper;

import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

@Mapper(nullValuePropertyMappingStrategy = IGNORE)
public interface WorkOrderDtoMapper {

    WorkOrder convert(WorkOrderDto.Request source);

    WorkOrderDto.Response convert(WorkOrder source);

    WorkOrderDto.Representation convertToRepresentation(WorkOrder source);
}
