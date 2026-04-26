package br.com.fiap.garage.application.v1.def;

import br.com.fiap.commons.def.AuditableDef;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.time.Year;
import java.util.UUID;

import static com.fasterxml.jackson.annotation.JsonFormat.Shape.STRING;

/**
 * | dto            | Request     | Response             |
 * | Representation | Represented | RepresentedPersisted |
 * | Detailed       | Detailed    | DetailedPersisted    |
 */
public interface VehicleDef {

    interface Represented extends Serializable {

        @Schema(example = "Volkswagen", description = "Vehicle make. Owner: self")
        @NotBlank
        @Size(max = 100)
        String getMake();

        @Schema(example = "Gol", description = "Vehicle model. Owner: self")
        @NotBlank
        @Size(max = 100)
        String getModel();

        @Schema(example = "ABC1C34", description = "Vehicle license plate. Owner: self")
        @NotBlank
        @Size(max = 10)
        String getLicensePlate();

        @Schema(example = "2026", description = "Vehicle manufacture year. Owner: self")
        @JsonFormat(pattern = "yyyy", shape = STRING)
        @NotNull
        Year getManufactureYear();
    }

    interface Detailed extends Represented {

    }

    interface RepresentedPersisted extends AuditableDef {

        @Schema(example = "50902f1c-1db9-4f38-94b1-02b538d54f7c", description = "Vehicle id. Owner: db")
        @NotNull
        UUID getId();
    }

    interface DetailedPersisted extends RepresentedPersisted {

    }

    interface Request extends Detailed {

    }

    interface Response extends Detailed, DetailedPersisted {

        <T extends CustomerDef.ResumedRepresentation> T getCustomer();
    }

    interface Representation extends Represented, RepresentedPersisted {

        <T extends CustomerDef.ResumedRepresentation> T getCustomer();
    }
}
