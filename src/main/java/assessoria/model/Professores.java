package assessoria.model;

public class Professores extends Pessoa {
    public Professores(int id, String nome, String cpf, int idade) {
        super(id, nome, cpf, idade);
    }

    public Professores(int id, String nome, String email, String cpf, int idade) {
        super(id, nome, email, cpf, idade);
    }
}
