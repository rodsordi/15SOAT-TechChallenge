package br.com.fiap.garage.infra.msg;

import br.com.fiap.garage.infra.def.EmailDef;
import lombok.Builder;
import lombok.Getter;

@Getter(onMethod_ = @Override)
@Builder
public class EmailPublishMsg implements EmailDef.Request {

    private String recipient;
    private String subject;
    private String message;
}
