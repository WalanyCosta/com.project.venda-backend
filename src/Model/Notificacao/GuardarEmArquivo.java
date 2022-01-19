package Model.Notificacao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class GuardarEmArquivo implements INotificaDAO {

    private final Path path = Paths.get("");
    private final Charset utf8 = StandardCharsets.UTF_8;
    private List<NotificacaoDTO> notificar =new ArrayList<>();
   
   
    @Override
    public void guardar(NotificacaoDTO NDTO) {
       try {   
        Files.createDirectories(path.getParent());
            try (BufferedWriter bw = Files.newBufferedWriter(path, utf8)) {
                bw.write(NDTO.toString());
                bw.flush();
            }
        } catch (IOException ex) {
            throw new RuntimeException(ex.getMessage());
        }

    }

    @Override
    public List<NotificacaoDTO> extrair() {
       List <NotificacaoDTO> lista = new ArrayList<>();
        try (BufferedReader reader= Files.newBufferedReader(path,utf8)){
             String linha = null;
             while((linha = reader.readLine()) != null){
                 String coluna[] = linha.split("-");
                 lista.add(new NotificacaoDTO(converter(coluna[0]), coluna[1], coluna[2]));
             }
        } catch (IOException ex) {
            throw new RuntimeException(ex.getMessage());
        }
        return lista;
    }

    @Override
    public void apagar() {
         try {
            Files.deleteIfExists(path);
        } catch (IOException ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }

    private LocalDate converter(String data) {
        String [] datas = data.split("-");
        return LocalDate.of( Integer.parseInt(datas[0]),
                Integer.parseInt(datas[1]) , Integer.parseInt(datas[2]));
    }
}
