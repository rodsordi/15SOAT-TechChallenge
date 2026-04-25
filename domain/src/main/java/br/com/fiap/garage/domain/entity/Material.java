package br.com.fiap.garage.domain.entity;

import br.com.fiap.commons.entity.AuditableEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.math.BigDecimal;

import static jakarta.persistence.InheritanceType.JOINED;
import static lombok.AccessLevel.PROTECTED;

@Getter
@NoArgsConstructor(access = PROTECTED)
@SuperBuilder
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(schema = "garage")
@Inheritance(strategy = JOINED)
public class Material extends AuditableEntity implements Serializable {

    @Column(nullable = false, length = 55, comment = "Material name. Owner: self")
    private String name;

    @Column(comment = "Material description. Owner: self")
    private String description;

    @Column(nullable = false, comment = "Material amount. Owner: self")
    private BigDecimal amount;
}
