package Model.Pagamento;

import Model.Exception.MensagemException;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DinheiroVivo implements IDebito{

    private BigDecimal total;
    private BigDecimal valorApagar;

    public DinheiroVivo(BigDecimal total, BigDecimal valorApagar) {
        this.total = total;
        this.valorApagar = valorApagar;
    }
    
    @Override
    public BigDecimal pagar() throws MensagemException{
        BigDecimal resto = total.subtract(valorApagar);
        if(total.doubleValue() > valorApagar.doubleValue()){
            throw new MensagemException("Falta " + resto.doubleValue()+" Kz ");
        }
        return total;
    }
}
