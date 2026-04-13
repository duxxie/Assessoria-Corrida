package assessoria.util.log;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Log {

    private static final String arquivo = "src/main/java/assessoria/log.txt";

    //erro
    public static void registrarErro(String mensagem) {
        registrar("ERROR - " + mensagem);
    }

    //erro com throwable
    public static void registrarErro(String mensagem, Throwable cause) {
        StringBuilder sb = new StringBuilder(mensagem);

        if(cause != null) {
            sb.append(" | Exception: ").append(cause.getClass().getSimpleName());

            if(cause.getCause() != null) {
                sb.append(" | Causa original: ")
                        .append(cause.getCause().getClass().getSimpleName());

                if(cause.getCause().getMessage() != null) {
                    sb.append(" - ").append(cause.getCause().getMessage());
                }
            }
        }

        registrar("ERROR - " + sb);
    }


    //warn
    public static void registrarAviso(String mensagem) {
        registrar("WARN - " + mensagem);
    }

    //info
    public static void registrarInfo(String mensagem) {
        registrar("INFO - " + mensagem);
    }

    public static void registrar(String mensagem) {
        try(FileWriter fileWriter = new FileWriter(arquivo, true)) {
            LocalDateTime agora = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String dataHora = agora.format(formatter);
            fileWriter.write("[" + dataHora + "] " + mensagem + "\n");
        }catch (IOException e) {
            System.out.println("Erro ao adicionar log no arquivo!!");
        }
    }
}
