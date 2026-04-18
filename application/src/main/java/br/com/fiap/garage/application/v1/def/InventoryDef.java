package br.com.fiap.garage.application.v1.def;

import br.com.fiap.commons.def.AuditableDef;

import java.io.Serializable;
import java.util.Set;
import java.util.UUID;

/**
 * | dto            | Request     | Response             |
 * | Representation | Represented | RepresentedPersisted |
 * | Detailed       | Detailed    | DetailedPersisted    |
 */
public interface InventoryDef {

    interface Represented extends Serializable {
    }

    interface Detailed extends Represented {

    }

    interface RepresentedPersisted extends AuditableDef {

        UUID getId();
    }

    interface DetailedPersisted extends RepresentedPersisted {

    }

    interface Request extends Detailed {

        <T extends SparePartDef.Request> Set<T> getSpareParts();
    }

    interface Response extends Detailed, DetailedPersisted {

        <T extends SparePartDef.Response> Set<T> getSpareParts();
    }

    interface Representation extends Represented, RepresentedPersisted {

    }
}
