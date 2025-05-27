package assessoria.app;
import assessoria.controller.AlunoController;
import assessoria.model.Aluno;
import assessoria.util.InputHelper;
import java.util.Map;
import assessoria.view.*;

public class Aplicacao {

    private final MenuPrincipal menuPrincipal = new MenuPrincipal();
    private final AlunoController alunoController = new AlunoController();
    private final MenuCadastro menuCadastro = new MenuCadastro();
    private final MenuLogin menuLogin = new MenuLogin();
    private final MensagemView mensagemView = new MensagemView();

    public void executarPrograma() {
        int opcaoMenuPrincipal;
        do {
            menuPrincipal.mostrarMenuPrincipal();
            opcaoMenuPrincipal = InputHelper.lerOpcao();
            tratarOpcaoMenuPrincipal(opcaoMenuPrincipal);
        }while (opcaoMenuPrincipal != 0);
        InputHelper.encerrarInput();
    }

    private void tratarOpcaoMenuPrincipal(int opcaoMenuPrincipal) {
        switch (opcaoMenuPrincipal) {
            case 1 -> executarMenuCadastro();
            case 2 -> executarMenuLogin();
            case 0 -> mensagemView.mostrarMensagem("Encerrando o programa...");
            default -> mensagemView.mostrarErro("Escolha uma opção válida!!");
        }
    }

    private void executarMenuCadastro() {
        int opcaoMenuCadastro;
        do {
            menuCadastro.mostrarMenu();
            opcaoMenuCadastro = InputHelper.lerOpcao();
            switch (opcaoMenuCadastro) {
                case 1 -> alunoController.adicionarAluno();
                //case 2 -> adicionarProfessor;
                //case 3 -> adicionarAdministrador;
                case 0 -> mensagemView.mostrarMensagem("Votando ao menu principal...");
                default -> mensagemView.mostrarErro("Opção inválida para cadastro!!");
            }
        }while(opcaoMenuCadastro != 0);
    }

    private void executarMenuLogin() {
        int opcaoMenuLogin;
        do {
            menuLogin.mostrarMenu();
            opcaoMenuLogin = InputHelper.lerOpcao();
            switch (opcaoMenuLogin) {
                case 1 -> System.out.println("Entrar como aluno");
                case 2 -> System.out.println("Entrar como professor");
                case 3 -> System.out.println("Entrar como Administrador");
                case 0 -> mensagemView.mostrarMensagem("Votando ao menu principal...");
                default -> mensagemView.mostrarErro("Opção inválida para login!!");
            }
        }while (opcaoMenuLogin != 0);
    }
 }
