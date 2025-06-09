package assessoria.model;

import assessoria.util.Hash;

public class Aluno extends Pessoa implements Usuario{

    public Aluno() {super();}

    public Aluno(int id, String nome, String cpf, int idade) {
        super(id, nome, cpf, idade);
    }

    public Aluno(int id, String nome, String email, String cpf, int idade, String telefone) {
        super(id, nome, email, cpf, idade, telefone);
    }

    public Aluno(int id, String nome, String email, String cpf, int idade, String telefone, String senhaHash, String hashProvider, ContatoEmergencia contatoEmergencia, InfoMedica infoMedica) {
        super(id, nome, email, cpf, idade, telefone, senhaHash, hashProvider, contatoEmergencia, infoMedica);
    }

    public Aluno(int id, String nome, String email, String cpf, int idade, String telefone, String senhaHash, String hashProvider, InfoMedica infoMedica) {
        super(id, nome, email, cpf, idade, telefone, senhaHash, hashProvider, infoMedica);
    }


}