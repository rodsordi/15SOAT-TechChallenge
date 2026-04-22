package br.com.fiap.garage.application.v1.dto.factory;

import br.com.fiap.garage.application.v1.dto.EstimateDto;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

import static br.com.fiap.commons.util.DateUtil.newDateTime;
import static br.com.fiap.commons.util.ReflectionUtil.assertThatObject;
import static lombok.AccessLevel.PRIVATE;

@RequiredArgsConstructor(access = PRIVATE)
public final class EstimateDtoFactory {

    public static Request create_EstimateDto_Request() {
        return new Request(EstimateDto.Request.builder());
    }

    @RequiredArgsConstructor(access = PRIVATE)
    public static final class Request {

        private final EstimateDto.Request.RequestBuilder builder;

        public EstimateDto.Request withAllFields() {
            var result = builder
                    .amount(new BigDecimal("1500.00"))
                    .build();

            assertThatObject(result).hasNoEmptyFields();
            return result;
        }

        public EstimateDto.Request valid() {
            return builder
                    .amount(new BigDecimal("500.00"))
                    .build();
        }

        public EstimateDto.Request initiatedEmpty() {
            return builder.build();
        }
    }
}