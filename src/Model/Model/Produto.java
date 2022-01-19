package Model.Model;

import Model.Enum.EStatusProduto;
import Model.Notificacao.NotificacaoDTO;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.MathContext;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import javax.persistence.*;
import lombok.*;

@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name = "tb_produto",indexes = @Index(name = "In_cod",columnList = "cod_barra"))
public class Produto implements Serializable{
    
    @EqualsAndHashCode.Include
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Version
    private Integer versao;

    @Column(name = "cod_barra")
    private String codigoBarra;
    
    private String nome;
    
    private BigDecimal preco;
    
    private BigDecimal peso;
    
    @Column(name = "data_validade")
    private LocalDate dataValidade;
    
    @Enumerated(EnumType.STRING)
    private EStatusProduto statusProduto;
    
    @OneToOne(mappedBy = "produto",cascade = CascadeType.ALL)
    private Stock stock;
    
    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;
    
    public boolean isExpirar(){
        return 2 > ChronoUnit.MONTHS.between(dataValidade, LocalDate.now());
    }
    
    public NotificacaoDTO mensagemNotificacao(){
        LocalDate dataHoje = LocalDate.now();
        String msm = nome + "com a " + dataValidade + " está prá expirar no proximo mês";
        if(dataHoje.isEqual(dataValidade) || dataHoje.isAfter(dataValidade)){
            statusProduto  = EStatusProduto.EXPIRADO;
            msm = nome + " com a " + dataValidade + " expirou e o sistema já o removeu";
        }
        return new NotificacaoDTO(dataHoje, "", msm);
    }

    public BigDecimal CalcularSubtotal(Integer param){
        return preco.multiply(new BigDecimal(param.intValue()), MathContext.UNLIMITED);
    }
    
    public void diminuirStockMn(Integer quant){
        stock.OperacaoStockMinimo(quant);
    }
}
