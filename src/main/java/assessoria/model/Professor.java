package assessoria.model;

public class Professor extends Pessoa {
    public Professor(int id, String nome, String cpf, int idade) {
        super(id, nome, cpf, idade);
    }

    public Professor(int id, String nome, String email, String cpf, int idade) {
        super(id, nome, email, cpf, idade);
    }
}
