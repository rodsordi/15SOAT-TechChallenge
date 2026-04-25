package br.com.fiap.garage.application.v1.def;

import br.com.fiap.commons.def.AuditableDef;
import br.com.fiap.garage.domain.enums.WorkOrderStatus;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Set;
import java.util.UUID;

/**
 * | dto            | Request     | Response             |
 * | Representation | Represented | RepresentedPersisted |
 * | Detailed       | Detailed    | DetailedPersisted    |
 */
public interface WorkOrderDef {

    interface Represented extends Serializable {
    }

    interface Detailed extends Represented {

    }

    interface RepresentedPersisted extends AuditableDef {

        UUID getId();

        WorkOrderStatus getStatus();

        BigDecimal getTotalAmount();
    }

    interface DetailedPersisted extends RepresentedPersisted {

    }

    interface Request extends Detailed {

        // Aggregation
        UUID getCustomerId();

        // Aggregation
        UUID getEmployeeId();

        // Aggregation
        Set<UUID> getServicesIds();
    }

    interface Response extends Detailed, DetailedPersisted {

        // Aggregation
        <T extends CustomerDef.Response> T getCustomer();

        // Aggregation
        <T extends EmployeeDef.Response> T getEmployee();

        // Aggregation
        <T extends EstimatedServiceDef.Response> Set<T> getEstimatedServices();
    }

    interface Representation extends Represented, RepresentedPersisted {

        // Aggregation
        <T extends CustomerDef.Representation> T getCustomer();

        // Aggregation
        <T extends EmployeeDef.Representation> T getEmployee();

        // Aggregation
        <T extends EstimatedServiceDef.Representation> Set<T> getEstimatedServices();
    }
}
