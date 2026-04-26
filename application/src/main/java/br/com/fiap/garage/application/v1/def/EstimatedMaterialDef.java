package br.com.fiap.garage.application.v1.def;

import br.com.fiap.commons.def.AuditableDef;
import br.com.fiap.garage.domain.enums.MaterialType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

/**
 * | dto            | Request     | Response             |
 * | Representation | Represented | RepresentedPersisted |
 * | Detailed       | Detailed    | DetailedPersisted    |
 */
public interface EstimatedMaterialDef {

    interface RepresentedPersisted extends AuditableDef {

        @Schema(example = "ba6c3148-53ad-4fec-9e55-4bb17582b4b0", description = "Estimated Material id. Owner: db")
        @NotNull
        UUID getId();

        @NotNull
        MaterialType getType();

        @Schema(example = "Oil", description = "Estimated Material name. Owner: self")
        @NotBlank
        @Size(max = 255)
        String getName();

        @Schema(example = "59.99", description = "Estimated Material cost. Owner: self")
        @NotNull
        BigDecimal getCost();
    }

    interface DetailedPersisted extends RepresentedPersisted {

        @Schema(example = "Synthetic 0W-20", description = "Estimated Material description. Owner: self")
        @Size(max = 255)
        String getDescription();
    }

    interface Response extends DetailedPersisted {

    }

    interface Representation extends RepresentedPersisted {

    }
}
