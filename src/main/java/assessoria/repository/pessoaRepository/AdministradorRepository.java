package assessoria.repository.pessoaRepository;

import assessoria.model.dao.AdministradorDAO;
import assessoria.model.entidades.Administrador;

import java.util.Map;
import java.util.Optional;

public class AdministradorRepository implements  PessoaRepository<Administrador>{

    private final Map<String, Administrador> mapAdminstrador;
    private final AdministradorDAO dao;

    public AdministradorRepository(AdministradorDAO dao) {
        this.dao = dao;
        this.mapAdminstrador = this.dao.lerDadosDoArquivo();
    }

    public Map<String, Administrador> getAll() {
        return this.mapAdminstrador;
    }

    public Administrador add(Administrador administrador) {
        mapAdminstrador.put(administrador.getId(), administrador);
        return administrador;
    }

    public void remove(Administrador administrador) {
        mapAdminstrador.remove(administrador.getId(), administrador);
    }

    public Optional<Administrador> findById(String id) {
        return Optional.ofNullable(mapAdminstrador.get(id));
    }

    public  Optional<Administrador> findByEmail(String email) {
        return mapAdminstrador.values().stream()
                .filter(admin -> admin.getEmail().equals(email))
                .findAny();
    }

    public boolean existsByEmail(String email) {
        return mapAdminstrador.values().stream()
                .anyMatch(admin -> admin.getEmail().equals(email));
    }

    public boolean existsByEmail(String email, String idIgnorar) {
        return mapAdminstrador.values().stream()
                .anyMatch(admin -> !admin.getId().equals(idIgnorar) && admin.getEmail().equals(email));
    }

    public boolean existsByCpf(String cpf) {
        return mapAdminstrador.values().stream()
                .anyMatch(admin -> admin.getCpf().equals(cpf));
    }

    public boolean existsByCpf(String cpf, String idIgnorar) {
        return mapAdminstrador.values().stream()
                .anyMatch(admin -> !admin.getId().equals(idIgnorar) && admin.getCpf().equals(cpf));
    }

    public void save() {
        dao.inserirDadosNoArquivo(getAll());
    }

}
