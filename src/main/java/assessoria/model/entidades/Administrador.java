package assessoria.model.entidades;

public class Administrador extends Pessoa implements Usuario,Savable{

    private String idCodigoAdministrador;
    private boolean adiminRaiz;

    public Administrador() {super();}

    private Administrador(Builder builder, String idCodigoAdministrador, boolean adiminRaiz) {
        super(
                builder.id,
                builder.nome,
                builder.email,
                builder.cpf,
                builder.idade,
                builder.telefone,
                builder.senhaHash,
                builder.hashProvider,
                builder.contatoEmergencia,
                builder.infoMedica
        );
        this.idCodigoAdministrador = idCodigoAdministrador;
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

    public static class Builder {
        private String id;
        private String nome;
        private String email;
        private String cpf;
        private int idade;
        private String telefone;
        private String senhaHash;
        private String hashProvider;
        private ContatoEmergencia contatoEmergencia;
        private InfoMedica infoMedica;

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder nome(String nome) {
            this.nome = nome;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder cpf(String cpf) {
            this.cpf = cpf;
            return this;
        }

        public Builder idade(int idade) {
            this.idade = idade;
            return this;
        }

        public Builder telefone(String telefone) {
            this.telefone = telefone;
            return this;
        }

        public Builder senhaHash(String senhaHash) {
            this.senhaHash = senhaHash;
            return this;
        }

        public Builder hashProvider(String hashProvider) {
            this.hashProvider = hashProvider;
            return this;
        }

        public Builder contatoEmergencia(ContatoEmergencia contatoEmergencia) {
            this.contatoEmergencia = contatoEmergencia;
            return this;
        }

        public Builder infoMedica(InfoMedica infoMedica) {
            this.infoMedica = infoMedica;
            return this;
        }

        public Administrador build(String codigoAdmin, boolean adiminRaiz) {
            return new Administrador(this, codigoAdmin, adiminRaiz);
        }

    }
}