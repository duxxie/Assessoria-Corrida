package assessoria.service;

import assessoria.exceptions.NotFoundException;
import assessoria.model.dao.TreinoDAO;
import assessoria.model.entidades.Aluno;
import assessoria.model.entidades.Professor;
import assessoria.model.entidades.Treino;
import assessoria.repository.TreinoRepository;
import assessoria.util.helpers.GeradorID;
import assessoria.util.log.Log;

import java.util.Map;
import java.util.Optional;

public class TreinoService {

    private final TreinoRepository treinoRepository;

    public TreinoService(TreinoRepository treinoRepository) {
        this.treinoRepository = treinoRepository;
    }

    public Treino salvarTreino(String alunoId, String professorId) {
        Treino treino = treinoRepository.add(new Treino(alunoId, professorId));
        treinoRepository.save();
        return treino;
    }

    public Map<String, Treino> getMapTreino() {
        return treinoRepository.getAll();
    }

    public Treino getTreinoPorId(String id) {
        return treinoRepository.findById(id).orElseThrow(() -> new NotFoundException("Treino nao encontrado"));
    }

    public int pegarTamanhoMap() {
        return mapTreino.size();
    }

    public void salvarTreinoMap(Treino treino) {
        mapTreino.put(treino.getId(), treino);
    }

    public void salvarTreinoArquivo() {
        dao.inserirDadosNoArquivo(getMapTreino());
    }
}
