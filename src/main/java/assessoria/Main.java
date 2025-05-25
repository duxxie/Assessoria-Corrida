package assessoria;
import assessoria.controller.AlunoController;
import assessoria.model.Aluno;
import assessoria.util.InputHelper;
import assessoria.view.MenuPrincipal;
import java.util.Map;


public class Main {

    public static void main(String[] args) {
        AlunoController alunoController = new AlunoController();
        int opcao;

        do {
            MenuPrincipal.mostrarMenuPrincipal();
            opcao = InputHelper.lerInt("Escolha uma opção: ");

            switch (opcao) {
                case 1 -> alunoController.adicionarAluno();
                case 0 -> System.out.println("Saindo...");
                default -> System.out.println("Escolha uma opção válida");
            }
        }while(opcao != 0);

        for(Map.Entry<String, Aluno> a : alunoController.pegarMapAlunos().entrySet()) {
            System.out.println(a.getKey());
        }
    }
}