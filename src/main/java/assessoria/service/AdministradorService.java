package assessoria.service;

import assessoria.model.dao.AdministradorDAO;
import assessoria.model.entidades.Administrador;
import assessoria.util.log.Log;

import java.util.LinkedHashMap;
import java.util.Map;

public class AdministradorService {
    private Map<String, Administrador> mapAdministrador;
    private final AdministradorDAO dao = new AdministradorDAO();

    public void salvarAdministrador(Administrador administrador) {
        salvarAdministradorMap(administrador);
    }

    public void carregarMapAdministrador() {
        this.mapAdministrador = dao.lerDadosDoArquivo(getMapAdministrador());
    }

    private void salvarAdministradorMap(Administrador administrador) {
        mapAdministrador.put("K" + administrador.getId(), administrador);
        Log.registrar("Info", "Administrador ID " + administrador.getId() + " foi adicionado ao Map");
    }

    public void inserirAdministradorArquivo() {
        dao.inserirDadosNoArquivo(getMapAdministrador());
    }

    public int pegarTamanhoMapAdministrador() {
        return mapAdministrador.size();
    }

    public Map<String,Administrador> pegarCopiaMapAdministrador() {
        return new LinkedHashMap<>(mapAdministrador);
    }

    public Map<String, Administrador> getMapAdministrador() {
        return mapAdministrador;
    }
}
