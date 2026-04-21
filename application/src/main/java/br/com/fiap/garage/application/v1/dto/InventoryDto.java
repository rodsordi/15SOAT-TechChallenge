package br.com.fiap.garage.application.v1.dto;

import br.com.fiap.garage.application.v1.controller.InventoryController;
import br.com.fiap.garage.application.v1.def.InventoryDef;
import br.com.fiap.garage.application.v1.mapper.InventoryDtoMapper;
import br.com.fiap.garage.domain.entity.Inventory;
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
public final class InventoryDto {

    private static final InventoryDtoMapper MAPPER = getMapper(InventoryDtoMapper.class);

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

        public static InventoryDto.Representation buildInventoryDtoRepresentation(Inventory inventory) {
            var representation = MAPPER.convertToRepresentation(inventory);
            representation.add(linkTo(InventoryController.class)
                    .slash(representation.getId())
                    .withSelfRel());
            return representation;
        }
    }
}
