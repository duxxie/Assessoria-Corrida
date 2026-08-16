package assessoria.controller;

import assessoria.exceptions.NotFoundException;
import assessoria.model.entidades.Aluno;
import assessoria.model.entidades.Professor;
import assessoria.model.entidades.Treino;
import assessoria.service.TreinoService;
import assessoria.util.helpers.GeradorID;
import assessoria.view.MensagemView;

import java.util.Map;
import java.util.function.Supplier;


public class TreinoController {

    private final TreinoService treinoService;

    public TreinoController(TreinoService treinoService) {
        this.treinoService = treinoService;
    }

    public Treino criarTreino(String alunoId, String professorId) {
       return treinoService.salvarTreino(alunoId, professorId);
    }

    public Map<String,Treino> pegarMapTreino() {
        return treinoService.getMapTreino();
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

    public void salvarTreino(Treino treino) {
        treinoService.salvarTreinoMap(treino);
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

}
