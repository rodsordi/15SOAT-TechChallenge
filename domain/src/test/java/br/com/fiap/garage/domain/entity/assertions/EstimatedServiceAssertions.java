package br.com.fiap.garage.domain.entity.assertions;

import br.com.fiap.garage.domain.entity.WorkOrder;
import br.com.fiap.garage.domain.enums.WorkOrderStatus;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

// Assumed imports based on the provided composition structure
import static br.com.fiap.garage.domain.entity.assertions.CustomerAssertions.assertThat_Customer;
import static br.com.fiap.garage.domain.entity.assertions.EmployeeAssertions.assertThat_Employee;
import static br.com.fiap.garage.domain.entity.assertions.EstimatedServiceAssertions.assertThat_EstimatedService;
import static br.com.fiap.commons.util.DateUtil.newDateTime;
import static br.com.fiap.commons.util.ReflectionUtil.assertThatObject;
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
                .hasToString("d290f1ee-6c54-4b01-90e6-d701748f0851");
        assertThat(actual.getStatus())
                .isEqualTo(WorkOrderStatus.RECEIVED);
        assertThat(actual.getTotalAmount())
                .isEqualTo(new BigDecimal("1500.00"));

        // Composition
        assertThat_Employee(actual.getEmployee())
                .isEqualTo_Employee();

        assertThat_Customer(actual.getCustomer())
                .isEqualTo_Customer();

        assertThat_EstimatedService(actual.getEstimatedServices().stream().findFirst().orElseThrow())
                .isEqualTo_EstimatedService();

        // Inheritance (AuditableEntity)
        assertThat(actual.getCreatedAt())
                .isEqualTo(newDateTime("21/04/2026 10:00:00"));
        assertThat(actual.getUpdatedAt())
                .isEqualTo(newDateTime("21/04/2026 15:30:00"));

        // And
        assertThatObject(actual)
                .hasAllGetMethodsVerifiedOnceAtLeast();
    }
}