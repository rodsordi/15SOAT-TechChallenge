package br.com.fiap.garage.application.v1.dto;

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

import static lombok.AccessLevel.PRIVATE;
import static lombok.AccessLevel.PROTECTED;

@NoArgsConstructor(access = PRIVATE)
public final class ShopSupplyDto {

    @Id
    @GeneratedValue
    @Column(comment = "Id do insumo. Owner: postgres")
    private UUID id;

    @Column(nullable = false, length = 55, comment = "ShopSupplies name. Owner: self")
    private String name;

    @Column(nullable = false, comment = "ShopSupplies price. Owner: self")
    private BigDecimal price;
}
