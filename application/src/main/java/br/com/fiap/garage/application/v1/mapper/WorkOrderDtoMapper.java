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

    @Mapping(target = "customer", source = "customerId")
    @Mapping(target = "employee", source = "employeeId")
    @Mapping(target = "estimatedServices", source = "servicesIds")
    WorkOrder convert(WorkOrderDto.Request source);

    @Mapping(target = "customer.document", source = "customer.document", qualifiedByName = "formattedDocument")
    @Mapping(target = "employee.cpf", source = "employee.cpf", qualifiedByName = "formattedCpf")
    WorkOrderDto.Response convert(WorkOrder source);

    @Mapping(target = "customer.document", source = "customer.document", qualifiedByName = "formattedDocument")
    @Mapping(target = "employee.cpf", source = "employee.cpf", qualifiedByName = "formattedCpf")
    WorkOrderDto.Representation convertToRepresentation(WorkOrder source);

    @Mapping(target = "serviceId", source = "service.id")
    EstimatedServiceDto.Response convert(EstimatedService source);

    @Mapping(target = "materialId", source = "material.id")
    EstimatedMaterialDto.Response convert(EstimatedMaterial source);

    default Customer mapCustomer(UUID customerId) {
        if (customerId == null)
            return null;
        return Customer.builder()
                .id(customerId)
                .build();
    }

    default Employee mapEmployee(UUID employeeId) {
        if (employeeId == null)
            return null;
        return Employee.builder()
                .id(employeeId)
                .build();
    }

    default EstimatedService mapEstimatedService(UUID serviceId) {
        if (serviceId == null)
            return null;
        return EstimatedService.builder()
                .service(Service.builder()
                        .id(serviceId)
                        .build())
                .build();
    }
}
