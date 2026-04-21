package br.com.fiap.garage.domain.filter;

import br.com.fiap.commons.filter.AuditableFilter;
import br.com.fiap.garage.domain.entity.WorkOrder;
import br.com.fiap.garage.domain.entity.enums.WorkOrderStatus;
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

import static br.com.fiap.garage.domain.entity.enums.WorkOrderStatus.RECEIVED;

@Getter
@Setter
@ParameterObject
public class WorkOrderFilter extends AuditableFilter<WorkOrder> implements Specification<WorkOrder> {

    @Schema(example = "RECEIVED", description = "WorkOrder status.")
    private WorkOrderStatus status = RECEIVED;

    private Specification<WorkOrder> statusEqual() {
        return (root, query, builder) -> status == null ? null :
                builder.equal(root.get("status"), status);
    }

    @Override
    public @Nullable Predicate toPredicate(Root<WorkOrder> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        return super.buildSpecification()
                .and(statusEqual())
                .toPredicate(root, query, criteriaBuilder);
    }
}
