package assessoria.service;

import assessoria.dao.AlunoDAO;
import assessoria.model.Aluno;

import java.util.LinkedHashMap;
import java.util.Map;

public class AlunoService {

    private final Map<String, Aluno> mapAluno = new LinkedHashMap<>();
    private final String caminhoArquivo = "src/main/java/assessoria/util/users/alunos.json";
    private final AlunoDAO dao = new AlunoDAO();

    public void salvarAluno(Aluno aluno) {
        salvarAlunoMap(aluno);
        salvarAlunoArquivo();
    }

    private void salvarAlunoMap(Aluno aluno) {
        mapAluno.put("K" + aluno.getId(), aluno);
    }

    private void salvarAlunoArquivo() {
        dao.inserirDadosNoArquivo(pegarCopiaMapAluno(), getCaminhoArquivo());
    }

    public int pegarTamanhoMapAluno() {
        return mapAluno.size();
    }

    public Map<String,Aluno> pegarCopiaMapAluno() {
        return new LinkedHashMap<>(mapAluno);
    }

    public String getCaminhoArquivo() {
        return caminhoArquivo;
    }
}
