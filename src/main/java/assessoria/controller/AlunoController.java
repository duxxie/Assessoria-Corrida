package assessoria.controller;
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

    public void criarAluno(String nome, String email, String cpf, int idade, String telefone, String senha, String hashSenha, String nomeEmergencia, String telefoneEmergencia, String relacao, String condicaoMedica, String alergia, String medicamentoEmUso, String frequenciaMedicamentoEmUso, String lesaoRecente, String cirurgiaRecente, String restricaoMedica, String tipoSanguineo) {
        executeActionWithErrorHandler(() -> alunoService.criarAluno(nome, email, cpf, idade, telefone, senha, hashSenha, nomeEmergencia, telefoneEmergencia, relacao, condicaoMedica, alergia, medicamentoEmUso, frequenciaMedicamentoEmUso, lesaoRecente, cirurgiaRecente, restricaoMedica, tipoSanguineo));
    }

    public void criarAluno(String nome, String email, String cpf, int idade, String telefone, String senha, String hashSenha, String condicaoMedica, String alergia, String medicamentoEmUso, String frequenciaMedicamentoEmUso, String lesaoRecente, String cirurgiaRecente, String restricaoMedica, String tipoSanguineo) {
        executeActionWithErrorHandler(() -> alunoService.criarAluno(nome, email, cpf, idade, telefone, senha, hashSenha, condicaoMedica, alergia, medicamentoEmUso, frequenciaMedicamentoEmUso, lesaoRecente, cirurgiaRecente, restricaoMedica, tipoSanguineo));
    }

    public void salvarAluno(Aluno aluno) {
        alunoService.salvarAluno(aluno);
    }
    
    public Map<String,Aluno> pegarMapAlunos() {
        return alunoService.getMapAluno();
    }

}
