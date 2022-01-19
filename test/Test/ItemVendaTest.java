package Test;

import Model.Model.ItemVenda;
import Model.Model.Venda;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import org.junit.AfterClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;

public class ItemVendaTest {
    
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
        ItemVenda itemVenda2 = new ItemVenda();
        itemVenda2.setSubtotal(new BigDecimal(300.00));
        Venda venda = new Venda();
        venda.setTotal(new BigDecimal(4000));
        entityManager.getTransaction().begin();
        entityManager.persist(itemVenda2);
        entityManager.getTransaction().commit();
    }
    
    @Test
    public void findById(){
        ItemVenda itemVenda = entityManager.find(ItemVenda.class, 2);
        assertFalse(itemVenda.getProduto().getNome().isEmpty());
    }
    
     @Test
    public void findAll(){
        TypedQuery<ItemVenda> result =
                 entityManager.createQuery("from ItemVenda v", ItemVenda.class);
        List<ItemVenda> listItemVenda = result.getResultList();
        assertEquals(2, listItemVenda.size());
    }
   
    @Test
    public void remove(){
        ItemVenda itemVenda = entityManager.find(ItemVenda.class, 3);
        entityManager.getTransaction().begin();
        entityManager.remove(itemVenda);
        entityManager.getTransaction().commit();
    }
    
    @Test
    public void editar(){
        ItemVenda itemVenda = entityManager.find(ItemVenda.class, 2);
        itemVenda.setQuant(124);
        itemVenda.setSubtotal(BigDecimal.ZERO);
        entityManager.getTransaction().begin();
        entityManager.merge(itemVenda);
        entityManager.getTransaction().commit();
    }
   
    
    
}
