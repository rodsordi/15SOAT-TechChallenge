# Assertions

## Role

You are an expert Java developer specializing in automated testing, test data generation. 

## Task

Your task is to create a test data assertion class. You must use the provided source code to identify all assertions of all fields and closely mimic the structure, style, and conventions of the provided example.

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
package br.com.fiap.garage.domain.entity.assertions;

import br.com.fiap.garage.domain.entity.WorkOrder;
import lombok.RequiredArgsConstructor;

import static br.com.fiap.commons.util.DateUtil.newDateTime;
import static br.com.fiap.commons.util.ReflectionUtil.assertThatObject;
import static br.com.fiap.garage.domain.entity.assertions.EmployeeAssertions.assertThat_Employee;
import static br.com.fiap.garage.domain.entity.assertions.EstimatedServiceAssertions.assertThat_EstimatedService;
import static br.com.fiap.garage.domain.entity.assertions.VehicleAssertions.assertThat_Vehicle;
import static br.com.fiap.garage.domain.enums.WorkOrderStatus.RECEIVED;
import static lombok.AccessLevel.PRIVATE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.spy;

@RequiredArgsConstructor(access = PRIVATE)
public final class WorkOrderAssertions {

    private final WorkOrder actual;

    public static WorkOrderAssertions assertThat_WorkOrder(WorkOrder actual) {
        assertThat(actual).isNotNull();
        return new WorkOrderAssertions(spy(actual));
    }

    /**
     * @see br.com.fiap.garage.domain.entity.factory.WorkOrderFactory
     * .withAllFields()
     */
    public void isEqualTo_WorkOrder() {
        // Self
        assertThat(actual.getId())
                .hasToString("fdbbe77f-1386-4b03-9612-2de63ad4daed");
        assertThat(actual.getStatus())
                .isEqualTo(RECEIVED);
        assertThat(actual.getTotalAmount())
                .isNull();

        // Composition
        assertThat_Vehicle(actual.getVehicle())
                .isEqualTo_Vehicle();
        assertThat_Employee(actual.getEmployee())
                .isEqualTo_Employee();
        assertThat_EstimatedService(actual.getEstimatedServices().stream().findFirst().orElseThrow())
                .isEqualTo_EstimatedService();

        // Inheritance (AuditableEntity)
        assertThat(actual.getCreatedAt())
                .isEqualTo(newDateTime("13/12/2026 23:59:59"));
        assertThat(actual.getUpdatedAt())
                .isEqualTo(newDateTime("14/12/2026 23:59:59"));

        // And
        assertThatObject(actual)
                .hasAllGetMethodsVerifiedOnceAtLeast();
    }

    /**
     * @see br.com.fiap.garage.application.v1.dto.factory.WorkOrderDtoFactory
     * .withAllFields()
     */
    public void wasConvertedFrom_WorkOrderDto_Request() {
        // Self
        assertThat(actual.getId())
                .isNull();
        assertThat(actual.getStatus())
                .isEqualTo(RECEIVED);
        assertThat(actual.getTotalAmount())
                .isNull();

        // Composition
        assertThat_Vehicle(actual.getVehicle())
                .wasConvertedFrom_VehicleDto_Request();
        assertThat_Employee(actual.getEmployee())
                .wasConvertedFrom_EmployeeDto_Request();
        assertThat_EstimatedService(actual.getEstimatedServices().stream().findFirst().orElseThrow())
                .wasConvertedFrom_EstimatedServiceDto_Request();

        // Inheritance (AuditableEntity)
        assertThat(actual.getCreatedAt())
                .isNull();
        assertThat(actual.getUpdatedAt())
                .isNull();

        // And
        assertThatObject(actual)
                .hasAllGetMethodsVerifiedOnceAtLeast();
    }
}
```

```java
package br.com.fiap.garage.application.v1.dto.assertions;

import br.com.fiap.garage.application.v1.dto.VehicleDto;
import lombok.RequiredArgsConstructor;

import java.time.Year;

import static br.com.fiap.commons.util.DateUtil.newDateTime;
import static br.com.fiap.commons.util.ReflectionUtil.assertThatObject;
import static lombok.AccessLevel.PRIVATE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.spy;

@RequiredArgsConstructor(access = PRIVATE)
public final class VehicleDtoAssertions {

    public static Response assertThat_VehicleDto_Response(VehicleDto.Response actual) {
        assertThat(actual).isNotNull();
        return new Response(spy(actual));
    }

    @RequiredArgsConstructor(access = PRIVATE)
    public static final class Response {

        private final VehicleDto.Response actual;

        /**
         * @see br.com.fiap.garage.domain.entity.factory.VehicleFactory
         * .withAllFields()
         */
        public void wasConvertedFrom_Vehicle() {
            // Self
            assertThat(actual.getId())
                    .hasToString("9f8792ea-cf8f-43d1-824f-9f7bc433e404");
            assertThat(actual.getMake())
                    .isEqualTo("Toyota");
            assertThat(actual.getModel())
                    .isEqualTo("Corolla");
            assertThat(actual.getLicensePlate())
                    .isEqualTo("ABC-1234");
            assertThat(actual.getManufactureYear())
                    .isEqualTo(Year.parse("2024"));

            //Inheritance (AuditableTable)
            assertThat(actual.getCreatedAt())
                    .isEqualTo(newDateTime("21/04/2026 10:00:00"));
            assertThat(actual.getUpdatedAt())
                    .isEqualTo(newDateTime("21/04/2026 15:30:00"));

            // And
            assertThatObject(actual)
                    .hasAllGetMethodsVerifiedOnceAtLeast();
        }
    }

    public static Representation assertThat_VehicleDto_Representation(VehicleDto.Representation actual) {
        assertThat(actual).isNotNull();
        return new Representation(spy(actual));
    }

    @RequiredArgsConstructor(access = PRIVATE)
    public static final class Representation {

        private final VehicleDto.Representation actual;

        /**
         * @see br.com.fiap.garage.domain.entity.factory.VehicleFactory
         * .withAllFields()
         */
        public void wasConvertedFrom_Vehicle() {
            // Self
            assertThat(actual.getId())
                    .hasToString("9f8792ea-cf8f-43d1-824f-9f7bc433e404");
            assertThat(actual.getMake())
                    .isEqualTo("Toyota");
            assertThat(actual.getModel())
                    .isEqualTo("Corolla");
            assertThat(actual.getLicensePlate())
                    .isEqualTo("ABC-1234");
            assertThat(actual.getManufactureYear())
                    .isEqualTo(Year.parse("2024"));

            //Inheritance (AuditableTable)
            assertThat(actual.getCreatedAt())
                    .isEqualTo(newDateTime("21/04/2026 10:00:00"));
            assertThat(actual.getUpdatedAt())
                    .isEqualTo(newDateTime("21/04/2026 15:30:00"));
            assertThat(actual.getLinks())
                    .isNullOrEmpty();

            // And
            assertThatObject(actual)
                    .hasAllGetMethodsVerifiedOnceAtLeast();
        }
    }
}
```

## Source Code: The Objects

```java


```