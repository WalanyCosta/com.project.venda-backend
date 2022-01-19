package Model.Model;

import Model.Notificacao.NotificacaoDTO;
import java.io.Serializable;
import java.math.BigInteger;
import java.time.LocalDate;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ConstraintMode;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)

@Entity
@Table(name = "tb_stock")
public class Stock implements Serializable{
    
    @EqualsAndHashCode.Include
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(name = "qtd_minimo")
    private BigInteger stockMinimo;
    
    @Column(name = "qtd_real")
    private BigInteger stockReal;
    
    @Column(name = "data_stock")
    private LocalDate dataStock;
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "produto_id")
    private Produto produto;
    
    @ManyToOne
    @JoinColumn(name = "fornecedor_id")
    private Fornecedor fornecedor;
    
    
    public boolean isProdStock(){
        return this.stockReal.intValue() <= stockMinimo.intValue();
    }
    
    public boolean isProdArmario(){
        return this.stockMinimo.intValue() <= 2;
    }

    public NotificacaoDTO mensagemNotificacao(String msm){
           String message = produto.getNome() + msm;
           return new NotificacaoDTO( LocalDate.now(), "", message);
    }
    
    public void OperacaoStockMinimo(Integer param) throws RuntimeException{
        if(stockMinimo.intValue() <= 0) throw new RuntimeException(" O/A " + produto.getNome() + " já não tem na pratileira");
        stockMinimo = stockMinimo.subtract(new BigInteger(param.toString()));
    }
    
    public BigInteger OperacaoStockMaximo(Integer param) throws RuntimeException{
        if(stockReal.intValue() <= 0) throw new RuntimeException(" O/A " + produto.getNome() + " acabou no estoque");
        return stockReal.subtract(new BigInteger(param.toString()));
    }
     
    public BigInteger aumentarMx(Integer param){
        return stockReal.add(new BigInteger(param.toString()));
    }
    
    public BigInteger aumentarMn(Integer param){
        return stockMinimo.add(new BigInteger(param.toString()));
    }
}
