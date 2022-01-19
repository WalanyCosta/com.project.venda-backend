package Model.Model;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "tb_itemVenda")
public class ItemVenda implements Serializable{
    
    @EqualsAndHashCode.Include
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    private Integer quant;

    @Column(name = "sub_total")
    private BigDecimal subtotal;
   
    @ManyToOne
    @JoinColumn(name = "venda_id")
    private Venda venda;
    
    @ManyToOne
    @JoinColumn(name = "produto_id")
    private Produto produto;
   
    public boolean isVendaTotal(Integer param){
        return venda.getId().equals(param);
    }
    
    public boolean isConfirmarSerie(String serie){
        return venda.isConfirmarSerie(serie);
    }
   
    public double subtotalDouble(){
       return subtotal.doubleValue();
    }
}
