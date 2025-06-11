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

   private final AdministradorView administradorView;

    public AdministradorApp(AdministradorView administradorView) {
        this.administradorView = administradorView;
    }

    public void carregarMap() {
        administradorView.getAdministradorController().carregarMap();
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
            case 0 -> MensagemView.mostrarMensagem("Encerrando login...");
            default -> MensagemView.mostrarErro("Escolha uma opção válida!!");
        }
    }

    public void mostrarAdministrador() {
        administradorView.mostrarAdministradorCadastrados();
    }
}
