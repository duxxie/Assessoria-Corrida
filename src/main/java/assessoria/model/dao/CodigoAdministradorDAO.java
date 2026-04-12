package assessoria.model.dao;

import assessoria.model.entidades.CodigoAdministrador;
import assessoria.util.log.Log;
import assessoria.view.MensagemView;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CodigoAdministradorDAO{

    private final String caminhoArquivo = "src/main/java/assessoria/model/dados/users/administrador/codigoAdministrador.json";


    public CodigoAdministradorDAO() {}

    public void inserirDadosNoArquivo(List<CodigoAdministrador> codigoAdministradorList) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        try{
            objectMapper.writeValue(new File(caminhoArquivo), codigoAdministradorList);
        } catch (IOException e) {
            MensagemView.mostrarErro("Erro ao tentar salvar dados!!");
            Log.registrarErro( "Falha ao salvar dados do codigoAdministrador no arquivo");
            System.out.println(e.getMessage());
        }
    }

    public List<CodigoAdministrador> lerDadosDoAquivo() {
        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File(caminhoArquivo);
        if(!file.exists() || file.length() == 0) return new ArrayList<>();

        try {
            return objectMapper.readValue(file, objectMapper.getTypeFactory().constructCollectionType(ArrayList.class, CodigoAdministrador.class));
        } catch (Exception e) {
            MensagemView.mostrarErro("Erro ao carregar list do codigo administrador");
            throw new RuntimeException(e);
        }
    }
//
//    @Override
//    public void inserirDadosNoArquivo(Map<String, CodigoAdministrador> typeMap) {
//        super.inserirDadosNoArquivo(typeMap);
//    }
//
//    @Override
//    public Map<String, CodigoAdministrador> lerDadosDoArquivo() {
//        return super.lerDadosDoArquivo();
//    }
//
//    @Override
//    public String getCaminhoArquivo() {
//        return caminhoArquivo;
//    }
}
