package br.com.fiap.garage.application.v1.dto.factory;

import br.com.fiap.garage.application.v1.dto.SparePartDto;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

import static br.com.fiap.commons.util.ReflectionUtil.assertThatObject;
import static lombok.AccessLevel.PRIVATE;

@RequiredArgsConstructor(access = PRIVATE)
public final class SparePartDtoFactory {

    @RequiredArgsConstructor(access = PRIVATE)
    public static final class Request {

        private final SparePartDto.Request.RequestBuilder builder;

        public static Request create_SparePartDto_Request() {
            return new Request(SparePartDto.Request.builder());
        }

        public SparePartDto.Request withAllFields() {
            var result = builder
                    .name("Brake Pad")
                    .price(new BigDecimal("120.50"))
                    .quantityInStock(10)
                    .build();

            // And
            assertThatObject(result)
                    .hasNoEmptyFields();
            return result;
        }

        public SparePartDto.Request valid() {
            return builder
                    .name("Ignition")
                    .price(new BigDecimal("45.00"))
                    .quantityInStock(12)
                    .build();
        }

        public SparePartDto.Request initiatedEmpty() {
            return builder.build();
        }
    }
}