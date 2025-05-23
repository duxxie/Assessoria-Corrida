package assessoria.controller;
import assessoria.model.Aluno;
import assessoria.view.InputHelper;

import java.util.LinkedHashMap;
import java.util.Map;
import assessoria.dao.AlunoDAO;

public class AlunoController {

    private final Map<String, Aluno> mapAlunos = new LinkedHashMap<>();;
    private final AlunoDAO alunoDAO = new AlunoDAO();

    public AlunoController() {}

    public Aluno criarAluno() {
        String nome = pegarNome();
        String email = pegarEmail();
        String cpf = pegarCpf();
        int idade = pegarIdade();
         return new Aluno(1, nome, email, cpf, idade);
    }

    public void adicionarAluno() {
        mapAlunos.put("K1", criarAluno());
        alunoDAO.inserirAlunoNoArquivo(pegarMapAlunos());
        System.out.println("Aluno adicionado com sucesso!!");
    }

    public String pegarNome() {
       return InputHelper.lerString("Digite o nome completo: ");
    }

    public int pegarIdade() {
        return InputHelper.lerInt("Digite a idade: ");
    }

    public String pegarEmail() {
        return InputHelper.lerString("Digite o email: ");
    }

    public String pegarCpf() {
        return InputHelper.lerString("Digite o CPF: ");
    }

    public Map<String,Aluno> pegarMapAlunos() {
        return new LinkedHashMap<>(mapAlunos);
    }

}
