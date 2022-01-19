package Model.Pagamento;

import Model.Exception.MensagemException;
import java.math.BigDecimal;

public interface IDebito {
    
    BigDecimal pagar()throws MensagemException;
    
}
