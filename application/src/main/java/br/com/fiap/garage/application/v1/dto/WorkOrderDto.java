package br.com.fiap.garage.application.v1.dto;

import br.com.fiap.garage.application.v1.def.WorkOrderDef;
import br.com.fiap.garage.domain.entity.enums.WorkOrderStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDateTime;
import java.util.UUID;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public final class WorkOrderDto {

    @Getter(onMethod_ = @Override)
    @Builder
    @NoArgsConstructor(access = PRIVATE)
    @AllArgsConstructor(access = PRIVATE)
    @Schema(name = ".WorkOrder.Request")
    public static class Request implements WorkOrderDef.Request {
        private EstimateDto.Request estimate;
    }

    @Getter(onMethod_ = @Override)
    @Builder
    @NoArgsConstructor(access = PRIVATE)
    @AllArgsConstructor(access = PRIVATE)
    @Schema(name = ".WorkOrder.Request")
    public static class Response implements WorkOrderDef.Response {
        private UUID id;
        private WorkOrderStatus status;
        private EstimateDto.Response estimate;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
    }

    @Getter(onMethod_ = @Override)
    @Builder
    @NoArgsConstructor(access = PRIVATE)
    @AllArgsConstructor(access = PRIVATE)
    @EqualsAndHashCode(callSuper = true)
    @Schema(name = ".WorkOrder.Representation")
    public static class Representation extends RepresentationModel<Representation> implements WorkOrderDef.Representation {
        private UUID id;
        private WorkOrderStatus status;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
    }
}
