package br.com.fiap.garage.application.v1.controller;

import br.com.fiap.garage.application.v1.swagger.SparePartAndShopSupplySwagger;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/v1/spare-parts-and-shop-supplies")
public class SparePartAndShopSupplyController implements SparePartAndShopSupplySwagger {


}
