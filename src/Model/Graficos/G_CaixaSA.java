package Model.Graficos;

import Model.Model.Caixa;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class G_CaixaSA implements IGraficos<Caixa,BigDecimal>{
    
    private Integer a;
    
    @Override
    public List<BigDecimal> gerarGrafico(List<Caixa> objt) {
        List<BigDecimal> lista = new ArrayList<>();
        for(a = 6; a >= 0 ; a--){
            double b = objt.stream()
                    .filter(u->u.isCaixaSemanalAtual(a))
                    .mapToDouble(u-> u.saldoFinalDouble())
                    .sum();
            lista.add(new BigDecimal(b));
        }
        return lista;
    }
        
}
