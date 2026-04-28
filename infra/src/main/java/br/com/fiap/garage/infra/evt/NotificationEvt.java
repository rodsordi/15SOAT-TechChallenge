package br.com.fiap.garage.infra.evt;

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
public class NotificationEvt implements NotificationDef.Request {

    private UUID externalId;

    private EmailEvt email;
}
