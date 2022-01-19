package Model.Notificacao;

import Model.Model.Produto;

import java.util.List;
import static java.util.stream.Collectors.toList;

public class N_ProdutoQuaseExpirar extends INotificacao<Produto>{

    public N_ProdutoQuaseExpirar(List<Produto> list) {
        super(list);
    }
    
    @Override
    public List<NotificacaoDTO> notificar() {
          return list.stream()
                .filter(u-> u.isExpirar())
                .map(u->u.mensagemNotificacao())
                .collect(toList());
    }
    
}
