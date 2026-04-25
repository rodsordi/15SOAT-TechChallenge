package br.com.fiap.garage.domain.filter;

import br.com.fiap.commons.filter.AuditableFilter;
import br.com.fiap.garage.domain.entity.InventoryMaterial;
import br.com.fiap.garage.domain.enums.MaterialType;
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
public class InventoryMaterialFilter extends AuditableFilter<InventoryMaterial> implements Specification<InventoryMaterial> {

    @Schema(example = "SPARE_PART", description = "Inventory Material type.")
    private MaterialType type;

    private Specification<InventoryMaterial> typeEqual() {
        return (root, query, builder) -> type == null ? null :
                builder.equal(root.get("material.type"), type);
    }

    @Schema(example = "Tire", description = "Inventory Material name.")
    private String name;

    private Specification<InventoryMaterial> nameEqual() {
        return (root, query, builder) -> isEmpty(name) ? null :
                builder.equal(root.get("material.name"), name);
    }

    @Schema(example = "1.99", description = "Inventory Material amount (from).")
    private BigDecimal amountFrom;

    private Specification<InventoryMaterial> amountFrom() {
        return (root, query, builder) -> amountFrom == null ? null :
                builder.greaterThanOrEqualTo(root.get("material.amount"), amountFrom);
    }

    @Schema(example = "9.99", description = "Inventory Material amount (to).")
    private BigDecimal amountTo;

    private Specification<InventoryMaterial> amountTo() {
        return (root, query, builder) -> amountTo == null ? null :
                builder.lessThanOrEqualTo(root.get("material.amount"), amountTo);
    }

    @Override
    public @Nullable Predicate toPredicate(Root<InventoryMaterial> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        return super.buildSpecification()
                .and(typeEqual())
                .and(nameEqual())
                .and(amountFrom())
                .and(amountTo())
                .toPredicate(root, query, criteriaBuilder);
    }
}
