package br.com.fiap.garage.application.v1.dto.factory;

import br.com.fiap.garage.application.v1.dto.EmployeeDto;
import lombok.RequiredArgsConstructor;

import static br.com.fiap.commons.util.ReflectionUtil.assertThatObject;
import static lombok.AccessLevel.PRIVATE;

@RequiredArgsConstructor(access = PRIVATE)
public final class EmployeeDtoFactory {

    @RequiredArgsConstructor(access = PRIVATE)
    public static final class Request {

        private final EmployeeDto.Request.RequestBuilder builder;

        public static Request create_EmployeeDto_Request() {
            return new Request(EmployeeDto.Request.builder());
        }

        public EmployeeDto.Request withAllFields() {
            var result = builder
                    .name("John")
                    .build();

            assertThatObject(result).hasNoEmptyFields();
            return result;
        }

        public EmployeeDto.Request valid() {
            return builder
                    .name("John")
                    .build();
        }

        public EmployeeDto.Request initiatedEmpty() {
            return builder.build();
        }
    }
}