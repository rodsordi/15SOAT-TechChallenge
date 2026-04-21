package br.com.fiap.garage.application.v1.controller;

import br.com.fiap.garage.application.v1.dto.VehicleDto;
import br.com.fiap.garage.application.v1.swagger.VehicleSwagger;
import br.com.fiap.garage.domain.filter.VehicleFilter;
import br.com.fiap.garage.domain.use_case.VehicleCreationUseCase;
import br.com.fiap.garage.domain.use_case.VehicleSearchUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static br.com.fiap.garage.application.v1.dto.VehicleDto.Response.buildVehicleDtoResponse;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/v1/vehicles")
public class VehicleController implements VehicleSwagger {

    private final VehicleCreationUseCase vehicleCreationUseCase;

    private final VehicleSearchUseCase vehicleSearchUseCase;

    @PostMapping(
            consumes = APPLICATION_JSON_VALUE,
            produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(CREATED)
    public VehicleDto.Response create(
            @Valid
            @RequestBody
            VehicleDto.Request requestBody) {
        var vehicle = requestBody.buildVehicle();
        var createdVehicle = vehicleCreationUseCase.create(vehicle);
        return buildVehicleDtoResponse(createdVehicle);
    }

    @GetMapping(path = "/{vehicleId}",
            produces = APPLICATION_JSON_VALUE)
    public VehicleDto.Response findById(
            @PathVariable("vehicleId")
            UUID vehicleId) {
        var foundVehicle = vehicleSearchUseCase.findById(vehicleId);
        return buildVehicleDtoResponse(foundVehicle);
    }

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public Page<VehicleDto.Representation> findAll(
            VehicleFilter filter) {
        var foundVehicles = vehicleSearchUseCase.findAll(filter);
        var responseBody = foundVehicles.stream()
                .map(VehicleDto.Representation::buildVehicleDtoRepresentation)
                .toList();
        return new PageImpl<>(responseBody, filter.buildPageRequest(), responseBody.size());
    }
}
