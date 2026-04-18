package br.com.fiap.garage.application.v1.dto;

import br.com.fiap.garage.application.v1.def.SparePartDef;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public final class SparePartDto {

    @Getter(onMethod_ = @Override)
    @Builder
    @NoArgsConstructor(access = PRIVATE)
    @AllArgsConstructor(access = PRIVATE)
    @Schema(name = ".SparePart.Request")
    public static class Request implements SparePartDef.Request {
        private String name;
        private BigDecimal price;
    }

    @Getter(onMethod_ = @Override)
    @Builder
    @NoArgsConstructor(access = PRIVATE)
    @AllArgsConstructor(access = PRIVATE)
    @Schema(name = ".SparePart.Request")
    public static class Response implements SparePartDef.Response {
        private UUID id;
        private String name;
        private BigDecimal price;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
    }

    @Getter(onMethod_ = @Override)
    @Builder
    @NoArgsConstructor(access = PRIVATE)
    @AllArgsConstructor(access = PRIVATE)
    @EqualsAndHashCode(callSuper = true)
    @Schema(name = ".SparePart.Representation")
    public static class Representation extends RepresentationModel<Representation> implements SparePartDef.Representation {
        private UUID id;
        private String name;
        private BigDecimal price;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
    }
}
