package assessoria.service;

import assessoria.mapper.ProfessorMapper;
import assessoria.model.dao.ProfessorDAO;
import assessoria.model.dto.DadosCadastroPessoa;
import assessoria.model.entidades.ContatoEmergencia;
import assessoria.model.entidades.InfoMedica;
import assessoria.model.entidades.Professor;
import assessoria.util.helpers.GeradorID;
import assessoria.util.log.Log;
import assessoria.view.MensagemView;

import java.util.LinkedHashMap;
import java.util.Map;

public class ProfessorService {
    private final Map<String, Professor> mapProfessor;
    private final ProfessorDAO dao;
    private AlunoService alunoService;

    public ProfessorService(ProfessorDAO dao) {
        this.dao = dao;
        this.mapProfessor = this.dao.lerDadosDoArquivo();
    }

    public void criarProfessor(DadosCadastroPessoa dadosCadastroPessoa, String cref) {
        validarCpfUnicoProfessor(dadosCadastroPessoa.getCpf());

        Professor professor = ProfessorMapper.toEntity(dadosCadastroPessoa, cref, GeradorID.gerarIdClass(Professor.class));

        salvarProfessor(professor);
        MensagemView.mostrarSucesso("Seu cadastrado foi realizado com sucesso!!");
    }

    public void salvarProfessor(Professor professor) {
        salvarProfessorMap(professor);
        inserirProfessorArquivo();
        Log.registrar("Professor cadastrador com sucesso. Id=" + professor.getId() + ", Nome=" + professor.getNome());
    }

    private void salvarProfessorMap(Professor professor) {
        mapProfessor.put("K" + professor.getId(), professor);
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

    public boolean cpfJaExisteEmProfessor(String cpf) {
        return mapProfessor.values().stream()
                .anyMatch(professor -> professor.getCpf().equals(cpf));
    }

    public boolean emailJaExisteEmProfessor(String email) {
        return mapProfessor.values().stream()
                .anyMatch(professor -> professor.getEmail().equals(email));
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

    public void setAlunoService(AlunoService alunoService) {
        this.alunoService = alunoService;
    }
}
