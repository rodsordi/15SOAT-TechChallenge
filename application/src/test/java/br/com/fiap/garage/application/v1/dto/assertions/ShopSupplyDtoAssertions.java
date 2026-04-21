package br.com.fiap.garage.application.v1.dto.assertions;

import br.com.fiap.garage.application.v1.dto.ShopSupplyDto;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

import static br.com.fiap.commons.util.DateUtil.newDateTime;
import static br.com.fiap.commons.util.ReflectionUtil.assertThatObject;
import static lombok.AccessLevel.PRIVATE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.spy;

@RequiredArgsConstructor(access = PRIVATE)
public final class ShopSupplyDtoAssertions {

    @RequiredArgsConstructor(access = PRIVATE)
    public static final class Response {

        private final ShopSupplyDto.Response actual;

        public static Response assertThat_ShopSupplyDto_Response(ShopSupplyDto.Response actual) {
            assertThat(actual).isNotNull();
            return new Response(spy(actual));
        }

        /**
         * @see br.com.fiap.garage.domain.entity.factory.ShopSupplyFactory
         * .withAllFields()
         */
        public void wasConvertedFrom_ShopSupply() {
            // Self
            assertThat(actual.getId())
                    .hasToString("d341007b-7d1f-406e-aabf-37db3ddbdb8e");
            assertThat(actual.getName())
                    .isEqualTo("Synthetic Oil 5W-30");
            assertThat(actual.getPrice())
                    .isEqualTo(new BigDecimal("45.90"));
            assertThat(actual.getQuantityInStock())
                    .isEqualTo(111);

            //Inheritance (AuditableTable)
            assertThat(actual.getCreatedAt())
                    .isEqualTo(newDateTime("13/12/2027 23:59:59"));
            assertThat(actual.getUpdatedAt())
                    .isEqualTo(newDateTime("14/12/2027 23:59:59"));

            // And
            assertThatObject(actual)
                    .hasAllGetMethodsVerifiedOnceAtLeast();
        }
    }
}