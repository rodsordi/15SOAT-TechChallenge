package br.com.fiap.garage.application.v1.def;

import br.com.fiap.commons.def.AuditableDef;

import java.io.Serializable;
import java.time.Year;
import java.util.UUID;

/**
 * | dto            | Request     | Response             |
 * | Representation | Represented | RepresentedPersisted |
 * | Detailed       | Detailed    | DetailedPersisted    |
 */
public interface VehicleDef {

    interface Represented extends Serializable {

        String getMake();
        String getModel();
        String getLicensePlate();
        Year getManufactureYear();
    }

    interface Detailed extends Represented {

    }

    interface RepresentedPersisted extends AuditableDef {

        UUID getId();
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
