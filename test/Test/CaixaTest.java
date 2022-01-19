package Test;

import Model.Model.Caixa;
import Model.Model.Funcionario;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.AfterClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;

public class CaixaTest {
     
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
        Funcionario eunice = entityManager.find(Funcionario.class,3);
        Caixa caixa = new Caixa();
        caixa.setSaldoInicial(new BigDecimal(0));
        caixa.setSaldoFinal(new BigDecimal(8000));
        caixa.setFuncionario(eunice);
        entityManager.getTransaction().begin();
        entityManager.persist(caixa);
        entityManager.getTransaction().commit();
    }
   
     @Test
    public void findById(){
        Caixa caixa = entityManager.find(Caixa.class,2);
         System.out.println(caixa.getDataCaixa());
        assertEquals("Victor", caixa.getFuncionario().getNome());
    }

    @Test
    public void findAll(){
       List<Caixa> list = entityManager
                        .createQuery("from Caixa c", Caixa.class)
                        .getResultList();
       assertEquals(13, list.size());
    }
   
    @Test
    public void remove(){
        Caixa caixa = entityManager.find(Caixa.class, 3);
        entityManager.getTransaction().begin();
        entityManager.remove(caixa);
        entityManager.getTransaction().commit();
    }
    
    @Test
    public void buscandoCaixaSemanalActual(){
        Caixa caixa = entityManager.find(Caixa.class, 3);
        System.out.println(caixa.getDataCaixa());
        assertTrue(caixa.isCaixaSemanalAtual(3));
    }
}
