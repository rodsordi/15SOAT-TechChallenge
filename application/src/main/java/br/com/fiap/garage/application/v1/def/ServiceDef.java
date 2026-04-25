package br.com.fiap.garage.application.v1.def;

import br.com.fiap.commons.def.AuditableDef;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Set;
import java.util.UUID;

/**
 * | dto            | Request     | Response             |
 * | Representation | Represented | RepresentedPersisted |
 * | Detailed       | Detailed    | DetailedPersisted    |
 */
public interface ServiceDef {

    interface Represented extends Serializable {

        String getName();
        String getDescription();
        BigDecimal getAmount();
    }

    interface Detailed extends Represented {

    }

    interface RepresentedPersisted extends AuditableDef {

        UUID getId();
    }

    interface DetailedPersisted extends RepresentedPersisted {

    }

    interface Request extends Detailed {

        Set<String> getInventoryMaterials();
    }

    interface Response extends Detailed, DetailedPersisted {

        Set<String> getInventoryMaterials();
    }

    interface Representation extends Represented, RepresentedPersisted {

    }
}
