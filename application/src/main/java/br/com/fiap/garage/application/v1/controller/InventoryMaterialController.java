package br.com.fiap.garage.application.v1.controller;

import br.com.fiap.garage.application.v1.dto.InventoryMaterialDto;
import br.com.fiap.garage.application.v1.swagger.InventoryMaterialSwagger;
import br.com.fiap.garage.domain.filter.InventoryMaterialFilter;
import br.com.fiap.garage.domain.use_case.InventoryMaterialCreationUseCase;
import br.com.fiap.garage.domain.use_case.InventoryMaterialSearchUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static br.com.fiap.garage.application.v1.dto.InventoryMaterialDto.Response.buildInventoryMaterialDtoResponse;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/v1/inventory-materials")
public class InventoryMaterialController implements InventoryMaterialSwagger {

    private final InventoryMaterialSearchUseCase inventoryMaterialSearchUseCase;

    private final InventoryMaterialCreationUseCase inventoryMaterialCreationUseCase;

    @PostMapping(
            consumes = APPLICATION_JSON_VALUE,
            produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(CREATED)
    public InventoryMaterialDto.Response create(
            @Valid
            @RequestBody
            InventoryMaterialDto.Request requestBody) {
        var inventoryMaterial = requestBody.buildInventoryMaterial();
        var createdInventoryMaterial = inventoryMaterialCreationUseCase.create(inventoryMaterial);
        return buildInventoryMaterialDtoResponse(createdInventoryMaterial);
    }

    @GetMapping(path = "/{inventoryMaterialId}",
            produces = APPLICATION_JSON_VALUE)
    public InventoryMaterialDto.Response findById(
            @PathVariable("inventoryMaterialId")
            UUID inventoryMaterialId) {
        var foundInventoryMaterial = inventoryMaterialSearchUseCase.findById(inventoryMaterialId);
        return buildInventoryMaterialDtoResponse(foundInventoryMaterial);
    }

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public Page<InventoryMaterialDto.Representation> findAll(
            InventoryMaterialFilter filter) {
        var foundInventories = inventoryMaterialSearchUseCase.findAll(filter);
        var responseBody = foundInventories.stream()
                .map(InventoryMaterialDto.Representation::buildInventoryDtoRepresentation)
                .toList();
        return new PageImpl<>(responseBody, filter.buildPageRequest(), responseBody.size());
    }
}
