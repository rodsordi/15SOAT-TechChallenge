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
    @Column(nullable = false, comment = "Register created at. Owner: postgres")
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(comment = "Register updated at. Owner: postgres")
    private LocalDateTime updatedAt;
}
```

## Example: Expected Output Style

```java
package br.com.fiap.chargeback.domain.entity.credit.assertions;

import br.com.fiap.chargeback.domain.entity.credit.CreditAuthorizationSingleMessage;
import br.com.fiap.chargeback.domain.entity.credit.CreditClearingSingleMessage;
import br.com.fiap.chargeback.domain.entity.credit.CreditTransaction;
import br.com.fiap.chargeback.domain.entity.credit.factory.CreditTransactionFactory;
import br.com.fiap.commons.util.DateUtil;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

import static br.com.fiap.chargeback.domain.entity.credit.assertions.AuthorizationAssertions.assertThatAuthorization;
import static br.com.fiap.commons.util.DateUtil.newDateTime;
import static br.com.fiap.commons.util.ReflectionUtil.assertThatObject;
import static lombok.AccessLevel.PRIVATE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.spy;

@RequiredArgsConstructor(access = PRIVATE)
public final class CreditTransactionAssertions {

    private final CreditTransaction actual;

    public static TransactionAssertions assertThat_CreditTransaction(CreditTransaction actual) {
        assertThat(actual).isNotNull();
        return new TransactionAssertions(spy(actual));
    }

    /**
     * @see CreditTransactionFactory
     * .withAllFields()
     */
    public void isEqualTo_Transaction() {
        // Inheritance (Transaction)
        assertThat(actual.getTransactionId())
                .hasToString("9d1b9b7c-bd7c-4f5f-a747-0b1f63aac409");

        // Self
        assertThat(actual.getCorrelationId())
                .isEqualTo("correlation-id-xpto-123");
        assertThat(actual.getArn())
                .isEqualTo("arn123xpto");
        assertThat(actual.getAmount())
                .isEqualTo(new BigDecimal("50.00"));
        assertThat(actual.getDateTime())
                .isEqualTo(newDateTime("31/12/2025 23:59:59"));
        assertThat(actual.getIsSingleMessage())
                .isFalse();
        assertThat(actual.getExternalTransactionId())
                .isEqualTo("abc123");
        assertThat(actual.getAuthorizationSummaryCount())
                .isEqualTo("1");
        assertThat(actual.getMessage())
                .isEqualTo("Message-123");

        // Composition
        assertThatAuthorization(actual.getAuthorizations().stream().findFirst().orElseThrow())
                .isEqualTo_Authorization();
        assertThat(actual.getAuthorizationSingleMessage())
                .isNotNull();

        // Inheritance (AuditableEntity)
        assertThat(actual.getCreatedAt())
                .isEqualTo(newDateTime("30/12/2024 23:59:59"));
        assertThat(actual.getUpdatedAt())
                .isEqualTo(newDateTime("31/12/2024 23:59:59"));

        // And
        assertThatObject(actual)
                .hasAllGetMethodsVerifiedOnceAtLeast();
    }
}
```

## Source Code: The Objects

```java
	

```