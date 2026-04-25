package br.com.fiap.garage.domain.entity;

import br.com.fiap.commons.entity.AuditableEntity;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.util.UUID;

import static jakarta.persistence.CascadeType.ALL;
import static lombok.AccessLevel.PROTECTED;

@Getter
@NoArgsConstructor(access = PROTECTED)
@SuperBuilder
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(schema = "garage")
public class InventoryMaterial extends AuditableEntity implements Serializable {

    @Id
    @GeneratedValue
    @Column(comment = "Inventory id. Owner: db")
    private UUID id;

    @Column(nullable = false, comment = "Inventory quantity in stock. Owner: self")
    private Integer quantityInStock;

    @Column(nullable = false, comment = "Inventory reserved quantity. Owner: self")
    private Integer reservedQuantity;

    // Value Object
    @OneToOne(cascade = ALL, orphanRemoval = true)
    @JoinColumn(name = "id")
    @MapsId
    private Material material;
}
