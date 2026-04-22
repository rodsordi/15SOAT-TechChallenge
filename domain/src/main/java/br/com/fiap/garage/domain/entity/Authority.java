package br.com.fiap.garage.domain.entity;

import br.com.fiap.commons.entity.AuditableEntity;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.security.core.GrantedAuthority;

import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@Getter
@NoArgsConstructor(access = PROTECTED)
@SuperBuilder
@EqualsAndHashCode(callSuper = false, exclude = "id")
@Entity
@Table(schema = "garage")
public class Authority extends AuditableEntity implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(comment = "Authorization id. Owner: postgres")
    private Long id;

    @Getter(onMethod_ = @Override)
    @Column(nullable = false, length = 20, comment = "Authorization name. Owner: self")
    private String authority;
}
