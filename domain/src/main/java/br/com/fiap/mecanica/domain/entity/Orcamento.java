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
public class Orcamento extends AuditableEntity implements Serializable {

    @Id
    @GeneratedValue
    @Column(comment = "Id do orçamento. Dono: postgres")
    private UUID id;

    @Column(nullable = false, comment = "Valor do orçamento. Dono: eu-mesmo")
    private BigDecimal valor;
}
