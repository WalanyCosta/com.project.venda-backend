package Model.Graficos;

import java.util.List;

public interface IGraficoDiario <W,Y> {

    Y gerarGrafico(List <W> list);
    
    
}
