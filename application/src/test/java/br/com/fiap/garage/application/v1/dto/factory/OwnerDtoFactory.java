package br.com.fiap.garage.application.v1.dto.factory;

import br.com.fiap.garage.application.v1.dto.OwnerDto;
import lombok.RequiredArgsConstructor;

import static br.com.fiap.commons.util.ReflectionUtil.assertThatObject;
import static lombok.AccessLevel.PRIVATE;

@RequiredArgsConstructor(access = PRIVATE)
public final class OwnerDtoFactory {

    public static Request create_OwnerDto_Request() {
        return new Request(OwnerDto.Request.builder());
    }

    @RequiredArgsConstructor(access = PRIVATE)
    public static final class Request {

        private final OwnerDto.Request.RequestBuilder builder;

        public OwnerDto.Request withAllFields() {
            var result = builder
                    // Self
                    .name("John Doe")
                    .email("john.doe@example.com")
                    .build();

            // And
            assertThatObject(result)
                    .hasNoEmptyFields();
            return result;
        }

        public OwnerDto.Request valid() {
            return builder
                    .name("Jane Doe")
                    .email("jane.doe@example.com")
                    .build();
        }

        public OwnerDto.Request initiatedEmpty() {
            return builder.build();
        }
    }
}