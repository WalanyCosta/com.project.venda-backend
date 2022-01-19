package Test;

import Model.Model.Cliente;
import Model.Model.ClienteFisico;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.AfterClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;

public class ClienteTest {
    
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
    public void persistir(){
        Cliente cliente = new ClienteFisico();
        cliente.setNome("Melrim");
        entityManager.getTransaction().begin();
        entityManager.persist(cliente);
        entityManager.getTransaction().commit();
    }
    
    @Test
    public void findById(){
        ClienteFisico cliente = entityManager.find(ClienteFisico.class,1);
        assertEquals("Miguel", cliente.getNome());
    }

        
    @Test
    public void findAll(){
       List<ClienteFisico> listCliente = entityManager
                        .createQuery("from ClienteFisico c", ClienteFisico.class)
                        .getResultList();
        assertEquals(1, listCliente.size());
    }
   
    @Test
    public void remove(){
        ClienteFisico cliente2 = entityManager.find(ClienteFisico.class, 2);
        entityManager.getTransaction().begin();
        entityManager.remove(cliente2);
        entityManager.getTransaction().commit();
    }
    
    @Test
    public void editar(){
        ClienteFisico cliente = entityManager.find(ClienteFisico.class, 1);
        cliente.setNome("Miguel");
        entityManager.getTransaction().begin();
        entityManager.merge(cliente);
        entityManager.getTransaction().commit();
    }
   
    

}
