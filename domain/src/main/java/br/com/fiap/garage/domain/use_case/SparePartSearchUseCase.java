package br.com.fiap.garage.domain.use_case;

import br.com.fiap.commons.exception.NotFoundException;
import br.com.fiap.garage.domain.entity.SparePart;
import br.com.fiap.garage.domain.repository.SparePartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SparePartSearchUseCase {

    private final SparePartRepository sparePartRepository;

    public SparePart findById(UUID id) {
        return sparePartRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(SparePart.class, "id", id));
    }
}
