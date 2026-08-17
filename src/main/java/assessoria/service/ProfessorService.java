package assessoria.service;

import assessoria.exceptions.ValidationException;
import assessoria.mapper.ProfessorMapper;
import assessoria.model.dto.DadosCadastroPessoa;
import assessoria.model.entidades.Professor;
import assessoria.repository.TreinoRepository;
import assessoria.repository.pessoaRepository.AdministradorRepository;
import assessoria.repository.pessoaRepository.AlunoRepository;
import assessoria.repository.pessoaRepository.ProfessorRepository;
import assessoria.util.log.Log;
import assessoria.view.MensagemView;

import java.util.HashMap;
import java.util.Map;

public class ProfessorService {

    private ProfessorRepository professorRepository;
    private AlunoRepository alunoRepository;
    private TreinoRepository treinoRepository;
    private AdministradorRepository administradorRepository;

    public ProfessorService(ProfessorRepository professorRepository,
                            AlunoRepository alunoRepository,
                            TreinoRepository treinoRepository,
                            AdministradorRepository administradorRepository) {
        this.professorRepository = professorRepository;
        this.alunoRepository = alunoRepository;
        this.treinoRepository = treinoRepository;
        this.administradorRepository = administradorRepository;
    }

    public void criarProfessor(DadosCadastroPessoa dadosCadastroPessoa, String cref) {
        if(professorRepository.existsByCpf(dadosCadastroPessoa.getCpf())
            || administradorRepository.existsByCpf(dadosCadastroPessoa.getCpf())
            || alunoRepository.existsByCpf(dadosCadastroPessoa.getCpf()))
            throw new ValidationException("Falha no cadastro do professor | Motivo: cpf informado já está registrado no sistema");

        if(professorRepository.existsByEmail(dadosCadastroPessoa.getEmail())
            || administradorRepository.existsByEmail(dadosCadastroPessoa.getEmail())
            || alunoRepository.existsByEmail(dadosCadastroPessoa.getEmail()))
            throw new ValidationException("Falha no cadastro do professor | Motivo: email informado já está registrado no sistema");


        Professor professor = professorRepository.add(ProfessorMapper.toEntity(dadosCadastroPessoa, cref));
        professorRepository.save();

        Log.registrarInfo("Professor cadastrado com sucesso. Id=" + professor.getId() + ", Nome=" + professor.getNome());
    }

    public void salvarAlteracoesProfessor() {
        professorRepository.save();
        Log.registrar("Professor atualizado com sucesso. Id=");
    }

    public Map<String, Professor> getMapProfessor() {
        return professorRepository.getAll();
    }
}
