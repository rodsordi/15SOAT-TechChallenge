package br.com.fiap.garage.domain.use_case;

import br.com.fiap.garage.domain.entity.ShopSupply;
import br.com.fiap.garage.domain.repository.ShopSupplyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static br.com.fiap.garage.domain.entity.factory.ShopSupplyFactory.create_ShopSupply;
import static java.util.UUID.fromString;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.util.ReflectionTestUtils.setField;

@ExtendWith(MockitoExtension.class)
class ShopSupplyCreationUseCaseTest {

    @InjectMocks
    private ShopSupplyCreationUseCase shopSupplyCreationUseCase;

    @Mock
    private ShopSupplyRepository repository;

    @DisplayName("When creating ShopSupply")
    @Nested
    class Create {

        @DisplayName("Then should execute successfully")
        @Nested
        class Success {

            @BeforeEach
            void beforeEach() {
                when(repository.save(any()))
                        .thenAnswer(invocationOnMock -> {
                            ShopSupply shopSupply = invocationOnMock.getArgument(0);
                            setField(shopSupply, "id", fromString("c0a1f176-d3e6-4910-8fba-9a6c31bc5577"));
                            return shopSupply;
                        });
            }

            @DisplayName("Given a ShopSupply with all fields")
            @Test
            void test1() {
                //Given
                var shopSupply = create_ShopSupply()
                        .withAllFields();
                //When
                var actual = shopSupplyCreationUseCase.create(shopSupply);
                //Then
                assertThat(actual.getId())
                        .hasToString("c0a1f176-d3e6-4910-8fba-9a6c31bc5577");
            }
        }
    }
}