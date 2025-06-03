package assessoria.app;
import assessoria.controller.AdministradorController;
import assessoria.util.InputHelper;
import assessoria.view.*;

public class Aplicacao {

    private final MenuPrincipal menuPrincipal = new MenuPrincipal();
    private final MenuCadastro menuCadastro = new MenuCadastro();
    private final MenuLogin menuLogin = new MenuLogin();
    private final AlunoApp alunoApp = new AlunoApp();

    //Administrador
    private final AdministradorController adicionarAdministrador = new AdministradorController();

    public void executarPrograma() {
        int opcaoMenuPrincipal;
        do {
            menuPrincipal.mostrarMenuPrincipal();
            opcaoMenuPrincipal = InputHelper.lerOpcao();
            tratarOpcaoMenuPrincipal(opcaoMenuPrincipal);
        }while (opcaoMenuPrincipal != 0);
        System.out.println("\n\n");
        alunoApp.mostrarAlunos();
        InputHelper.encerrarInput();
    }

    private void tratarOpcaoMenuPrincipal(int opcaoMenuPrincipal) {
        switch (opcaoMenuPrincipal) {
            case 1 -> executarMenuCadastro();
            case 2 -> executarMenuLogin();
            case 0 -> menuPrincipal.mostrarSaida();
            default -> menuPrincipal.mostrarDefaultMenu();
        }
    }

    private void executarMenuCadastro() {
        int opcaoMenuCadastro;
        do {
            menuCadastro.mostrarMenu();
            opcaoMenuCadastro = InputHelper.lerOpcao();
            switch (opcaoMenuCadastro) {
                case 1 -> alunoApp.executarCadastro();
                //case 2 -> adicionarProfessor;
                //case 3 -> adicionarAdministrador.adicionarAdministrador();
                case 0 -> menuCadastro.mostrarSaida();
                default -> menuCadastro.mostrarDefaultMenu();
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
                case 0 -> menuLogin.mostrarSaida();
                default -> menuLogin.mostrarDefaultMenu();
            }
        }while (opcaoMenuLogin != 0);
    }
 }
