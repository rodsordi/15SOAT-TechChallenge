package br.com.fiap.garage.domain.entity;

import br.com.fiap.commons.entity.AuditableEntity;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Singular;
import lombok.experimental.SuperBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;
import java.util.UUID;

import static jakarta.persistence.FetchType.EAGER;
import static jakarta.persistence.InheritanceType.JOINED;
import static lombok.AccessLevel.PROTECTED;

@Getter
@NoArgsConstructor(access = PROTECTED)
@SuperBuilder
@EqualsAndHashCode(callSuper = false, exclude = "id")
@Entity
@Table(schema = "garage", name = "users")
@Inheritance(strategy = JOINED)
public class User extends AuditableEntity implements UserDetails {

    @Id
    @GeneratedValue
    @Column(comment = "User id. Customer: postgres")
    private UUID id;

    @Getter(onMethod_ = @Override)
    @Column(nullable = false, unique = true, comment = "User e-mail. Customer: self")
    private String username;

    @Getter(onMethod_ = @Override)
    @Column(nullable = false, comment = "User password. Customer: self")
    private String password;

    @Column(nullable = false, length = 55, comment = "User name. Customer: self")
    private String name;

    @Column(nullable = false, unique = true, comment = "User e-mail. Customer: self")
    private String email;

    @Singular(value = "authority", ignoreNullCollections = true)
    @ManyToMany(fetch = EAGER)
    @JoinTable(schema = "garage", name = "users_authority",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "authority_id"))
    @OrderBy("createdAt desc")
    private Set<Authority> authorities;

    public void encodePassword(PasswordEncoder passwordEncoder) {
        password = passwordEncoder.encode(password);
    }
}
