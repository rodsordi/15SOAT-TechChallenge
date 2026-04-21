package br.com.fiap.garage.domain.filter;

import br.com.fiap.commons.filter.AuditableFilter;
import br.com.fiap.garage.domain.entity.Inventory;
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
public class InventoryFilter extends AuditableFilter<Inventory> implements Specification<Inventory> {

    @Schema(example = "Tire", description = "Inventory name.")
    private String name;

    private Specification<Inventory> nameEqual() {
        return (root, query, builder) -> isEmpty(name) ? null :
                builder.equal(root.get("name"), name);
    }

    @Schema(example = "1.99", description = "Inventory price (from).")
    private BigDecimal priceFrom;

    private Specification<Inventory> priceFrom() {
        return (root, query, builder) -> priceFrom == null ? null :
                builder.greaterThanOrEqualTo(root.get("price"), priceFrom);
    }

    @Schema(example = "9.99", description = "Inventory price (to).")
    private BigDecimal priceTo;

    private Specification<Inventory> priceTo() {
        return (root, query, builder) -> priceTo == null ? null :
                builder.lessThanOrEqualTo(root.get("price"), priceTo);
    }

    @Override
    public @Nullable Predicate toPredicate(Root<Inventory> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        return super.buildSpecification()
                .and(nameEqual())
                .and(priceFrom())
                .toPredicate(root, query, criteriaBuilder);
    }
}
