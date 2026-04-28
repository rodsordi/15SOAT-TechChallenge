package br.com.fiap.garage.application.v1.mapper;

import br.com.fiap.garage.application.v1.msg.NotificationListenMsg;
import br.com.fiap.garage.domain.entity.Notification;
import org.mapstruct.Mapper;

import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

@Mapper(nullValuePropertyMappingStrategy = IGNORE)
public interface NotificationListenMsgMapper {

    Notification convert(NotificationListenMsg source);
}
