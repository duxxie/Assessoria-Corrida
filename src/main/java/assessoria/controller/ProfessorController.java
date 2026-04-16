package assessoria.controller;

import assessoria.model.dto.DadosCadastroPessoa;
import assessoria.model.entidades.*;
import assessoria.model.entidades.Professor;
import assessoria.service.ProfessorService;
import assessoria.util.helpers.GeradorID;
import assessoria.view.MensagemView;

import java.util.Map;

public class ProfessorController{

    private final ProfessorService professorService;

    public ProfessorController(ProfessorService professorService) {
        this.professorService = professorService;
    }

    public void executeActionWithErrorHandler(Runnable action) {
        try {
            action.run();
        } catch (IllegalArgumentException e) {
            MensagemView.mostrarErro("Não foi possível realizar o cadastro | Motivo: " + e.getMessage());
        }
    }

    public void criarProfessor(DadosCadastroPessoa dadosCadastroPessoa, String cref) {
        executeActionWithErrorHandler(() -> professorService.criarProfessor(dadosCadastroPessoa, cref));
    }

    public void salvarProfessor(Professor professor) {
        professorService.salvarProfessor(professor);
    }

    public Map<String,Professor> pegarMapProfessor() {
        return professorService.getMapProfessor();
    }

}
