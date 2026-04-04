package assessoria.model.entidades;

public class CodigoAdministrador implements Savable {

    private String id;
    private boolean usado;

    public CodigoAdministrador() {};

    public CodigoAdministrador(String id, boolean usado) {
        this.id = id;
        this.usado = usado;
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
}
