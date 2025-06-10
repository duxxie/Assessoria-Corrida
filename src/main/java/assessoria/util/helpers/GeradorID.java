package assessoria.util.helpers;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

public class GeradorID {

    private static int idAluno = 0;
    private static int idProfessor = 0;
    private static int idAdministrador = 0;
    private static int idTreino = 0;

    public static String gerarIdAluno() {
        return "Alu" + ++idAluno;
    }

    public static String gerarIdProfessor() {
        return "Pro" + ++idProfessor;
    }

    public static String gerarIdAdministrador() {
        return "Adm" + ++idAdministrador;
    }

    public static String gerarIdTreino() {
        return "Tre" + ++idTreino;
    }

    public static void carregarIds() {
        try(FileReader reader = new FileReader("src/main/java/assessoria/model/dados/ids.properties")) {
            Properties properties = new Properties();
            properties.load(reader);
            idAluno = Integer.parseInt(properties.getProperty("aluno", "0"));
            idProfessor = Integer.parseInt(properties.getProperty("professor", "0"));
            idAdministrador = Integer.parseInt(properties.getProperty("administrador", "0"));
            idTreino = Integer.parseInt(properties.getProperty("treino", "0"));
        }catch (IOException e) {
            System.out.println("Erro ao carregar os ids no arquivo!!");
        }
    }

    public static void salvarIds() {
        try(FileWriter writer = new FileWriter("src/main/java/assessoria/model/dados/ids.properties")) {
            Properties properties = new Properties();
            properties.setProperty("aluno", String.valueOf(idAluno));
            properties.setProperty("professor", String.valueOf(idProfessor));
            properties.setProperty("administrador", String.valueOf(idAdministrador));
            properties.setProperty("treino", String.valueOf(idTreino));
            properties.store(writer, "Latest IDs saved.");
        }catch (IOException e) {
            System.out.println("Erro ao salvar id's no arquivo!!");
        }
    }
}
