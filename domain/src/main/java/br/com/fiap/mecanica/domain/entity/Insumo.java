package br.com.fiap.mecanica.domain.entity;

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
public abstract class Insumo extends AuditableEntity implements Serializable {

    @Id
    @GeneratedValue
    @Column(comment = "Id do insumo. Dono: postgres")
    private UUID id;

    @Column(nullable = false, length = 55, comment = "Nome do insumo. Dono: eu-mesmo")
    private String nome;

    @Column(nullable = false, comment = "Valor do insumo. Dono: eu-mesmo")
    private BigDecimal valor;
}
