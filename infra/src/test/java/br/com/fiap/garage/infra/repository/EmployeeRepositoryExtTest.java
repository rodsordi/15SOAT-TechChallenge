package br.com.fiap.garage.infra.repository;

import br.com.fiap.commons.config.JpaConfig;
import br.com.fiap.garage.domain.entity.Employee;
import br.com.fiap.garage.domain.filter.EmployeeFilter;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jpa.test.autoconfigure.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

import static br.com.fiap.garage.domain.entity.factory.EmployeeFactory.create_Employee;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.util.ReflectionTestUtils.setField;

@ActiveProfiles("test")
@DataJpaTest
@ContextConfiguration(classes = JpaConfig.class)
class EmployeeRepositoryExtTest {

    @Autowired
    private EmployeeRepositoryExt repository;

    @Autowired
    private TestEntityManager em;

    @DisplayName("When finding all employees")
    @Nested
    class FindAll {

        @DisplayName("Then should execute successfully")
        @Nested
        class Success {

            @DisplayName("Given a valid filter, in scenario with registers")
            @Test
            void test1() {
                //Scenario
                var employee = create_Employee().withAllFieldsExceptDB();
                employee.getAuthorities()
                        .forEach(authority -> em.persist(authority));
                setField(employee, "name", "John da Silva");
                repository.save(employee);
                em.flush();
                //Given
                var filter = new EmployeeFilter();
                //When
                var actual = repository.findAll(filter, filter.buildPageRequest());
                //Then
                assertThat(actual)
                        .hasSize(1)
                        .extracting(Employee::getName)
                        .containsExactly("John da Silva");
            }
        }
    }
}