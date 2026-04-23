package br.com.fiap.garage.application.v1.mapper;

import br.com.fiap.garage.application.v1.dto.CustomerDto;
import br.com.fiap.garage.domain.entity.Customer;
import org.mapstruct.Mapper;

import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

@Mapper(nullValuePropertyMappingStrategy = IGNORE)
public interface CustomerDtoMapper {

    Customer convert(CustomerDto.Request source);

    CustomerDto.Response convert(Customer source);

    CustomerDto.Representation convertToRepresentation(Customer source);
}
