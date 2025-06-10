package assessoria.model.entidades;

public class Professor extends Pessoa {
    public Professor(String id, String nome, String cpf, int idade) {
        super(id, nome, cpf, idade);
    }

    public Professor(String id, String nome, String email, String cpf, int idade, String telefone) {
        super(id, nome, email, cpf, idade, telefone);
    }
}
