package assessoria.model;

public class Administrador extends Pessoa{
    public Administrador(int id, String nome, String cpf, int idade) {
        super(id, nome, cpf, idade);
    }

    public Administrador(int id, String nome, String email, String cpf, int idade) {
        super(id, nome, email, cpf, idade);
    }
}