package br.com.fiap.garage.domain.use_case;

import br.com.fiap.garage.domain.entity.Vehicle;
import br.com.fiap.garage.domain.repository.VehicleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VehicleCreationUseCase {

    private final VehicleRepository repository;

    public Vehicle create(Vehicle vehicle) {
        return repository.save(vehicle);
    }
}
