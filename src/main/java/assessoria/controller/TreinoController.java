package assessoria.controller;

import assessoria.model.entidades.Aluno;
import assessoria.model.entidades.Professor;
import assessoria.model.entidades.Treino;
import assessoria.service.TreinoService;
import assessoria.util.helpers.GeradorID;

import java.util.Map;


public class TreinoController {

    private final TreinoService treinoService;

    public TreinoController(TreinoService treinoService) {
        this.treinoService = treinoService;
    }

    public String criarTreino(Aluno aluno, Professor professor) {
       return treinoService.salvarTreino(aluno, professor);
    }

    public Map<String,Treino> pegarMapTreino() {
        return treinoService.getMapTreino();
    }

    public Treino getTreinoPorID(String id) {
        return treinoService.getTreinoPorID(id);
    }

    public void salvarTreino(Treino treino) {
        treinoService.salvarTreinoMap(treino);
    }

    public void carregarMap() {
        treinoService.carregarMapTreino();
    }

    public Treino isAlunoInTreino(Aluno aluno) {
        Map<String,Treino> map = pegarMapTreino();
        for(Map.Entry<String,Treino> entry : map.entrySet()) {
            if(entry.getValue().getAluno().getId().equals(aluno.getId())) {
                return entry.getValue();
            }
        }
        return null;
    }

    public Treino isProfessorInTreino(Professor professor) {
        Map<String,Treino> map = pegarMapTreino();
        for(Map.Entry<String,Treino> entry : map.entrySet()) {
            if(entry.getValue().getAluno().getId().equals(professor.getId())) {
                return entry.getValue();
            }
        }
        return null;
    }

    public void atualizarProfessor(Professor professor) {
        treinoService.atualizarProfessorNoTreino(professor);
    }

    public void atualizarAluno(Aluno aluno) {
        treinoService.atualizarAlunoNoTreino(aluno);
    }
}
