package br.com.fiap.garage.application.v1.controller;

import br.com.fiap.garage.application.v1.dto.InventoryDto;
import br.com.fiap.garage.application.v1.swagger.InventorySwagger;
import br.com.fiap.garage.domain.filter.InventoryFilter;
import br.com.fiap.garage.domain.use_case.InventorySearchUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/v1/inventories")
public class InventoryController implements InventorySwagger {

    private final InventorySearchUseCase inventorySearchUseCase;

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public Page<InventoryDto.Representation> findAll(
            InventoryFilter filter) {
        var foundInventories = inventorySearchUseCase.findAll(filter);
        var responseBody = foundInventories.stream()
                .map(InventoryDto.Representation::buildInventoryDtoRepresentation)
                .toList();
        return new PageImpl<>(responseBody, filter.buildPageRequest(), responseBody.size());
    }
}
