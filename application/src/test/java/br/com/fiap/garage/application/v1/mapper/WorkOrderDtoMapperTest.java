package br.com.fiap.garage.application.v1.mapper;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static br.com.fiap.garage.application.v1.dto.factory.WorkOrderDtoFactory.Request.createWorkOrderDto_Request;
import static br.com.fiap.garage.domain.entity.assertions.WorkOrderAssertions.assertThat_WorkOrder;
import static org.mapstruct.factory.Mappers.getMapper;

class WorkOrderDtoMapperTest {

    private static final WorkOrderDtoMapper MAPPER = getMapper(WorkOrderDtoMapper.class);

    @DisplayName("When converting WorkOrderDto.Request to WorkOrder")
    @Nested
    class Convert1 {

        @DisplayName("Then should execute successfully")
        @Nested
        class Success {

            @DisplayName("Given a WorkOrderDto.Request with all fields")
            @Test
            void test1() {
                //Given
                var source = createWorkOrderDto_Request()
                        .withAllFields();
                //When
                var actual = MAPPER.convert(source);
                //Then
                assertThat_WorkOrder(actual)
                        .wasConvertedFrom_WorkOrderDto_Request();
            }
        }
    }

}