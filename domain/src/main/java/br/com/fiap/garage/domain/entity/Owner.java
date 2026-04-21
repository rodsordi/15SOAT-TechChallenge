package br.com.fiap.garage.domain.entity;

import br.com.fiap.commons.entity.AuditableEntity;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.util.UUID;

import static lombok.AccessLevel.PROTECTED;

@Getter
@NoArgsConstructor(access = PROTECTED)
@SuperBuilder
@EqualsAndHashCode(callSuper = false, exclude = "id")
@Entity
@Table(schema = "garage")
public class Owner extends AuditableEntity implements Serializable {

    @Id
    @GeneratedValue
    @Column(comment = "Owner id. Owner: postgres")
    private UUID id;

    @Column(nullable = false, length = 55, comment = "Owner name. Owner: self")
    private String name;

    @Column(nullable = false, comment = "Owner e-mail. Owner: self")
    private String email;
}
