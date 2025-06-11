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

    public void criarAluno(String nome, String email, String cpf, int idade, String telefone, String senha, String hashSenha, String nomeEmergencia, String telefoneEmergencia, String relacao, String condicaoMedica, String alergia, String medicamentoEmUso, String frequenciaMedicamentoEmUso, String lesaoRecente, String cirurgiaRecente, String restricaoMedica, String tipoSanguineo) {
        alunoService.salvarAluno(new Aluno(GeradorID.gerarIdAluno(), nome, email, cpf, idade, telefone, senha, hashSenha, new ContatoEmergencia(nomeEmergencia, telefoneEmergencia, relacao), new InfoMedica(condicaoMedica, alergia, medicamentoEmUso, frequenciaMedicamentoEmUso, lesaoRecente, cirurgiaRecente, restricaoMedica, tipoSanguineo)));
    }

    public void criarAluno(String nome, String email, String cpf, int idade, String telefone, String senha, String hashSenha, String condicaoMedica, String alergia, String medicamentoEmUso, String frequenciaMedicamentoEmUso, String lesaoRecente, String cirurgiaRecente, String restricaoMedica, String tipoSanguineo) {
        alunoService.salvarAluno(new Aluno(GeradorID.gerarIdAluno(),nome, email, cpf, idade, telefone, senha, hashSenha, new InfoMedica(condicaoMedica, alergia, medicamentoEmUso, frequenciaMedicamentoEmUso, lesaoRecente, cirurgiaRecente, restricaoMedica, tipoSanguineo)));
    }
    
    public Map<String,Aluno> pegarMapAlunos() {
        return alunoService.getMapAluno();
    }

}
