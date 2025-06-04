package assessoria.dao;

import assessoria.model.Aluno;
import assessoria.view.MensagemView;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

public abstract class GenericDAO<Type> {

    private final MensagemView mensagemView = new MensagemView();
    private final Class<Type> typeClass;

    public GenericDAO(Class<Type> typeClass) {
        this.typeClass = typeClass;
    }

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

    public Map<String,Type> lerDadosDoArquivo(Map<String, Type> typeMap, String caminhoArquivo) {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            return objectMapper.readValue(new File(caminhoArquivo), objectMapper.getTypeFactory().constructMapType(LinkedHashMap.class, String.class, typeClass));
            //mensagemView.mostrarSucesso("Map de " + getNomeClass() + "carregado");


        } catch (Exception e) {
            mensagemView.mostrarErro("Erro ao carregar map de " + getNomeClass());
            throw new RuntimeException(e);
        }
    }

    public String getNomeClass() {
        return getTypeClass().getSimpleName();
    }

    public Class<Type> getTypeClass() {
        return this.typeClass;
    }
}
