package br.com.fiap.garage.infra.repository;

import br.com.fiap.commons.config.JpaConfig;
import br.com.fiap.garage.domain.entity.Customer;
import br.com.fiap.garage.domain.filter.CustomerFilter;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jpa.test.autoconfigure.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

import java.time.LocalDate;

import static br.com.fiap.commons.util.ReflectionUtil.assertThatObject;
import static br.com.fiap.garage.domain.entity.factory.CustomerFactory.create_Customer;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;
import static org.springframework.test.util.ReflectionTestUtils.setField;

@ActiveProfiles("test")
@DataJpaTest
@ContextConfiguration(classes = JpaConfig.class)
class CustomerRepositoryExtTest {

    @Autowired
    private CustomerRepositoryExt repository;

    @Autowired
    private TestEntityManager em;

    @DisplayName("When finding all customers")
    @Nested
    class FindAll {

        @DisplayName("Then should execute successfully")
        @Nested
        class Success {

            @DisplayName("Given a filter with all fields, in scenario with registers")
            @Test
            void test1() {
                //Scenario
                var customer = create_Customer()
                        .withAllFieldsExceptDB();
                customer.getAuthorities()
                        .forEach(authority -> em.persist(authority));
                em.merge(customer);
                em.flush();
                //Given
                var filter = new CustomerFilter();
                filter.setDocument("27351626000107");
                filter.setName("John Doe");
                filter.setEmail("john.doe@fiap.com.br");
                filter.setCreatedAtFrom(LocalDate.now());//NOSONAR
                filter.setCreatedAtTo(LocalDate.now());//NOSONAR
                filter.setUpdatedAtFrom(LocalDate.now());//NOSONAR
                filter.setUpdatedAtTo(LocalDate.now());//NOSONAR
                assertThatObject(filter)
                        .hasNoEmptyFields();
                //When
                var actual = repository.findAll(filter, filter.buildPageRequest());
                //Then
                assertThat(actual)
                        .hasSize(1)
                        .extracting(
                                Customer::getDocument,
                                Customer::getName,
                                Customer::getEmail)
                        .containsExactly(tuple(
                                "27351626000107",
                                "John Doe",
                                "john.doe@fiap.com.br"));
            }
        }
    }

    @DisplayName("When finding customer by cpf")
    @Nested
    class FindByCpf {

        @DisplayName("Then should execute successfully")
        @Nested
        class Success {

            @DisplayName("Given a valid cpf, in scenario with register")
            @Test
            void test1() {
                //Scenario
                var customer = create_Customer()
                        .withAllFieldsExceptDB();
                customer.getAuthorities()
                        .forEach(authority -> em.persist(authority));
                setField(customer, "document", "86855874000146");
                repository.save(customer);
                em.flush();
                //Given
                var document = "86855874000146";
                //When
                var actual = repository.findByDocument(document);
                //Then
                assertThat(actual)
                        .isPresent()
                        .get()
                        .extracting(Customer::getDocument)
                        .isEqualTo("86855874000146");
            }
        }
    }
}