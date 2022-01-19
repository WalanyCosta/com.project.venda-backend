package Model.Notificacao;

import Model.Model.Stock;

import java.util.List;
import static java.util.stream.Collectors.toList;


public class N_StockEsgotado extends INotificacao<Stock>{

    public N_StockEsgotado(List<Stock> list) {
        super(list);
    }
    
    @Override
    public List<NotificacaoDTO> notificar() {
            return list.stream()
                .filter(u -> u.isProdStock())
                .map(u -> u.mensagemNotificacao("Já está esgotado no estoque"))
                .collect(toList());
    }


  
    
}
