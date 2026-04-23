package br.com.fiap.garage.application.v1.dto;

import br.com.fiap.garage.application.v1.controller.OwnerController;
import br.com.fiap.garage.application.v1.def.OwnerDef;
import br.com.fiap.garage.application.v1.mapper.OwnerDtoMapper;
import br.com.fiap.garage.domain.entity.Owner;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDateTime;
import java.util.UUID;

import static lombok.AccessLevel.PRIVATE;
import static org.mapstruct.factory.Mappers.getMapper;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@NoArgsConstructor(access = PRIVATE)
public final class OwnerDto {

    public static final OwnerDtoMapper MAPPER = getMapper(OwnerDtoMapper.class);

    @Getter(onMethod_ = @Override)
    @Builder
    @NoArgsConstructor(access = PRIVATE)
    @AllArgsConstructor(access = PRIVATE)
    @Schema(name = ".Owner.Request")
    public static class Request implements OwnerDef.Request {
        private String username;
        private String password;
        private String name;
        private String email;

        public Owner buildOwner() {
            return MAPPER.convert(this);
        }
    }

    @Getter(onMethod_ = @Override)
    @Builder
    @NoArgsConstructor(access = PRIVATE)
    @AllArgsConstructor(access = PRIVATE)
    @Schema(name = ".Owner.Response")
    public static class Response implements OwnerDef.Response {
        private UUID id;
        private String username;
        private String name;
        private String email;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;

        public static Response buildOwnerDtoResponse(Owner owner) {
            return MAPPER.convert(owner);
        }
    }

    @Getter(onMethod_ = @Override)
    @Builder
    @NoArgsConstructor(access = PRIVATE)
    @AllArgsConstructor(access = PRIVATE)
    @EqualsAndHashCode(callSuper = true)
    @Schema(name = ".Owner.Representation")
    public static class Representation extends RepresentationModel<Representation> implements OwnerDef.Representation {
        private UUID id;
        private String username;
        private String name;
        private String email;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;

        public static Representation buildOwnerDtoRepresentation(Owner owner) {
            var representation = MAPPER.convertToRepresentation(owner);
            representation.add(linkTo(OwnerController.class)
                    .slash(representation.getId())
                    .withSelfRel());
            return representation;
        }
    }
}
