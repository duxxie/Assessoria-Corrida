package assessoria.repository.pessoaRepository;

import assessoria.model.dao.ProfessorDAO;
import assessoria.model.entidades.Professor;

import java.util.Map;
import java.util.Optional;

public class ProfessorRepository implements PessoaRepository<Professor>{

    private final Map<String, Professor> mapProfessor;
    private final ProfessorDAO dao;

    public ProfessorRepository(ProfessorDAO dao) {
        this.dao = dao;
        this.mapProfessor = this.dao.lerDadosDoArquivo();
    }

    public Map<String, Professor> getAll() {
        return this.mapProfessor;
    }

    public Professor add(Professor professor) {
        mapProfessor.put(professor.getId(), professor);
        return professor;
    }

    public void remove(Professor professor) {
        mapProfessor.remove(professor.getId(), professor);
    }

    public Optional<Professor> findById(String id) {
        return Optional.ofNullable(mapProfessor.get(id));
    }

    public Optional<Professor> findByEmail(String email) {
        return mapProfessor.values().stream()
                .filter(p -> p.getEmail().equals(email))
                .findAny();
    }

    public boolean existsByEmail(String email) {
        return mapProfessor.values().stream()
                .anyMatch(p -> p.getEmail().equals(email));
    }

    public boolean existsByEmail(String email, String idIgnorar) {
        return mapProfessor.values().stream()
                .anyMatch(p -> !p.getId().equals(idIgnorar) && p.getEmail().equals(email));
    }

    public boolean existsByCpf(String cpf) {
        return mapProfessor.values().stream()
                .anyMatch(p -> p.getCpf().equals(cpf));
    }

    public boolean existsByCpf(String cpf, String idIgnorar) {
        return mapProfessor.values().stream()
                .anyMatch(p -> !p.getId().equals(idIgnorar) && p.getCpf().equals(cpf));
    }

    public void save() {
        dao.inserirDadosNoArquivo(getAll());
    }
}
