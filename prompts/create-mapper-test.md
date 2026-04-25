# Builder

## Role

You are an expert Java developer specializing in automated testing, test data generation, and the MapStruct library. 

## Task

Your task is to create a test data convertion class. You must use the provided source code to identify all necessary fields and closely mimic the structure, style, and conventions of the provided example.

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

```

```java

```
## Source Code: The Objects

```java


```