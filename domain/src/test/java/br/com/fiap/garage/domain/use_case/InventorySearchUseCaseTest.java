package br.com.fiap.garage.domain.use_case;

import br.com.fiap.garage.domain.filter.InventoryFilter;
import br.com.fiap.garage.domain.repository.InventoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;

import java.util.List;

import static br.com.fiap.garage.domain.entity.factory.ShopSupplyFactory.create_ShopSupply;
import static br.com.fiap.garage.domain.entity.factory.SparePartFactory.create_SparePart;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.lenient;

@ExtendWith(MockitoExtension.class)
class InventorySearchUseCaseTest {

    @InjectMocks
    private InventorySearchUseCase inventorySearchUseCase;

    @Mock
    private InventoryRepository inventoryRepository;

    @DisplayName("When finding all Inventories")
    @Nested
    class FindAll {

        @DisplayName("Then should execute successfully")
        @Nested
        class Success {

            @BeforeEach
            void beforeEach() {
                lenient()
                        .when(inventoryRepository.findAll(any(), any()))
                        .thenReturn(new PageImpl<>(List.of(
                                create_SparePart().withAllFields(),
                                create_ShopSupply().withAllFields()
                        )));
            }

            @DisplayName("Given a valid inventory filter")
            @Test
            void test1() {
                //Given
                var inventoryFilter = new InventoryFilter();
                //When
                var actual = inventorySearchUseCase.findAll(inventoryFilter);
                //Then
                assertThat(actual)
                        .isNotNull();
            }
        }
    }
}