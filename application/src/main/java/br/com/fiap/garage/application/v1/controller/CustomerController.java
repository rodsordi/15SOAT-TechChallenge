package br.com.fiap.garage.application.v1.controller;

import br.com.fiap.garage.application.v1.dto.CustomerDto;
import br.com.fiap.garage.application.v1.swagger.CustomerSwagger;
import br.com.fiap.garage.domain.filter.CustomerFilter;
import br.com.fiap.garage.domain.use_case.CustomerCreationUseCase;
import br.com.fiap.garage.domain.use_case.CustomerSearchUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static br.com.fiap.garage.application.v1.dto.CustomerDto.Response.buildCustomerDtoResponse;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/v1/customers")
public class CustomerController implements CustomerSwagger {

    private final CustomerCreationUseCase customerCreationUseCase;

    private final CustomerSearchUseCase customerSearchUseCase;

    @PostMapping(
            consumes = APPLICATION_JSON_VALUE,
            produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(CREATED)
    public CustomerDto.Response create(
            @Valid
            @RequestBody
            CustomerDto.Request requestBody) {
        var customer = requestBody.buildCustomer();
        var createdCustomer = customerCreationUseCase.create(customer);
        return buildCustomerDtoResponse(createdCustomer);
    }

    @GetMapping(path = "/{customerId}",
            produces = APPLICATION_JSON_VALUE)
    public CustomerDto.Response findById(
            @PathVariable("customerId")
            UUID customerId) {
        var foundCustomer = customerSearchUseCase.findById(customerId);
        return buildCustomerDtoResponse(foundCustomer);
    }

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public Page<CustomerDto.Representation> findAll(
            CustomerFilter filter) {
        var foundCustomers = customerSearchUseCase.findAll(filter);
        var responseBody = foundCustomers.stream()
                .map(CustomerDto.Representation::buildCustomerDtoRepresentation)
                .toList();
        return new PageImpl<>(responseBody, filter.buildPageRequest(), responseBody.size());
    }
}
