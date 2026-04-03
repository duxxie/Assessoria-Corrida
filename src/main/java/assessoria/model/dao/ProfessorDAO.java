package assessoria.model.dao;

import assessoria.model.entidades.Professor;
import java.util.Map;

public class ProfessorDAO extends GenericDAO<Professor>{

    private final String caminhoArquivo = "users/professores/professores.json";

    public ProfessorDAO() {
        super(Professor.class);
    }

    @Override
    public void inserirDadosNoArquivo(Map<String, Professor> professorMap) {
        super.inserirDadosNoArquivo(professorMap);
    }
    @Override
    public Map<String,Professor> lerDadosDoArquivo() {
        return super.lerDadosDoArquivo();
    }

    @Override
    public String getCaminhoArquivo() {
        return caminhoArquivo;
    }
}
