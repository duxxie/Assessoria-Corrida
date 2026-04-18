package assessoria.controller;
import assessoria.exceptions.OperationNotAllowedException;
import assessoria.exceptions.ValidationException;
import assessoria.model.dto.DadosCadastroPessoa;
import assessoria.model.entidades.Aluno;
import assessoria.model.entidades.ContatoEmergencia;
import assessoria.model.entidades.InfoMedica;
import assessoria.service.AlunoService;
import assessoria.util.helpers.GeradorID;
import assessoria.util.log.Log;
import assessoria.view.MensagemView;

import java.util.Map;
import java.util.function.Supplier;


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


    public void salvarAluno(Aluno aluno) {
        alunoService.salvarAluno(aluno);
    }

    private <T> T executeActionWithErrorHandlerWithReturn(Supplier<T> action) {
        try {
            return action.get();
        } catch (ValidationException e) {
            MensagemView.mostrarErro(e.getMessage());
            return null;
        } catch (OperationNotAllowedException e) {
            MensagemView.mostrarAviso(e.getMessage());
            Log.registrarAviso(e.getMessage());
            return null;
        }
    }

    public Aluno cadastrarAluno(DadosCadastroPessoa dadosCadastroPessoa) {
        return executeActionWithErrorHandlerWithReturn(() -> alunoService.cadastrarAluno(dadosCadastroPessoa));
    }

    public Map<String,Aluno> pegarMapAlunos() {
        return alunoService.getMapAluno();
    }

}
