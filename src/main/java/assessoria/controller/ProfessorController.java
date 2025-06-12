package assessoria.controller;

import assessoria.model.entidades.*;
import assessoria.model.entidades.Professor;
import assessoria.service.ProfessorService;
import assessoria.util.helpers.GeradorID;

import java.util.Map;

public class ProfessorController{

    private final ProfessorService professorService;

    public ProfessorController(ProfessorService professorService) {
        this.professorService = professorService;
    }

    public void criarProfessor(String nome, String email, String cpf, int idade, String telefone, String senha, String hashSenha, String nomeEmergencia, String telefoneEmergencia, String relacao, String condicaoMedica, String alergia, String medicamentoEmUso, String frequenciaMedicamentoEmUso, String lesaoRecente, String cirurgiaRecente, String restricaoMedica, String tipoSanguineo) {
        professorService.salvarProfessor(new Professor(GeradorID.gerarIdProfessor(), nome, email, cpf, idade, telefone, senha, hashSenha, new ContatoEmergencia(nomeEmergencia, telefoneEmergencia, relacao), new InfoMedica(condicaoMedica, alergia, medicamentoEmUso, frequenciaMedicamentoEmUso, lesaoRecente, cirurgiaRecente, restricaoMedica, tipoSanguineo)));
    }

    public void criarProfessor(String nome, String email, String cpf, int idade, String telefone, String senha, String hashSenha, String condicaoMedica, String alergia, String medicamentoEmUso, String frequenciaMedicamentoEmUso, String lesaoRecente, String cirurgiaRecente, String restricaoMedica, String tipoSanguineo) {
        professorService.salvarProfessor(new Professor(GeradorID.gerarIdProfessor(),nome, email, cpf, idade, telefone, senha, hashSenha, new InfoMedica(condicaoMedica, alergia, medicamentoEmUso, frequenciaMedicamentoEmUso, lesaoRecente, cirurgiaRecente, restricaoMedica, tipoSanguineo)));
    }

    public void salvarProfessor(Professor professor) {
        professorService.salvarProfessor(professor);
    }

    public Map<String,Professor> pegarMapProfessor() {
        return professorService.getMapProfessor();
    }

    public void carregarMap() {
        professorService.carregarMapProfessor();
    }

}
