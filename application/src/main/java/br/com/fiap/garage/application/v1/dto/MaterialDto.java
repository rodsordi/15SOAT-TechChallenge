package br.com.fiap.garage.application.v1.dto;

import br.com.fiap.garage.application.v1.def.MaterialDef;
import br.com.fiap.garage.application.v1.mapper.MaterialDtoMapper;
import br.com.fiap.garage.domain.entity.Material;
import br.com.fiap.garage.domain.enums.MaterialType;
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
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@NoArgsConstructor(access = PRIVATE)
public final class MaterialDto {

    private static final MaterialDtoMapper MAPPER = getMapper(MaterialDtoMapper.class);

    @Getter(onMethod_ = @Override)
    @Builder
    @NoArgsConstructor(access = PRIVATE)
    @AllArgsConstructor(access = PRIVATE)
    @Schema(name = ".Material.Request")
    public static class Request implements MaterialDef.Request {
        private MaterialType type;
        private String name;
        private String description;
        private BigDecimal cost;

        public Material buildMaterial() {
            return MAPPER.convert(this);
        }
    }

    @Getter(onMethod_ = @Override)
    @Builder
    @NoArgsConstructor(access = PRIVATE)
    @AllArgsConstructor(access = PRIVATE)
    @Schema(name = ".Material.Response")
    public static class Response implements MaterialDef.Response {
        private UUID id;
        private MaterialType type;
        private String name;
        private String description;
        private BigDecimal cost;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;

        public static MaterialDto.Response buildMaterialDtoResponse(Material material) {
            return MAPPER.convert(material);
        }
    }

    @Getter(onMethod_ = @Override)
    @Builder
    @NoArgsConstructor(access = PRIVATE)
    @AllArgsConstructor(access = PRIVATE)
    @Schema(name = ".Material.Representation")
    public static class Representation implements MaterialDef.Representation {
        private UUID id;
        private MaterialType type;
        private String name;
        private BigDecimal cost;
        private LocalDateTime createdAt;
    }
}
