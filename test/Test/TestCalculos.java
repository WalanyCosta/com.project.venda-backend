package Test;

import Model.Calculos.Calculos;
import Model.Model.Caixa;
import Model.Model.ItemVenda;
import Model.Model.Produto;
import Model.Model.Stock;
import Model.Model.Venda;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class TestCalculos {
    
    private static EntityManager entityManager;
    private static EntityManagerFactory emf;
    
    @BeforeClass
    public static void abrindoConexao() {
        emf = Persistence.createEntityManagerFactory("Vendas-PU");
        entityManager = emf.createEntityManager();
    }
    
    @AfterClass
    public static void fechandoConexao() {
        entityManager.close();
        emf.close();
    }
    
    @Test
    public void DiminuindoStock(){
        Stock alterar = entityManager.find(Stock.class, 1);
        alterar.setStockReal(new Calculos().retirarStockMx(alterar, 20));
        assertEquals(80, entityManager.find(Stock.class, 1).getStockReal().intValue());
    }
    
    @Test
    public void aumentandoStockMinimo(){
        Stock alterar = entityManager.find(Stock.class, 1);
        alterar.setStockMinimo(new Calculos().aumentarStockMn(alterar,10));
        assertEquals(40, entityManager.find(Stock.class, 1).getStockMinimo().intValue());
    }
    
    @Test
    public void aumentadoStock(){
        Stock alterar = entityManager.find(Stock.class, 2);
        alterar.setStockReal(new Calculos().aumentarStockMx(alterar, 20));
        assertEquals(120, entityManager.find(Stock.class, 2).getStockReal().intValue());
    }
    
    @Test
    public void calculandoSubtotal(){
        Produto produto = entityManager.find(Produto.class, 3);
        ItemVenda itemvenda = new ItemVenda();
        itemvenda.setQuant(2);
        itemvenda.setProduto(produto);
        itemvenda.setSubtotal(new Calculos().calcularSubtotal(produto, 6));
        entityManager.getTransaction().begin();
        entityManager.persist(itemvenda);
        entityManager.getTransaction().commit();
    }
    
    @Test 
    public void calculandototal(){
        List <ItemVenda> list = entityManager
                .createQuery("from ItemVenda i",
                        ItemVenda.class)
                .getResultList();
        BigDecimal total = new Calculos().calcularTotalVenda(list);
        assertEquals(400.0, total.doubleValue(),0.0);
    }
    
    @Test
    public void calculandoSaldoCaixaDiario(){
        List<Venda> list= entityManager
                .createQuery("from Venda c", Venda.class)
                .getResultList();
        
        Caixa caixa = new Caixa();
        caixa.setSaldoInicial(new BigDecimal(0));
        caixa.setSaldoFinal(new Calculos().calculoSaldoCaixa(list));
        caixa.setDataCaixa(LocalDate.now());
        caixa.setHoraEntrada(LocalTime.of(8, 30));
        caixa.setHoraSaida(LocalTime.now());
        entityManager.getTransaction().begin();
        entityManager.persist(caixa);
        entityManager.getTransaction().commit();
    }
}
