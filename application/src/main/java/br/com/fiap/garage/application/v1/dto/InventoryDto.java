package br.com.fiap.garage.application.v1.dto;

import br.com.fiap.garage.application.v1.def.InventoryDef;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public final class InventoryDto {

    @Getter(onMethod_ = @Override)
    @Builder
    @NoArgsConstructor(access = PRIVATE)
    @AllArgsConstructor(access = PRIVATE)
    @Schema(name = ".Inventory.Request")
    public static class Request implements InventoryDef.Request {
        private String name;
        private BigDecimal price;
        private Integer quantityInStock;
    }

    @Getter(onMethod_ = @Override)
    @Builder
    @NoArgsConstructor(access = PRIVATE)
    @AllArgsConstructor(access = PRIVATE)
    @Schema(name = ".Inventory.Request")
    public static class Response implements InventoryDef.Response {
        private UUID id;
        private String name;
        private BigDecimal price;
        private Integer quantityInStock;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
    }

    @Getter(onMethod_ = @Override)
    @Builder
    @NoArgsConstructor(access = PRIVATE)
    @AllArgsConstructor(access = PRIVATE)
    @EqualsAndHashCode(callSuper = true)
    @Schema(name = ".Inventory.Representation")
    public static class Representation extends RepresentationModel<Representation> implements InventoryDef.Representation {
        private UUID id;
        private String name;
        private BigDecimal price;
        private Integer quantityInStock;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
    }
}
