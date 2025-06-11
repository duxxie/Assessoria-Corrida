package assessoria.model.entidades;

public class Administrador extends Pessoa implements Usuario,Savable{

    public Administrador() {super();}

    public Administrador(String id, String nome, String cpf, int idade) {
        super(id, nome, cpf, idade);
    }

    public Administrador(String id, String nome, String email, String cpf, int idade, String telefone) {
        super(id, nome, email, cpf, idade, telefone);
    }

    public Administrador(String id, String nome, String email, String cpf, int idade, String telefone, String senhaHash, String hashProvider, ContatoEmergencia contatoEmergencia, InfoMedica infoMedica) {
        super(id, nome, email, cpf, idade, telefone, senhaHash, hashProvider, contatoEmergencia, infoMedica);
    }

    public Administrador(String id, String nome, String email, String cpf, int idade, String telefone, String senhaHash, String hashProvider, InfoMedica infoMedica) {
        super(id, nome, email, cpf, idade, telefone, senhaHash, hashProvider, infoMedica);
    }

    public Administrador(String id, String nome, String email, String cpf, int idade, String telefone, String senhaHash, String hashProvider) {
        super(id, nome, email, cpf, idade, telefone, senhaHash, hashProvider);
    }
}