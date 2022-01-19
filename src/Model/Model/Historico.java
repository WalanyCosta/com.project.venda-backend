package Model.Model;

import java.io.Serializable;
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
@Table(name = "tb_historico")
public class Historico implements Serializable {
    
    @EqualsAndHashCode.Include
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String operacao;
    
    @Column(name = "data_hist")
    private LocalDate dataHistorico;
    @Column(name = "hora_hist")
    private LocalTime horaHistorico;
    
    @ManyToOne
    @JoinColumn(name = "funcionario_id",foreignKey = @ForeignKey(name = "Funcionario_Historico"))
    private Funcionario funcionario;
    
    
    
}
