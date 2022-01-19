package Model.Model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import javax.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)

@Entity
@Table(name = "tb_venda",indexes = @Index(name = "In_serie",columnList = "serie",unique = true))
public class Venda implements Serializable{
    
    @EqualsAndHashCode.Include
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String serie;
    
    private BigDecimal valorPago;

    private BigDecimal troco;
    
    private BigDecimal total;
    
    @Column(name = "data_venda")
    private LocalDate dataVenda;
    
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;
    
    @ManyToOne
    @JoinColumn(name = "funcionario_id")
    private Funcionario vendedor;
    
    public boolean isGrafico(int semana, int dia){
        LocalDate semanas = LocalDate.now().minusWeeks(semana);
        if(dataVenda == null) return false;
        return dataVenda.plusDays(1).isEqual(semanas.plusDays(dia));
    }
    
    public boolean isGraficoDiario(){
        return this.isGrafico(0, 0);
    }
    
    public double totalDouble(){
        return total.doubleValue();
    }
    
    public boolean isConfirmarSerie(String param){
        System.out.println(serie.equals(param));
        return serie.equals(param);
    }


        
}
