package br.com.fiap.garage.domain.entity.assertions;

import br.com.fiap.garage.domain.entity.Vehicle;
import lombok.RequiredArgsConstructor;

import static br.com.fiap.commons.util.DateUtil.newDateTime;
import static br.com.fiap.commons.util.ReflectionUtil.assertThatObject;
import static lombok.AccessLevel.PRIVATE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.spy;

@RequiredArgsConstructor(access = PRIVATE)
public final class VehicleAssertions {

    private final Vehicle actual;

    public static VehicleAssertions assertThat_Vehicle(Vehicle actual) {
        assertThat(actual).isNotNull();
        return new VehicleAssertions(spy(actual));
    }

    /**
     * @see br.com.fiap.garage.application.v1.dto.factory.VehicleDtoFactory
     * .withAllFields()
     */
    public void wasConvertedFrom_VehicleDto_Request() {
        // Self
        assertThat(actual.getId())
                .isNull();
        assertThat(actual.getMake())
                .isEqualTo("Toyota");
        assertThat(actual.getModel())
                .isEqualTo("Corolla");
        assertThat(actual.getLicensePlate())
                .isEqualTo("ABC-1234");

        // Inheritance (AuditableEntity)
        assertThat(actual.getCreatedAt())
                .isNull();
        assertThat(actual.getUpdatedAt())
                .isNull();

        // And
        assertThatObject(actual)
                .hasAllGetMethodsVerifiedOnceAtLeast();
    }
}