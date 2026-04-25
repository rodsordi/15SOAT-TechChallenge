package br.com.fiap.garage.application.v1.dto;

import br.com.fiap.garage.application.v1.def.EstimatedServiceDef;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public final class EstimatedServiceDto {

    @Getter(onMethod_ = @Override)
    @Builder
    @NoArgsConstructor(access = PRIVATE)
    @AllArgsConstructor(access = PRIVATE)
    @Schema(name = ".EstimatedService.Response")
    public static class Response implements EstimatedServiceDef.Response {
        private UUID id;
        private String name;
        private String description;
        private BigDecimal amount;
        private LocalDateTime finishedAt;
        private UUID serviceId;
        @Singular("estimatedMaterial")
        private Set<EstimatedMaterialDto.Response> estimatedMaterials;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
    }

    @Getter(onMethod_ = @Override)
    @Builder
    @NoArgsConstructor(access = PRIVATE)
    @AllArgsConstructor(access = PRIVATE)
    @EqualsAndHashCode(callSuper = true)
    @Schema(name = ".EstimatedService.Representation")
    public static class Representation extends RepresentationModel<EstimatedServiceDto.Representation> implements EstimatedServiceDef.Representation {
        private UUID id;
        private String name;
        private BigDecimal amount;
        private LocalDateTime finishedAt;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
    }
}
