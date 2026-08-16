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

    public Optional<Aluno> add(Aluno aluno) {
        return Optional.ofNullable(mapAluno.put(aluno.getId(), aluno));
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

    public void save() {
        dao.inserirDadosNoArquivo(getAll());
    }

}
