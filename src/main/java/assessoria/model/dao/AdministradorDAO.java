package assessoria.model.dao;

import assessoria.model.entidades.Administrador;
import java.util.Map;

public class AdministradorDAO extends GenericDAO<Administrador>{

    private final String caminhoArquivo = "users/administrador/administrador.json";

    public AdministradorDAO() {
        super(Administrador.class);
    }

    @Override
    public void inserirDadosNoArquivo(Map<String, Administrador> administradorMap) {
        super.inserirDadosNoArquivo(administradorMap);
    }

    @Override
    public Map<String,Administrador> lerDadosDoArquivo() {
        return super.lerDadosDoArquivo();
    }

    @Override
    public String getCaminhoArquivo() {
        return caminhoArquivo;
    }
}