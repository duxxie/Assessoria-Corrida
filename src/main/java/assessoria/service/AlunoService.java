package assessoria.service;

import assessoria.model.dao.AlunoDAO;
import assessoria.model.entidades.Aluno;
import assessoria.util.log.Log;

import java.util.LinkedHashMap;
import java.util.Map;

public class AlunoService {

    private Map<String, Aluno> mapAluno;
    private final AlunoDAO dao;

    public AlunoService(AlunoDAO dao) {
        this.dao = dao;
    }

    public void salvarAluno(Aluno aluno) {
        salvarAlunoMap(aluno);
        inserirAlunosArquivo();
        Log.registrar("Info", "Dados do aluno (ID " + aluno.getId() + ") foi registrado no arquivo.");
    }

    public void carregarMapAluno() {
         this.mapAluno = dao.lerDadosDoArquivo();
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
