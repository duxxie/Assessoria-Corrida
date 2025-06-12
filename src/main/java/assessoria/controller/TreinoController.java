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

    public void salvarTreino() {
        treinoService.salvarTreinoArquivo();
    }

    public void carregarMap() {
        treinoService.carregarMapTreino();
    }

}
