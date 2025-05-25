package assessoria.controller;
import assessoria.model.Aluno;
import assessoria.util.InputHelper;
import java.util.LinkedHashMap;
import java.util.Map;
import assessoria.dao.AlunoDAO;
import assessoria.view.AlunoView;

public class AlunoController {

    private final Map<String, Aluno> mapAlunos = new LinkedHashMap<>();;
    private final AlunoDAO alunoDAO = new AlunoDAO();

    public AlunoController() {}

    private Aluno criarAluno(int id) {
        String nome = pegarNome();
        String email = pegarEmail();
        String cpf = pegarCpf();
        int idade = pegarIdade();
        String telefone = pegarTelefone();
         return new Aluno(id, nome, email, cpf, idade, telefone);
    }

    public void adicionarAluno() {
        int idAluno = pegarMapAlunos().size() + 1;
        AlunoView.mostrarMenuCadastrarAluno();
        mapAlunos.put("K" + idAluno, criarAluno(idAluno));
        alunoDAO.inserirAlunoNoArquivo(pegarMapAlunos());
        System.out.println("Aluno adicionado com sucesso!!");
    }

    private String pegarNome() {
       return InputHelper.lerString("Digite o nome completo: ");
    }

    private int pegarIdade() {
        return InputHelper.lerInt("Digite a idade: ");
    }

    private String pegarEmail() {
        return InputHelper.lerString("Digite o email (Ex: user@gmail.com): ");
    }

    private String pegarCpf() {
        return InputHelper.lerCpf("Digite o CPF (xxx.xxx.xxx-xx) : ");
    }

    private String pegarTelefone() {
        return InputHelper.lerString("Digite o telefone ((DDD)9xxxx-xxxx): ");
    }

    public Map<String,Aluno> pegarMapAlunos() {
        return new LinkedHashMap<>(mapAlunos);
    }

}
