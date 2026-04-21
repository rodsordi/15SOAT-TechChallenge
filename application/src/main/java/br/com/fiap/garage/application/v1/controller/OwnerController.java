package br.com.fiap.garage.application.v1.controller;

import br.com.fiap.garage.application.v1.dto.OwnerDto;
import br.com.fiap.garage.application.v1.swagger.OwnerSwagger;
import br.com.fiap.garage.domain.filter.OwnerFilter;
import br.com.fiap.garage.domain.use_case.OwnerCreationUseCase;
import br.com.fiap.garage.domain.use_case.OwnerSearchUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static br.com.fiap.garage.application.v1.dto.OwnerDto.Response.buildOwnerDtoResponse;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/v1/owners")
public class OwnerController implements OwnerSwagger {

    private final OwnerCreationUseCase ownerCreationUseCase;

    private final OwnerSearchUseCase ownerSearchUseCase;

    @PostMapping(
            consumes = APPLICATION_JSON_VALUE,
            produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(CREATED)
    public OwnerDto.Response create(
            @Valid
            @RequestBody
            OwnerDto.Request requestBody) {
        var owner = requestBody.buildOwner();
        var createdOwner = ownerCreationUseCase.create(owner);
        return buildOwnerDtoResponse(createdOwner);
    }

    @GetMapping(path = "/{ownerId}",
            produces = APPLICATION_JSON_VALUE)
    public OwnerDto.Response findById(
            @PathVariable("ownerId")
            UUID ownerId) {
        var foundOwner = ownerSearchUseCase.findById(ownerId);
        return buildOwnerDtoResponse(foundOwner);
    }

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public Page<OwnerDto.Representation> findAll(
            OwnerFilter filter) {
        var foundOwners = ownerSearchUseCase.findAll(filter);
        var responseBody = foundOwners.stream()
                .map(OwnerDto.Representation::buildOwnerDtoRepresentation)
                .toList();
        return new PageImpl<>(responseBody, filter.buildPageRequest(), responseBody.size());
    }
}
