package assessoria.repository;

import assessoria.model.dao.CodigoAdministradorDAO;
import assessoria.model.entidades.CodigoAdministrador;

import java.util.List;
import java.util.Optional;

public class CodigoAdministradorRepository {

    private final CodigoAdministradorDAO dao;
    private final List<CodigoAdministrador> codigoAdministradorList;
    private final String KEY_ADMIN_RAIZ = "RRK1";

    public CodigoAdministradorRepository(CodigoAdministradorDAO dao) {
        this.dao = dao;
        this.codigoAdministradorList = this.dao.lerDadosDoAquivo();
    }

    public List<CodigoAdministrador> getAll() {
        return this.codigoAdministradorList;
    }

    public String add(CodigoAdministrador codigoAdministrador) {
        codigoAdministradorList.add(codigoAdministrador);
        return codigoAdministrador.getId();
    }

    public void save() {
        dao.inserirDadosNoArquivo(getAll());
    }

    public Optional<CodigoAdministrador> findCodigoAdministradorById(String id) {
        return codigoAdministradorList.stream()
                .filter(c -> c.getId().equals(id))
                .findAny();
    }

    public boolean isCodigoAdminRaiz(String codigoInformado) {
        return codigoInformado.contains(KEY_ADMIN_RAIZ);
    }

    public String gerarCodigoAdministradorAndSalvar() {
        CodigoAdministrador codigoAdministrador = new CodigoAdministrador();
        add(codigoAdministrador);
        save();
        return codigoAdministrador.getId();
    }


}
