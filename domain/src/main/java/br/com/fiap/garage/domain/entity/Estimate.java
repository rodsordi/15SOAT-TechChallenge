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

import static lombok.AccessLevel.PROTECTED;

@Getter
@NoArgsConstructor(access = PROTECTED)
@SuperBuilder
@EqualsAndHashCode(callSuper = false, exclude = "id")
@Entity
@Table(schema = "garage")
public class Estimate extends AuditableEntity implements Serializable {

    @Id
    @GeneratedValue
    @Column(comment = "Estimate id. Owner: postgres")
    private UUID id;

    @Column(nullable = false, comment = "Estimate amount. Owner: self")
    private BigDecimal amount;
}
