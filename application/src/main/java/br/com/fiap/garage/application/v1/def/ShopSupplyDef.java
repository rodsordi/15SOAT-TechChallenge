package br.com.fiap.garage.application.v1.def;

import br.com.fiap.commons.def.AuditableDef;

import java.io.Serializable;

/**
 * | dto            | Request     | Response             |
 * | Representation | Represented | RepresentedPersisted |
 * | Detailed       | Detailed    | DetailedPersisted    |
 */
public interface ShopSupplyDef {

    interface Represented extends Serializable {
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
