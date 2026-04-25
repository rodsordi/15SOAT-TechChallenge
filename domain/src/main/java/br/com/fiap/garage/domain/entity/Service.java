package br.com.fiap.garage.domain.entity;

import br.com.fiap.commons.entity.AuditableEntity;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Singular;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Set;
import java.util.UUID;

import static jakarta.persistence.FetchType.EAGER;
import static lombok.AccessLevel.PROTECTED;

@Getter
@NoArgsConstructor(access = PROTECTED)
@SuperBuilder
@EqualsAndHashCode(callSuper = false, exclude = "id")
@Entity
@Table(schema = "garage")
public class Service extends AuditableEntity implements Serializable {

    @Id
    @GeneratedValue
    @Column(comment = "Service id. Owner: db")
    private UUID id;

    @Column(nullable = false, comment = "Service name. Owner: self")
    private String name;

    @Column(comment = "Service description. Owner: self")
    private String description;

    @Column(nullable = false, comment = "Service amount. Owner: self")
    private BigDecimal amount;

    @Singular(value = "inventoryMaterial", ignoreNullCollections = true)
    @ManyToMany(fetch = EAGER)
    @JoinTable(schema = "garage", name = "service_inventory_material",
            joinColumns = @JoinColumn(name = "service_id"),
            inverseJoinColumns = @JoinColumn(name = "inventory_material_id"))
    @OrderBy("createdAt desc")
    private Set<InventoryMaterial> inventoryMaterials;
}
