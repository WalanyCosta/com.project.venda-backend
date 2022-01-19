package Model.Notificacao;

import Model.Model.Stock;
import java.util.List;
import static java.util.stream.Collectors.toList;

public class N_ProdutoEsgotadoArmario extends INotificacao<Stock>{

    public N_ProdutoEsgotadoArmario(List<Stock> list) {
        super(list);
    }

    @Override
    public List<NotificacaoDTO> notificar() {   
     return list.stream()
                .filter(u -> u.isProdArmario())
                .map(u -> u.mensagemNotificacao("está acabando na pratileira"))
                .collect(toList());
    }
    
}
