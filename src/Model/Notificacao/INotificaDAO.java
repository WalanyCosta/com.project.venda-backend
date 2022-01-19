package Model.Notificacao;

import java.util.List;

public interface INotificaDAO {

   public void guardar(NotificacaoDTO NDTO);
   
   public List<NotificacaoDTO> extrair();
   
   public void apagar();
    
}
