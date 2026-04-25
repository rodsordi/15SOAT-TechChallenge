package br.com.fiap.garage.domain.entity.assertions;

import br.com.fiap.garage.domain.entity.WorkOrder;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

import static br.com.fiap.commons.util.ReflectionUtil.assertThatObject;
import static br.com.fiap.garage.domain.entity.assertions.CustomerAssertions.assertThat_Customer;
import static br.com.fiap.garage.domain.entity.assertions.EmployeeAssertions.assertThat_Employee;
import static br.com.fiap.garage.domain.entity.assertions.EstimateAssertions.assertThat_Estimate;
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
                .isEqualByComparingTo(new BigDecimal("999.99"));

        // Composition
        assertThat_Employee(actual.getEmployee())
                .wasConvertedFrom_EmployeeDto_Request();
        assertThat_Customer(actual.getCustomer())
                .wasConvertedFrom_CustomerDto_Request();
        assertThat_Estimate(actual.getEstimate())
                .wasConvertedFrom_EstimateDto_Request();

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