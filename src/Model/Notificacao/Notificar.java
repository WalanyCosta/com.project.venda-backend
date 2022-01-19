package Model.Notificacao;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Notificar {
    
    private List<Observar> obs = new ArrayList<>();
    private final List<INotificacao> list;
    private final INotificaDAO notificaDAO;


    public Notificar(List<INotificacao> list){
       this.list = list;
       this.notificaDAO = new GuardarEmArquivo();
    }

    public Notificar(List<INotificacao> list, INotificaDAO notificaDAO) {
        this.list = list;
        this.notificaDAO = notificaDAO;
    }

    public void operacaoNotifica(){
        List<NotificacaoDTO> lista = new ArrayList<>();
        list.forEach(param ->{
            processoNotifica(param.notificar(), notificaDAO).forEach(NDTO->{
            lista.add((NotificacaoDTO) NDTO);
        });});
        notificaObs(lista);
    }
    
    private void notificaObs(List<NotificacaoDTO> list){
        obs.forEach(ob->{
            ob.actualizar(list);
        });
    }
    
    public void adicionaObservador(Observar param){
        obs.add(param);
    }
    
    private List<NotificacaoDTO> processoNotifica(List<NotificacaoDTO> objt,INotificaDAO dao) {
        List<NotificacaoDTO> lista = dao.extrair();
        return objt.stream()
                   .filter(msm -> !lista.contains(msm))
                   .map(NDTO->{dao.guardar(NDTO);return NDTO;})
                   .collect(Collectors.toList());
    }
}
