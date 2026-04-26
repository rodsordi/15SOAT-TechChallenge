package br.com.fiap.garage.application.v1.def;

import br.com.fiap.commons.def.AuditableDef;
import br.com.fiap.commons.validation.CpfOrCnpj;

import java.io.Serializable;
import java.util.Set;
import java.util.UUID;

/**
 * | dto            | Request     | Response             |
 * | Representation | Represented | RepresentedPersisted |
 * | Detailed       | Detailed    | DetailedPersisted    |
 */
public interface CustomerDef {

    interface Represented extends Serializable {

        String getUsername();

        String getName();

        String getEmail();

        @CpfOrCnpj
        String getDocument();
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

        <T extends VehicleDef.Request> Set<T> getVehicles();
    }

    interface Response extends Detailed, DetailedPersisted {

        <T extends VehicleDef.Response> Set<T> getVehicles();
    }

    interface Representation extends Represented, RepresentedPersisted {

        <T extends VehicleDef.Representation> Set<T> getVehicles();
    }

    interface ResumedRepresentation extends Represented, RepresentedPersisted {

    }
}
