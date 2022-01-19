package Test;

import Model.Exception.MensagemException;
import Model.Pagamento.DinheiroVivo;
import Model.Pagamento.IDebito;

import java.math.BigDecimal;
import java.math.MathContext;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class PagamentoTest {

    @Test
    public void faltandoDinheiro(){
       try{
        IDebito pagamento = new DinheiroVivo(new BigDecimal(150), new BigDecimal(50,MathContext.DECIMAL32));
        BigDecimal valorAfatar = pagamento.pagar();
        }catch(MensagemException e){
            assertEquals("121.0", e.getMessage());
        }
    }
  
    @Test
    public void pagando(){
        try{
        IDebito pagamento = new DinheiroVivo(new BigDecimal(150), new BigDecimal(50,MathContext.DECIMAL32));
        BigDecimal valorPagar = pagamento.pagar();
            assertEquals(0.0, valorPagar.doubleValue(),0.0);
        }catch(MensagemException e){
            System.out.println("Deu pau o resultado é: " + e.getMessage());
        }
        
    }
    
}
