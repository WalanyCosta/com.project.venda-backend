package Model.Model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor

@Embeddable
public class Endereco implements Serializable {
    
    @Column(name = "n_casa")
    private Integer numeroCasa;
    
    private String rua;

    private String municipio;
  
    private String bairro;
    
}
