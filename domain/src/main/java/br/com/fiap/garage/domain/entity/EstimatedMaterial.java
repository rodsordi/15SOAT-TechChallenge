package br.com.fiap.garage.domain.entity;

import br.com.fiap.commons.entity.AuditableEntity;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

import static jakarta.persistence.InheritanceType.JOINED;
import static lombok.AccessLevel.PROTECTED;

@Getter
@NoArgsConstructor(access = PROTECTED)
@SuperBuilder
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(schema = "garage")
@Inheritance(strategy = JOINED)
public class EstimatedMaterial extends AuditableEntity implements Serializable {

    @Id
    @GeneratedValue
    @Column(comment = "Estimated Material id. Owner: db")
    private UUID id;

    @Column(nullable = false, length = 55, comment = "Estimated Material name. Owner: self")
    private String name;

    @Column(comment = "Estimated Material description. Owner: self")
    private String description;

    @Column(nullable = false, comment = "Estimated Material amount. Owner: self")
    private BigDecimal amount;
}
