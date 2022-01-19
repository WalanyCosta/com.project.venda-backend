package Model.Model;

import Model.Enum.EStatus;
import Model.Enum.EPerfil;
import Model.Enum.EGenero;
import java.time.LocalDate;
import java.util.List;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Version;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name = "tb_funcionario",indexes = @Index(name = "Id_email",columnList = "email"))
public class Funcionario extends Pessoa{
     
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Version
    private Integer versao;

    @Column(name = "data_nasc")
    private LocalDate dataNascimento;
    
    @Column(length = 13)
    private String nif;
    
    @Enumerated(EnumType.STRING)
    private EGenero genero;
    
    @Embedded
    private Endereco endereco;
    
    private String senha;
    
    @Enumerated(EnumType.STRING)
    private EPerfil perfil;
    
    @Enumerated(EnumType.STRING)
    private EStatus status;
    
    @ElementCollection
    @CollectionTable(name = "func_telefone",joinColumns = @JoinColumn(name = "funcionario_id"))
    private List<String> telefone;
    
    public boolean confirmaSenha(String confirm){
        return senha.equals(confirm);
    }
    
    public boolean perfilAveriguar(){
        return ! (perfil.equals(EPerfil.ADM) || perfil.equals(EPerfil.VENDEDOR));
    }
    
    public boolean verificaUsuario(String email){
        return getEmail().equals(email);
    }
}
