package assessoria.controller;
import assessoria.model.Aluno;
import assessoria.model.ContatoEmergencia;
import assessoria.model.InfoMedica;
import assessoria.service.AlunoService;

import java.util.Map;


public class AlunoController{

    private final AlunoService alunoService;

    public AlunoController(AlunoService alunoService) {
        this.alunoService = alunoService;
    }

    public void criarAluno(String nome, String email, String cpf, int idade, String telefone, String senha, String hashSenha, String nomeEmergencia, String telefoneEmergencia, String relacao, String condicaoMedica, String alergia, String medicamentoEmUso, String frequenciaMedicamentoEmUso, String lesaoRecente, String cirurgiaRecente, String restricaoMedica, String tipoSanguineo) {
        alunoService.salvarAluno(new Aluno(gerarId(), nome, email, cpf, idade, telefone, senha, hashSenha, new ContatoEmergencia(nomeEmergencia, telefoneEmergencia, relacao), new InfoMedica(condicaoMedica, alergia, medicamentoEmUso, frequenciaMedicamentoEmUso, lesaoRecente, cirurgiaRecente, restricaoMedica, tipoSanguineo)));
    }

    public void criarAluno(String nome, String email, String cpf, int idade, String telefone, String senha, String hashSenha, String condicaoMedica, String alergia, String medicamentoEmUso, String frequenciaMedicamentoEmUso, String lesaoRecente, String cirurgiaRecente, String restricaoMedica, String tipoSanguineo) {
        alunoService.salvarAluno(new Aluno(gerarId() ,nome, email, cpf, idade, telefone, senha, hashSenha, new InfoMedica(condicaoMedica, alergia, medicamentoEmUso, frequenciaMedicamentoEmUso, lesaoRecente, cirurgiaRecente, restricaoMedica, tipoSanguineo)));
    }

    private int gerarId() {
        int id = 0;
        for(Map.Entry<String,Aluno> entry : alunoService.pegarCopiaMapAluno().entrySet()) {
            id = entry.getValue().getId();
        }
        return id+1;
    }
    
    public Map<String,Aluno> pegarMapAlunos() {
        return alunoService.getMapAluno();
    }

}
