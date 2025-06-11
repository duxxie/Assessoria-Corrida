package assessoria.app;
import assessoria.controller.AdministradorController;
import assessoria.util.helpers.GeradorID;
import assessoria.util.helpers.InputHelper;
import assessoria.util.log.Log;
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
        InputHelper.encerrarInput();
    }

    public void mostrarDados() {
        alunoApp.mostrarAlunos();
    }

    public void inicializarDados() {
        alunoApp.carregarMap();
        Log.registrar("Info", "Map de Alunos inicializado.");
        GeradorID.carregarIds();
        Log.registrar("Info", "IDs inicializados.");
    }

    public void salvarDados() {
        GeradorID.salvarIds();
        Log.registrar("Info", "Salvos os ultimos IDs que foram gerados.");
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
                case 1 -> alunoApp.executarLogin();
                case 2 -> System.out.println("Entrar como professor");
                case 3 -> System.out.println("Entrar como Administrador");
                case 0 -> menuLogin.mostrarSaida();
                default -> menuLogin.mostrarDefaultMenu();
            }
        }while (opcaoMenuLogin != 0);
    }
 }
