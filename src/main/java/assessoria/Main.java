package assessoria;
import assessoria.controller.AlunoController;
import assessoria.model.Aluno;
import assessoria.util.InputHelper;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.Map;


public class Main {

     public static final File arquivoUsers = new File("src/main/java/assessoria/util/users/usuarios.json");
     //public static final File arquivoProfessores = new File("src/main/java/assessoria/util/users/professores.json");

    public static void main(String[] args) {
        System.out.println("Assessoria-Corrida");
        AlunoController alunoController = new AlunoController();

        Map<String, Aluno> mapClientes = new LinkedHashMap<>();
        int opcao;
        //mapClientes.put("K3",new Aluno(3, "Gustavo", "232.858.545-21", 20));
        //mapClientes.put("K1", new Aluno(1, "Raul Kon Uk Nakama", "234.432.334-54", 34));
        //mapClientes.put("K2", new Aluno(2, "Fredd", "434.565.443-90", 54));

        do {
            System.out.println("------------------------");
            System.out.println("| -->> Assessoria -->> |");
            System.out.println("------------------------");
            System.out.println("Cadastrar aluno [1]");
            System.out.println("Sair[0]");
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