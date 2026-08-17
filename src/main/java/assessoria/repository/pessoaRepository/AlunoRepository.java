package assessoria.repository.pessoaRepository;

import assessoria.model.dao.AlunoDAO;
import assessoria.model.entidades.Aluno;

import java.util.Map;
import java.util.Optional;

public class AlunoRepository implements PessoaRepository<Aluno> {

    private final Map<String, Aluno> mapAluno;
    private final AlunoDAO dao;

    public AlunoRepository(AlunoDAO dao) {
        this.dao = dao;
        this.mapAluno = this.dao.lerDadosDoArquivo();
    }

    public Map<String, Aluno> getAll() {
        return this.mapAluno;
    }

    public Aluno add(Aluno aluno) {
        mapAluno.put(aluno.getId(), aluno);
        return aluno;
    }

    public void remove(Aluno aluno) {
        mapAluno.remove(aluno.getId(), aluno);
    }

    public Optional<Aluno> findById(String id) {
        return Optional.ofNullable(mapAluno.get(id));
    }

    public Optional<Aluno> findByEmail(String email) {
        return mapAluno.values().stream()
                .filter(a -> a.getEmail().equals(email))
                .findAny();
    }

    public Optional<Aluno> findByCpf(String cpf) {
        return mapAluno.values().stream()
                .filter(a -> a.getCpf().equals(cpf))
                .findAny();
    }

    public boolean existsByEmail(String email) {
        return mapAluno.values().stream()
                .anyMatch(a -> a.getEmail().equals(email));
    }

    public boolean existsByEmail(String email, String idIgnorar) {
        return mapAluno.values().stream()
                .anyMatch(aluno -> !aluno.getId().equals(idIgnorar) && aluno.getEmail().equals(email));
    }

    public boolean existsByCpf(String cpf) {
        return mapAluno.values().stream()
                .anyMatch(aluno -> aluno.getCpf().equals(cpf));
    }

    public boolean existsByCpf(String cpf, String idIgnorar) {
        return mapAluno.values().stream()
                .anyMatch(aluno -> !aluno.getId().equals(idIgnorar) && aluno.getCpf().equals(cpf));
    }

    public void save() {
        dao.inserirDadosNoArquivo(getAll());
    }

}
