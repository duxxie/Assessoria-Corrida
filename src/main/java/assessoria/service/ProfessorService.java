package assessoria.service;

import assessoria.model.dao.ProfessorDAO;
import assessoria.model.entidades.Professor;
import assessoria.util.log.Log;

import java.util.LinkedHashMap;
import java.util.Map;

public class ProfessorService {
    private Map<String, Professor> mapProfessor;
    private final ProfessorDAO dao;

    public ProfessorService(ProfessorDAO dao) {
        this.dao = dao;
    }

    public void salvarProfessor(Professor professor) {
        salvarProfessorMap(professor);
        inserirProfessorArquivo();
        Log.registrar("Info", "Dados do professor (ID " + professor.getId() + ") foi registrado no arquivo.");
    }

    public void carregarMapProfessor() {
        this.mapProfessor = dao.lerDadosDoArquivo();
    }

    private void salvarProfessorMap(Professor professor) {
        mapProfessor.put("K" + professor.getId(), professor);
        Log.registrar("Info", "Professor ID " + professor.getId() + " foi adicionado ao Map");
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
