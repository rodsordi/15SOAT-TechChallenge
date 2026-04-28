package br.com.fiap.garage.application.v1.msg;

import br.com.fiap.garage.infra.def.NotificationDef;
import lombok.Builder;
import lombok.Getter;

@Getter(onMethod_ = @Override)
@Builder
public class NotificationListenMsg implements NotificationDef.Request {

    private EmailListenMsg email;
}
