package Model.Graficos;

import Model.Model.Venda;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


public class G_VendaSemanal implements IGraficos<Venda,BigDecimal>{
    
    private int semana;
    private int dia;
    private List<BigDecimal> listaGrafico;
    @Override
    public List<BigDecimal> gerarGrafico(List<Venda> objt) {
        listaGrafico = new ArrayList<>();
        for(semana = 4 ; semana >= 0; semana --){
            double soma = 0;
            for(dia = 0 ; dia <= 6 ; dia ++){
                soma = objt.stream()
                    .filter( d ->d.isGrafico(semana, dia))
                    .mapToDouble(u->u.totalDouble())
                    .reduce(soma, (actual,u)->actual+ u);
            }
            listaGrafico.add(new BigDecimal(soma));
        }
        return listaGrafico;
    }
    
}
