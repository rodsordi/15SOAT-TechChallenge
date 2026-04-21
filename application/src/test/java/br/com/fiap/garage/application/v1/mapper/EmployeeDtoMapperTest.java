package br.com.fiap.garage.application.v1.mapper;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static br.com.fiap.garage.application.v1.dto.factory.EmployeeDtoFactory.Request.createEmployeeDto_Request;
import static br.com.fiap.garage.domain.entity.assertions.EmployeeAssertions.assertThat_Employee;
import static org.mapstruct.factory.Mappers.getMapper;

class EmployeeDtoMapperTest {

    private static final EmployeeDtoMapper MAPPER = getMapper(EmployeeDtoMapper.class);

    @DisplayName("When converting EmployeeDto.Request to Employee")
    @Nested
    class Convert1 {

        @DisplayName("Then should execute successfully")
        @Nested
        class Success {

            @DisplayName("Given a EmployeeDto.Request with all fields")
            @Test
            void test1() {
                //Given
                var source = createEmployeeDto_Request()
                        .withAllFields();
                //When
                var actual = MAPPER.convert(source);
                //Then
                assertThat_Employee(actual)
                        .wasConvertedFrom_EmployeeDto_Request();
            }
        }
    }

}