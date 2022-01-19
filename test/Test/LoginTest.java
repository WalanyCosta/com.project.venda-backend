package Test;

import Model.Exception.MensagemException;
import Model.Model.Funcionario;
import Model.Model.Logar;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.AfterClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;

public class LoginTest {
    private static EntityManager entityManager;
    private static EntityManagerFactory emf;
    private static List<Funcionario> list;
    
    @BeforeClass
    public static void abrindoConexao(){
        emf = Persistence.createEntityManagerFactory("Vendas-PU");
        entityManager = emf.createEntityManager();
        list = entityManager
                .createQuery("from Funcionario f", Funcionario.class)
                .getResultList();
    }
    
    @AfterClass
    public static void fechandoConexao(){
        entityManager.close();
        emf.close();
    }
    
    @Test
    public void usuarioNaoExiste(){
        Logar logar = new Logar();
        try{
           
            System.out.println( logar.logar("Edna@gmail.com", "123", list));
        }catch(MensagemException e){
            assertEquals("Este usuario não existe no banco de dados", e.getMessage());
        }
    }
    
       @Test
    public void perfilInvalido(){
        Logar logar = new Logar();
        try{
            logar.logar("Elsa@gmail.com", "", list);
        }catch(MensagemException e){
            assertEquals("Este usuario não pode acender o sistema", e.getMessage());
        }
    }
    
    @Test
    public void SenhaInvalido(){
        Logar logar = new Logar();
        try{
            logar.logar("Eunice@gmail.com", "223", list);
        }catch(MensagemException e){
            assertEquals("Senha incorreta : tem duas tentativa", e.getMessage());
        }
    }
    
    @Test
    public void entrou(){
        Logar logar = new Logar();
        Funcionario funcionario = null ;
        try{
          funcionario = logar.logar("Eunice@gmail.com", "123", list);
        }catch(MensagemException e){}
        assertEquals("Eunice", funcionario.getNome());
    }
    
 
}
