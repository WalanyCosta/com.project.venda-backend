package Model.Model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.*;


@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)

@Entity
@Table(name = "tb_caixa")
public class Caixa{
    
    @EqualsAndHashCode.Include
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(name = "saldo_inicial")
    private BigDecimal saldoInicial;
    
    @Column(name = "saldo_final")
    private BigDecimal saldoFinal;
    
    @Column(name = "data_caixa")
    private LocalDate dataCaixa;

    @Column(name="hora_entrada")
    private LocalTime horaEntrada;
    
    @Column(name="hora_saida")
    private LocalTime horaSaida;
    
    @ManyToOne
    @JoinColumn(name = "funcionario_id", foreignKey = @ForeignKey(name = "Funcionario_Caixa"))
    private Funcionario funcionario;
    
    public boolean isCaixaSemanalAtual(Integer a){
        LocalDate semanaActual = LocalDate.now().minusWeeks(0);
        return semanaActual.plusDays(a).isEqual(dataCaixa.plusDays(1));
    }
    
    public double saldoFinalDouble(){
        return saldoFinal.doubleValue();
    }
}
