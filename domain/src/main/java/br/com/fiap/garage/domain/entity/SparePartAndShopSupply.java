package br.com.fiap.garage.domain.entity;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.util.UUID;

import static lombok.AccessLevel.PROTECTED;

@Getter
@NoArgsConstructor(access = PROTECTED)
@SuperBuilder
@EqualsAndHashCode(callSuper = false, exclude = "id")
@Entity
@Table(schema = "garage")
public class SparePartAndShopSupply {

    @Id
    @GeneratedValue
    @Column(comment = "SparePartAndShopSupply id. Owner: postgres")
    private UUID id;

    @Column(nullable = false, length = 55, comment = "SparePartAndShopSupply name. Owner: self")
    private String name;

    @Column(nullable = false, comment = "SparePartAndShopSupply price. Owner: self")
    private BigDecimal price;

    @Column(nullable = false, comment = "SparePartAndShopSupply inventory. Owner: self")
    private Integer inventory;
}
