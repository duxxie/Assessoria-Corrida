package assessoria;
import assessoria.model.Clientes;
import assessoria.model.Professores;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import javax.swing.*;
import java.io.File;
import java.util.LinkedHashMap;
import java.util.Map;


public class Main {

     public static final File arquivoUsers = new File("src/main/java/assessoria/util/users/usuarios.json");
     //public static final File arquivoProfessores = new File("src/main/java/assessoria/util/users/professores.json");

    public static void main(String[] args) {
        System.out.println("Assessoria-Corrida");

        Map<String, Clientes> mapClientes = new LinkedHashMap<>();

        mapClientes.put("K3",new Clientes(3, "Gustavo", "123.476.559-75", 20));
        mapClientes.put("K1", new Clientes(1, "Raul Kon Uk Nakama", "234.432.334-54", 34));
        mapClientes.put("K2", new Clientes(2, "Fredd", "434.565.443-90", 54));

        //Map<String, Professores> mapProfessores = new LinkedHashMap<>();

        //mapProfessores.put("K3", new Professores(3, "Joacir", "232.111.454-34", 60));
        //mapProfessores.put("K1", new Professores(1, "Joselma", "443.343.776-21", 57));

        try{
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
            objectMapper.writeValue(arquivoUsers, mapClientes);
            System.out.println("Arquivo escrito com sucesso");

        } catch (Exception e) {
            System.out.println("Erro ao escrever no arquivo");
            System.out.println(e.getMessage());
        }
    }
}