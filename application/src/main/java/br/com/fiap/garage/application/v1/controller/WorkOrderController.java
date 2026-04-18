package br.com.fiap.garage.application.v1.controller;

import br.com.fiap.garage.application.v1.swagger.WorkOrderSwagger;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/v1/work-orders")
public class WorkOrderController implements WorkOrderSwagger {


}
