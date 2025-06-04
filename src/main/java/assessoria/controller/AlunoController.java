package assessoria.controller;
import assessoria.model.Aluno;
import assessoria.service.AlunoService;
import java.util.Map;


public class AlunoController{

    private final AlunoService alunoService;

    public AlunoController(AlunoService alunoService) {
        this.alunoService = alunoService;
    }

    public void criarAluno(String nome, String email, String cpf, int idade, String telefone) {
        alunoService.salvarAluno(new Aluno(gerarId(), nome, email, cpf, idade, telefone));
    }

    private int gerarId() {
        int id = 0;
        for(Map.Entry<String,Aluno> entry : alunoService.pegarCopiaMapAluno().entrySet()) {
            id = entry.getValue().getId();
        }
        return id+1;
    }
    
    public Map<String,Aluno> pegarMapAlunos() {
        return alunoService.getMapAluno();
    }

}
