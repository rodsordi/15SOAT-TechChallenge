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
package br.com.fiap.chargeback.domain.entity.credit.factory;

import br.com.fiap.chargeback.domain.entity.credit.CreditAuthorization;
import br.com.fiap.chargeback.domain.entity.credit.CreditTransaction;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import static br.com.fiap.chargeback.domain.entity.credit.factory.CreditAuthorizationFactory.create_CreditAuthorization;
import static br.com.fiap.chargeback.domain.entity.credit.factory.CreditAuthorizationSingleMessageFactory.create_CreditAuthorizationSingleMessage;
import static br.com.fiap.commons.util.DateUtil.newDateTime;
import static br.com.fiap.commons.util.ReflectionUtil.assertThatObject;
import static java.util.UUID.fromString;
import static lombok.AccessLevel.PRIVATE;

@RequiredArgsConstructor(access = PRIVATE)
public final class CreditTransactionFactory {

    private final CreditTransaction.CreditTransactionBuilder<?, ?> builder;

    public static CreditTransactionFactory create_CreditTransaction() {
        return new CreditTransactionFactory(CreditTransaction.builder());
    }

    public CreditTransaction withAllFields() {
        var result = builder
                // Inheritance (Transaction)
                .transactionId(fromString("9d1b9b7c-bd7c-4f5f-a747-0b1f63aac409"))
                // Self
                .correlationId("correlation-id-xpto-123")
                .arn("arn123xpto")
                .amount(new BigDecimal("50.00"))
                .dateTime(newDateTime("31/12/2025 23:59:59"))
                .isSingleMessage(false)
                .externalTransactionId("abc123")
                .authorizationSummaryCount("1")
                .message("Message-123")
                // Composition
                .authorizations(new HashSet<>(Set.of(create_CreditAuthorization().withAllFields())))
                .authorizationSingleMessage(create_CreditAuthorizationSingleMessage().withAllFields())
                // Inheritance (AuditableEntity)
                .createdAt(newDateTime("30/12/2024 23:59:59"))
                .updatedAt(newDateTime("31/12/2024 23:59:59"))
                .build();
        // And
        assertThatObject(result)
                .hasNoEmptyFields();
        return result;
    }

    public CreditTransaction withAllFieldsExceptDB() {
        withAllFields();
        return builder
                .transactionId(null)
                .authorizations(new HashSet<>(Set.of(create_CreditAuthorization().withAllFieldsExceptDB())))
                .authorizationSingleMessage(create_CreditAuthorizationSingleMessage().withAllFieldsExceptId())
                .build();
    }

    public CreditTransaction valid() {
        return builder
                .correlationId("correlation-id-xpto-321")
                .arn("arn123xpto")
                .amount(new BigDecimal("50.00"))
                .dateTime(newDateTime("31/12/2025 23:59:59"))
                .isSingleMessage(false)
                .authorizations(new HashSet<>(Set.of(create_CreditAuthorization().withAllFields())))
                .build();
    }

    public CreditTransaction initiatedEmpty() {
        return builder
                .authorizations(new HashSet<>(Set.of(CreditAuthorization.builder().build())))
                .authorizationSingleMessage(create_CreditAuthorizationSingleMessage().initiatedEmpty())
                .build();
    }
}
```

```java
package br.com.fiap.garage.application.v1.dto.factory;

import br.com.fiap.garage.application.v1.dto.CustomerDto;
import lombok.RequiredArgsConstructor;

import static br.com.fiap.commons.util.ReflectionUtil.assertThatObject;
import static lombok.AccessLevel.PRIVATE;

@RequiredArgsConstructor(access = PRIVATE)
public final class CustomerDtoFactory {

    public static Request create_CustomerDto_Request() {
        return new Request(CustomerDto.Request.builder());
    }

    @RequiredArgsConstructor(access = PRIVATE)
    public static final class Request {

        private final CustomerDto.Request.RequestBuilder builder;

        public CustomerDto.Request withAllFields() {
            var result = builder
                    // Self
                    .username("john.doe@example.com")
                    .name("John Doe")
                    .password("1234asdl")
                    .email("john.doe@example.com")
                    .document("00.123.456/0001-90")
                    .build();

            // And
            assertThatObject(result)
                    .hasNoEmptyFields();
            return result;
        }

        public CustomerDto.Request valid() {
            return builder
                    .name("Jane Doe")
                    .email("jane.doe@example.com")
                    .build();
        }

        public CustomerDto.Request initiatedEmpty() {
            return builder.build();
        }
    }
}
```
## Source Code: The Objects

```java


```