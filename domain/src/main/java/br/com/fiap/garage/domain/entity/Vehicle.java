package br.com.fiap.garage.domain.entity;

import br.com.fiap.commons.entity.AuditableEntity;
import br.com.fiap.commons.validation.LicensePlate;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.time.Year;
import java.util.UUID;

import static lombok.AccessLevel.PROTECTED;

@Getter
@NoArgsConstructor(access = PROTECTED)
@SuperBuilder
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(schema = "garage")
public class Vehicle extends AuditableEntity implements Serializable {

    @Id
    @GeneratedValue
    @Column(comment = "Vehicle id. Owner: db")
    private UUID id;

    @Column(nullable = false, length = 55, comment = "Vehicle make. Owner: self")
    private String make;

    @Column(nullable = false, length = 55, comment = "Vehicle model. Owner: self")
    private String model;

    @LicensePlate
    @Column(nullable = false, length = 10, comment = "Vehicle license plate. Owner: self")
    private String licensePlate;

    @Column(nullable = false, length = 10, comment = "Vehicle manufacture year. Owner: self")
    private Year manufactureYear;
}
