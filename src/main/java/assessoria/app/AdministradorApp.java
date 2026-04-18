package assessoria.app;

import assessoria.controller.AdministradorController;
import assessoria.model.dto.DadosAtualizacaoPessoa;
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

    public void executarCadastro() {
        administradorView.mostrarMenuCadastrarAdministrador();
        Administrador administrador = administradorView.realizarCadastroAdministrador();
        if(administrador != null)
            executarAcao(administrador);
    }

    public void executarLogin() {
        administradorView.mostrarMenuLoginAdministrador();
        Administrador administrador = administradorView.pegarEtratarDadosLogin();
        if(administrador != null) executarAcao(administrador);
    }

    private void executarAcao(Administrador administrador) {
        int opcao;
        do {
            if(administrador.isAdiminRaiz()) {
                administradorView.mostrarMenuAcoesAdminRaiz();
                opcao = InputHelper.lerOpcao();
                tratarOpcaoMenuAcoesAdminRaiz(opcao, administrador);
            } else {
                administradorView.mostrarMenuAcoesAdminNormal();
                opcao = InputHelper.lerOpcao();
                tratarOpcaoMenuAcoesAdminNormal(opcao, administrador);
            }
        }while(opcao != 0);
    }

    private void tratarOpcaoMenuAcoesAdminNormal(int opcao, Administrador administrador) {
        switch (opcao) {
            case 1 -> administradorView.mostrarDadosAdministrador(administrador);
            case 2 -> executarUpdate(administrador);
            case 3 -> administradorView.mostrarProfessorCadastrados();
            case 4 -> administradorView.mostrarAlunosCadastrados();
            case 0 -> MensagemView.mostrarMensagem("Encerrando login...");
            default -> MensagemView.mostrarErro("Escolha uma opção válida!!");
        }
    }

    private void tratarOpcaoMenuAcoesAdminRaiz(int opcao, Administrador administrador) {
        switch (opcao) {
            case 1 -> administradorView.mostrarDadosAdministrador(administrador);
            case 2 -> executarUpdate(administrador);
            case 3 -> administradorView.mostrarProfessorCadastrados();
            case 4 -> administradorView.mostrarAlunosCadastrados();
            case 5 -> administradorView.gerarCodigoAdministrador(administrador);
            case 6 -> administradorView.mostrarAdministradores(administrador);
            case 7 -> administradorView.mostrarCodigosAdministrador(administrador);
            case 8 -> administradorView.desativarAdministrador(administrador);
            case 9 -> administradorView.reativarAdministrador(administrador);
            case 10 -> administradorView.excluirAdministrador(administrador);
            case 0 -> MensagemView.mostrarMensagem("Encerrando login...");
            default -> MensagemView.mostrarErro("Escolha uma opção válida!!");
        }
    }

    public void executarUpdate(Administrador administrador) {
        int opcao;
        DadosAtualizacaoPessoa dadosAtualizacaoPessoa = administradorView.gerarAdministradorParaUpdate(administrador);

        do {
            administradorView.mostrarMenuUpdate(dadosAtualizacaoPessoa);
            opcao = InputHelper.lerOpcao();
            tratarOpcaoMenuUpdate(opcao, dadosAtualizacaoPessoa);
        }while(opcao != 0 && opcao != 6);
    }

    private void tratarOpcaoMenuUpdate(int opcao, DadosAtualizacaoPessoa dadosAtualizacaoPessoa) {
        switch (opcao) {
            case 1:
                String nome = administradorView.pegarNomeParaAtualizar();
                dadosAtualizacaoPessoa.setNome(nome);
                break;
            case 2:
                String email = administradorView.pegarEmailParaAtualizar();
                dadosAtualizacaoPessoa.setEmail(email);
                break;
            case 3:
                String senha = administradorView.pegarSenhaParaAtualizar();
                dadosAtualizacaoPessoa.setNovaSenha(senha);
                break;
            case 4:
                String telefone = administradorView.pegarTelefoneParaAtualizar();
                dadosAtualizacaoPessoa.setTelefone(telefone);
                break;
            case 5:
                String cpf = administradorView.pegarCpfParaAtualizar();
                dadosAtualizacaoPessoa.setCpf(cpf);
                break;
            case 6:
                administradorView.salvarAlteracoesAdministrador(dadosAtualizacaoPessoa);
                break;
            case 0:
                MensagemView.mostrarMensagem("Encerrando update...");
                break;
            default:
                MensagemView.mostrarErro("Escolha uma opção válida!!");
                break;
        }
    }

}
