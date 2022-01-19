package Model.Calculos;

import Model.Model.ItemVenda;
import Model.Model.Produto;
import Model.Model.Stock;
import Model.Model.Venda;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.util.List;

public class Calculos {

   private final double IVA = 0.14;
    
   public BigInteger retirarStockMx(Stock param,Integer quant){
       return param.OperacaoStockMaximo(quant);
   }
   
   public BigInteger aumentarStockMn(Stock param,Integer quant){
      return param.aumentarMn(quant);
   }
   
   public BigInteger aumentarStockMx(Stock stock,Integer quant){
       return stock.aumentarMx(quant);
   }

   public BigDecimal calcularSubtotal(Produto produto,Integer quant){
       produto.diminuirStockMn(quant);
       return produto.CalcularSubtotal(quant);
   }
   
   public BigDecimal calcularTotalVenda(List<ItemVenda> list){ 
       BigDecimal total = new BigDecimal(
                 list.stream()
                .mapToDouble(u->u.subtotalDouble())
                .sum());
       return incrementeIVA(total);
   }
   
   public BigDecimal calculoSaldoCaixa(List<Venda> list){
        return new BigDecimal(
                list.stream()
                    .filter(u->u.isGraficoDiario())
                    .mapToDouble(u->u.totalDouble())
                    .sum());
   }
   
   private BigDecimal incrementeIVA(BigDecimal total){
       BigDecimal resultado = total.multiply(new BigDecimal(IVA), MathContext.DECIMAL32);
       return total.add(resultado,MathContext.DECIMAL32);
   }
   
}
