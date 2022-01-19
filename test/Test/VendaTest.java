package Test;

import Model.Model.Cliente;
import Model.Model.ClienteFisico;
import Model.Model.Venda;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.AfterClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;


public class VendaTest {
    
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
        Cliente cliente = entityManager.find(ClienteFisico.class, 2);
        Venda venda = new Venda();
        venda.setSerie("5030304040");
        venda.setDataVenda(LocalDate.now());
        venda.setTroco(BigDecimal.ZERO);
        venda.setValorPago(new BigDecimal(500.00));
        venda.setCliente(cliente);
        entityManager.getTransaction().begin();
        entityManager.persist(venda);
        entityManager.getTransaction().commit();
    }
    
    @Test
    public void findById(){
        Venda vendedor = entityManager.find(Venda.class,2);
        assertEquals("Elsa", vendedor.getVendedor().getNome());
    }

        
    @Test
    public void findAll(){
       List<Venda> listVenda = entityManager
                        .createQuery("from Venda v", Venda.class)
                        .getResultList();
        assertEquals(2, listVenda.size());
    }
   
    @Test
    public void remove(){
        Venda vendedora = entityManager.find(Venda.class, 3);
        entityManager.getTransaction().begin();
        entityManager.remove(vendedora);
        entityManager.getTransaction().commit();
    }
    
    @Test
    public void editar(){
        Venda func_comum = entityManager.find(Venda.class, 2);
        func_comum.setTroco(new BigDecimal(30));
        entityManager.getTransaction().begin();
        entityManager.merge(func_comum);
        entityManager.getTransaction().commit();
    }
}
