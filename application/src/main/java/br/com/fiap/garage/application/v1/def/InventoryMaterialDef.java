package br.com.fiap.garage.application.v1.def;

import br.com.fiap.commons.def.AuditableDef;

import java.io.Serializable;
import java.util.UUID;

/**
 * | dto            | Request     | Response             |
 * | Representation | Represented | RepresentedPersisted |
 * | Detailed       | Detailed    | DetailedPersisted    |
 */
public interface InventoryMaterialDef {

    interface Represented extends Serializable {

    }

    interface Detailed extends Represented {

        Integer getQuantityInStock();
        Integer getReservedQuantity();
    }

    interface RepresentedPersisted extends AuditableDef {

        UUID getId();
    }

    interface DetailedPersisted extends RepresentedPersisted {

    }

    interface Request extends Detailed {

        <T extends MaterialDef.Request> T getMaterial();
    }

    interface Response extends Detailed, DetailedPersisted {

        <T extends MaterialDef.Response> T getMaterial();
    }

    interface Representation extends Represented, RepresentedPersisted {

        <T extends MaterialDef.Representation> T getMaterial();
    }
}
