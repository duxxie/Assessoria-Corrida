package assessoria.app;
import assessoria.controller.AdministradorController;
import assessoria.controller.AlunoController;
import assessoria.controller.ProfessorController;
import assessoria.controller.TreinoController;
import assessoria.model.dao.AdministradorDAO;
import assessoria.model.dao.AlunoDAO;
import assessoria.model.dao.ProfessorDAO;
import assessoria.model.dao.TreinoDAO;
import assessoria.service.AdministradorService;
import assessoria.service.AlunoService;
import assessoria.service.ProfessorService;
import assessoria.service.TreinoService;
import assessoria.util.helpers.GeradorID;
import assessoria.util.helpers.InputHelper;
import assessoria.util.log.Log;
import assessoria.view.*;

public class Aplicacao {

    //Instancias Aluno
    private final AlunoApp alunoApp;
    private final AlunoView alunoView;
    private final AlunoController alunoController;
    private final AlunoService alunoService;
    private final AlunoDAO alunoDAO;

    //Instancias Treino
    private final TreinoApp treinoApp;
    private final TreinoView treinoView;
    private final TreinoController treinoController;
    private final TreinoService treinoService;
    private final TreinoDAO treinoDAO;

    //Instancias Professor
    private final ProfessorApp professorApp;
    private final ProfessorView professorView;
    private final ProfessorController professorController;
    private final ProfessorService professorService;
    private final ProfessorDAO professorDAO;

    //Intancias Administrados
    private final AdministradorApp administradorApp;
    private final AdministradorView administradorView;
    private final AdministradorController administradorController;
    private final AdministradorService administradorService;
    private final AdministradorDAO administradorDAO;


    public Aplicacao() {
        this.treinoDAO = new TreinoDAO();
        this.treinoService = new TreinoService(treinoDAO);
        this.treinoController = new TreinoController(treinoService);
        this.treinoView = new TreinoView(treinoController);
        this.treinoApp = new TreinoApp(treinoView);

        this.alunoDAO = new AlunoDAO();
        this.alunoService = new AlunoService(alunoDAO);
        this.alunoController = new AlunoController(alunoService);
        this.alunoView = new AlunoView(alunoController);
        this.alunoApp = new AlunoApp(alunoView);

        this.professorDAO = new ProfessorDAO();
        this.professorService = new ProfessorService(professorDAO);
        this.professorController = new ProfessorController(professorService);
        this.professorView = new ProfessorView(professorController, alunoController);
        this.professorApp = new ProfessorApp(professorView);

        this.administradorDAO = new AdministradorDAO();
        this.administradorService = new AdministradorService(administradorDAO);
        this.administradorController = new AdministradorController(administradorService);
        this.administradorView = new AdministradorView(administradorController, alunoController, treinoController, professorController);
        this.administradorApp = new AdministradorApp(administradorView);
    }

    private final MenuPrincipal menuPrincipal = new MenuPrincipal();
    private final MenuCadastro menuCadastro = new MenuCadastro();
    private final MenuLogin menuLogin = new MenuLogin();


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
        professorApp.mostrarProfessor();
    }

    public void inicializarDados() {
        professorApp.carregarMap();
        Log.registrar("Info", "Map de Professores inicializado.");
        administradorApp.carregarMap();
        Log.registrar("Info", "Map de Administradores inicializado.");
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
                case 2 -> professorApp.executarCadastro();
                case 3 -> administradorApp.executarCadastro();
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
                case 2 -> professorApp.executarLogin();
                case 3 -> administradorApp.executarLogin();
                case 0 -> menuLogin.mostrarSaida();
                default -> menuLogin.mostrarDefaultMenu();
            }
        }while (opcaoMenuLogin != 0);
    }
 }