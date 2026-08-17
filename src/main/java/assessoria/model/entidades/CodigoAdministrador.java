package assessoria.model.entidades;

import java.util.UUID;

public class CodigoAdministrador implements Savable {

    private String id = "ADM-" + UUID.randomUUID().toString().substring(0,8).toUpperCase();
    private boolean usado = false;
    private boolean ativo = true;


    public CodigoAdministrador() {}

    public boolean isUsado() {
        return usado;
    }

    public void setUsado(boolean usado) {
        this.usado = usado;
    }

    public String getId() {
        return id;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public boolean isAtivo() {
        return ativo;
    }
}
