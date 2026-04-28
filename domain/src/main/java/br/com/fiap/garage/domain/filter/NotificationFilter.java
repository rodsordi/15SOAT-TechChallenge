package br.com.fiap.garage.domain.filter;

import br.com.fiap.commons.filter.AuditableFilter;
import br.com.fiap.garage.domain.entity.Notification;
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

@Getter
@Setter
@ParameterObject
public class NotificationFilter extends AuditableFilter<Notification> implements Specification<Notification> {

    @Schema(example = "john.doe@email.com", description = "Notification recipient.")
    private String recipient;

    private Specification<Notification> emailEqual() {
        return (root, query, builder) -> recipient == null ? null :
                builder.equal(root.get("email.recipient"), recipient);
    }

    @Override
    public @Nullable Predicate toPredicate(Root<Notification> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        return super.buildSpecification()
                .and(emailEqual())
                .toPredicate(root, query, criteriaBuilder);
    }
}
