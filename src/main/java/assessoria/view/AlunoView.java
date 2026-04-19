package assessoria.view;

import assessoria.controller.AlunoController;
import assessoria.controller.TreinoController;
import assessoria.exceptions.ValidationException;
import assessoria.model.dto.DadosCadastroPessoa;
import assessoria.model.entidades.Aluno;
import assessoria.model.entidades.Treino;
import assessoria.util.helpers.CadastroViewHelper;
import assessoria.util.helpers.InputHelper;
import assessoria.util.helpers.Validador;

public class AlunoView {

    private final AlunoController alunoController;
    private final TreinoController treinoController;
    public AlunoView(AlunoController alunoController, TreinoController treinoController) {
        this.alunoController = alunoController;
        this.treinoController = treinoController;
    }
    AlunoDashBoard alunoDashBoard = new AlunoDashBoard();

    public void mostrarMenuCadastrarAluno() {
        System.out.println("\n\n+ ---------------------------- +");
        System.out.println("|  << -- Cadastro Aluno -- >>  |");
        System.out.println("+ ---------------------------- +");
    }

    public Aluno realizarCadastroAluno() {
        DadosCadastroPessoa dadosCadastroPessoa = CadastroViewHelper.pegarDadosCadastroPessoa();
        return alunoController.cadastrarAluno(dadosCadastroPessoa);
    }

    public void mostrarMenuLoginAluno() {
        System.out.println("+ ------------------------- +");
        System.out.println("|  << -- Login Aluno -- >>  |");
        System.out.println("+ ------------------------- +");
    }

    public Aluno pegarEtratarDadosLogin() {
        while(true) {
            try {
                String email = InputHelper.pegarEmail();
                String senha = InputHelper.pegarSenhaToLogin();
                return Validador.isDadosLoginValido(email, senha, alunoController.pegarMapAlunos());
            }catch (ValidationException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void mostrarMenuAcoes() {
        System.out.println("\n\n+ ------------------------- +");
        System.out.println("|  << -- Ações Aluno -- >>  |");
        System.out.println("+ ------------------------- +");
        System.out.println("|   [1] Ver meus dados      |");
        System.out.println("|   [2] Ver meu treino      |");
        System.out.println("|   [3] Alterar meus dados  |");
        System.out.println("|   [0] Encerrar sessão     |");
        System.out.println("+ ------------------------- +");
    }

    public void mostrarMenuUpdate() {
        System.out.println("\n\n+ --------------------------------- +");
        System.out.println("|  << -- Ações Administrador -- >>  |");
        System.out.println("+ --------------------------------- +");
        System.out.println("|         [1] Alterar nome          |");
        System.out.println("|         [2] Alterar email         |");
        System.out.println("|         [3] Alterar senha         |");
        System.out.println("|         [4] Alterar telefone      |");
        System.out.println("|         [5] Alterar CPF           |");
        System.out.println("|         [0] Encerrar sessão       |");
        System.out.println("+ --------------------------------- +");
    }

    public void mostrarDadosAluno(Aluno aluno) {
        aluno.mostrarInfoCompleta();
    }

    public void mostrarTreino(Aluno aluno) {
        Treino treino = treinoController.isAlunoInTreino(aluno);
        if (treino != null) {
            treino.mostrarTreino();
        } else {
            MensagemView.mostrarMensagem("\n [  >>> Você ainda não possuí treino <<<  ]\n\n");
        }
    }

    public void atualizarAlunoNoTreino(Aluno aluno) {
        treinoController.atualizarAluno(aluno);
    }
}
