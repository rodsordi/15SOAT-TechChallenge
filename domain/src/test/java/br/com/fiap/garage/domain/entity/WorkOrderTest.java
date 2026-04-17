package br.com.fiap.garage.domain.entity;

import br.com.fiap.garage.domain.entity.enums.WorkOrderStatus;
import br.com.fiap.garage.domain.exception.BusinessException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static br.com.fiap.garage.domain.entity.enums.WorkOrderStatus.*;
import static br.com.fiap.garage.domain.entity.factory.WorkOrderFactory.createWorkOrder;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class WorkOrderTest {

    private WorkOrder workOrder;

    @DisplayName("When diagnosing work order")
    @Nested
    class Diagnose {

        @DisplayName("Then should execute successfully")
        @Nested
        class Success {

            @DisplayName("Given a work order in RECEIVED status")
            @Test
            void test1() {
                //Given
                workOrder = createWorkOrder()
                        .withAllFields()
                        .status(RECEIVED)
                        .build();
                //When
                workOrder.diagnose();
                //Then
                assertThat(workOrder.getStatus())
                        .isEqualTo(DIAGNOSING);
            }
        }

        @DisplayName("Then should return an error message")
        @Nested
        class Failure {

            @DisplayName("Given a work order in {status} status")
            @CsvSource(value = {
                    "DIAGNOSING           | WorkOrder in DIAGNOSING status, cannot be updated to DIAGNOSING status.",
                    "WAITING_FOR_APPROVAL | WorkOrder in WAITING_FOR_APPROVAL status, cannot be updated to DIAGNOSING status.",
                    "EXECUTING            | WorkOrder in EXECUTING status, cannot be updated to DIAGNOSING status.",
                    "FINISHED             | WorkOrder in FINISHED status, cannot be updated to DIAGNOSING status.",
                    "RELEASED             | WorkOrder in RELEASED status, cannot be updated to DIAGNOSING status.",
            }, delimiterString = "|")
            @ParameterizedTest
            void test1(WorkOrderStatus status, String mensagemEsperada) {
                //Given
                workOrder = createWorkOrder()
                        .withAllFields()
                        .status(status)
                        .build();
                //When
                var atual = assertThrows(BusinessException.class,
                        () -> workOrder.diagnose());
                //Then
                assertThat(atual.getMessage())
                        .isEqualTo(mensagemEsperada);
            }
        }
    }

    @DisplayName("When waiting for approval work order")
    @Nested
    class WaitForApproval {

        @DisplayName("Then should execute successfully")
        @Nested
        class Success {

            @DisplayName("Given a work order in DIAGNOSING status")
            @Test
            void test1() {
                //Given
                workOrder = createWorkOrder()
                        .withAllFields()
                        .status(DIAGNOSING)
                        .build();
                //When
                workOrder.waitForApproval();
                //Then
                assertThat(workOrder.getStatus())
                        .isEqualTo(WAITING_FOR_APPROVAL);
            }
        }

        @DisplayName("Then should return an error message")
        @Nested
        class Failure {

            @DisplayName("Given a work order in {status} status")
            @CsvSource(value = {
                    "RECEIVED             | WorkOrder in RECEIVED status, cannot be updated to WAITING_FOR_APPROVAL status.",
                    "WAITING_FOR_APPROVAL | WorkOrder in WAITING_FOR_APPROVAL status, cannot be updated to WAITING_FOR_APPROVAL status.",
                    "EXECUTING            | WorkOrder in EXECUTING status, cannot be updated to WAITING_FOR_APPROVAL status.",
                    "FINISHED             | WorkOrder in FINISHED status, cannot be updated to WAITING_FOR_APPROVAL status.",
                    "RELEASED             | WorkOrder in RELEASED status, cannot be updated to WAITING_FOR_APPROVAL status.",
            }, delimiterString = "|")
            @ParameterizedTest
            void test1(WorkOrderStatus status, String mensagemEsperada) {
                //Given
                workOrder = createWorkOrder()
                        .withAllFields()
                        .status(status)
                        .build();
                //When
                var atual = assertThrows(BusinessException.class,
                        () -> workOrder.waitForApproval());
                //Then
                assertThat(atual.getMessage())
                        .isEqualTo(mensagemEsperada);
            }
        }
    }

    @DisplayName("When executing work order")
    @Nested
    class Execute {

        @DisplayName("Then should execute successfully")
        @Nested
        class Success {

            @DisplayName("Given a work order in WAITING_FOR_APPROVAL status")
            @Test
            void test1() {
                //Given
                workOrder = createWorkOrder()
                        .withAllFields()
                        .status(WAITING_FOR_APPROVAL)
                        .build();
                //When
                workOrder.execute();
                //Then
                assertThat(workOrder.getStatus())
                        .isEqualTo(EXECUTING);
            }
        }

        @DisplayName("Then should return an error message")
        @Nested
        class Failure {

            @DisplayName("Given a work order in {status} status")
            @CsvSource(value = {
                    "RECEIVED   | WorkOrder in RECEIVED status, cannot be updated to EXECUTING status.",
                    "DIAGNOSING | WorkOrder in DIAGNOSING status, cannot be updated to EXECUTING status.",
                    "EXECUTING  | WorkOrder in EXECUTING status, cannot be updated to EXECUTING status.",
                    "FINISHED   | WorkOrder in FINISHED status, cannot be updated to EXECUTING status.",
                    "RELEASED   | WorkOrder in RELEASED status, cannot be updated to EXECUTING status.",
            }, delimiterString = "|")
            @ParameterizedTest
            void test1(WorkOrderStatus status, String mensagemEsperada) {
                //Given
                workOrder = createWorkOrder()
                        .withAllFields()
                        .status(status)
                        .build();
                //When
                var atual = assertThrows(BusinessException.class,
                        () -> workOrder.execute());
                //Then
                assertThat(atual.getMessage())
                        .isEqualTo(mensagemEsperada);
            }
        }
    }

    @DisplayName("When finishing work order")
    @Nested
    class Finish {

        @DisplayName("Then should execute successfully")
        @Nested
        class Success {

            @DisplayName("Given a work order in EXECUTING status")
            @Test
            void test1() {
                //Given
                workOrder = createWorkOrder()
                        .withAllFields()
                        .status(EXECUTING)
                        .build();
                //When
                workOrder.finish();
                //Then
                assertThat(workOrder.getStatus())
                        .isEqualTo(FINISHED);
            }
        }

        @DisplayName("Then should return an error message")
        @Nested
        class Failure {

            @DisplayName("Given a work order in {status} status")
            @CsvSource(value = {
                    "RECEIVED             | WorkOrder in RECEIVED status, cannot be updated to FINISHED status.",
                    "DIAGNOSING           | WorkOrder in DIAGNOSING status, cannot be updated to FINISHED status.",
                    "WAITING_FOR_APPROVAL | WorkOrder in WAITING_FOR_APPROVAL status, cannot be updated to FINISHED status.",
                    "FINISHED             | WorkOrder in FINISHED status, cannot be updated to FINISHED status.",
                    "RELEASED             | WorkOrder in RELEASED status, cannot be updated to FINISHED status.",
            }, delimiterString = "|")
            @ParameterizedTest
            void test1(WorkOrderStatus status, String mensagemEsperada) {
                //Given
                workOrder = createWorkOrder()
                        .withAllFields()
                        .status(status)
                        .build();
                //When
                var atual = assertThrows(BusinessException.class,
                        () -> workOrder.finish());
                //Then
                assertThat(atual.getMessage())
                        .isEqualTo(mensagemEsperada);
            }
        }
    }

    @DisplayName("When releasing work order")
    @Nested
    class Release {

        @DisplayName("Then should execute successfully")
        @Nested
        class Success {

            @DisplayName("Given a work order in FINISHED status")
            @Test
            void test1() {
                //Given
                workOrder = createWorkOrder()
                        .withAllFields()
                        .status(FINISHED)
                        .build();
                //When
                workOrder.release();
                //Then
                assertThat(workOrder.getStatus())
                        .isEqualTo(RELEASED);
            }
        }

        @DisplayName("Then should return an error message")
        @Nested
        class Failure {

            @DisplayName("Given a work order in {status} status")
            @CsvSource(value = {
                    "RECEIVED             | WorkOrder in RECEIVED status, cannot be updated to RELEASED status.",
                    "DIAGNOSING           | WorkOrder in DIAGNOSING status, cannot be updated to RELEASED status.",
                    "WAITING_FOR_APPROVAL | WorkOrder in WAITING_FOR_APPROVAL status, cannot be updated to RELEASED status.",
                    "EXECUTING            | WorkOrder in EXECUTING status, cannot be updated to RELEASED status.",
                    "RELEASED             | WorkOrder in RELEASED status, cannot be updated to RELEASED status.",
            }, delimiterString = "|")
            @ParameterizedTest
            void test1(WorkOrderStatus status, String mensagemEsperada) {
                //Given
                workOrder = createWorkOrder()
                        .withAllFields()
                        .status(status)
                        .build();
                //When
                var atual = assertThrows(BusinessException.class,
                        () -> workOrder.release());
                //Then
                assertThat(atual.getMessage())
                        .isEqualTo(mensagemEsperada);
            }
        }
    }
}