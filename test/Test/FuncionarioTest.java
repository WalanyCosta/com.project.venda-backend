package Test;

import Model.Enum.EPerfil;
import Model.Enum.EStatus;
import Model.Model.Endereco;
import Model.Model.Funcionario;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.AfterClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;

public class FuncionarioTest {

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
    public void testSenha(){
        Funcionario adm=entityManager.find(Funcionario.class, 3);
        assertTrue(adm.confirmaSenha("1235"));
    }
    
    
    @Test
    public void executar(){
        Endereco endereco=new Endereco();
        endereco.setRua("RUA DO ZOOL 64");
        Funcionario adm = new Funcionario();
        adm.setSenha("1235");
        adm.setNome("Maggue");
        adm.setPerfil(EPerfil.ADM);
        adm.setStatus(EStatus.FERIAS);
        adm.setEndereco(endereco);
        entityManager.getTransaction().begin();
        entityManager.persist(adm);
        entityManager.getTransaction().commit();
    }
    
    @Test
    public void findById(){
        Funcionario vendedor = entityManager.find(Funcionario.class,4);
        assertEquals("Elsa", vendedor.getNome());
    }

        
    @Test
    public void findAll(){
       List<Funcionario> listFuncionario = entityManager
                        .createQuery("from Funcionario f", Funcionario.class)
                        .getResultList();
       
        assertEquals(3, listFuncionario.size());
    }
   
    @Test
    public void remove(){
        Funcionario vendedora = entityManager.find(Funcionario.class, 1);
        entityManager.getTransaction().begin();
        entityManager.remove(vendedora);
        entityManager.getTransaction().commit();
    }
    
    @Test
    public void editar(){
        Funcionario func_comum = entityManager.find(Funcionario.class, 2);
        func_comum.setNome("Carter");
        func_comum.setEndereco(new Endereco(null, "RUA CANDELABRO", null, null));
        func_comum.setPerfil(EPerfil.FUNCIONARIO_COMUM);
        entityManager.getTransaction().begin();
        entityManager.merge(func_comum);
        entityManager.getTransaction().commit();
    }
    
    @Test
    public void averiguarperfil(){
        Funcionario adm=entityManager.find(Funcionario.class, 4);
        // Se o Funcionario tiver o perfil funcionario_comum retornará false então
        // Correto está esse mambo
        assertTrue(adm.perfilAveriguar());
    }

    @Test
    public void retornaRua(){
        entityManager.clear();
        Funcionario vendedor = entityManager.find(Funcionario.class, 2);
        assertEquals("RUA CANDELABRO", vendedor.getEndereco().getRua());
    }

    @Test
    public void retornPerfil(){
        Funcionario vendedor = entityManager.find(Funcionario.class, 2);
        System.out.println("valor é: "+vendedor.getPerfil());
    }
    
}
