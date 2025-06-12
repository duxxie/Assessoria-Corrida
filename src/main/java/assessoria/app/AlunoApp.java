package assessoria.app;

import assessoria.controller.AlunoController;
import assessoria.model.entidades.Aluno;
import assessoria.util.helpers.BCryptHash;
import assessoria.util.helpers.InputHelper;
import assessoria.util.log.Log;
import assessoria.view.AlunoView;
import assessoria.view.MensagemView;

public class AlunoApp {
    // Criando instancias que serao usadas nas classes

    private final AlunoView alunoView;
    private final AlunoController alunoController;

    public AlunoApp(AlunoView alunoView, AlunoController alunoController) {
        this.alunoView = alunoView;
        this.alunoController = alunoController;
    }

    public void carregarMap() {
        alunoController.carregarAlunos();
    }

    public void executarCadastro() {
        alunoView.mostrarMenuCadastrarAluno();
        alunoView.pegarDadosAluno();
    }

    public void executarLogin() {
        alunoView.mostrarMenuLoginAluno();
        executarAcao(alunoView.pegarEtratarDadosLogin());
    }

    public void executarUpdate(Aluno aluno) {
        int opcao;
        do {
            alunoView.mostrarMenuUpdate();
            opcao = InputHelper.lerOpcao();
            tratarOpcaoMenuUpdate(opcao, aluno);
        }while(opcao != 0);
    }

    private void tratarOpcaoMenuUpdate(int opcao, Aluno aluno) {
        switch (opcao) {
            case 1:
                String nome = InputHelper.pegarNome();
                aluno.setNome(nome);
                break;
            case 2:
                String email = InputHelper.pegarEmail();
                aluno.setEmail(email);
                break;
            case 3:
                String senha = InputHelper.pegarSenhaToCadastro();
                aluno.setSenhaHash(senha);
                BCryptHash bCryptHash = new BCryptHash();
                String hash = bCryptHash.gerarHash(senha);
                aluno.setHashProvider(hash);
                break;
            case 4:
                String telefone = InputHelper.pegarTelefone();
                aluno.setTelefone(telefone);
                break;
            case 5:
                String cpf = InputHelper.pegarCpf();
                aluno.setCpf(cpf);
                break;
            case 0:
                    MensagemView.mostrarMensagem("Voltando...");
                break;
            default:
                MensagemView.mostrarErro("Escolha uma opção válida!!");
                break;
        }
        alunoController.salvarAluno(aluno);
        alunoView.atualizarAlunoNoTreino(aluno);
    }

    private void executarAcao(Aluno aluno) {
        Log.registrar("info", aluno.getNome() + " fez login");
        int opcao;
        do {
            alunoView.mostrarMenuAcoes();
            opcao = InputHelper.lerOpcao();
            tratarOpcaoMenuAcoes(opcao, aluno);
        }while(opcao != 0);

    }

    private void tratarOpcaoMenuAcoes(int opcao, Aluno aluno) {
        switch (opcao) {
            case 1 -> alunoView.mostrarDadosAluno(aluno);
            case 2 -> alunoView.mostrarTreino(aluno);
            case 3 -> executarUpdate(aluno);
            case 0 -> MensagemView.mostrarMensagem("Encerrando login...");
            default -> MensagemView.mostrarErro("Escolha uma opção válida!!");
        }
    }

    public void mostrarAlunos() {
        alunoView.mostrarAlunosCadastrados();
    }

}
