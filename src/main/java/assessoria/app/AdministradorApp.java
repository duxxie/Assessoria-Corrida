package assessoria.app;

import assessoria.controller.AdministradorController;
import assessoria.model.entidades.Administrador;
import assessoria.util.helpers.BCryptHash;
import assessoria.util.helpers.InputHelper;
import assessoria.view.AdministradorView;
import assessoria.view.MensagemView;

public class AdministradorApp {

    private final AdministradorController administradorController;
    private final AdministradorView administradorView;

    public AdministradorApp(AdministradorView administradorView, AdministradorController administradorController) {
        this.administradorView = administradorView;
        this.administradorController = administradorController;
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

    public void executarUpdate(Administrador administrador) {
        int opcao;
        do {
            administradorView.mostrarMenuUpdate();
            opcao = InputHelper.lerOpcao();
            tratarOpcaoMenuUpdate(opcao, administrador);
        }while(opcao != 0);
    }

    private void tratarOpcaoMenuUpdate(int opcao, Administrador administrador) {
        boolean salvo = false;
        switch (opcao) {
            case 1:
                String nome = InputHelper.pegarNome();
                administrador.setNome(nome);
                break;
            case 2:
                String email = InputHelper.pegarEmail();
                administrador.setEmail(email);
                break;
            case 3:
                String senha = InputHelper.pegarSenhaToCadastro();
                administrador.setSenhaHash(senha);
                BCryptHash bCryptHash = new BCryptHash();
                String hash = bCryptHash.gerarHash(senha);
                administrador.setHashProvider(hash);
                break;
            case 4:
                String telefone = InputHelper.pegarTelefone();
                administrador.setTelefone(telefone);
                break;
            case 5:
                String cpf = InputHelper.pegarCpf();
                administrador.setCpf(cpf);
                break;
            case 6:
                administradorController.salvarAdministrador(administrador);
                salvo = true;
                break;
            case 0:
                if (salvo){
                    MensagemView.mostrarMensagem("Encerrando update...");
                } else {
                    MensagemView.mostrarMensagem("Os dados não foram salvos!!!");
                }
                break;
            default:
                MensagemView.mostrarErro("Escolha uma opção válida!!");
                break;
        }
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
            case 2 -> executarUpdate(administrador);
            case 3 -> administradorView.mostrarProfessorCadastrados();
            case 4 -> administradorView.mostrarAlunosCadastrados();
            case 0 -> MensagemView.mostrarMensagem("Encerrando login...");
            default -> MensagemView.mostrarErro("Escolha uma opção válida!!");
        }
    }

}
