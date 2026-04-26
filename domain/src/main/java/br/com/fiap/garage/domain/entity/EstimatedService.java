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
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

import static jakarta.persistence.CascadeType.*;
import static lombok.AccessLevel.PROTECTED;

@Getter
@NoArgsConstructor(access = PROTECTED)
@SuperBuilder
@EqualsAndHashCode(callSuper = true, exclude = "id")
@Entity
@Table(schema = "garage")
public class EstimatedService extends AuditableEntity implements Serializable {

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

    @Column(comment = "Estimated Service finished at. Owner: self")
    private LocalDateTime finishedAt;

    // Aggregate
    @ManyToOne(cascade = {MERGE, PERSIST})
    @JoinColumn(updatable = false, comment = "Service id. Owner: db")
    private Service service;

    // Value Object
    @Singular(value = "estimatedMaterial", ignoreNullCollections = true)
    @OneToMany(cascade = ALL, orphanRemoval = true)
    @JoinColumn(name = "estimated_service_id", updatable = false, nullable = false)
    @OrderBy("createdAt desc")
    private Set<EstimatedMaterial> estimatedMaterials;
}
