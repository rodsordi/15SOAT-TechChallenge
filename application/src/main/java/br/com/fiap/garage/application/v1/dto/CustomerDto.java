package br.com.fiap.garage.application.v1.dto;

import br.com.fiap.garage.application.v1.controller.CustomerController;
import br.com.fiap.garage.application.v1.def.CustomerDef;
import br.com.fiap.garage.application.v1.mapper.CustomerDtoMapper;
import br.com.fiap.garage.domain.entity.Customer;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

import static lombok.AccessLevel.PRIVATE;
import static org.mapstruct.factory.Mappers.getMapper;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@NoArgsConstructor(access = PRIVATE)
public final class CustomerDto {

    public static final CustomerDtoMapper MAPPER = getMapper(CustomerDtoMapper.class);

    @Getter(onMethod_ = @Override)
    @Builder
    @NoArgsConstructor(access = PRIVATE)
    @AllArgsConstructor(access = PRIVATE)
    @Schema(name = ".Customer.Request")
    public static class Request implements CustomerDef.Request {
        private String username;
        private String password;
        private String name;
        private String email;
        private String document;
        @Singular(value = "vehicle", ignoreNullCollections = true)
        private Set<VehicleDto.Request> vehicles;

        public Customer buildCustomer() {
            return MAPPER.convert(this);
        }
    }

    @Getter(onMethod_ = @Override)
    @Builder
    @NoArgsConstructor(access = PRIVATE)
    @AllArgsConstructor(access = PRIVATE)
    @Schema(name = ".Customer.Response")
    public static class Response implements CustomerDef.Response {
        private UUID id;
        private String username;
        private String name;
        private String email;
        private String document;
        @Singular(value = "vehicle", ignoreNullCollections = true)
        private Set<VehicleDto.Response> vehicles;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;

        public static Response buildCustomerDtoResponse(Customer customer) {
            return MAPPER.convert(customer);
        }
    }

    @Getter(onMethod_ = @Override)
    @Builder
    @NoArgsConstructor(access = PRIVATE)
    @AllArgsConstructor(access = PRIVATE)
    @EqualsAndHashCode(callSuper = true, exclude = "id")
    @Schema(name = ".Customer.Representation")
    public static class Representation extends RepresentationModel<Representation> implements CustomerDef.Representation {
        private UUID id;
        private String username;
        private String name;
        private String email;
        private String document;
        @Singular(value = "vehicle", ignoreNullCollections = true)
        private Set<VehicleDto.Representation> vehicles;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;

        // Entity/Aggregate
        public static Representation buildCustomerDtoRepresentation(Customer customer) {
            var representation = MAPPER.convertToRepresentation(customer);
            representation.add(linkTo(CustomerController.class)
                    .slash(representation.getId())
                    .withSelfRel());
            return representation;
        }
    }

    @Getter(onMethod_ = @Override)
    @Builder
    @NoArgsConstructor(access = PRIVATE)
    @AllArgsConstructor(access = PRIVATE)
    @EqualsAndHashCode(callSuper = true, exclude = "id")
    @Schema(name = ".Customer.ResumedRepresentation")
    public static class ResumedRepresentation extends RepresentationModel<ResumedRepresentation> implements CustomerDef.ResumedRepresentation {
        private UUID id;
        private String username;
        private String name;
        private String email;
        private String document;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
    }
}
