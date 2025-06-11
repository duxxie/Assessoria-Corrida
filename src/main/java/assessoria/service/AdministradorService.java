package assessoria.service;

import assessoria.model.dao.AdministradorDAO;
import assessoria.model.entidades.Administrador;
import assessoria.util.log.Log;

import java.util.LinkedHashMap;
import java.util.Map;

public class AdministradorService {
    private Map<String, Administrador> mapAdministrador;
    private final AdministradorDAO dao = new AdministradorDAO();

    public void salvarAdministrador(Administrador Administrador) {
        salvarAdministradorMap(Administrador);
    }

    public void carregarMapAdministrador() {
        this.mapAdministrador = dao.lerDadosDoArquivo(getMapAdministrador());
    }

    private void salvarAdministradorMap(Administrador Administrador) {
        mapAdministrador.put("K" + Administrador.getId(), Administrador);
        Log.registrar("Info", "Administrador ID " + Administrador.getId() + " foi adicionado ao Map");
    }

    public void inserirAdministradorsArquivo() {
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
