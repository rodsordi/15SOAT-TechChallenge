package br.com.fiap.garage.application.v1.controller;

import br.com.fiap.garage.application.v1.dto.EmployeeDto;
import br.com.fiap.garage.application.v1.swagger.EmployeeSwagger;
import br.com.fiap.garage.domain.filter.EmployeeFilter;
import br.com.fiap.garage.domain.use_case.EmployeeCreationUseCase;
import br.com.fiap.garage.domain.use_case.EmployeeSearchUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static br.com.fiap.garage.application.v1.dto.EmployeeDto.Response.buildEmployeeDtoResponse;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/v1/employees")
public class EmployeeController implements EmployeeSwagger {

    private final EmployeeCreationUseCase ownerCreationUseCase;

    private final EmployeeSearchUseCase ownerSearchUseCase;

    @PostMapping(
            consumes = APPLICATION_JSON_VALUE,
            produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(CREATED)
    public EmployeeDto.Response create(
            @Valid
            @RequestBody
            EmployeeDto.Request requestBody) {
        var owner = requestBody.buildEmployee();
        var createdEmployee = ownerCreationUseCase.create(owner);
        return buildEmployeeDtoResponse(createdEmployee);
    }

    @GetMapping(path = "/{ownerId}",
            produces = APPLICATION_JSON_VALUE)
    public EmployeeDto.Response findById(
            @PathVariable("ownerId")
            UUID ownerId) {
        var foundEmployee = ownerSearchUseCase.findById(ownerId);
        return buildEmployeeDtoResponse(foundEmployee);
    }

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public Page<EmployeeDto.Representation> findAll(
            EmployeeFilter filter) {
        var foundEmployees = ownerSearchUseCase.findAll(filter);
        var responseBody = foundEmployees.stream()
                .map(EmployeeDto.Representation::buildEmployeeDtoRepresentation)
                .toList();
        return new PageImpl<>(responseBody, filter.buildPageRequest(), responseBody.size());
    }
}
