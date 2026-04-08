package assessoria.model.entidades;

public class Aluno extends Pessoa implements Usuario,Savable {

    public Aluno() {super();}

    public Aluno(String id, String nome, String email, String cpf, int idade, String telefone) {
        super(id, nome, email, cpf, idade, telefone);
    }

    public Aluno(String id, String nome, String email, String cpf, int idade, String telefone, String senhaHash, String hashProvider, ContatoEmergencia contatoEmergencia, InfoMedica infoMedica) {
        super(id, nome, email, cpf, idade, telefone, senhaHash, hashProvider, contatoEmergencia, infoMedica);
    }

    public Aluno(String id, String nome, String email, String cpf, int idade, String telefone, String senhaHash, String hashProvider, InfoMedica infoMedica) {
        super(id, nome, email, cpf, idade, telefone, senhaHash, hashProvider, infoMedica);
    }

    public Aluno(String id, String nome, String email, String cpf, int idade, String telefone, String senhaHash, String hashProvider, ContatoEmergencia contatoEmergencia) {
        super(id, nome, email, cpf, idade, telefone, senhaHash, hashProvider, contatoEmergencia);
    }

    public Aluno(String id, String nome, String email, String cpf, int idade, String telefone, String senhaHash, String hashProvider) {
        super(id, nome, email, cpf, idade, telefone, senhaHash, hashProvider);
    }

}