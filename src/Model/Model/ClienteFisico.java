package Model.Model;

import java.io.Serializable;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
 
@Getter
@Setter
@NoArgsConstructor

@DiscriminatorValue(value = "CLIENTE_FISICO")
@Entity
public class ClienteFisico extends Cliente implements Serializable{

    private String nif;
    
}
