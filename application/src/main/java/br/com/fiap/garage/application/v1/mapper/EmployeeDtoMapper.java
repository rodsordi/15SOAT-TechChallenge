package br.com.fiap.garage.application.v1.mapper;

import br.com.fiap.garage.application.v1.dto.EmployeeDto;
import br.com.fiap.garage.domain.entity.Employee;
import org.mapstruct.Mapper;

import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

@Mapper(nullValuePropertyMappingStrategy = IGNORE)
public interface EmployeeDtoMapper {

    Employee convert(EmployeeDto.Request source);

    EmployeeDto.Response convert(Employee source);

    EmployeeDto.Representation convertToRepresentation(Employee source);
}
