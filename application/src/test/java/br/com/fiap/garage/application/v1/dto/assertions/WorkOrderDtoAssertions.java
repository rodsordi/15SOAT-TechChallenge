package br.com.fiap.garage.application.v1.dto.assertions;

import br.com.fiap.garage.application.v1.dto.WorkOrderDto;
import lombok.RequiredArgsConstructor;

import static br.com.fiap.commons.util.DateUtil.newDateTime;
import static br.com.fiap.commons.util.ReflectionUtil.assertThatObject;
import static br.com.fiap.garage.application.v1.dto.assertions.EstimateDtoAssertions.assertThat_EstimateDto_Response;
import static br.com.fiap.garage.domain.enums.WorkOrderStatus.RECEIVED;
import static lombok.AccessLevel.PRIVATE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.spy;

@RequiredArgsConstructor(access = PRIVATE)
public final class WorkOrderDtoAssertions {

    public static Response assertThat_WorkOrderDto_Response(WorkOrderDto.Response actual) {
        assertThat(actual).isNotNull();
        return new Response(spy(actual));
    }

    @RequiredArgsConstructor(access = PRIVATE)
    public static final class Response {
        
        private final WorkOrderDto.Response actual;

        /**
         * @see br.com.fiap.garage.domain.entity.factory.WorkOrderFactory
         * .withAllFields()
         */
        public void wasConvertedFrom_WorkOrder() {
            // Self
            assertThat(actual.getId())
                    .hasToString("e48ad20c-69dd-4382-b567-0e02b2c3d480");
            assertThat(actual.getStatus())
                    .isEqualTo(RECEIVED);
            assertThat_EstimateDto_Response(actual.getEstimate())
                    .wasConvertedFrom_Estimate();

            //Inheritance (AuditableTable)
            assertThat(actual.getCreatedAt())
                    .isEqualTo(newDateTime("21/04/2026 10:00:00"));
            assertThat(actual.getUpdatedAt())
                    .isEqualTo(newDateTime("21/04/2026 15:30:00"));

            // And
            assertThatObject(actual)
                    .hasAllGetMethodsVerifiedOnceAtLeast();
        }
    }

    public static Representation assertThat_WorkOrderDto_Representation(WorkOrderDto.Representation actual) {
        assertThat(actual).isNotNull();
        return new Representation(spy(actual));
    }

    @RequiredArgsConstructor(access = PRIVATE)
    public static final class Representation {

        private final WorkOrderDto.Representation actual;

        /**
         * @see br.com.fiap.garage.domain.entity.factory.WorkOrderFactory
         * .withAllFields()
         */
        public void wasConvertedFrom_WorkOrder() {
            // Self
            assertThat(actual.getId())
                    .hasToString("e48ad20c-69dd-4382-b567-0e02b2c3d480");
            assertThat(actual.getStatus())
                    .isEqualTo(RECEIVED);

            //Inheritance (AuditableTable)
            assertThat(actual.getCreatedAt())
                    .isEqualTo(newDateTime("21/04/2026 10:00:00"));
            assertThat(actual.getUpdatedAt())
                    .isEqualTo(newDateTime("21/04/2026 15:30:00"));
            assertThat(actual.getLinks())
                    .isNullOrEmpty();

            // And
            assertThatObject(actual)
                    .hasAllGetMethodsVerifiedOnceAtLeast();
        }
    }
}