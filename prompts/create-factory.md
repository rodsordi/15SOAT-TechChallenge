# Builder

## Role

You are an expert Java developer specializing in automated testing, test data generation, and the Builder design pattern. 

## Task

Your task is to create a test data factory class. You must use the provided source code to identify all necessary fields and closely mimic the structure, style, and conventions of the provided example.

## External parent entity lib

- If needed, here you are the base class for auditable entities

```java
package br.com.fiap.commons.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.time.LocalDateTime;

import static lombok.AccessLevel.PROTECTED;

@Getter
@NoArgsConstructor(access = PROTECTED)
@SuperBuilder
@EqualsAndHashCode
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class AuditableEntity implements Serializable {

    @CreatedDate
    @Column(nullable = false, comment = "Register created at. Owner: db")
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(comment = "Register updated at. Owner: db")
    private LocalDateTime updatedAt;
}
```

## Example: Expected Output Style

```java
package br.com.fiap.garage.domain.entity.factory;

import br.com.fiap.garage.domain.entity.WorkOrder;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

import static br.com.fiap.commons.util.DateUtil.newDateTime;
import static br.com.fiap.commons.util.ReflectionUtil.assertThatObject;
import static br.com.fiap.garage.domain.entity.factory.CustomerFactory.create_Customer;
import static br.com.fiap.garage.domain.entity.factory.EmployeeFactory.create_Employee;
import static br.com.fiap.garage.domain.entity.factory.EstimatedServiceFactory.create_EstimatedService;
import static br.com.fiap.garage.domain.entity.factory.VehicleFactory.create_Vehicle;
import static br.com.fiap.garage.domain.enums.WorkOrderStatus.RECEIVED;
import static java.util.UUID.fromString;
import static lombok.AccessLevel.PRIVATE;

@RequiredArgsConstructor(access = PRIVATE)
public final class WorkOrderFactory {

    private final WorkOrder.WorkOrderBuilder<?, ?> builder;

    public static WorkOrderFactory create_WorkOrder() {
        return new WorkOrderFactory(WorkOrder.builder());
    }

    public WorkOrder withAllFields() {
        var result = builder
                // Self
                .id(fromString("e48ad20c-69dd-4382-b567-0e02b2c3d480"))
                .status(RECEIVED)
                .totalAmount(new BigDecimal("999.99"))
                // Composition
                .vehicle(create_Vehicle().withAllFields())
                .employee(create_Employee().withAllFields())
                .estimatedService(create_EstimatedService().withAllFields())
                // Inheritance (AuditableEntity)
                .createdAt(newDateTime("13/12/2026 23:59:59"))
                .updatedAt(newDateTime("14/12/2026 23:59:59"))
                .build();

        // And
        assertThatObject(result)
                .hasNoEmptyFields();
        return result;
    }

    public WorkOrder withAllFieldsExceptDB() {
        withAllFields();
        return builder
                .id(null)
                .vehicle(create_Vehicle().withAllFieldsExceptDB())
                .employee(create_Employee().withAllFieldsExceptDB())
                .clearEstimatedServices()
                .estimatedService(create_EstimatedService().withAllFieldsExceptDB())
                .createdAt(null)
                .updatedAt(null)
                .build();
    }

    public WorkOrder valid() {
        return builder
                .status(RECEIVED)
                .estimatedService(create_EstimatedService().valid())
                .build();
    }

    public WorkOrder initiatedEmpty() {
        return builder
                .estimatedService(create_EstimatedService().initiatedEmpty())
                .build();
    }
}
```

```java
package br.com.fiap.garage.application.v1.dto.factory;

import br.com.fiap.garage.application.v1.dto.MaterialDto;
import br.com.fiap.garage.domain.enums.MaterialType;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

import static br.com.fiap.commons.util.DateUtil.newDateTime;
import static br.com.fiap.commons.util.ReflectionUtil.assertThatObject;
import static br.com.fiap.garage.domain.enums.MaterialType.SHOP_SUPPLY;
import static java.util.UUID.fromString;
import static lombok.AccessLevel.PRIVATE;

@RequiredArgsConstructor(access = PRIVATE)
public final class MaterialDtoFactory {

    public static Request create_MaterialDto_Request() {
        return new Request(MaterialDto.Request.builder());
    }

    @RequiredArgsConstructor(access = PRIVATE)
    public static final class Request {

        private final MaterialDto.Request.RequestBuilder builder;

        public MaterialDto.Request withAllFields() {
            var result = builder
                    .type(SHOP_SUPPLY)
                    .name("Synthetic Engine Oil")
                    .description("Oil 5W-30")
                    .cost(new BigDecimal("85.50"))
                    .build();
            // And
            assertThatObject(result)
                    .hasNoEmptyFields();
            return result;
        }

        public MaterialDto.Request valid() {
            return builder
                    .type(SHOP_SUPPLY)
                    .name("Synthetic Engine Oil")
                    .description("Oil 5W-30")
                    .cost(new BigDecimal("85.50"))
                    .build();
        }

        public MaterialDto.Request initiatedEmpty() {
            return builder.build();
        }
    }

    public static Response create_MaterialDto_Response() {
        return new Response(MaterialDto.Response.builder());
    }

    @RequiredArgsConstructor(access = PRIVATE)
    public static final class Response {

        private final MaterialDto.Response.ResponseBuilder builder;

        public MaterialDto.Response withAllFields() {
            var result = builder
                    .id(fromString("4f5f9b7c-bd7c-4f5f-a747-0b1f63aac409"))
                    .type(MaterialType.values()[0])
                    .name("Synthetic Engine Oil")
                    .description("High-performance 5W-30 synthetic oil")
                    .cost(new BigDecimal("45.50"))
                    .createdAt(newDateTime("13/12/2026 23:59:59"))
                    .updatedAt(newDateTime("14/12/2026 23:59:59"))
                    .build();
            // And
            assertThatObject(result)
                    .hasNoEmptyFields();
            return result;
        }

        public MaterialDto.Response valid() {
            return builder
                    .id(fromString("123e4567-e89b-12d3-a456-426614174000"))
                    .name("Standard Brake Pad")
                    .cost(new BigDecimal("120.00"))
                    .build();
        }

        public MaterialDto.Response initiatedEmpty() {
            return builder.build();
        }
    }

    public static Representation create_MaterialDto_Representation() {
        return new Representation(MaterialDto.Representation.builder());
    }

    @RequiredArgsConstructor(access = PRIVATE)
    public static final class Representation {

        private final MaterialDto.Representation.RepresentationBuilder builder;

        public MaterialDto.Representation withAllFields() {
            var result = builder
                    .id(fromString("4f5f9b7c-bd7c-4f5f-a747-0b1f63aac409"))
                    .type(MaterialType.values()[0])
                    .name("Synthetic Engine Oil")
                    .cost(new BigDecimal("45.50"))
                    .createdAt(newDateTime("13/12/2026 23:59:59"))
                    .updatedAt(newDateTime("14/12/2026 23:59:59"))
                    .build();
            // And
            assertThatObject(result)
                    .hasNoEmptyFields();
            return result;
        }

        public MaterialDto.Representation valid() {
            return builder
                    .id(fromString("123e4567-e89b-12d3-a456-426614174000"))
                    .name("Standard Brake Pad")
                    .cost(new BigDecimal("120.00"))
                    .build();
        }

        public MaterialDto.Representation initiatedEmpty() {
            return builder.build();
        }
    }
}
```
## Source Code: The Objects

```java



```