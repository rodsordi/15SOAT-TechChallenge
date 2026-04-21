package br.com.fiap.garage.domain.use_case;

import br.com.fiap.garage.domain.entity.Owner;
import br.com.fiap.garage.domain.repository.OwnerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static br.com.fiap.garage.domain.entity.factory.OwnerFactory.createOwner;
import static java.util.UUID.fromString;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.util.ReflectionTestUtils.setField;

@ExtendWith(MockitoExtension.class)
class OwnerCreationUseCaseTest {

    @InjectMocks
    private OwnerCreationUseCase ownerCreationUseCase;

    @Mock
    private OwnerRepository repository;

    @DisplayName("When creating Owner")
    @Nested
    class Create {

        @DisplayName("Then should execute successfully")
        @Nested
        class Success {

            @BeforeEach
            void beforeEach() {
                when(repository.save(any()))
                        .thenAnswer(invocationOnMock -> {
                            Owner owner = invocationOnMock.getArgument(0);
                            setField(owner, "id", fromString("c0a1f176-d3e6-4910-8fba-9a6c31bc5577"));
                            return owner;
                        });
            }

            @DisplayName("Given a Owner with all fields")
            @Test
            void test1() {
                //Given
                var owner = createOwner()
                        .withAllFields();
                //When
                var actual = ownerCreationUseCase.create(owner);
                //Then
                assertThat(actual.getId())
                        .hasToString("c0a1f176-d3e6-4910-8fba-9a6c31bc5577");
            }
        }
    }
}