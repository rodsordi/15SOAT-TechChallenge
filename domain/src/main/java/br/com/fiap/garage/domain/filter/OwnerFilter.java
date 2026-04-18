package br.com.fiap.garage.domain.filter;

import br.com.fiap.commons.domain.filter.AuditableFilter;
import br.com.fiap.garage.domain.entity.Owner;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.Getter;
import lombok.Setter;
import org.jspecify.annotations.Nullable;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.jpa.domain.Specification;

import static org.apache.commons.lang3.StringUtils.isEmpty;

@Getter
@Setter
@ParameterObject
public class OwnerFilter extends AuditableFilter<Owner> implements Specification<Owner> {

    @Schema(example = "John", description = "Owner name.")
    private String name;

    private Specification<Owner> nameEqual() {
        return (root, query, builder) -> isEmpty(name) ? null :
                builder.equal(root.get("name"), name);
    }

    @Schema(example = "user@email.com", description = "Owner e-mail.")
    private String email;

    private Specification<Owner> emailEqual() {
        return (root, query, builder) -> isEmpty(email) ? null :
                builder.equal(root.get("email"), email);
    }

    @Override
    public @Nullable Predicate toPredicate(Root<Owner> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        return super.buildSpecification()
                .and(nameEqual())
                .and(emailEqual())
                .toPredicate(root, query, criteriaBuilder);
    }
}
