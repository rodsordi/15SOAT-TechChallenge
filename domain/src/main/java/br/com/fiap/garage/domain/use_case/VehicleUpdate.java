package br.com.fiap.garage.domain.use_case;

import br.com.fiap.garage.domain.entity.Vehicle;
import br.com.fiap.commons.domain.exception.NotFoundException;
import br.com.fiap.garage.domain.repository.VehicleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VehicleUpdate {

    private final VehicleRepository repository;

    public Vehicle update(Vehicle vehicle) {
        var fetchedVehicle = repository.findById(vehicle.getId())
                .orElseThrow(() -> new NotFoundException(Vehicle.class, "id", vehicle.getId()));
        return repository.save(fetchedVehicle);
    }
}
