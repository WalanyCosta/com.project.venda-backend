package Test;

import Model.Enum.EStatusProduto;
import Model.Model.Categoria;
import Model.Model.Produto;
import Model.Model.Stock;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.AfterClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;

public class ProdutoTest {

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
        Stock stock = new Stock();
        stock.setStockReal(new BigInteger("200"));
        stock.setStockMinimo(BigInteger.ZERO);
        Categoria desporto = entityManager.find(Categoria.class,2);
        Produto bola = new Produto();
        bola.setNome("Bola-baskete");
        bola.setPreco(new BigDecimal(5000));
        bola.setStock(stock);
        bola.setStatusProduto(EStatusProduto.EM_USO);
        bola.setCategoria(desporto);
        stock.setProduto(bola);
        entityManager.getTransaction().begin();
        entityManager.persist(bola);
        entityManager.getTransaction().commit();
    }
   
     @Test
    public void findById(){
        Produto produto = entityManager.find(Produto.class,4);
        assertEquals("himpopo", produto.getNome());
    }

    @Test
    public void findAll(){
       List<Produto> list = entityManager
                        .createQuery("from Produto p")
                        .getResultList();
       assertEquals(3, list.size());
    }
   
    @Test
    public void remove(){
        Produto produto = entityManager.find(Produto.class, 2);
        entityManager.getTransaction().begin();
        entityManager.remove(produto);
        entityManager.getTransaction().commit();
    }
    
    @Test
    public void vendoProdutoExpirado(){
        Produto produto = entityManager.find(Produto.class, 4);
        assertTrue(produto.isExpirar());
    }
    
    @Test
    public void operacaoProdutoASerComprado(){
        Produto produto = entityManager.find(Produto.class, 2);
        assertEquals(300.00, produto.CalcularSubtotal(2).doubleValue(), 0.5);
    }

    @Test
    public void mensagemDeProduto(){
        Produto produto = entityManager.find(Produto.class, 4);
        produto.isExpirar();
        assertEquals("himpopo com a 2020-08-19 expirou e o sistema já o removeu",
                produto.mensagemNotificacao().getMensagem());
    }
    
    @Test
    public void testData(){
        LocalDate dataAnterior = LocalDate.of(2020, Month.AUGUST, 20);
        LocalDate hoje = LocalDate.now();
        if(dataAnterior.isEqual(hoje) || dataAnterior.isBefore(hoje)){
            System.out.println(dataAnterior);
            assertEquals("2020-08-20", dataAnterior+"");
        }
    }
    
}
