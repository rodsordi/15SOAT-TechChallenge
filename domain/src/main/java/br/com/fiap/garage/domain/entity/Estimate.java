package br.com.fiap.garage.domain.entity;

import br.com.fiap.commons.entity.AuditableEntity;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.math.BigDecimal;

import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@Getter
@NoArgsConstructor(access = PROTECTED)
@SuperBuilder
@EqualsAndHashCode(callSuper = false, exclude = "id")
@Entity
@Table(schema = "garage")
public class Estimate extends AuditableEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(comment = "Estimate id. Owner: postgres")
    private Long id;

    @Column(nullable = false, comment = "Estimate amount. Owner: self")
    private BigDecimal amount;
}
