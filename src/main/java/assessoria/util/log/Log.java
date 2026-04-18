package assessoria.util.log;


import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

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

    //info de alteracao de dados de entidade
    public static void registrarAlteracao(String entidade, String id, String campo, String antes, String depois) {
        if(!Objects.equals(antes, depois)) {
            registrarInfo(entidade + " atualizado. Id=" + id
                + " | Campo=" + campo
                + " | Antes=" + antes
                + " | Depois=" + depois);
        }
    }

    //info de alteracao de dados de entidade sensivel
    public static void registrarAlteracaoSensivel(String entidade, String id, String campo) {
        registrarInfo(entidade + " atualizado. Id="+ id +" | Campo=" + campo
            + " | Alteracao realizada com sucesso");
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
