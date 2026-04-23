package br.com.fiap.garage.application.v1.dto;

import br.com.fiap.garage.application.v1.def.ShopSupplyDef;
import br.com.fiap.garage.application.v1.mapper.ShopSupplyDtoMapper;
import br.com.fiap.garage.application.v1.mapper.ShopSupplyDtoMapper;
import br.com.fiap.garage.domain.entity.ShopSupply;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import static lombok.AccessLevel.PRIVATE;
import static org.mapstruct.factory.Mappers.getMapper;

@NoArgsConstructor(access = PRIVATE)
public final class ShopSupplyDto {

    private static final ShopSupplyDtoMapper MAPPER = getMapper(ShopSupplyDtoMapper.class);

    @Getter(onMethod_ = @Override)
    @Builder
    @NoArgsConstructor(access = PRIVATE)
    @AllArgsConstructor(access = PRIVATE)
    @Schema(name = ".ShopSupply.Request")
    public static class Request implements ShopSupplyDef.Request {
        private String name;
        private BigDecimal price;
        private Integer quantityInStock;

        public ShopSupply buildShopSupply() {
            return MAPPER.convert(this);
        }
    }

    @Getter(onMethod_ = @Override)
    @Builder
    @NoArgsConstructor(access = PRIVATE)
    @AllArgsConstructor(access = PRIVATE)
    @Schema(name = ".ShopSupply.Response")
    public static class Response implements ShopSupplyDef.Response {
        private UUID id;
        private String name;
        private BigDecimal price;
        private Integer quantityInStock;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;

        public static ShopSupplyDto.Response buildShopSupplyDtoResponse(ShopSupply customer) {
            return MAPPER.convert(customer);
        }
    }
}
