package br.com.fiap.garage.domain.entity;

import br.com.fiap.commons.entity.AuditableEntity;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
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
@MappedSuperclass
public class Inventory extends AuditableEntity implements Serializable {

    @Id
    @GeneratedValue
    @Column(comment = "Material id. Owner: postgres")
    private UUID id;

    @Column(nullable = false, length = 55, comment = "Inventory name. Owner: self")
    private String name;

    @Column(nullable = false, comment = "Inventory price. Owner: self")
    private BigDecimal price;

    @Column(nullable = false, comment = "Inventory quantity in stock. Owner: self")
    private Integer quantityInStock;
}
