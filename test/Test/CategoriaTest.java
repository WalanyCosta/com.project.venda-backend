package Test;

import Model.Model.Caixa;
import Model.Model.Categoria;
import Model.Model.Categoria;
import Model.Model.Produto;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.AfterClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;

public class CategoriaTest {
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
        Categoria categoria = new Categoria();
        categoria.setCategoria("Materias Eletronicos");
        entityManager.getTransaction().begin();
        entityManager.persist(categoria);
        entityManager.getTransaction().commit();
    }
   
     @Test
    public void findById(){
        Categoria categoria = entityManager.find(Categoria.class,1);
        assertEquals("bebidas", categoria.getCategoria());
    }

    @Test
    public void findAll(){
       List<Categoria> list = entityManager
                        .createQuery("from Categoria c")
                        .getResultList();
       assertEquals(2, list.size());
    }
   
    @Test
    public void remove(){
        Categoria categoria = entityManager.find(Categoria.class, 3);
        entityManager.getTransaction().begin();
        entityManager.remove(categoria);
        entityManager.getTransaction().commit();
    }
    
    @Test
    public void buscarProdutoDestaCategoria(){
        Categoria categoria = entityManager.find(Categoria.class, 2);
        List<Produto> listproduto = categoria.getListProduto();
        assertEquals(2, listproduto.size());
    }
    
    @Test
    public void editar(){
        Categoria categoria = entityManager.find(Categoria.class, 1);
        categoria.setCategoria("bebidas");
        entityManager.getTransaction().begin();
        entityManager.merge(categoria);
        entityManager.getTransaction().commit();
    }
}
