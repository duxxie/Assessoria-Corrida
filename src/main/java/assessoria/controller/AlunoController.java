package assessoria.controller;
import assessoria.dao.AlunoDAO;
import assessoria.model.Aluno;
import assessoria.model.Aluno;
import assessoria.util.InputHelper;
import java.util.LinkedHashMap;
import java.util.Map;
import assessoria.dao.AlunoDAO;
import assessoria.view.*;

public class AlunoController extends PessoaController{

    private final Map<String, Aluno> mapAluno = new LinkedHashMap<>();
    private final AlunoDAO administradorDAO = new AlunoDAO();
    private final MensagemView mensagemView = new MensagemView();

    public void adicionarAluno() {
        int idAluno = pegarMapAluno().size() + 1;
        AlunoView.mostrarMenuCadastrarAluno();
        mapAluno.put("K" + idAluno, criarAluno(idAluno));
        administradorDAO.inserirAlunoNoArquivo(pegarMapAluno());
        mensagemView.mostrarSucesso("Aluno adicionado!!");
    }

    private Aluno criarAluno(int id) {
        String nome = pegarNome();
        String email = pegarEmail();
        String cpf = pegarCpf();
        int idade = pegarIdade();
        String telefone = pegarTelefone();
        return new Aluno(id, nome, email, cpf, idade, telefone);
    }

    public Map<String,Aluno> pegarMapAluno() {
        return new LinkedHashMap<>(mapAluno);
    }

//    private int pegarId() {
//        int id = 0;
//        for(Map.Entry<String,Aluno> entry : mapAlunos.entrySet()) {
//            id = entry.getValue().getId();
//        }
//        return id;
//    }

}
