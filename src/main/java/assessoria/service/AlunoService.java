package assessoria.service;

import assessoria.model.dao.AlunoDAO;
import assessoria.model.entidades.Aluno;
import assessoria.model.entidades.ContatoEmergencia;
import assessoria.model.entidades.InfoMedica;
import assessoria.util.helpers.GeradorID;
import assessoria.util.log.Log;
import assessoria.view.MensagemView;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

public class AlunoService {

    private Map<String, Aluno> mapAluno;
    private final AlunoDAO dao;

    public AlunoService(AlunoDAO dao) {
        this.dao = dao;
        this.mapAluno = this.dao.lerDadosDoArquivo();
    }

    public void criarAluno(String nome, String email, String cpf, int idade, String telefone, String senha, String hashSenha, String nomeEmergencia, String telefoneEmergencia, String relacao, String condicaoMedica, String alergia, String medicamentoEmUso, String frequenciaMedicamentoEmUso, String lesaoRecente, String cirurgiaRecente, String restricaoMedica, String tipoSanguineo) {
       validarCpfUnico(cpf);
        salvarAluno(new Aluno(GeradorID.gerarIdClass(Aluno.class), nome, email, cpf, idade, telefone, senha, hashSenha, new ContatoEmergencia(nomeEmergencia, telefoneEmergencia, relacao), new InfoMedica(condicaoMedica, alergia, medicamentoEmUso, frequenciaMedicamentoEmUso, lesaoRecente, cirurgiaRecente, restricaoMedica, tipoSanguineo)));
        MensagemView.mostrarSucesso("Seu cadastrado foi realizado com sucesso!!");
    }

    public void criarAluno(String nome, String email, String cpf, int idade, String telefone, String senha, String hashSenha, String condicaoMedica, String alergia, String medicamentoEmUso, String frequenciaMedicamentoEmUso, String lesaoRecente, String cirurgiaRecente, String restricaoMedica, String tipoSanguineo) {
        validarCpfUnico(cpf);
        salvarAluno(new Aluno(GeradorID.gerarIdClass(Aluno.class),nome, email, cpf, idade, telefone, senha, hashSenha, new InfoMedica(condicaoMedica, alergia, medicamentoEmUso, frequenciaMedicamentoEmUso, lesaoRecente, cirurgiaRecente, restricaoMedica, tipoSanguineo)));
        MensagemView.mostrarSucesso("Seu cadastrado foi realizado com sucesso!!");
    }

    public void criarAluno(String nome, String email, String cpf, int idade, String telefone, String senha, String hashSenha, String nomeEmergencia, String telefoneEmergencia, String relacao) {
        validarCpfUnico(cpf);
        salvarAluno(new Aluno(GeradorID.gerarIdClass(Aluno.class), nome, email, cpf, idade, telefone, senha, hashSenha, new ContatoEmergencia(nomeEmergencia, telefoneEmergencia, relacao)));
        MensagemView.mostrarSucesso("Seu cadastrado foi realizado com sucesso!!");
    }

    public void criarAluno(String nome, String email, String cpf, int idade, String telefone, String senha, String hashSenha) {
        validarCpfUnico(cpf);
        salvarAluno(new Aluno(GeradorID.gerarIdClass(Aluno.class), nome, email, cpf, idade, telefone, senha, hashSenha));
        MensagemView.mostrarSucesso("Seu cadastrado foi realizado com sucesso!!");
    }


    public void validarCpfUnico(String cpf) {
        mapAluno.values().stream()
                .filter(aluno -> aluno.getCpf().equals(cpf))
                .findAny()
                .ifPresent(aluno -> {
                    throw new IllegalArgumentException("Cpf já informado já está cadastrado");
                });
    }

    public void salvarAluno(Aluno aluno) {
        salvarAlunoMap(aluno);
        inserirAlunosArquivo();
        Log.registrar("Info", "Dados do aluno (ID " + aluno.getId() + ") foi registrado no arquivo.");
    }

    private void salvarAlunoMap(Aluno aluno) {
        mapAluno.put(aluno.getId(), aluno);
    }

    private void inserirAlunosArquivo() {
        dao.inserirDadosNoArquivo(getMapAluno());
    }

    public int pegarTamanhoMapAluno() {
        return mapAluno.size();
    }

    public Map<String, Aluno> getMapAluno() {
        return mapAluno;
    }
}
