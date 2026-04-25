package br.com.fiap.garage.domain.filter;

import br.com.fiap.commons.filter.AuditableFilter;
import br.com.fiap.garage.domain.entity.Service;
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

import java.math.BigDecimal;

import static org.apache.commons.lang3.StringUtils.isEmpty;

@Getter
@Setter
@ParameterObject
public class ServiceFilter extends AuditableFilter<Service> implements Specification<Service> {

    @Schema(example = "Oil", description = "Service name.")
    private String name;

    private Specification<Service> nameEqual() {
        return (root, query, builder) -> isEmpty(name) ? null :
                builder.equal(root.get("name"), name);
    }

    @Schema(example = "1.99", description = "Service amount (from).")
    private BigDecimal amountFrom;

    private Specification<Service> amountFrom() {
        return (root, query, builder) -> amountFrom == null ? null :
                builder.greaterThanOrEqualTo(root.get("amount"), amountFrom);
    }

    @Schema(example = "9.99", description = "Service amount (to).")
    private BigDecimal amountTo;

    private Specification<Service> amountTo() {
        return (root, query, builder) -> amountTo == null ? null :
                builder.lessThanOrEqualTo(root.get("amount"), amountTo);
    }

    @Override
    public @Nullable Predicate toPredicate(Root<Service> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        return super.buildSpecification()
                .and(nameEqual())
                .and(amountFrom())
                .and(amountTo())
                .toPredicate(root, query, criteriaBuilder);
    }
}
