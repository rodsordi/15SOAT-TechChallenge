package br.com.fiap.garage.application.v1.controller;

import br.com.fiap.garage.application.v1.dto.ShopSupplyDto;
import br.com.fiap.garage.application.v1.swagger.ShopSupplySwagger;
import br.com.fiap.garage.domain.use_case.ShopSupplyCreationUseCase;
import br.com.fiap.garage.domain.use_case.ShopSupplySearchUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static br.com.fiap.garage.application.v1.dto.ShopSupplyDto.Response.buildShopSupplyDtoResponse;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/v1/shop-supplies")
public class ShopSupplyController implements ShopSupplySwagger {

    private final ShopSupplyCreationUseCase shopSupplyCreationUseCase;

    private final ShopSupplySearchUseCase shopSupplySearchUseCase;

    @PostMapping(
            consumes = APPLICATION_JSON_VALUE,
            produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(CREATED)
    public ShopSupplyDto.Response create(
            @Valid
            @RequestBody
            ShopSupplyDto.Request requestBody) {
        var shopSupply = requestBody.buildShopSupply();
        var createdShopSupply = shopSupplyCreationUseCase.create(shopSupply);
        return buildShopSupplyDtoResponse(createdShopSupply);
    }

    @GetMapping(path = "/{shopSupplyId}",
            produces = APPLICATION_JSON_VALUE)
    public ShopSupplyDto.Response findById(
            @PathVariable("shopSupplyId")
            UUID shopSupplyId) {
        var foundShopSupply = shopSupplySearchUseCase.findById(shopSupplyId);
        return buildShopSupplyDtoResponse(foundShopSupply);
    }
}
