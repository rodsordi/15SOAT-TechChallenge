package br.com.fiap.garage.application.v1.def;

import br.com.fiap.commons.def.AuditableDef;

import java.io.Serializable;
import java.util.UUID;

/**
 * | dto            | Request     | Response             |
 * | Representation | Represented | RepresentedPersisted |
 * | Detailed       | Detailed    | DetailedPersisted    |
 */
public interface EmployeeDef {

    interface Represented extends Serializable {

        String getUsername();
        String getName();
        String getEmail();
        String getCpf();
    }

    interface Detailed extends Represented {

    }

    interface RepresentedPersisted extends AuditableDef {

        UUID getId();
    }

    interface DetailedPersisted extends RepresentedPersisted {

    }

    interface Request extends Detailed {

        String getPassword();
    }

    interface Response extends Detailed, DetailedPersisted {

    }

    interface Representation extends Represented, RepresentedPersisted {

    }
}
