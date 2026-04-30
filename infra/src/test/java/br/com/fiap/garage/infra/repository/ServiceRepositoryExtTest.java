package br.com.fiap.garage.infra.repository;

import br.com.fiap.commons.config.JpaConfig;
import br.com.fiap.garage.domain.entity.EstimatedService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jpa.test.autoconfigure.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

import java.util.HashSet;

import static br.com.fiap.garage.domain.entity.factory.EstimatedServiceFactory.create_EstimatedService;
import static br.com.fiap.garage.domain.entity.factory.ServiceFactory.create_Service;
import static br.com.fiap.garage.domain.entity.factory.WorkOrderFactory.create_WorkOrder;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.util.ReflectionTestUtils.setField;

@ActiveProfiles("test")
@DataJpaTest
@ContextConfiguration(classes = JpaConfig.class)
class ServiceRepositoryExtTest {

    @Autowired
    private ServiceRepositoryExt repository;

    @Autowired
    private TestEntityManager em;

    @DisplayName("When calculating average time of service in minutes")
    @Nested
    class CalculateAverageTimeOfServiceInMinutes {

        @DisplayName("Then should execute successfully")
        @Nested
        class Success {

            @DisplayName("Given a valid serviceId, in scenario with registers")
            @Test
            void test1() {
                //Scenario
                var service = create_Service().withAllFieldsExceptDB();
                var material = service.getMaterials().stream().findFirst().orElseThrow();
                em.persist(material);
                em.flush();
                service = em.merge(service);
                em.flush();

                //And
                var workOrder = create_WorkOrder().withAllFieldsExceptDB();

                var esSet = new HashSet<EstimatedService>();
                var es1 = create_EstimatedService().withAllFieldsExceptDB();
                setField(es1, "name", "Service 1");
                esSet.add(es1);
                var es2 = create_EstimatedService().withAllFieldsExceptDB();
                setField(es2, "name", "Service 2");
                esSet.add(es2);
                var es3 = create_EstimatedService().withAllFieldsExceptDB();
                setField(es3, "name", "Service 3");
                esSet.add(es3);

                setField(workOrder, "estimatedServices", esSet);

                for (var estimateService : workOrder.getEstimatedServices())
                    setField(estimateService, "serviceId", service.getId());

                workOrder = em.merge(workOrder);
                em.flush();

                //And
                for (var estimateService : workOrder.getEstimatedServices())
                    setField(estimateService, "finishedAt", estimateService.getCreatedAt().plusMinutes(10));

                //Given
                var serviceId = service.getId();
                //When
                var actual = repository.calculateAverageTimeOfServiceInMinutes(serviceId);
                //Then
                assertThat(actual)
                        .isEqualTo(10L);
            }
        }
    }
}