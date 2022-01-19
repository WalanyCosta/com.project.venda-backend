package Test;

import Model.Graficos.G_CaixaSA;
import Model.Graficos.G_VendaDiario;
import Model.Graficos.G_VendaSemanal;
import Model.Model.Caixa;
import Model.Model.Venda;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class TestGraficos {
    
    private static EntityManager entityManager;
    private static EntityManagerFactory emf;
    
    @BeforeClass
    public static void abrindoConnection(){
        emf=Persistence.createEntityManagerFactory("Vendas-PU");
        entityManager=emf.createEntityManager();
    }
    
    @AfterClass
    public static void fechandoConnection() {
        entityManager.close();
        emf.close();
    }
    
    @Test
    public void graficoSemanalVenda(){
        List<Venda> listaVenda = entityManager
                .createQuery("from Venda v", Venda.class)
                .getResultList();
        List<BigDecimal> listVendas = new G_VendaSemanal()
                                .gerarGrafico(listaVenda);
        System.out.println(listVendas.get(3).doubleValue());
        assertEquals(5, listVendas.size());
    }
    
    @Test
    public void graficoDiarioVenda(){
        List<Venda> listaVenda = entityManager
                .createQuery("from Venda v", Venda.class)
                .getResultList();
        BigDecimal quant = new G_VendaDiario().gerarGrafico(listaVenda);
        assertEquals(500, quant.doubleValue(), 0.0);
    }
    
    @Test
    public void graficoSemanaActualCaixa(){
        List<Caixa> listaCaixa = entityManager
                .createQuery("from Caixa c", Caixa.class)
                .getResultList();
        List<BigDecimal> list = new G_CaixaSA()
                               .gerarGrafico(listaCaixa);
        assertEquals(7, list.size());
    }
  
    
}
