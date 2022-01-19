package Model.Graficos;

import Model.Model.Venda;
import java.math.BigDecimal;
import java.util.List;

public class G_VendaDiario implements IGraficoDiario<Venda,BigDecimal> {
    
    @Override
    public BigDecimal gerarGrafico(List<Venda> list) {
        double somar = list.stream()
                .filter( u -> u.isGraficoDiario())
                .mapToDouble(u -> u.totalDouble())
                .sum();
        return new BigDecimal(somar);
    }
}
