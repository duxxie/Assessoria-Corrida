package assessoria.app;

import assessoria.controller.ProfessorController;
import assessoria.model.dao.ProfessorDAO;
import assessoria.model.entidades.Professor;
import assessoria.service.ProfessorService;
import assessoria.util.helpers.InputHelper;
import assessoria.util.log.Log;
import assessoria.view.ProfessorView;
import assessoria.view.MensagemView;

public class ProfessorApp {

    private final ProfessorView professorView;

    public ProfessorApp(ProfessorView professorView) {
        this.professorView = professorView;
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
            case 0 -> MensagemView.mostrarMensagem("Encerrando login...");
            default -> MensagemView.mostrarErro("Escolha uma opção válida!!");
        }
    }

    public void mostrarProfessor() {
        professorView.mostrarProfessorCadastrados();
    }
}
