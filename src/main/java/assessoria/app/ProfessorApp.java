package assessoria.app;

import assessoria.controller.ProfessorController;
import assessoria.model.entidades.Professor;
import assessoria.util.helpers.BCryptHash;
import assessoria.util.helpers.InputHelper;
import assessoria.util.log.Log;
import assessoria.view.ProfessorView;
import assessoria.view.MensagemView;

public class ProfessorApp {

    private final ProfessorView professorView;
    private final ProfessorController professorController;

    public ProfessorApp(ProfessorView professorView, ProfessorController professorController) {
        this.professorView = professorView;
        this.professorController = professorController;
    }

    public void carregarMap() {
        professorView.getProfessorController().carregarMap();
    }

    public void executarCadastro() {
        professorView.mostrarMenuCadastrarProfessor();
        professorView.pegarDadosProfessor();
    }

    public void executarLogin() {
        professorView.mostrarMenuLoginProfessor();
        executarAcao(professorView.pegarEtratarDadosLogin());
    }

    public void executarUpdate(Professor professor) {
        int opcao;
        do {
            professorView.mostrarMenuUpdate();
            opcao = InputHelper.lerOpcao();
            tratarOpcaoMenuUpdate(opcao, professor);
        }while(opcao != 0);
    }

    private void tratarOpcaoMenuUpdate(int opcao, Professor professor) {
        boolean salvo = false;
        switch (opcao) {
            case 1:
                String nome = InputHelper.pegarNome();
                professor.setNome(nome);
                break;
            case 2:
                String email = InputHelper.pegarEmail();
                professor.setEmail(email);
                break;
            case 3:
                String senha = InputHelper.pegarSenhaToCadastro();
                professor.setSenhaHash(senha);
                BCryptHash bCryptHash = new BCryptHash();
                String hash = bCryptHash.gerarHash(senha);
                professor.setHashProvider(hash);
                break;
            case 4:
                String telefone = InputHelper.pegarTelefone();
                professor.setTelefone(telefone);
                break;
            case 5:
                String cpf = InputHelper.pegarCpf();
                professor.setCpf(cpf);
                break;
            case 6:
                professorController.salvarProfessor(professor);
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

    private void executarAcao(Professor professor) {
        Log.registrar("info", professor.getNome() + " fez login");
        int opcao;
        do {
            professorView.mostrarMenuAcoes();
            opcao = InputHelper.lerOpcao();
            tratarOpcaoMenuAcoes(opcao, professor);
        }while(opcao != 0);

    }

    private void tratarOpcaoMenuAcoes(int opcao, Professor professor) {
        switch (opcao) {
            case 1 -> professorView.mostrarDadosProfessor(professor);
            case 4 -> professorView.mostrarMenuCriarTreino();
            case 5 -> executarUpdate(professor);
            case 0 -> MensagemView.mostrarMensagem("Encerrando login...");
            default -> MensagemView.mostrarErro("Escolha uma opção válida!!");
        }
    }

    public void mostrarProfessor() {
        professorView.mostrarProfessorCadastrados();
    }
}
