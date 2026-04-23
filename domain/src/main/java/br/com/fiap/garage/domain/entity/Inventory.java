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
@EqualsAndHashCode(callSuper = false, exclude = "id")
@Entity
@Table(schema = "garage")
@Inheritance(strategy = JOINED)
public class Inventory extends AuditableEntity implements Serializable {

    @Id
    @GeneratedValue
    @Column(comment = "Material id. Customer: postgres")
    private UUID id;

    @Column(nullable = false, length = 55, comment = "Inventory name. Customer: self")
    private String name;

    @Column(nullable = false, comment = "Inventory price. Customer: self")
    private BigDecimal price;

    @Column(nullable = false, comment = "Inventory quantity in stock. Customer: self")
    private Integer quantityInStock;
}
