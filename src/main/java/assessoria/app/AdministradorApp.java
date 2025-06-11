package assessoria.app;

import assessoria.controller.AdministradorController;
import assessoria.model.dao.AdministradorDAO;
import assessoria.model.dao.AlunoDAO;
import assessoria.model.entidades.Administrador;
import assessoria.service.AdministradorService;
import assessoria.util.helpers.InputHelper;
import assessoria.view.AdministradorView;
import assessoria.view.MensagemView;

public class AdministradorApp {
    // Criando instancias que serao usadas nas classes
    private final AdministradorView administradorView;
    private final AdministradorController administradorController;
    private final AdministradorService administradorService;
    private final MensagemView mensagemView;
    private final AdministradorDAO administradorDAO;

    public AdministradorApp() {
        this.mensagemView = new MensagemView();
        this.administradorDAO = new AdministradorDAO();
        this.administradorService = new AdministradorService(administradorDAO);
        this.administradorController = new AdministradorController(administradorService);
        this.administradorView = new AdministradorView(administradorController);
    }

    public void carregarMap() {
        administradorService.carregarMapAdministrador();
    }

    public void salvarDadosNoArquivo() {
        administradorController.salvarAdministradorArquivo();
    }

    public void executarCadastro() {
        administradorView.mostrarMenuCadastrarAdministrador();
        administradorView.pegarDadosAdministrador();
    }

    public void executarLogin() {
        administradorView.mostrarMenuLoginAdministrador();
        executarAcao(administradorView.pegarEtratarDadosLogin());
    }

    private void executarAcao(Administrador administrador) {
        int opcao;
        do {
            administradorView.mostrarMenuAcoes();
            opcao = InputHelper.lerOpcao();
            tratarOpcaoMenuAcoes(opcao, administrador);
        }while(opcao != 0);

    }

    private void tratarOpcaoMenuAcoes(int opcao, Administrador administrador) {
        switch (opcao) {
            case 1 -> administradorView.mostrarDadosAdministrador(administrador);
            case 0 -> mensagemView.mostrarMensagem("Encerrando login...");
            default -> mensagemView.mostrarErro("Escolha uma opção válida!!");
        }
    }

    public void mostrarAdministrador() {
        administradorView.mostrarAdministradorCadastrados();
    }
}
