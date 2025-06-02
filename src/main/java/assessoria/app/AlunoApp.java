package assessoria.app;

import assessoria.view.AlunoView;

public class AlunoApp {

    AlunoView alunoView = new AlunoView();

    public void executarCadastro() {
        alunoView.mostrarMenuCadastrarAluno();
        alunoView.pegarDadosAluno();
    }
}
