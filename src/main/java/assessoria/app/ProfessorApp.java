package assessoria.app;

import assessoria.controller.ProfessorController;
import assessoria.model.dao.ProfessorDAO;
import assessoria.model.entidades.Aluno;
import assessoria.model.entidades.Professor;
import assessoria.model.entidades.Treino;
import assessoria.service.ProfessorService;
import assessoria.util.helpers.InputHelper;
import assessoria.util.log.Log;
import assessoria.view.ProfessorView;
import assessoria.view.MensagemView;

import java.time.DayOfWeek;

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
            case 4 -> executarCriacaoTreino(professor);
            case 0 -> MensagemView.mostrarMensagem("Encerrando login...");
            default -> MensagemView.mostrarErro("Escolha uma opção válida!!");
        }
    }

    private void executarCriacaoTreino(Professor professor) {
        int opExecutarCriacaoTreino;
        do {
            professorView.mostrarMenuCriarTreino();
            opExecutarCriacaoTreino = InputHelper.lerOpcao();
            tratarOpCriacaoTreino(opExecutarCriacaoTreino, professor);
        }while(opExecutarCriacaoTreino != 0);
    }

    private void tratarOpCriacaoTreino(int opCriacaoTreino, Professor professor) {
        switch (opCriacaoTreino) {
            case 1 -> criarTreino(professor);
            case 2 -> professorView.salvarTreino();
            case 0 -> MensagemView.mostrarMensagem("Voltando...");
            default -> MensagemView.mostrarErro("Escolha uma opção válida!!");
        }
    }


    private void criarTreino(Professor professor) {
        Aluno aluno = professorView.escolherAlunoPorCpf();
        Treino treino = professorView.pegarTreinoPorID(professorView.criarTreino(aluno, professor));
        int opCriarTreino;
        do {
            professorView.mostrarMenuOpDiaTreino();
            opCriarTreino = InputHelper.lerOpcao();
            tratarOpDiaTreino(opCriarTreino, treino);
        }while(opCriarTreino != 0);
    }

    private void tratarOpDiaTreino(int opDia, Treino treino) {
        DayOfWeek day = null;
        switch (opDia) {
            case 1 -> day = DayOfWeek.MONDAY;
            case 2 -> day = DayOfWeek.TUESDAY;
            case 3 -> day = DayOfWeek.WEDNESDAY;
            case 4 -> day = DayOfWeek.THURSDAY;
            case 5 -> day = DayOfWeek.FRIDAY;
            case 6 -> day = DayOfWeek.SATURDAY;
            case 7 -> day = DayOfWeek.SUNDAY;
            case 0 -> MensagemView.mostrarMensagem("Voltando...");
            default -> MensagemView.mostrarErro("Escolha uma opção válida!!");
        }
        adicionarAtividades(day, treino);
    }

    private void adicionarAtividades(DayOfWeek day, Treino treino) {
        int opAdicionarAtv;
        do {
            professorView.mostrarMenuAdicionarAtividades();
            opAdicionarAtv = InputHelper.lerOpcao();
            tratarOpAddAtv(opAdicionarAtv, day, treino);
        }while(opAdicionarAtv != 0);
    }

    private void tratarOpAddAtv(int opAdd, DayOfWeek day, Treino treino) {
        switch (opAdd) {
            case 1 -> professorView.adicionarLinha(day, treino);
            case 2 -> professorView.removerLinha(day, treino);
            case 0 -> MensagemView.mostrarMensagem("Saindo...");
            default -> MensagemView.mostrarErro("Escolha uma opção válida!!!");
        }
    }

    public void mostrarProfessor() {
        professorView.mostrarProfessorCadastrados();
    }
}
