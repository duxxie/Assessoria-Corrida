package assessoria.model.entidades;

public class Treino {
    private String InicioTreino;
    private String MeioTreino;
    private String MimTreino;

    public Treino(String inicioTreino, String meioTreino, String mimTreino) {
        InicioTreino = inicioTreino;
        MeioTreino = meioTreino;
        MimTreino = mimTreino;
    }

    public String getInicioTreino() {
        return InicioTreino;
    }

    public String getMeioTreino() {
        return MeioTreino;
    }

    public String getMimTreino() {
        return MimTreino;
    }

    public void setInicioTreino(String inicioTreino) {
        InicioTreino = inicioTreino;
    }

    public void setMeioTreino(String meioTreino) {
        MeioTreino = meioTreino;
    }

    public void setMimTreino(String mimTreino) {
        MimTreino = mimTreino;
    }


}
