package assessoria.dao;

import assessoria.model.Administrador;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class AdministradorDAO {
    private final String arquivoAdministrador = "src/main/java/assessoria/util/users/administrador.json";

    public AdministradorDAO(){}

    public void inserirAdministradorNoArquivo(Map<String, Administrador> administradorMap) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        try{
            objectMapper.writeValue(new File(arquivoAdministrador), administradorMap);
            System.out.println("Arquivo escrito com sucesso");
        } catch (IOException e) {
            System.out.println("Erro ao escrever no arquivo");
            System.out.println(e.getMessage());
        }
    }
}
