package assessoria.repository;

import assessoria.model.dao.TreinoDAO;
import assessoria.model.entidades.Treino;

import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class TreinoRepository {

    private Map<String, Treino> mapTreino;
    private final TreinoDAO dao;

    public TreinoRepository(TreinoDAO dao) {
        this.dao = dao;
        this.mapTreino = this.dao.lerDadosDoArquivo();
    }

    public Map<String, Treino> getAll() {
        return this.mapTreino;
    }

    public Treino add(Treino treino) {
        return mapTreino.put(treino.getId(), treino);
    }

    public void remove(Treino treino) {
        mapTreino.remove(treino.getId(), treino);
    }

    public Optional<Treino> findById(String id) {
        return Optional.ofNullable(mapTreino.get(id));
    }

    public void save() {
        dao.inserirDadosNoArquivo(getAll());
    }

    public Map<String, Treino> getByAlunoId(String id) {
        return getAll().entrySet().stream()
                .filter(entry -> entry.getValue().getIdAluno().equals(id))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue
                ));
    }

    public Map<String, Treino> getByProfessorId(String id) {
        return getAll().entrySet().stream()
                .filter(entry -> entry.getValue().getIdProfessor().equals(id))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue
                ));
    }

    public Map<String, Treino> getByProfessorIdAndAlunoId(String professorId, String alunoId) {
        return getAll().entrySet().stream()
                .filter(entry -> entry.getValue().getIdProfessor().equals(professorId)
                        && entry.getValue().getIdAluno().equals(alunoId))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue
                ));

    }

}
