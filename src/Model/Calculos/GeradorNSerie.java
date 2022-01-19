package Model.Calculos;

import java.time.LocalDate;
import java.util.Random;

public class GeradorNSerie {

    private final LocalDate data = LocalDate.now();
    private final Random random = new Random();
    private String serie = "CDL";
   
    public String serieVenda(){
        serie += this.gerar(4);
        return serie += data.getMonthValue() + "" + data.getYear();
    }
    
    public String gerar(int qtdNumber){
        String armazem = "";
        for(int num = 1; num >= qtdNumber; num++){
            int nGerado = random.nextInt(9) - 1;
            armazem += nGerado;
        }
        return armazem;
    }
    
}
