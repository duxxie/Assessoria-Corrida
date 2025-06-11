package assessoria.model.entidades;

public class Professor extends Pessoa implements Usuario, Savable {
    public Professor() {super();}

    public Professor(String id, String nome, String cpf, int idade) {
        super(id, nome, cpf, idade);
    }

    public Professor(String id, String nome, String email, String cpf, int idade, String telefone) {
        super(id, nome, email, cpf, idade, telefone);
    }

    public Professor(String id, String nome, String email, String cpf, int idade, String telefone, String senhaHash, String hashProvider, ContatoEmergencia contatoEmergencia, InfoMedica infoMedica) {
        super(id, nome, email, cpf, idade, telefone, senhaHash, hashProvider, contatoEmergencia, infoMedica);
    }

    public Professor(String id, String nome, String email, String cpf, int idade, String telefone, String senhaHash, String hashProvider, InfoMedica infoMedica) {
        super(id, nome, email, cpf, idade, telefone, senhaHash, hashProvider, infoMedica);
    }
}
