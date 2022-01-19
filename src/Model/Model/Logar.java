package Model.Model;

import Model.Exception.MensagemException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Logar {

    private Map<String,Integer> tentativa=new HashMap<>();
    
    public Funcionario logar(String login, String senha, List<Funcionario> fun) throws MensagemException{
        Funcionario funcionario = pesquisar(login, fun);
        
        if(tentativa.containsKey(login) && tentativa.get(login) >= 3){
            tentativa.put(login, tentativa.get(login) + 1);
            throw new MensagemException("Acabaste com todas as tentativas");
        }
        
        if(funcionario.confirmaSenha(senha)){
           if(funcionario.perfilAveriguar()) throw new MensagemException("Este usuario não pode acender o sistema");
           return funcionario;
        }
        Integer quant=0;
        if(tentativa.containsKey(login)){
            quant += tentativa.get(login);
        }
        tentativa.put(login, quant);
        String msm = quant == 2 ? " esta é a ultima tentativa" : " tem duas tentativa";
        throw new MensagemException("Senha incorreta :" + msm);
    }
    
    private Funcionario pesquisar(String login,List<Funcionario> fun) throws MensagemException{
        try{          
            return fun.stream()
                     .filter(fs -> fs.verificaUsuario(login))
                     .findAny().get();
        }catch(Exception ex){
            throw new MensagemException("Este usuario não existe no banco de dados");
        }
    }
    
}
