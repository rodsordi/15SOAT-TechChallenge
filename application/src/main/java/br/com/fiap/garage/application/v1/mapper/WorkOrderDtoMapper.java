package br.com.fiap.garage.application.v1.mapper;

import br.com.fiap.commons.map.CpfMap;
import br.com.fiap.commons.map.DocumentMap;
import br.com.fiap.garage.application.v1.dto.EstimatedMaterialDto;
import br.com.fiap.garage.application.v1.dto.EstimatedServiceDto;
import br.com.fiap.garage.application.v1.dto.WorkOrderDto;
import br.com.fiap.garage.domain.entity.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.UUID;

import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

@Mapper(nullValuePropertyMappingStrategy = IGNORE)
public interface WorkOrderDtoMapper extends CpfMap, DocumentMap {

    @Mapping(target = "vehicle", source = "vehicleId")
    @Mapping(target = "employee", source = "employeeId")
    WorkOrder convert(WorkOrderDto.Request source);

    @Mapping(target = "vehicle.customer.document", source = "vehicle.customer.document", qualifiedByName = "formattedDocument")
    @Mapping(target = "employee.cpf", source = "employee.cpf", qualifiedByName = "formattedCpf")
    WorkOrderDto.Response convert(WorkOrder source);

    @Mapping(target = "vehicle.customer.document", source = "vehicle.customer.document", qualifiedByName = "formattedDocument")
    @Mapping(target = "employee.cpf", source = "employee.cpf", qualifiedByName = "formattedCpf")
    WorkOrderDto.Representation convertToRepresentation(WorkOrder source);

    EstimatedServiceDto.Response convert(EstimatedService source);

    EstimatedMaterialDto.Response convert(EstimatedMaterial source);

    default Vehicle mapVehicle(UUID vehicleId) {
        if (vehicleId == null)
            return null;
        return Vehicle.builder()
                .id(vehicleId)
                .build();
    }

    default Employee mapEmployee(UUID employeeId) {
        if (employeeId == null)
            return null;
        return Employee.builder()
                .id(employeeId)
                .build();
    }
}
