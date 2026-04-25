package br.com.fiap.garage.application.v1.dto.factory;

import br.com.fiap.garage.application.v1.dto.MaterialDto;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

import static br.com.fiap.commons.util.ReflectionUtil.assertThatObject;
import static br.com.fiap.garage.domain.enums.MaterialType.SHOP_SUPPLY;
import static br.com.fiap.garage.domain.enums.MaterialType.SPARE_PART;
import static lombok.AccessLevel.PRIVATE;

@RequiredArgsConstructor(access = PRIVATE)
public final class MaterialDtoFactory {

    public static Request create_MaterialDto_Request() {
        return new Request(MaterialDto.Request.builder());
    }

    @RequiredArgsConstructor(access = PRIVATE)
    public static final class Request {

        private final MaterialDto.Request.RequestBuilder builder;

        public MaterialDto.Request withAllFields() {
            var result = builder
                    .type(SHOP_SUPPLY)
                    .name("Synthetic Oil 5W-30")
                    .description("Oil 5W-30")
                    .amount(new BigDecimal("85.50"))
                    .build();

            // And
            assertThatObject(result)
                    .hasNoEmptyFields();
            return result;
        }

        public MaterialDto.Request valid() {
            return builder
                    .type(SPARE_PART)
                    .name("Brake Cleaner")
                    .amount(new BigDecimal("12.90"))
                    .build();
        }

        public MaterialDto.Request initiatedEmpty() {
            return builder.build();
        }
    }
}