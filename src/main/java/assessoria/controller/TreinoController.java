package assessoria.controller;

import assessoria.exceptions.NotFoundException;
import assessoria.model.entidades.Aluno;
import assessoria.model.entidades.Treino;
import assessoria.service.TreinoService;
import assessoria.view.MensagemView;

import java.util.Map;
import java.util.function.Supplier;


public class TreinoController {

    private final TreinoService treinoService;

    public TreinoController(TreinoService treinoService) {
        this.treinoService = treinoService;
    }

    public Treino criarTreino(String alunoId, String professorId, String descTreino) {
       return treinoService.salvarTreino(alunoId, professorId, descTreino);
    }

    public Map<String,Treino> getAllTreino() {
        return treinoService.getAllTreino();
    }

    private <T> T executeActionWithErrorHandlerWithReturN(Supplier<T> action) {
        try {
            return action.get();
        } catch (NotFoundException e) {
            MensagemView.mostrarErro(e.getMessage());
            return null;
        }
    }

    public Treino getTreinoPorID(String id) {
        return executeActionWithErrorHandlerWithReturN(() -> treinoService.getTreinoPorId(id));
    }

    public Map<String, Treino> getTreinoByProfessorId(String id) {
        return treinoService.getTreinoByProfessorId(id);
    }

    public Map<String, Treino> getTreinoByAlunoId(String id) {
        return treinoService.getTreinoByAlunoId(id);
    }

    public Map<String, Treino> getTreinoByProfessorAndAlunoId(String professorId, String alunoId) {
        return treinoService.getTreinoByProfessorAndAlunoId(professorId, alunoId);
    }

    public void salvarTreino() {
        treinoService.salvarTreino();
    }

//    public Treino isAlunoInTreino(Aluno aluno) {
//        Map<String,Treino> map = pegarMapTreino();
//        for(Map.Entry<String,Treino> entry : map.entrySet()) {
//            if(entry.getValue().getAluno().getId().equals(aluno.getId())) {
//                return entry.getValue();
//            }
//        }
//        return null;
//    }

}
