package assessoria.util.log;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Log {

    private static final String arquivo = "src/main/java/assessoria/log.txt";

    public static void registrar(String tipo, String mensagem) {
        try(FileWriter fileWriter = new FileWriter(arquivo, true)) {
            LocalDateTime agora = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String dataHora = agora.format(formatter);
            fileWriter.write("[" + dataHora + "] " + tipo.toUpperCase() + " - " + mensagem + "\n");
        }catch (IOException e) {
            System.out.println("Erro ao adicionar log no arquivo!!");
        }
    }
}
