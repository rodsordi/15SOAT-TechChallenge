package br.com.fiap.garage.application.v1.dto.factory;

import br.com.fiap.garage.application.v1.dto.ShopSupplyDto;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

import static br.com.fiap.commons.util.ReflectionUtil.assertThatObject;
import static lombok.AccessLevel.PRIVATE;

@RequiredArgsConstructor(access = PRIVATE)
public final class ShopSupplyDtoFactory {

    @RequiredArgsConstructor(access = PRIVATE)
    public static final class Request {

        private final ShopSupplyDto.Request.RequestBuilder builder;

        public static Request createShopSupplyDto_Request() {
            return new Request(ShopSupplyDto.Request.builder());
        }

        public ShopSupplyDto.Request withAllFields() {
            var result = builder
                    .name("Synthetic Oil 5W-30")
                    .price(new BigDecimal("85.50"))
                    .quantityInStock(6)
                    .build();

            // And
            assertThatObject(result)
                    .hasNoEmptyFields();
            return result;
        }

        public ShopSupplyDto.Request valid() {
            return builder
                    .name("Brake Cleaner")
                    .price(new BigDecimal("12.90"))
                    .quantityInStock(7)
                    .build();
        }

        public ShopSupplyDto.Request initiatedEmpty() {
            return builder.build();
        }
    }
}