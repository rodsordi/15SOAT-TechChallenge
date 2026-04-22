package br.com.fiap.garage.domain.entity;

import br.com.fiap.commons.entity.AuditableEntity;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Singular;
import lombok.experimental.SuperBuilder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Set;
import java.util.UUID;

import static jakarta.persistence.CascadeType.ALL;
import static lombok.AccessLevel.PROTECTED;

@Getter
@NoArgsConstructor(access = PROTECTED)
@SuperBuilder
@EqualsAndHashCode(callSuper = false, exclude = "id")
@Entity
@Table(schema = "garage")
public class User extends AuditableEntity implements UserDetails {

    @Id
    @GeneratedValue
    @Column(comment = "User id. Owner: postgres")
    private UUID id;

    @Getter(onMethod_ = @Override)
    @Column(nullable = false, length = 20, comment = "Username. Owner: self")
    private String username;

    @Getter(onMethod_ = @Override)
    @Column(nullable = false, length = 20, comment = "User password. Owner: self")
    private String password;

    @Singular(value = "authority", ignoreNullCollections = true)
    @OneToMany(cascade = ALL, orphanRemoval = true)
    @JoinColumn(name = "authority_id", updatable = false, nullable = false, comment = "Authority id.")
    @OrderBy("createdAt desc")
    private Set<Authority> authorities;
}
