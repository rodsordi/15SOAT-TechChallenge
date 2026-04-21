package br.com.fiap.garage.application.v1.dto;

import br.com.fiap.garage.application.v1.def.SparePartDef;
import br.com.fiap.garage.application.v1.mapper.SparePartDtoMapper;
import br.com.fiap.garage.domain.entity.SparePart;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import static lombok.AccessLevel.PRIVATE;
import static org.mapstruct.factory.Mappers.getMapper;

@NoArgsConstructor(access = PRIVATE)
public final class SparePartDto {

    private static final SparePartDtoMapper MAPPER = getMapper(SparePartDtoMapper.class);

    @Getter(onMethod_ = @Override)
    @Builder
    @NoArgsConstructor(access = PRIVATE)
    @AllArgsConstructor(access = PRIVATE)
    @Schema(name = ".SparePart.Request")
    public static class Request implements SparePartDef.Request {
        private String name;
        private BigDecimal price;
        private Integer quantityInStock;

        public SparePart buildSparePart() {
            return MAPPER.convert(this);
        }
    }

    @Getter(onMethod_ = @Override)
    @Builder
    @NoArgsConstructor(access = PRIVATE)
    @AllArgsConstructor(access = PRIVATE)
    @Schema(name = ".SparePart.Response")
    public static class Response implements SparePartDef.Response {
        private UUID id;
        private String name;
        private BigDecimal price;
        private Integer quantityInStock;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;

        public static SparePartDto.Response buildSparePartDtoResponse(SparePart owner) {
            return MAPPER.convert(owner);
        }
    }
}
