package br.com.fiap.garage.application.v1.dto;

import br.com.fiap.garage.application.v1.def.EmployeeDef;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDateTime;
import java.util.UUID;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public final class EmployeeDto {

    @Getter(onMethod_ = @Override)
    @Builder
    @NoArgsConstructor(access = PRIVATE)
    @AllArgsConstructor(access = PRIVATE)
    @Schema(name = ".Employee.Request")
    public static class Request implements EmployeeDef.Request {
        private String name;
    }

    @Getter(onMethod_ = @Override)
    @Builder
    @NoArgsConstructor(access = PRIVATE)
    @AllArgsConstructor(access = PRIVATE)
    @Schema(name = ".Employee.Request")
    public static class Response implements EmployeeDef.Response {
        private UUID id;
        private String name;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
    }

    @Getter(onMethod_ = @Override)
    @Builder
    @NoArgsConstructor(access = PRIVATE)
    @AllArgsConstructor(access = PRIVATE)
    @EqualsAndHashCode(callSuper = true)
    @Schema(name = ".Employee.Representation")
    public static class Representation extends RepresentationModel<Representation> implements EmployeeDef.Representation {
        private UUID id;
        private String name;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
    }
}
