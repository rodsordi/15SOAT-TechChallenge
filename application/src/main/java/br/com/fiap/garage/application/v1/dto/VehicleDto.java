package br.com.fiap.garage.application.v1.dto;

import br.com.fiap.garage.application.v1.def.VehicleDef;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDateTime;
import java.util.UUID;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public final class VehicleDto {

    @Getter(onMethod_ = @Override)
    @Builder
    @NoArgsConstructor(access = PRIVATE)
    @AllArgsConstructor(access = PRIVATE)
    @Schema(name = ".Vehicle.Request")
    public static class Request implements VehicleDef.Request {
        private String make;
        private String model;
        private String licensePlate;
    }

    @Getter(onMethod_ = @Override)
    @Builder
    @NoArgsConstructor(access = PRIVATE)
    @AllArgsConstructor(access = PRIVATE)
    @Schema(name = ".Vehicle.Request")
    public static class Response implements VehicleDef.Response {
        private UUID id;
        private String make;
        private String model;
        private String licensePlate;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
    }

    @Getter(onMethod_ = @Override)
    @Builder
    @NoArgsConstructor(access = PRIVATE)
    @AllArgsConstructor(access = PRIVATE)
    @EqualsAndHashCode(callSuper = true)
    @Schema(name = ".Vehicle.Representation")
    public static class Representation extends RepresentationModel<Representation> implements VehicleDef.Representation {
        private UUID id;
        private String make;
        private String model;
        private String licensePlate;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
    }
}
