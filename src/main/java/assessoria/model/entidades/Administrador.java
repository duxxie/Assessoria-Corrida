package assessoria.model.entidades;

public class Administrador extends Pessoa implements Usuario,Savable{

    private String idCodigoAdministrador;
    private boolean adiminRaiz;

    public Administrador() {super();}

    public Administrador(String id, String nome, String email, String cpf, int idade, String telefone) {
        super(id, nome, email, cpf, idade, telefone);
    }

    public Administrador(String id, String nome, String email, String cpf, int idade, String telefone, String senhaHash, String hashProvider, String codigoAdministrador, boolean adiminRaiz, ContatoEmergencia contatoEmergencia, InfoMedica infoMedica) {
        super(id, nome, email, cpf, idade, telefone, senhaHash, hashProvider, contatoEmergencia, infoMedica);
        this.idCodigoAdministrador = codigoAdministrador;
        this.adiminRaiz = adiminRaiz;
    }

    public Administrador(String id, String nome, String email, String cpf, int idade, String telefone, String senhaHash, String hashProvider, String codigoAdministrador, boolean adiminRaiz, InfoMedica infoMedica) {
        super(id, nome, email, cpf, idade, telefone, senhaHash, hashProvider, infoMedica);
        this.idCodigoAdministrador = codigoAdministrador;
        this.adiminRaiz = adiminRaiz;
    }

    public String getIdCodigoAdministrador() {
        return idCodigoAdministrador;
    }

    public void setIdCodigoAdministrador(String idCodigoAdministrador) {
        this.idCodigoAdministrador = idCodigoAdministrador;
    }

    public boolean isAdiminRaiz() {
        return adiminRaiz;
    }

    public void setAdiminRaiz(boolean adiminRaiz) {
        this.adiminRaiz = adiminRaiz;
    }
}