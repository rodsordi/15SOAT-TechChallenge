package br.com.fiap.garage.application.v1.listener;

import br.com.fiap.garage.application.v1.mapper.NotificationListenMsgMapper;
import br.com.fiap.garage.application.v1.msg.NotificationListenMsg;
import br.com.fiap.garage.domain.use_case.NotificationCreationUseCase;
import io.awspring.cloud.sqs.annotation.SqsListener;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static org.mapstruct.factory.Mappers.getMapper;

@RequiredArgsConstructor
@Component
public class NotificationListener {

    private static final NotificationListenMsgMapper MAPPER = getMapper(NotificationListenMsgMapper.class);

    private final NotificationCreationUseCase notificationCreationUseCase;

    @SqsListener(value = "${sns.notification-creation.topic}")
    public void listenNotificationCreation(NotificationListenMsg notificationListenMsg) {
        var notification = MAPPER.convert(notificationListenMsg);
        notificationCreationUseCase.create(notification);
    }
}
