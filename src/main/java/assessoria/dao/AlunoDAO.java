package assessoria.dao;

import assessoria.model.Aluno;
import assessoria.model.Pessoa;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class AlunoDAO extends PessoaDAO {

    private final String arquivoUsuarios = "src/main/java/assessoria/util/users/aluno.json";

    public void inserirAlunoNoArquivo(Map<String, Aluno> alunoMap) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        try{
            objectMapper.writeValue(new File(arquivoUsuarios), alunoMap);
            System.out.println("Arquivo escrito com sucesso");
        } catch (IOException e) {
            System.out.println("Erro ao escrever no arquivo");
            System.out.println(e.getMessage());
        }
    }
}