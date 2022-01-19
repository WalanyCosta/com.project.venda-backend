package Model.Notificacao;

import java.time.LocalDate;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@AllArgsConstructor
@NoArgsConstructor

public class NotificacaoDTO {

    private LocalDate dataNotificacao;
    private String img;

    @EqualsAndHashCode.Include
    private String mensagem; 
    
    @Override
    public String toString(){
        return dataNotificacao + " - " + img + " - " + mensagem;
    }
}
