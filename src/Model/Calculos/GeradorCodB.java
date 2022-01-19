package Model.Calculos;

public class GeradorCodB {
    
    private final GeradorNSerie gerador = new GeradorNSerie();
    
    public String codBarra(){
        return gerador.gerar(13);
    }  
}
