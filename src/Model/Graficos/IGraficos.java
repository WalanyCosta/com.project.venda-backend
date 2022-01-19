package Model.Graficos;

import java.util.List;

public interface IGraficos <W, Y>{
    
    List<Y> gerarGrafico(List <W> objt);
    
}
