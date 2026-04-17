package br.com.fiap.mecanica.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.util.UUID;

import static lombok.AccessLevel.PROTECTED;

@Getter
@NoArgsConstructor(access = PROTECTED)
@SuperBuilder
@EqualsAndHashCode(callSuper = false, exclude = "id")
@MappedSuperclass
public class Employee extends AuditableEntity implements Serializable {

    @Id
    @GeneratedValue
    @Column(comment = "Employee id. Owner: postgres")
    private UUID id;

    @Column(nullable = false, length = 55, comment = "Employee name. Owner: self")
    private String name;
}
