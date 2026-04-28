package br.com.fiap.garage.infra.publisher;

import br.com.fiap.garage.domain.entity.WorkOrder;
import br.com.fiap.garage.domain.publisher.NotifyCustomerForApprovalPublisher;
import br.com.fiap.garage.infra.mapper.NotificationPublishMsgMapper;
import io.awspring.cloud.sns.core.SnsTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class NotifyCustomerForApprovalPublisherImpl implements NotifyCustomerForApprovalPublisher {

    private final NotificationPublishMsgMapper mapper;

    private final SnsTemplate snsTemplate;

    @Value("${sns.notification-creation.topic}")
    private String queueName;

    @Override
    public void notify(WorkOrder workOrder) {
        var msg = mapper.convert(workOrder);
        snsTemplate.convertAndSend(queueName, msg);
    }
}
