package assessoria.service;

import assessoria.exceptions.NotFoundException;
import assessoria.exceptions.ValidationException;
import assessoria.mapper.AlunoMapper;
import assessoria.model.dto.AlunoBase;
import assessoria.model.dto.DadosCadastroPessoa;
import assessoria.model.entidades.Aluno;
import assessoria.repository.TreinoRepository;
import assessoria.repository.pessoaRepository.AdministradorRepository;
import assessoria.repository.pessoaRepository.AlunoRepository;
import assessoria.repository.pessoaRepository.ProfessorRepository;
import assessoria.util.log.Log;

import java.util.List;
import java.util.Map;

public class AlunoService {

    private AlunoRepository alunoRepository;
    private ProfessorRepository professorRepository;
    private AdministradorRepository administradorRepository;
    private TreinoRepository treinoRepository;

    public AlunoService(AlunoRepository alunoRepository, ProfessorRepository professorRepository, AdministradorRepository administradorRepository, TreinoRepository treinoRepository) {
        this.alunoRepository = alunoRepository;
        this.professorRepository = professorRepository;
        this.administradorRepository = administradorRepository;
        this.treinoRepository = treinoRepository;
    }

    public Aluno cadastrarAluno(DadosCadastroPessoa dadosCadastroPessoa) {
        if(alunoRepository.existsByCpf(dadosCadastroPessoa.getCpf())
            || professorRepository.existsByCpf(dadosCadastroPessoa.getCpf())
            || administradorRepository.existsByCpf(dadosCadastroPessoa.getCpf()))
            throw new ValidationException("Falha no cadastro do aluno | Motivo: cpf informado já está registrado no sistema");

        if(alunoRepository.existsByEmail(dadosCadastroPessoa.getEmail())
            || professorRepository.existsByEmail(dadosCadastroPessoa.getEmail())
            || administradorRepository.existsByEmail(dadosCadastroPessoa.getEmail()))
            throw new ValidationException("Falha no cadastro do aluno | Motivo: email informado já está registrado no sistema");

        Aluno aluno = alunoRepository.add(AlunoMapper.toEntity(dadosCadastroPessoa));
        alunoRepository.save();

        Log.registrarInfo("Aluno cadastrado com sucesso. Id=" + aluno.getId() + ", Nome=" + aluno.getNome());
        return aluno;
    }

    public List<AlunoBase> gerarListaAlunoParaExibicao() {
        if(alunoRepository.getAll().isEmpty()) throw new NotFoundException("Falha ao gerar lista para Aluno | Motivo: nenhum aluno cadastrado");

        return alunoRepository.getAll().values().stream()
                .map(this::gerarAlunoBase)
                .toList();
    }

    public Aluno getAlunoByCpf(String cpf) {
        return alunoRepository.findByCpf(cpf)
                .orElseThrow(() -> new NotFoundException("Falha ao econtrar aluno | Motivo: cpf não econtrado"));
    }

    private AlunoBase gerarAlunoBase(Aluno aluno) {
        return AlunoMapper.toBase(aluno);
    }

    public void salvarAlteracoesAluno() {
        alunoRepository.save();
    }

    public Map<String, Aluno> getMapAluno() {
        return alunoRepository.getAll();
    }
}
