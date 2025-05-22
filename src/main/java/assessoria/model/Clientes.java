package assessoria.model;

public class Clientes extends Pessoa{

    public Clientes(int id, String nome, String cpf, int idade) {
        super(id, nome, cpf, idade);
    }

    public Clientes(int id, String nome, String email, String cpf, int idade) {
        super(id, nome, email, cpf, idade);
    }
}