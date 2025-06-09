package assessoria.app;

import assessoria.controller.AlunoController;
import assessoria.model.Aluno;
import assessoria.service.AlunoService;
import assessoria.util.InputHelper;
import assessoria.view.AlunoView;
import assessoria.view.MensagemView;

// Carlos Alcaraz
// 883.628.930-44
//22
// (11)91234-5678
// carlos_alcaraz@gmail.com
public class AlunoApp {
    // Criando instancias que serao usadas nas classes
    AlunoView alunoView;
    AlunoController alunoController;
    AlunoService alunoService;
    MensagemView mensagemView;

    public AlunoApp() {
        this.mensagemView = new MensagemView();
        this.alunoService = new AlunoService();
        this.alunoController = new AlunoController(alunoService);
        this.alunoView = new AlunoView(alunoController);
    }

    public void carregarMap() {
        alunoService.carregarMapAluno();
    }

    public void executarCadastro() {
        alunoView.mostrarMenuCadastrarAluno();
        alunoView.pegarDadosAluno();
    }

    public void executarLogin() {
        alunoView.mostrarMenuLoginAluno();
        executarAcao(alunoView.pegarEtratarDadosLogin());
    }

    private void executarAcao(Aluno aluno) {
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
            case 0 -> mensagemView.mostrarMensagem("Encerrando login...");
            default -> mensagemView.mostrarErro("Escolha uma opção válida!!");
        }
    }

    public void mostrarAlunos() {
        alunoView.mostrarAlunosCadastrados();
    }

}
