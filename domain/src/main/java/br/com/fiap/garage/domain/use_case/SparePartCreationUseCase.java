package br.com.fiap.garage.domain.use_case;

import br.com.fiap.garage.domain.entity.SparePart;
import br.com.fiap.garage.domain.repository.SparePartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SparePartCreationUseCase {

    private final SparePartRepository repository;

    public SparePart create(SparePart sparePart) {
        return repository.save(sparePart);
    }
}
