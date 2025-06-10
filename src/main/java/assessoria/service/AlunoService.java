package assessoria.service;

import assessoria.model.dao.AlunoDAO;
import assessoria.model.entidades.Aluno;
import assessoria.util.log.Log;

import java.util.LinkedHashMap;
import java.util.Map;

public class AlunoService {

    private Map<String, Aluno> mapAluno;
    private final AlunoDAO dao = new AlunoDAO();

    public void salvarAluno(Aluno aluno) {
        salvarAlunoMap(aluno);
    }

    public void carregarMapAluno() {
         this.mapAluno = dao.lerDadosDoArquivo(getMapAluno());
    }

    private void salvarAlunoMap(Aluno aluno) {
        mapAluno.put("K" + aluno.getId(), aluno);
        Log.registrar("Info", "Aluno ID " + aluno.getId() + " foi adicionado ao Map");
    }

    public void inserirAlunosArquivo() {
        dao.inserirDadosNoArquivo(getMapAluno());
    }

    public int pegarTamanhoMapAluno() {
        return mapAluno.size();
    }

    public Map<String,Aluno> pegarCopiaMapAluno() {
        return new LinkedHashMap<>(mapAluno);
    }

    public Map<String, Aluno> getMapAluno() {
        return mapAluno;
    }
}
