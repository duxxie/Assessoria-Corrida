package assessoria.service;

import assessoria.model.dao.TreinoDAO;
import assessoria.model.entidades.Aluno;
import assessoria.model.entidades.Professor;
import assessoria.model.entidades.Treino;
import assessoria.util.helpers.GeradorID;
import assessoria.util.log.Log;

import java.util.Map;

public class TreinoService {

    private Map<String, Treino> mapTreino;
    private final TreinoDAO dao;

    public TreinoService(TreinoDAO dao) {
        this.dao = dao;
    }

    public void carregarMapTreino() {
        this.mapTreino = dao.lerDadosDoArquivo();
    }

    public String salvarTreino(Aluno aluno, Professor professor) {
        String id = GeradorID.gerarIdTreino();
        salvarTreinoMap(new Treino(id, aluno, professor));
        salvarTreinoArquivo();
        Log.registrar("info", "Dados do treino (ID " + id + ") foi registrado no arquivo.");
        return id;
    }

    public Map<String, Treino> getMapTreino() {
        return mapTreino;
    }

    public Treino getTreinoPorID(String id) {
        return mapTreino.getOrDefault(id, null);
    }

    public int pegarTamanhoMap() {
        return mapTreino.size();
    }

    public void salvarTreinoMap(Treino treino) {
        mapTreino.put(treino.getId(), treino);
    }

    public void salvarTreinoArquivo() {
        dao.inserirDadosNoArquivo(getMapTreino());
        Log.registrar("Info", "Map de treinos foi salvo no arquivo.");
    }

    public void atualizarProfessorNoTreino(Professor professor) {
        Map<String,Treino> map = getMapTreino();

        for(Map.Entry<String,Treino> entry : map.entrySet()) {
            if(entry.getValue().getProfessor().getId().equals(professor.getId())) {
                entry.getValue().setProfessor(professor);
            }
        }
        salvarTreinoArquivo();
    }

    public void atualizarAlunoNoTreino(Aluno aluno) {
        Map<String, Treino> map = getMapTreino();

        for(Map.Entry<String,Treino> entry : map.entrySet()) {
            if(entry.getValue().getAluno().getId().equals(aluno.getId())) {
                entry.getValue().setAluno(aluno);
            }
        }
        salvarTreinoArquivo();
    }
}
