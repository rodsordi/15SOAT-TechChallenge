package br.com.fiap.garage.application.v1.msg;

import br.com.fiap.garage.infra.def.NotificationDef;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter(onMethod_ = @Override)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NotificationMsg implements NotificationDef.Request {

    private UUID externalId;

    private EmailListenMsg email;
}
