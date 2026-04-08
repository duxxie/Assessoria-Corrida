package assessoria.controller;
import assessoria.model.dto.DadosCadastroPessoa;
import assessoria.model.entidades.Aluno;
import assessoria.model.entidades.ContatoEmergencia;
import assessoria.model.entidades.InfoMedica;
import assessoria.service.AlunoService;
import assessoria.util.helpers.GeradorID;

import java.util.Map;


public class AlunoController{

    private final AlunoService alunoService;

    public AlunoController(AlunoService alunoService) {
        this.alunoService = alunoService;
    }

    private static void executeActionWithErrorHandler(Runnable action) {
        try {
            action.run();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    public void criarAluno(DadosCadastroPessoa dadosCadastroPessoa) {
        executeActionWithErrorHandler(() -> alunoService.criarAluno(dadosCadastroPessoa));
    }

    public void salvarAluno(Aluno aluno) {
        alunoService.salvarAluno(aluno);
    }
    
    public Map<String,Aluno> pegarMapAlunos() {
        return alunoService.getMapAluno();
    }

}
