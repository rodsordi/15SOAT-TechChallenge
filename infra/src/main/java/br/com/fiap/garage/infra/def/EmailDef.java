package br.com.fiap.garage.infra.def;

import br.com.fiap.commons.def.AuditableDef;

import java.io.Serializable;

/**
 * | dto            | Request     | Response             |
 * | Representation | Represented | RepresentedPersisted |
 * | Detailed       | Detailed    | DetailedPersisted    |
 */
public interface EmailDef {

    interface Represented extends Serializable {

        String getRecipient();

        String getSubject();
    }

    interface Detailed extends Represented {

        String getMessage();
    }

    interface RepresentedPersisted extends AuditableDef {


    }

    interface DetailedPersisted extends RepresentedPersisted {

        String getBcc();
    }

    interface Request extends Detailed {

    }

    interface Response extends Detailed, DetailedPersisted {

    }

    interface Representation extends Represented, RepresentedPersisted {

    }
}
