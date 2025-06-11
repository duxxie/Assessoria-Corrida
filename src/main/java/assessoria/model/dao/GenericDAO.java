package assessoria.model.dao;

import assessoria.model.entidades.Savable;
import assessoria.util.log.Log;
import assessoria.view.MensagemView;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

public abstract class GenericDAO<T extends Savable> {

    private final MensagemView mensagemView = new MensagemView();
    private final Class<T> typeClass;
    private final String caminhoBase = "src/main/java/assessoria/model/dados/";

    public GenericDAO(Class<T> typeClass) {
        this.typeClass = typeClass;
    }

    public void inserirDadosNoArquivo(Map<String, T> typeMap) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        try{
            objectMapper.writeValue(new File(getCaminhoBase() + getCaminhoArquivo()), typeMap);

        } catch (IOException e) {
            mensagemView.mostrarErro("Erro ao tentar salvar dados!!");
            Log.registrar("Error", "Falha ao tentar salvar dados ");
            System.out.println(e.getMessage());
        }
    }

    public Map<String,T> lerDadosDoArquivo() {
        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File(getCaminhoBase() + getCaminhoArquivo());
        if(!file.exists() || file.length() == 0) {
            return new LinkedHashMap<>();
        }
        try {
            return objectMapper.readValue(file, objectMapper.getTypeFactory().constructMapType(LinkedHashMap.class, String.class, typeClass));
            //mensagemView.mostrarSucesso("Map de " + getNomeClass() + "carregado");


        } catch (Exception e) {
            mensagemView.mostrarErro("Erro ao carregar map de " + getNomeClass());
            throw new RuntimeException(e);
        }
    }

    public String getCaminhoBase() {
        return caminhoBase;
    }

    public abstract String getCaminhoArquivo();

    public String getNomeClass() {
        return getTypeClass().getSimpleName();
    }

    public Class<T> getTypeClass() {
        return this.typeClass;
    }
}
