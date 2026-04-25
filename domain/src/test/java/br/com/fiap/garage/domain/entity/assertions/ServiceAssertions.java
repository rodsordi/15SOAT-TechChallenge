package br.com.fiap.garage.domain.entity.assertions;

import br.com.fiap.garage.domain.entity.Service;
import br.com.fiap.garage.domain.entity.factory.ServiceFactory;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

import static br.com.fiap.commons.util.DateUtil.newDateTime;
import static br.com.fiap.commons.util.ReflectionUtil.assertThatObject;
import static br.com.fiap.garage.domain.entity.assertions.InventoryAssertions.assertThat_Inventory;
import static lombok.AccessLevel.PRIVATE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.spy;

@RequiredArgsConstructor(access = PRIVATE)
public final class ServiceAssertions {

    private final Service actual;

    public static ServiceAssertions assertThat_Service(Service actual) {
        assertThat(actual).isNotNull();
        return new ServiceAssertions(spy(actual));
    }

    /**
     * @see ServiceFactory
     * .withAllFields()
     */
    public void isEqualTo_Service() {
        // Self
        assertThat(actual.getId())
                .hasToString("9d1b9b7c-bd7c-4f5f-a747-0b1f63aac409");
        assertThat(actual.getName())
                .isEqualTo("Standard Engine Maintenance");
        assertThat(actual.getDescription())
                .isEqualTo("Complete engine checkup and oil change");
        assertThat(actual.getAmount())
                .isEqualTo(new BigDecimal("250.00"));

        // Composition (Many-to-Many)
        assertThat_Inventory(actual.getEstimatedMaterials().stream().findFirst().orElseThrow())
                .isEqualTo_Inventory();

        // Inheritance (AuditableEntity)
        assertThat(actual.getCreatedAt())
                .isEqualTo(newDateTime("30/12/2024 23:59:59"));
        assertThat(actual.getUpdatedAt())
                .isEqualTo(newDateTime("31/12/2024 23:59:59"));

        // And
        assertThatObject(actual)
                .hasAllGetMethodsVerifiedOnceAtLeast();
    }

    /**
     * @see br.com.fiap.garage.application.v1.dto.factory.ServiceDtoFactory
     * .withAllFields()
     */
    public void wasConvertedFrom_ServiceDto() {
        // Self
        assertThat(actual.getId())
                .hasToString("9d1b9b7c-bd7c-4f5f-a747-0b1f63aac409");
        assertThat(actual.getName())
                .isEqualTo("Standard Engine Maintenance");
        assertThat(actual.getDescription())
                .isEqualTo("Complete engine checkup and oil change");
        assertThat(actual.getAmount())
                .isEqualTo(new BigDecimal("250.00"));

        // Composition (Many-to-Many)
        assertThat_Inventory(actual.getEstimatedMaterials().stream().findFirst().orElseThrow())
                .wasConvertedFrom_InventoryDto();

        // Inheritance (AuditableEntity)
        assertThat(actual.getCreatedAt())
                .isEqualTo(newDateTime("30/12/2024 23:59:59"));
        assertThat(actual.getUpdatedAt())
                .isEqualTo(newDateTime("31/12/2024 23:59:59"));

        // And
        assertThatObject(actual)
                .hasAllGetMethodsVerifiedOnceAtLeast();
    }
}