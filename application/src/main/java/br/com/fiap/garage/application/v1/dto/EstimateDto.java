package br.com.fiap.garage.application.v1.dto;

import br.com.fiap.garage.application.v1.def.EstimateDef;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public final class EstimateDto {

    @Getter(onMethod_ = @Override)
    @Builder
    @NoArgsConstructor(access = PRIVATE)
    @AllArgsConstructor(access = PRIVATE)
    @Schema(name = ".Estimate.Request")
    public static class Request implements EstimateDef.Request {
        private BigDecimal amount;
    }

    @Getter(onMethod_ = @Override)
    @Builder
    @NoArgsConstructor(access = PRIVATE)
    @AllArgsConstructor(access = PRIVATE)
    @Schema(name = ".Estimate.Response")
    public static class Response implements EstimateDef.Response {
        private UUID id;
        private BigDecimal amount;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
    }

    @Getter(onMethod_ = @Override)
    @Builder
    @NoArgsConstructor(access = PRIVATE)
    @AllArgsConstructor(access = PRIVATE)
    @EqualsAndHashCode(callSuper = true)
    @Schema(name = ".Estimate.Representation")
    public static class Representation extends RepresentationModel<Representation> implements EstimateDef.Representation {
        private UUID id;
        private BigDecimal amount;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
    }
}
