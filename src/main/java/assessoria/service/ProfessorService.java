package assessoria.service;

import assessoria.model.dao.ProfessorDAO;
import assessoria.model.entidades.ContatoEmergencia;
import assessoria.model.entidades.InfoMedica;
import assessoria.model.entidades.Professor;
import assessoria.util.helpers.GeradorID;
import assessoria.util.log.Log;
import assessoria.view.MensagemView;

import java.util.LinkedHashMap;
import java.util.Map;

public class ProfessorService {
    private Map<String, Professor> mapProfessor;
    private final ProfessorDAO dao;

    public ProfessorService(ProfessorDAO dao) {
        this.dao = dao;
        this.mapProfessor = this.dao.lerDadosDoArquivo();
    }

    public void criarProfessor(String nome, String email, String cpf, int idade, String telefone, String senha, String hashSenha, String nomeEmergencia, String telefoneEmergencia, String relacao, String condicaoMedica, String alergia, String medicamentoEmUso, String frequenciaMedicamentoEmUso, String lesaoRecente, String cirurgiaRecente, String restricaoMedica, String tipoSanguineo) {
        validarCpfUnicoProfessor(cpf);
        salvarProfessor(new Professor(GeradorID.gerarIdClass(Professor.class), nome, email, cpf, idade, telefone, senha, hashSenha, new ContatoEmergencia(nomeEmergencia, telefoneEmergencia, relacao), new InfoMedica(condicaoMedica, alergia, medicamentoEmUso, frequenciaMedicamentoEmUso, lesaoRecente, cirurgiaRecente, restricaoMedica, tipoSanguineo)));
        MensagemView.mostrarSucesso("Seu cadastrado foi realizado com sucesso!!");
    }

    public void criarProfessor(String nome, String email, String cpf, int idade, String telefone, String senha, String hashSenha, String condicaoMedica, String alergia, String medicamentoEmUso, String frequenciaMedicamentoEmUso, String lesaoRecente, String cirurgiaRecente, String restricaoMedica, String tipoSanguineo) {
        validarCpfUnicoProfessor(cpf);
        salvarProfessor(new Professor(GeradorID.gerarIdClass(Professor.class),nome, email, cpf, idade, telefone, senha, hashSenha, new InfoMedica(condicaoMedica, alergia, medicamentoEmUso, frequenciaMedicamentoEmUso, lesaoRecente, cirurgiaRecente, restricaoMedica, tipoSanguineo)));
        MensagemView.mostrarSucesso("Seu cadastrado foi realizado com sucesso!!");
    }

    public void salvarProfessor(Professor professor) {
        salvarProfessorMap(professor);
        inserirProfessorArquivo();
        Log.registrar("Info", "Dados do professor (ID " + professor.getId() + ") foi registrado no arquivo.");
    }

    private void salvarProfessorMap(Professor professor) {
        mapProfessor.put("K" + professor.getId(), professor);
        Log.registrar("Info", "Professor ID " + professor.getId() + " foi adicionado ao Map");
    }

    private void validarCpfUnicoProfessor(String cpf) {
        mapProfessor.values().stream()
                .filter(professor -> professor.getCpf().equals(cpf))
                .findAny()
                .ifPresent(professor -> {
                    throw new IllegalArgumentException("O cpf informado já está cadastrado");
                });
    }

    public void inserirProfessorArquivo() {
        dao.inserirDadosNoArquivo(getMapProfessor());
    }

    public int pegarTamanhoMapProfessor() {
        return mapProfessor.size();
    }

    public Map<String,Professor> pegarCopiaMapProfessor() {
        return new LinkedHashMap<>(mapProfessor);
    }

    public Map<String, Professor> getMapProfessor() {
        return mapProfessor;
    }
}
