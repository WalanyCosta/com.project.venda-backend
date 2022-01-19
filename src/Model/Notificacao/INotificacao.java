package Model.Notificacao;

import java.util.List;
import lombok.Getter;


public abstract class INotificacao <W>{
    
    protected final List<W> list;

    public INotificacao(List<W> list) {
        this.list = list;
    }
    
    abstract List<NotificacaoDTO> notificar();
    
}
