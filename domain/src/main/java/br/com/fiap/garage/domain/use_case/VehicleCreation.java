package br.com.fiap.garage.domain.use_case;

import br.com.fiap.garage.domain.repository.VehicleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VehicleCreation {

    private final VehicleRepository repository;
}
