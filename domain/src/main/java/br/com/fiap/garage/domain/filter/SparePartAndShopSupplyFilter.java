package br.com.fiap.garage.domain.filter;

import br.com.fiap.commons.domain.filter.AuditableFilter;
import br.com.fiap.garage.domain.entity.Owner;
import br.com.fiap.garage.domain.entity.SparePartAndShopSupply;
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
public class SparePartAndShopSupplyFilter extends AuditableFilter<SparePartAndShopSupply> implements Specification<SparePartAndShopSupply> {

    @Schema(example = "Tire", description = "SparePartAndShopSupply name.")
    private String name;

    private Specification<SparePartAndShopSupply> nameEqual() {
        return (root, query, builder) -> isEmpty(name) ? null :
                builder.equal(root.get("name"), name);
    }

    @Schema(example = "1.99", description = "SparePartAndShopSupply price (from).")
    private BigDecimal priceFrom;

    private Specification<SparePartAndShopSupply> priceFrom() {
        return (root, query, builder) -> priceFrom == null ? null :
                builder.greaterThanOrEqualTo(root.get("price"), priceFrom);
    }

    @Schema(example = "9.99", description = "SparePartAndShopSupply price (to).")
    private BigDecimal priceTo;

    private Specification<SparePartAndShopSupply> priceTo() {
        return (root, query, builder) -> priceTo == null ? null :
                builder.lessThanOrEqualTo(root.get("price"), priceTo);
    }

    @Override
    public @Nullable Predicate toPredicate(Root<SparePartAndShopSupply> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        return super.buildSpecification()
                .and(nameEqual())
                .and(priceFrom())
                .toPredicate(root, query, criteriaBuilder);
    }
}
