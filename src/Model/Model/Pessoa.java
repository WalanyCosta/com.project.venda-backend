package Model.Model;

import java.io.Serializable;

import javax.persistence.MappedSuperclass;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

@EqualsAndHashCode(onlyExplicitlyIncluded = true)

@MappedSuperclass
public abstract class Pessoa implements Serializable{
    
    private String nome;
    
    private String email;
    
}
