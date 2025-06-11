package assessoria.app;

import assessoria.controller.AdministradorController;
import assessoria.model.entidades.Administrador;
import assessoria.service.AdministradorService;
import assessoria.util.helpers.InputHelper;
import assessoria.view.AdministradorView;
import assessoria.view.MensagemView;

public class AdministradorApp {
    // Criando instancias que serao usadas nas classes
    AdministradorView administradorView;
    AdministradorController administradorController;
    AdministradorService administradorService;
    MensagemView mensagemView;

    public AdministradorApp() {
        this.mensagemView = new MensagemView();
        this.administradorService = new AdministradorService();
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
