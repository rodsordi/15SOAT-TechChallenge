package br.com.fiap.garage.application.v1.dto.factory;

import br.com.fiap.garage.application.v1.dto.CustomerDto;
import lombok.RequiredArgsConstructor;

import static br.com.fiap.commons.util.ReflectionUtil.assertThatObject;
import static lombok.AccessLevel.PRIVATE;

@RequiredArgsConstructor(access = PRIVATE)
public final class CustomerDtoFactory {

    public static Request create_CustomerDto_Request() {
        return new Request(CustomerDto.Request.builder());
    }

    @RequiredArgsConstructor(access = PRIVATE)
    public static final class Request {

        private final CustomerDto.Request.RequestBuilder builder;

        public CustomerDto.Request withAllFields() {
            var result = builder
                    // Self
                    .username("john.doe@example.com")
                    .name("John Doe")
                    .password("1234asdl")
                    .email("john.doe@example.com")
                    .document("00.123.456/0001-90")
                    .build();

            // And
            assertThatObject(result)
                    .hasNoEmptyFields();
            return result;
        }

        public CustomerDto.Request valid() {
            return builder
                    .name("Jane Doe")
                    .email("jane.doe@example.com")
                    .build();
        }

        public CustomerDto.Request initiatedEmpty() {
            return builder.build();
        }
    }
}