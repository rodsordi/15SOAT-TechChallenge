package br.com.fiap.garage.infra.msg;

import br.com.fiap.garage.infra.def.NotificationDef;
import lombok.Builder;
import lombok.Getter;

@Getter(onMethod_ = @Override)
@Builder
public class NotificationPublishMsg implements NotificationDef.Request {

    private EmailPublishMsg email;
}
