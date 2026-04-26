package br.com.fiap.garage.application.v1.dto;

import br.com.fiap.garage.application.v1.def.EstimatedMaterialDef;
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
        private String name;
        private String description;
        private BigDecimal amount;
        private UUID materialId;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
    }

    @Getter(onMethod_ = @Override)
    @Builder
    @NoArgsConstructor(access = PRIVATE)
    @AllArgsConstructor(access = PRIVATE)
    @EqualsAndHashCode(callSuper = true, exclude = "id")
    @Schema(name = ".EstimatedMaterial.Representation")
    public static class Representation extends RepresentationModel<EstimatedMaterialDto.Representation> implements EstimatedMaterialDef.Representation {
        private UUID id;
        private String name;
        private BigDecimal amount;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
    }
}
