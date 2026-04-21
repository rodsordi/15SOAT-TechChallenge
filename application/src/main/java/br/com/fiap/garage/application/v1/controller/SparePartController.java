package br.com.fiap.garage.application.v1.controller;

import br.com.fiap.garage.application.v1.dto.SparePartDto;
import br.com.fiap.garage.application.v1.swagger.SparePartSwagger;
import br.com.fiap.garage.domain.use_case.SparePartCreationUseCase;
import br.com.fiap.garage.domain.use_case.SparePartSearchUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static br.com.fiap.garage.application.v1.dto.SparePartDto.Response.buildSparePartDtoResponse;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/v1/spare-parts")
public class SparePartController implements SparePartSwagger {

    private final SparePartCreationUseCase sparePartCreationUseCase;

    private final SparePartSearchUseCase sparePartSearchUseCase;

    @PostMapping(
            consumes = APPLICATION_JSON_VALUE,
            produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(CREATED)
    public SparePartDto.Response create(
            @Valid
            @RequestBody
            SparePartDto.Request requestBody) {
        var sparePart = requestBody.buildSparePart();
        var createdSparePart = sparePartCreationUseCase.create(sparePart);
        return buildSparePartDtoResponse(createdSparePart);
    }

    @GetMapping(path = "/{sparePartId}",
            produces = APPLICATION_JSON_VALUE)
    public SparePartDto.Response findById(
            @PathVariable("sparePartId")
            UUID sparePartId) {
        var foundSparePart = sparePartSearchUseCase.findById(sparePartId);
        return buildSparePartDtoResponse(foundSparePart);
    }
}
