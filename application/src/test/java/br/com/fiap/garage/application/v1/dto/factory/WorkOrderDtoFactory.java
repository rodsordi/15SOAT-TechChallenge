package br.com.fiap.garage.application.v1.dto.factory;

import br.com.fiap.garage.application.v1.dto.WorkOrderDto;
import lombok.RequiredArgsConstructor;

import static br.com.fiap.commons.util.ReflectionUtil.assertThatObject;
import static br.com.fiap.garage.application.v1.dto.factory.EstimateDtoFactory.Request.create_EstimateDto_Request;
import static lombok.AccessLevel.PRIVATE;

@RequiredArgsConstructor(access = PRIVATE)
public final class WorkOrderDtoFactory {

    @RequiredArgsConstructor(access = PRIVATE)
    public static final class Request {

        private final WorkOrderDto.Request.RequestBuilder builder;

        public static Request create_WorkOrderDto_Request() {
            return new Request(WorkOrderDto.Request.builder());
        }

        public WorkOrderDto.Request withAllFields() {
            var result = builder
                    .estimate(create_EstimateDto_Request().withAllFields())
                    .build();

            assertThatObject(result).hasNoEmptyFields();
            return result;
        }

        public WorkOrderDto.Request valid() {
            return builder
                    .estimate(create_EstimateDto_Request().valid())
                    .build();
        }

        public WorkOrderDto.Request initiatedEmpty() {
            return builder
                    .estimate(create_EstimateDto_Request().initiatedEmpty())
                    .build();
        }
    }
}