package br.com.fiap.garage.application.v1.dto;

import br.com.fiap.garage.application.v1.controller.InventoryController;
import br.com.fiap.garage.application.v1.def.InventoryMaterialDef;
import br.com.fiap.garage.application.v1.mapper.InventoryDtoMapper;
import br.com.fiap.garage.domain.entity.InventoryMaterial;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import static lombok.AccessLevel.PRIVATE;
import static org.mapstruct.factory.Mappers.getMapper;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@NoArgsConstructor(access = PRIVATE)
public final class InventoryMaterialDto {

    private static final InventoryDtoMapper MAPPER = getMapper(InventoryDtoMapper.class);

    @Getter(onMethod_ = @Override)
    @Builder
    @NoArgsConstructor(access = PRIVATE)
    @AllArgsConstructor(access = PRIVATE)
    @EqualsAndHashCode(callSuper = true)
    @Schema(name = ".Inventory.Representation")
    public static class Representation extends RepresentationModel<Representation> implements InventoryMaterialDef.Representation {
        private UUID id;
        private String name;
        private BigDecimal amount;
        private Integer quantityInStock;
        private Integer reservedQuantity;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;

        public static InventoryMaterialDto.Representation buildInventoryDtoRepresentation(InventoryMaterial inventoryMaterial) {
            var representation = MAPPER.convertToRepresentation(inventoryMaterial);
            representation.add(linkTo(InventoryController.class)
                    .slash(representation.getId())
                    .withSelfRel());
            return representation;
        }
    }
}
