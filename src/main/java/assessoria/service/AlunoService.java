package assessoria.service;

import assessoria.exceptions.ValidationException;
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
        if(cpfAlunoJaExiste(dadosCadastroPessoa.getCpf(), null))
            throw new ValidationException("Cpf informado já está cadastrado");

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

    public boolean cpfAlunoJaExiste(String cpf, String idIgnorado) {
        return mapAluno.values().stream()
                .filter(aluno -> idIgnorado == null || !aluno.getId().equals(idIgnorado))
                .anyMatch(aluno -> aluno.getCpf().equals(cpf));
    }

    public boolean cpfJaExisteEmAluno(String cpf) {
        return mapAluno.values().stream()
                .anyMatch(aluno -> aluno.getCpf().equals(cpf));
    }

    public boolean emailAlunoJaExiste(String email, String idIgnorado) {
        return mapAluno.values().stream()
                .filter(aluno -> idIgnorado == null || !aluno.getId().equals(idIgnorado))
                .anyMatch(aluno -> aluno.getEmail().equals(email));
    }

    public boolean emailJaExisteEmAluno(String email) {
        return mapAluno.values().stream()
                .anyMatch(aluno -> aluno.getEmail().equals(email));
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
