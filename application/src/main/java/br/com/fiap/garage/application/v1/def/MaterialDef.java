package br.com.fiap.garage.application.v1.def;

import br.com.fiap.commons.def.AuditableDef;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * | dto            | Request     | Response             |
 * | Representation | Represented | RepresentedPersisted |
 * | Detailed       | Detailed    | DetailedPersisted    |
 */
public interface MaterialDef {

    interface Represented extends Serializable {

        String getName();
        String getDescription();
        BigDecimal getAmount();
    }

    interface Detailed extends Represented {

    }

    interface RepresentedPersisted extends AuditableDef {

    }

    interface DetailedPersisted extends RepresentedPersisted {

    }

    interface Request extends Detailed {

    }

    interface Response extends Detailed, DetailedPersisted {

    }

    interface Representation extends Represented, RepresentedPersisted {

    }
}