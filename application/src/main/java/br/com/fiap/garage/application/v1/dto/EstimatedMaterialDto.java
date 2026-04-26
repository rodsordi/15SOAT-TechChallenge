package br.com.fiap.garage.application.v1.dto;

import br.com.fiap.garage.application.v1.def.EstimatedMaterialDef;
import br.com.fiap.garage.domain.enums.MaterialType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public final class EstimatedMaterialDto {

    @Getter(onMethod_ = @Override)
    @Builder
    @NoArgsConstructor(access = PRIVATE)
    @AllArgsConstructor(access = PRIVATE)
    @Schema(name = ".EstimatedMaterial.Response")
    public static class Response implements EstimatedMaterialDef.Response {
        private UUID id;
        private MaterialType type;
        private String name;
        private String description;
        private BigDecimal cost;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
    }

    @Getter(onMethod_ = @Override)
    @Builder
    @NoArgsConstructor(access = PRIVATE)
    @AllArgsConstructor(access = PRIVATE)
    @EqualsAndHashCode(callSuper = true)
    @Schema(name = ".EstimatedMaterial.Representation")
    public static class Representation extends RepresentationModel<EstimatedMaterialDto.Representation> implements EstimatedMaterialDef.Representation {
        private UUID id;
        private MaterialType type;
        private String name;
        private BigDecimal cost;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
    }
}
