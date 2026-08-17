package assessoria.service;

import assessoria.exceptions.NotFoundException;
import assessoria.model.entidades.Treino;
import assessoria.repository.TreinoRepository;

import java.util.Map;
import java.util.SimpleTimeZone;

public class TreinoService {

    private final TreinoRepository treinoRepository;

    public TreinoService(TreinoRepository treinoRepository) {
        this.treinoRepository = treinoRepository;
    }

    public Treino salvarTreino(String alunoId, String professorId, String descTreino) {
        Treino treino = treinoRepository.add(new Treino(descTreino, alunoId, professorId));
        treinoRepository.save();
        return treino;
    }

    public Map<String, Treino> getAllTreino() {
        return treinoRepository.getAll();
    }

    public Treino getTreinoPorId(String id) {
        return treinoRepository.findById(id).orElseThrow(() -> new NotFoundException("Treino nao encontrado"));
    }

    public Map<String, Treino> getTreinoByProfessorId(String id) {
        return treinoRepository.getByProfessorId(id);
    }

    public Map<String, Treino> getTreinoByAlunoId(String id) {
        return treinoRepository.getByAlunoId(id);
    }

    public Map<String, Treino> getTreinoByProfessorAndAlunoId(String professorId, String alunoId) {
        return treinoRepository.getByProfessorIdAndAlunoId(professorId, alunoId);
    }

    public void salvarTreino() {
        treinoRepository.save();
    }
}
