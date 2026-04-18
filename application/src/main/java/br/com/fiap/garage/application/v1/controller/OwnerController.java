package br.com.fiap.garage.application.v1.controller;

import br.com.fiap.garage.application.v1.swagger.OwnerSwagger;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/v1/owners")
public class OwnerController implements OwnerSwagger {


}
