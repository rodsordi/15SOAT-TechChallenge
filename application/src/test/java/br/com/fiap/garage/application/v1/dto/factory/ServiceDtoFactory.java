package br.com.fiap.garage.application.v1.dto.factory;

import br.com.fiap.garage.application.v1.dto.ServiceDto;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.util.Set;

import static br.com.fiap.commons.util.ReflectionUtil.assertThatObject;
import static br.com.fiap.garage.application.v1.dto.factory.EstimateDtoFactory.create_EstimateDto_Request;
import static lombok.AccessLevel.PRIVATE;

@RequiredArgsConstructor(access = PRIVATE)
public final class ServiceDtoFactory {

    public static Request create_ServiceDto_Request() {
        return new Request(ServiceDto.Request.builder());
    }

    @RequiredArgsConstructor(access = PRIVATE)
    public static final class Request {

        private final ServiceDto.Request.RequestBuilder builder;

        public ServiceDto.Request withAllFields() {
            var result = builder
                    // Self
                    .name("Oil Change")
                    .description("Complete engine oil and filter change")
                    .amount(new BigDecimal("150.00"))
                    // Composition
                    .estimatedMaterials(Set.of(create_EstimateDto_Request().withAllFields()))
                    .build();

            // And
            assertThatObject(result)
                    .hasNoEmptyFields();
            return result;
        }

        public ServiceDto.Request valid() {
            return builder
                    .name("Oil Change")
                    .description("Complete engine oil and filter change")
                    .amount(new BigDecimal("150.00"))
                    .build();
        }

        public ServiceDto.Request initiatedEmpty() {
            return builder.build();
        }
    }
}