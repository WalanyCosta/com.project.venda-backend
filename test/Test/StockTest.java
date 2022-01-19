package Test;

import static org.junit.Assert.*;
import Model.Model.Produto;
import Model.Model.Stock;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.AfterClass;
import org.junit.Test;
import org.junit.BeforeClass;
public class StockTest {
    
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
        Produto produto = new Produto();
        produto.setNome("Bananas");
        produto.setPreco(new BigDecimal(35));
        Stock stock=new Stock();
        stock.setDataStock(LocalDate.now());
        stock.setStockReal(new BigInteger("300"));
        stock.setStockMinimo(new BigInteger("100"));
        stock.setProduto(produto);
        entityManager.getTransaction().begin();
        entityManager.persist(stock);
        entityManager.getTransaction().commit();
    }
   
     @Test
    public void findById(){
        Stock stock = entityManager.find(Stock.class, 2);
        System.out.println(stock.getProduto().getNome());
        assertEquals("eka", stock.getProduto().getNome());
    }

    @Test
    public void findAll(){
       List<Stock> list = entityManager
                        .createQuery("from Stock p")
                        .getResultList();
       assertEquals(2, list.size());
    }
   
    @Test
    public void remove(){
        Stock stock = entityManager.find(Stock.class, 1);
        entityManager.getTransaction().begin();
        entityManager.remove(stock);
        entityManager.getTransaction().commit();
    }
    
    @Test
    public void verificandoProdutoStock(){
       Stock stock = entityManager.find(Stock.class, 3);
       stock.setStockReal(BigInteger.ONE);
       assertTrue(stock.isProdStock());
    }
    
    @Test
    public void VerificaProdutoArmario(){
       Stock stock = entityManager.find(Stock.class, 3);
       stock.setStockMinimo(BigInteger.ONE);
       assertTrue(stock.isProdArmario());
    }
    
    @Test
    public void notificacao(){
       Stock stock = entityManager.find(Stock.class, 3);
       stock.setStockMinimo(BigInteger.ONE);
       assertEquals("tigra boa qualidade",stock
               .mensagemNotificacao(" boa qualidade").getMensagem());
    }
    
    @Test
    public void Calcularstock(){
       Stock stock = entityManager.find(Stock.class, 3);
        assertEquals(70, stock.OperacaoStockMaximo(30).intValue());
    }
    
    @Test
    public void CalcularStockMinimo(){
       Stock stock = entityManager.find(Stock.class, 3);
       try{
        stock.OperacaoStockMinimo(30);
       }catch(RuntimeException e){
           assertEquals(" O/A " + stock.getProduto().getNome()+ " já não tem na pratileira", e.getMessage());
       }
    }
}
