package assessoria.service;

import assessoria.model.dao.AlunoDAO;
import assessoria.model.dto.DadosCadastroPessoa;
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

    public void criarAluno(DadosCadastroPessoa dadosCadastroPessoa) {
        validarCpfUnico(dadosCadastroPessoa.getCpf());

        Aluno aluno = new Aluno.Builder()
                .id(GeradorID.gerarIdClass(Aluno.class))
                .nome(dadosCadastroPessoa.getNome())
                .email(dadosCadastroPessoa.getEmail())
                .cpf(dadosCadastroPessoa.getCpf())
                .idade(dadosCadastroPessoa.getIdade())
                .telefone(dadosCadastroPessoa.getTelefone())
                .senhaHash(dadosCadastroPessoa.getSenhaHash())
                .hashProvider(dadosCadastroPessoa.getHashProvider())
                .contatoEmergencia(dadosCadastroPessoa.getContatoEmergencia())
                .infoMedica(dadosCadastroPessoa.getInfoMedica())
                .build();

        salvarAluno(aluno);
        MensagemView.mostrarSucesso("Seu cadastrado foi realizado com sucesso!!");
    }

    public void validarCpfUnico(String cpf) {
        mapAluno.values().stream()
                .filter(aluno -> aluno.getCpf().equals(cpf))
                .findAny()
                .ifPresent(aluno -> {
                    throw new IllegalArgumentException("Cpf informado já está cadastrado");
                });
    }

    public void salvarAluno(Aluno aluno) {
        salvarAlunoMap(aluno);
        inserirAlunosArquivo();
        Log.registrarInfo( "Aluno cadastrado com sucesso. Id=" + aluno.getId() + ", Nome=" + aluno.getNome());
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
