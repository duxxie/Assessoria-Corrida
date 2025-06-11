package assessoria.service;

import assessoria.model.dao.TreinoDAO;
import assessoria.model.entidades.Treino;
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

    public void salvarTreino(Treino treino) {
        salvarTreinoMap(treino);
        salvarTreinoArquivo();
        Log.registrar("info", "Dados do treino (ID " + treino.getId() + ") foi registrado no arquivo.");
    }

    private void inserirTreinosArquivo() {
        dao.inserirDadosNoArquivo(getMapTreino());
    }

    public Map<String, Treino> getMapTreino() {
        return mapTreino;
    }

    public int pegarTamanhoMap() {
        return mapTreino.size();
    }

    private void salvarTreinoMap(Treino treino) {
        mapTreino.put(treino.getId(), treino);
    }

    private void salvarTreinoArquivo() {
        dao.inserirDadosNoArquivo(getMapTreino());
    }


}
