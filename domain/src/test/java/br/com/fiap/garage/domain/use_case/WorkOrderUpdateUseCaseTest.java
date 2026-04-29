package br.com.fiap.garage.domain.use_case;

import br.com.fiap.commons.exception.ResourceNotFoundException;
import br.com.fiap.garage.domain.repository.WorkOrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static br.com.fiap.garage.domain.entity.factory.WorkOrderFactory.create_WorkOrder;
import static br.com.fiap.garage.domain.enums.WorkOrderStatus.DIAGNOSING;
import static br.com.fiap.garage.domain.enums.WorkOrderStatus.EXECUTING;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class WorkOrderUpdateUseCaseTest {

    @InjectMocks
    private WorkOrderUpdateUseCase workOrderUpdateUseCase;

    @Mock
    private WorkOrderRepository repository;

    @DisplayName("When updating WorkOrder")
    @Nested
    class Update {

        @DisplayName("Then should execute successfully")
        @Nested
        class Success {

            @BeforeEach
            void beforeEach() {
                when(repository.findById(any()))
                        .thenReturn(Optional.of(create_WorkOrder()
                                .withAllFields()));
                when(repository.save(any()))
                        .thenAnswer(i -> i.getArgument(0));
            }

            @DisplayName("Given a valid id, and a valid status")
            @Test
            void test1() {
                //Given
                var id = UUID.fromString("b06b216f-215d-41eb-8c03-570f03562064");
                var status = DIAGNOSING;
                //When
                var actual = workOrderUpdateUseCase.update(id, status);
                //Then
                assertThat(actual).isNotNull();
                verify(repository, times(1)).save(any());
            }
        }

        @DisplayName("Then should return error")
        @Nested
        class Failure {

            @DisplayName("Given a WorkOrder with all fields, in scenario with no registered WorkOrder")
            @Test
            void test1() {
                //Given
                var id = UUID.fromString("b06b216f-215d-41eb-8c03-570f03562064");
                var status = EXECUTING;
                //When
                var actual = assertThrows(ResourceNotFoundException.class,
                        () -> workOrderUpdateUseCase.update(id, status));
                //Then
                assertThat(actual)
                        .hasMessage("Resource [WorkOrder] with [id]: [b06b216f-215d-41eb-8c03-570f03562064] not found");
            }
        }
    }
}