package assessoria.app;

import assessoria.controller.AlunoController;
import assessoria.service.AlunoService;
import assessoria.view.AlunoView;
import assessoria.view.DashBoardView;

public class AlunoApp {
    // Criando instancias que serao usadas nas classes
    AlunoView alunoView;
    AlunoController alunoController;
    AlunoService alunoService;

    public AlunoApp() {
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

    public void mostrarAlunos() {
        alunoView.mostrarAlunosCadastrados();
    }

}
