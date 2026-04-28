package br.com.fiap.garage.domain.entity.assertions;

import br.com.fiap.garage.domain.entity.WorkOrder;
import lombok.RequiredArgsConstructor;

import static br.com.fiap.commons.util.DateUtil.newDateTime;
import static br.com.fiap.commons.util.ReflectionUtil.assertThatObject;
import static br.com.fiap.garage.domain.entity.assertions.EmployeeAssertions.assertThat_Employee;
import static br.com.fiap.garage.domain.entity.assertions.EstimatedServiceAssertions.assertThat_EstimatedService;
import static br.com.fiap.garage.domain.entity.assertions.VehicleAssertions.assertThat_Vehicle;
import static br.com.fiap.garage.domain.enums.WorkOrderStatus.RECEIVED;
import static lombok.AccessLevel.PRIVATE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.spy;

@RequiredArgsConstructor(access = PRIVATE)
public final class WorkOrderAssertions {

    private final WorkOrder actual;

    public static WorkOrderAssertions assertThat_WorkOrder(WorkOrder actual) {
        assertThat(actual).isNotNull();
        return new WorkOrderAssertions(spy(actual));
    }

    /**
     * @see br.com.fiap.garage.domain.entity.factory.WorkOrderFactory
     * .withAllFields()
     */
    public void isEqualTo_WorkOrder() {
        // Self
        assertThat(actual.getId())
                .hasToString("fdbbe77f-1386-4b03-9612-2de63ad4daed");
        assertThat(actual.getStatus())
                .isEqualTo(RECEIVED);
        assertThat(actual.getTotalAmount())
                .isNull();

        // Composition
        assertThat_Vehicle(actual.getVehicle())
                .isEqualTo_Vehicle();
        assertThat_Employee(actual.getEmployee())
                .isEqualTo_Employee();
        assertThat_EstimatedService(actual.getEstimatedServices().stream().findFirst().orElseThrow())
                .isEqualTo_EstimatedService();

        // Inheritance (AuditableEntity)
        assertThat(actual.getCreatedAt())
                .isEqualTo(newDateTime("13/12/2026 23:59:59"));
        assertThat(actual.getUpdatedAt())
                .isEqualTo(newDateTime("14/12/2026 23:59:59"));

        // And
        assertThatObject(actual)
                .hasAllGetMethodsVerifiedOnceAtLeast();
    }

    /**
     * @see br.com.fiap.garage.application.v1.dto.factory.WorkOrderDtoFactory
     * .withAllFields()
     */
    public void wasConvertedFrom_WorkOrderDto_Request() {
        // Self
        assertThat(actual.getId())
                .isNull();
        assertThat(actual.getStatus())
                .isEqualTo(RECEIVED);
        assertThat(actual.getTotalAmount())
                .isNull();

        // Composition
        assertThat_Vehicle(actual.getVehicle())
                .wasConvertedFrom_WorkOrderDto_Request();
        assertThat_Employee(actual.getEmployee())
                .wasConvertedFrom_WorkOrderDto_Request();
        assertThat(actual.getEstimatedServices())
                .isNullOrEmpty();

        // Inheritance (AuditableEntity)
        assertThat(actual.getCreatedAt())
                .isNull();
        assertThat(actual.getUpdatedAt())
                .isNull();

        // And
        assertThatObject(actual)
                .hasAllGetMethodsVerifiedOnceAtLeast();
    }
}