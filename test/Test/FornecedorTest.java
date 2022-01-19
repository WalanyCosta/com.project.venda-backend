package Test;

import Model.Model.Fornecedor;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.AfterClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;

public class FornecedorTest {
    
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
        Fornecedor carne = new Fornecedor();
        carne.setNome("techana");
        entityManager.getTransaction().begin();
        entityManager.persist(carne);
        entityManager.getTransaction().commit();
    }
    
    @Test
    public void findById(){
        Fornecedor vendedor = entityManager.find(Fornecedor.class,4);
        assertEquals("angoMart", vendedor.getNome());
    }
        
    @Test
    public void findAll(){
       List<Fornecedor> listFornecedor = entityManager
                        .createQuery("from Fornecedor f", Fornecedor.class)
                        .getResultList();
        assertEquals(3, listFornecedor.size());
    }
   
    @Test
    public void remove(){
        entityManager.clear();
        Fornecedor fornecedor = entityManager.find(Fornecedor.class, 3);
        entityManager.getTransaction().begin();
        entityManager.remove(fornecedor);
        entityManager.getTransaction().commit();
    }
    
    @Test
    public void editar(){
        Fornecedor empresa = entityManager.find(Fornecedor.class, 2);
        empresa.setNome("AngoMart");
        entityManager.getTransaction().begin();
        entityManager.merge(empresa);
        entityManager.getTransaction().commit();
    }
    
}
