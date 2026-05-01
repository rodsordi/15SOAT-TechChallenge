package br.com.fiap.garage.application.v1.swagger;

import br.com.fiap.garage.application.v1.dto.EmployeeDto;
import br.com.fiap.garage.domain.filter.EmployeeFilter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Tag(name = "Employee (v1)", description = "Employee resource.")
public interface EmployeeSwagger extends GenericSwagger {

    EmployeeDto.Response create(
            EmployeeDto.Request requestBody);

    EmployeeDto.Response findById(
            UUID employeeId);

    Page<EmployeeDto.Representation> findAll(
            EmployeeFilter filter);

    EmployeeDto.Response update(
            UUID employeeId,
            EmployeeDto.PatchRequest requestBody);
}
