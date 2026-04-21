package br.com.fiap.garage.domain.use_case;

import br.com.fiap.garage.domain.filter.InventoryFilter;
import br.com.fiap.garage.domain.repository.InventoryRepository;
import br.com.fiap.garage.domain.repository.ShopSupplyRepository;
import br.com.fiap.garage.domain.repository.SparePartRepository;
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
import java.util.Optional;
import java.util.UUID;

import static br.com.fiap.garage.domain.entity.factory.ShopSupplyFactory.createShopSupply;
import static br.com.fiap.garage.domain.entity.factory.SparePartFactory.createSparePart;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class InventorySearchUseCaseTest {

    @InjectMocks
    private InventorySearchUseCase inventorySearchUseCase;

    @Mock
    private InventoryRepository inventoryRepository;

    @Mock
    private SparePartRepository sparePartRepository;

    @Mock
    private ShopSupplyRepository shopSupplyRepository;

    @DisplayName("When finding Inventory by id")
    @Nested
    class FindById {

        private final UUID givenSparePartId = UUID.fromString("fbd817e7-64f5-4e0a-80fe-51a6eb35e9a2");
        private final UUID givenShopSupplyId = UUID.fromString("1ea882d1-258a-49d9-b616-ff4d81586056");

        @DisplayName("Then should execute successfully")
        @Nested
        class Success {

            @BeforeEach
            void beforeEach() {
                lenient()
                        .when(sparePartRepository.findById(givenSparePartId))
                        .thenReturn(Optional.of(createSparePart()
                                .withAllFields()));

                lenient()
                        .when(shopSupplyRepository.findById(givenShopSupplyId))
                        .thenReturn(Optional.of(createShopSupply()
                                .withAllFields()));
            }

            @DisplayName("Given a valid spare part id")
            @Test
            void test1() {
                //When
                var actual = inventorySearchUseCase.findById(givenSparePartId);
                //Then
                assertThat(actual)
                        .isNotNull();
            }

            @DisplayName("Given a valid shop supply id")
            @Test
            void test2() {
                //When
                var actual = inventorySearchUseCase.findById(givenShopSupplyId);
                //Then
                assertThat(actual)
                        .isNotNull();
            }
        }
    }

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
                                createSparePart().withAllFields(),
                                createShopSupply().withAllFields()
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