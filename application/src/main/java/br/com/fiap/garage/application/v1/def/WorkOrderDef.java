package br.com.fiap.garage.application.v1.def;

import br.com.fiap.commons.def.AuditableDef;
import br.com.fiap.garage.domain.enums.WorkOrderStatus;

import java.io.Serializable;
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
    }

    interface DetailedPersisted extends RepresentedPersisted {

    }

    interface Request extends Detailed {

        <T extends EstimateDef.Request> T getEstimate();
    }

    interface Response extends Detailed, DetailedPersisted {

        <T extends EstimateDef.Response> T getEstimate();
    }

    interface Representation extends Represented, RepresentedPersisted {

    }
}
