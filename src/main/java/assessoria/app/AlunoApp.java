package assessoria.app;

import assessoria.controller.AlunoController;
import assessoria.model.dao.AlunoDAO;
import assessoria.model.entidades.Aluno;
import assessoria.service.AlunoService;
import assessoria.util.helpers.InputHelper;
import assessoria.util.log.Log;
import assessoria.view.AlunoView;
import assessoria.view.MensagemView;

public class AlunoApp {
    // Criando instancias que serao usadas nas classes

    private final AlunoView alunoView;

    public AlunoApp(AlunoView alunoView) {
        this.alunoView = alunoView;
    }

    public void carregarMap() {
        alunoView.getAlunoController().carregarAlunos();
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
            case 0 -> MensagemView.mostrarMensagem("Encerrando login...");
            default -> MensagemView.mostrarErro("Escolha uma opção válida!!");
        }
    }

    public void mostrarAlunos() {
        alunoView.mostrarAlunosCadastrados();
    }

}
