package assessoria.model.dao;

import assessoria.model.entidades.Treino;

import java.util.Map;

public class TreinoDAO extends GenericDAO<Treino> {

    private final String caminhoArquivo = "treino/treinos.json";

    public TreinoDAO() {
        super(Treino.class);
    }

    @Override
    public void inserirDadosNoArquivo(Map<String, Treino> typeMap) {
        super.inserirDadosNoArquivo(typeMap);
    }

    @Override
    public Map<String, Treino> lerDadosDoArquivo() {
        return super.lerDadosDoArquivo();
    }

    @Override
    public String getCaminhoArquivo() {
        return caminhoArquivo;
    }


}
