package Test;

import Model.Model.Funcionario;
import Model.Model.Historico;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.AfterClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;

public class HistoricoTest {
    
    private static EntityManager entityManager;
    private static EntityManagerFactory emf;
    
    @BeforeClass
    public static void abrindoConexao(){
        emf = Persistence.createEntityManagerFactory("Vendas-PU");
        entityManager = emf.createEntityManager();
    }
    
    @AfterClass
    public static void fechandoConexao(){
        entityManager.close();
        emf.close();
    }
    
    @Test
    public void executar(){
        Funcionario funcionario=entityManager.find(Funcionario.class, 2);
        Historico historico = new Historico();
        historico.setDataHistorico(LocalDate.now());
        historico.setHoraHistorico(LocalTime.now());
        historico.setOperacao("Eliminou usuario");
        historico.setFuncionario(funcionario);
        entityManager.getTransaction().begin();
        entityManager.persist(historico);
        entityManager.getTransaction().commit();
    }
    
    @Test
    public void findById(){
        Historico historico = entityManager.find(Historico.class, 1);
        assertEquals("Elsa", historico.getFuncionario().getNome());
    }
   
    @Test
    public void findAll(){
       List<Historico> listHistorico = entityManager
                        .createQuery("from Historico f", Historico.class)
                        .getResultList();
        assertEquals(1, listHistorico.size());
    }
   
    @Test
    public void remove(){
        Historico vendedora = entityManager.find(Historico.class, 2);
        entityManager.getTransaction().begin();
        entityManager.remove(vendedora);
        entityManager.getTransaction().commit();
    }
}
