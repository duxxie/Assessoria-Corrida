package assessoria.model.entidades;

public class CodigoAdministrador implements Savable {

    private String id;
    private boolean usado;
    private boolean ativo;

    public CodigoAdministrador() {};

    public CodigoAdministrador(String id, boolean usado, boolean ativo) {
        this.id = id;
        this.usado = usado;
        this.ativo = ativo;
    }

    public boolean isUsado() {
        return usado;
    }

    public void setUsado(boolean usado) {
        this.usado = usado;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public boolean isAtivo() {
        return ativo;
    }
}
