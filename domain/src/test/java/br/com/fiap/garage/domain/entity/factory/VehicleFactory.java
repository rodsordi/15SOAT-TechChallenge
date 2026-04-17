package br.com.fiap.garage.domain.entity.factory;

import br.com.fiap.garage.domain.entity.Vehicle;
import lombok.RequiredArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@RequiredArgsConstructor(access = PRIVATE)
public final class VehicleFactory {

    private final Vehicle.VehicleBuilder<?, ?> builder;

    public static VehicleFactory createVehicle() {
        return new VehicleFactory(Vehicle.builder());
    }

    public Vehicle.VehicleBuilder<?, ?> withAllFields() {
        return builder;
    }
}