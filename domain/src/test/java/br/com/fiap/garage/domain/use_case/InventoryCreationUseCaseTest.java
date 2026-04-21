package br.com.fiap.garage.domain.use_case;

import br.com.fiap.garage.domain.entity.ShopSupply;
import br.com.fiap.garage.domain.entity.SparePart;
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

import static br.com.fiap.garage.domain.entity.factory.ShopSupplyFactory.createShopSupply;
import static br.com.fiap.garage.domain.entity.factory.SparePartFactory.createSparePart;
import static java.util.UUID.fromString;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.lenient;
import static org.springframework.test.util.ReflectionTestUtils.setField;

@ExtendWith(MockitoExtension.class)
class InventoryCreationUseCaseTest {

    @InjectMocks
    private InventoryCreationUseCase inventoryCreationUseCase;

    @Mock
    private SparePartRepository sparePartRepository;

    @Mock
    private ShopSupplyRepository shopSupplyRepository;

    @DisplayName("When creating SparePart")
    @Nested
    class Create1 {

        @DisplayName("Then should execute successfully")
        @Nested
        class Success {

            @BeforeEach
            void beforeEach() {
                lenient()
                        .when(sparePartRepository.save(any(SparePart.class)))
                        .thenAnswer(invocationOnMock -> {
                            SparePart sparePart = invocationOnMock.getArgument(0);
                            setField(sparePart, "id", fromString("c0a1f176-d3e6-4910-8fba-9a6c31bc5577"));
                            return sparePart;
                        });

                lenient()
                        .when(shopSupplyRepository.save(any(ShopSupply.class)))
                        .thenAnswer(invocationOnMock -> {
                            ShopSupply shopSupply = invocationOnMock.getArgument(0);
                            setField(shopSupply, "id", fromString("5fa87dbf-cd02-40e9-a01a-3750be34a275"));
                            return shopSupply;
                        });
            }

            @DisplayName("Given a SparePart with all fields")
            @Test
            void test1() {
                //Given
                var sparePart = createSparePart()
                        .withAllFields();
                //When
                var actual = inventoryCreationUseCase.create(sparePart);
                //Then
                assertThat(actual.getId())
                        .hasToString("c0a1f176-d3e6-4910-8fba-9a6c31bc5577");
            }

            @DisplayName("Given a ShopSupply with all fields")
            @Test
            void test2() {
                //Given
                var shopSupply = createShopSupply()
                        .withAllFields();
                //When
                var actual = inventoryCreationUseCase.create(shopSupply);
                //Then
                assertThat(actual.getId())
                        .hasToString("5fa87dbf-cd02-40e9-a01a-3750be34a275");
            }
        }
    }
}