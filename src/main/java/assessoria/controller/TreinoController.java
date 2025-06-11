package assessoria.controller;

import assessoria.model.entidades.Aluno;
import assessoria.model.entidades.Professor;
import assessoria.model.entidades.Treino;
import assessoria.service.TreinoService;
import assessoria.util.helpers.GeradorID;


public class TreinoController {

    private final TreinoService treinoService;

    public TreinoController(TreinoService treinoService) {
        this.treinoService = treinoService;
    }

    public void criarTreino(Aluno aluno, Professor professor) {
        treinoService.salvarTreino(new Treino(GeradorID.gerarIdTreino(), aluno, professor));
    }



}
