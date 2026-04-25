package br.com.fiap.garage.application.v1.def;

import br.com.fiap.commons.def.AuditableDef;
import br.com.fiap.garage.application.v1.dto.MaterialDto;

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

        Set<UUID> getMaterialsIds();
    }

    interface Response extends Detailed, DetailedPersisted {

        <T extends MaterialDto.Response> Set<T> getMaterials();
    }

    interface Representation extends Represented, RepresentedPersisted {

    }
}
