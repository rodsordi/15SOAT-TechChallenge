package br.com.fiap.garage.application.v1.dto.factory;

import br.com.fiap.garage.application.v1.dto.VehicleDto;
import lombok.RequiredArgsConstructor;

import static br.com.fiap.commons.util.ReflectionUtil.assertThatObject;
import static lombok.AccessLevel.PRIVATE;

@RequiredArgsConstructor(access = PRIVATE)
public final class VehicleDtoFactory {

    @RequiredArgsConstructor(access = PRIVATE)
    public static final class Request {

        private final VehicleDto.Request.RequestBuilder builder;

        public static Request create_VehicleDto_Request() {
            return new Request(VehicleDto.Request.builder());
        }

        public VehicleDto.Request withAllFields() {
            var result = builder
                    .make("Toyota")
                    .model("Corolla")
                    .licensePlate("ABC-1234")
                    .build();

            // And
            assertThatObject(result)
                    .hasNoEmptyFields();
            return result;
        }

        public VehicleDto.Request valid() {
            return builder
                    .make("Honda")
                    .model("Civic")
                    .licensePlate("XYZ-9876")
                    .build();
        }

        public VehicleDto.Request initiatedEmpty() {
            return builder.build();
        }
    }
}