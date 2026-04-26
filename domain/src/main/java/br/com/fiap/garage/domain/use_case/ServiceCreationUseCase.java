package br.com.fiap.garage.domain.use_case;

import br.com.fiap.commons.exception.NotFoundException;
import br.com.fiap.garage.domain.entity.Material;
import br.com.fiap.garage.domain.entity.Service;
import br.com.fiap.garage.domain.repository.MaterialRepository;
import br.com.fiap.garage.domain.repository.ServiceRepository;
import lombok.RequiredArgsConstructor;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@org.springframework.stereotype.Service
@RequiredArgsConstructor
public class ServiceCreationUseCase {

    private final MaterialRepository materialRepository;

    private final ServiceRepository serviceRepository;

    public Service create(Service service, Set<UUID> materialsIds) {
        var materials = materialsIds.stream()
                .map(materialId -> materialRepository.findById(materialId)
                        .orElseThrow(() -> new NotFoundException(Material.class, "id", materialId)))
                .collect(Collectors.toSet());
        service.updateReferences(materials);
        return serviceRepository.save(service);
    }
}
