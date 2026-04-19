package assessoria.service;

import assessoria.exceptions.NotFoundException;
import assessoria.exceptions.ValidationException;
import assessoria.mapper.AlunoMapper;
import assessoria.model.dao.AlunoDAO;
import assessoria.model.dto.AlunoBase;
import assessoria.model.dto.DadosCadastroPessoa;
import assessoria.model.entidades.Aluno;
import assessoria.util.helpers.GeradorID;
import assessoria.util.log.Log;
import assessoria.view.MensagemView;

import java.util.List;
import java.util.Map;

public class AlunoService {

    private final Map<String, Aluno> mapAluno;
    private final AlunoDAO dao;
    private AdministradorService administradorService;
    private ProfessorService professorService;

    public AlunoService(AlunoDAO dao) {
        this.dao = dao;
        this.mapAluno = this.dao.lerDadosDoArquivo();
    }

    public Aluno cadastrarAluno(DadosCadastroPessoa dadosCadastroPessoa) {
        if(cpfAlunoJaExiste(dadosCadastroPessoa.getCpf(), null)
                || professorService.cpfJaExisteEmProfessor(dadosCadastroPessoa.getCpf())
                || administradorService.cpfJaExisteEmAdministrador(dadosCadastroPessoa.getCpf()))
            throw new ValidationException("Falha no cadastro do aluno | Motivo: cpf informado já está registrado no sistema");

        if(emailAlunoJaExiste(dadosCadastroPessoa.getEmail(), null)
                || professorService.emailJaExisteEmProfessor(dadosCadastroPessoa.getEmail())
                || administradorService.emailJaExisteEmAdministrador(dadosCadastroPessoa.getEmail()))
            throw new ValidationException("Falha no cadastro do aluno | Motivo: email informado já está registrado no sistema");

        Aluno aluno = AlunoMapper.toEntity(dadosCadastroPessoa, GeradorID.gerarIdClass(Aluno.class));

        salvarAluno(aluno);
        MensagemView.mostrarSucesso("Seu cadastrado foi realizado com sucesso!!");
        Log.registrarInfo("Aluno cadastrado com sucesso. Id=" + aluno.getId() + ", Nome=" + aluno.getNome());
        return aluno;
    }

    public List<AlunoBase> gerarListaAlunoParaExibicao() {
        if(mapAluno.isEmpty()) throw new NotFoundException("Falha ao gerar lista para Aluno | Motivo: nenhum aluno cadastrado");

        return mapAluno.values().stream()
                .map(this::gerarAlunoBase)
                .toList();
    }

    private AlunoBase gerarAlunoBase(Aluno aluno) {
        return AlunoMapper.toBase(aluno);
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

    public void setAdministradorService(AdministradorService administradorService) {
        this.administradorService = administradorService;
    }

    public void setProfessorService(ProfessorService professorService) {
        this.professorService = professorService;
    }
}
