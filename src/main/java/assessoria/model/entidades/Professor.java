package assessoria.model.entidades;

public class Professor extends Pessoa implements Usuario, Savable {

    private String cref;

    public Professor() {super();}

    public Professor(Builder builder, String cref) {
        super(
                builder.id,
                builder.nome,
                builder.email,
                builder.cpf,
                builder.idade,
                builder.telefone,
                builder.hashProvider,
                builder.contatoEmergencia,
                builder.infoMedica
        );
        this.cref = cref;
    }

    public String getCref() {
        return cref;
    }

    public void setCref(String cref) {
        this.cref = cref;
    }

    public static class Builder {
        private String id;
        private String nome;
        private String email;
        private String cpf;
        private int idade;
        private String telefone;
        private String hashProvider;
        private ContatoEmergencia contatoEmergencia;
        private InfoMedica infoMedica;

        public Professor.Builder id(String id) {
            this.id = id;
            return this;
        }

        public Professor.Builder nome(String nome) {
            this.nome = nome;
            return this;
        }

        public Professor.Builder email(String email) {
            this.email = email;
            return this;
        }

        public Professor.Builder cpf(String cpf) {
            this.cpf = cpf;
            return this;
        }

        public Professor.Builder idade(int idade) {
            this.idade = idade;
            return this;
        }

        public Professor.Builder telefone(String telefone) {
            this.telefone = telefone;
            return this;
        }

        public Professor.Builder hashProvider(String hashProvider) {
            this.hashProvider = hashProvider;
            return this;
        }

        public Professor.Builder contatoEmergencia(ContatoEmergencia contatoEmergencia) {
            this.contatoEmergencia = contatoEmergencia;
            return this;
        }

        public Professor.Builder infoMedica(InfoMedica infoMedica) {
            this.infoMedica = infoMedica;
            return this;
        }

        public Professor build(String cref) {
            return new Professor(this, cref);
        }
    }


}
