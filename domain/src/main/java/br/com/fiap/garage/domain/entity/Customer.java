package br.com.fiap.garage.domain.entity;

import br.com.fiap.commons.validation.CpfOrCnpj;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

import static lombok.AccessLevel.PROTECTED;

@Getter
@NoArgsConstructor(access = PROTECTED)
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(schema = "garage")
public class Customer extends User implements Serializable {

    @CpfOrCnpj
    @Column(nullable = false, length = 14, comment = "Customer document. Owner: self")
    private String document;
}
