package br.com.fiap.garage.domain.entity;

import br.com.fiap.commons.entity.AuditableEntity;
import jakarta.persistence.*;
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
@Entity
@Table(schema = "garage")
public class Vehicle extends AuditableEntity implements Serializable {

    @Id
    @GeneratedValue
    @Column(comment = "Vehicle id. Customer: postgres")
    private UUID id;

    @Column(nullable = false, length = 55, comment = "Vehicle make. Customer: self")
    private String make;

    @Column(nullable = false, length = 55, comment = "Vehicle model. Customer: self")
    private String model;

    @Column(nullable = false, length = 10, comment = "Vehicle license plate. Customer: self")
    private String licensePlate;
}
