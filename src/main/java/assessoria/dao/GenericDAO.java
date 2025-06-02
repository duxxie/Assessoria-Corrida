package assessoria.dao;

import assessoria.model.Aluno;
import assessoria.view.MensagemView;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public abstract class GenericDAO<Type> {

    private final MensagemView mensagemView = new MensagemView();

    public void inserirDadosNoArquivo(Map<String, Type> typeMap, String caminhoArquivo) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        try{
            objectMapper.writeValue(new File(caminhoArquivo), typeMap);
        } catch (IOException e) {
            mensagemView.mostrarErro("Erro ao tentar salvar dados!!");
            System.out.println(e.getMessage());
        }
    }
}
