package br.com.fiap.garage.application.v1.dto;

import br.com.fiap.garage.application.v1.def.ShopSupplyDef;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public final class ShopSupplyDto {

    @Getter(onMethod_ = @Override)
    @Builder
    @NoArgsConstructor(access = PRIVATE)
    @AllArgsConstructor(access = PRIVATE)
    @Schema(name = ".ShopSupply.Request")
    public static class Request implements ShopSupplyDef.Request {
        private String name;
        private BigDecimal price;
        private Integer quantityInStock;
    }

    @Getter(onMethod_ = @Override)
    @Builder
    @NoArgsConstructor(access = PRIVATE)
    @AllArgsConstructor(access = PRIVATE)
    @Schema(name = ".ShopSupply.Request")
    public static class Response implements ShopSupplyDef.Response {
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
    @Schema(name = ".ShopSupply.Representation")
    public static class Representation extends RepresentationModel<ShopSupplyDto.Representation> implements ShopSupplyDef.Representation {
        private UUID id;
        private String name;
        private BigDecimal price;
        private Integer quantityInStock;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
    }
}
