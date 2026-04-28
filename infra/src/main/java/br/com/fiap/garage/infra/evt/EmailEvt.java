package br.com.fiap.garage.infra.evt;

import br.com.fiap.garage.infra.def.EmailDef;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter(onMethod_ = @Override)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmailEvt implements EmailDef.Request {

    private String recipient;
    private String subject;
    private String message;
}
