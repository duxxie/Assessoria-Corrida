package assessoria.view;

import assessoria.controller.AlunoController;
import assessoria.controller.ProfessorController;
import assessoria.controller.TreinoController;
import assessoria.model.dto.DadosCadastroPessoa;
import assessoria.model.entidades.Aluno;
import assessoria.model.entidades.Professor;
import assessoria.model.entidades.Treino;
import assessoria.util.helpers.BCryptHash;
import assessoria.util.helpers.CadastroViewHelper;
import assessoria.util.helpers.InputHelper;
import assessoria.util.helpers.Validador;
import assessoria.util.log.Log;

import java.time.DayOfWeek;
import java.util.List;

public class ProfessorView {

    ProfessorController professorController;
    AlunoController alunoController;
    TreinoController treinoController;

    public ProfessorView(ProfessorController professorController, AlunoController alunoController, TreinoController treinoController) {
        this.professorController = professorController;
        this.alunoController = alunoController;
        this.treinoController = treinoController;
    }
    ProfessorDashBoard professorDashBoard = new ProfessorDashBoard();


    public void mostrarMenuCadastrarProfessor() {
        System.out.println("\n\n+ -------------------------------- +");
        System.out.println("|  << -- Cadastro Professor -- >>  |");
        System.out.println("+ -------------------------------- +");
    }

    public void pegarDadosProfessor() {
        DadosCadastroPessoa dadosCadastroPessoa = CadastroViewHelper.pegarDadosCadastroPessoa();
        String cref = InputHelper.pegarCref();

        professorController.criarProfessor(dadosCadastroPessoa, cref);
    }


    public void mostrarMenuLoginProfessor() {
        System.out.println("+ ----------------------------- +");
        System.out.println("|  << -- Login Professor -- >>  |");
        System.out.println("+ ----------------------------- +");
    }

    public Professor pegarEtratarDadosLogin() {
        while(true) {
            try {
                String email = InputHelper.pegarEmail();
                String senha = InputHelper.pegarSenhaToLogin();
                return Validador.isDadosLoginValido(email, senha, professorController.pegarMapProfessor());
            }catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void mostrarProfessorCadastrados() {
        professorDashBoard.mostrarTabela(professorController.pegarMapProfessor());
    }

    public void mostrarMenuAcoes() {
        System.out.println("\n\n+ ------------------------------- +");
        System.out.println("|   << -- Ações Professor -- >>   |");
        System.out.println("+ ------------------------------- +");
        System.out.println("|      [1] Ver meus dados         |");
        System.out.println("|      [2] Ver meus treinos       |");
        System.out.println("|      [3] Ver meus alunos        |");
        System.out.println("|      [4] Criar um treino        |");
        System.out.println("|      [5] Alterar meus dados     |");
        System.out.println("|      [6] Modificar Treino       |");
        System.out.println("|      [0] Encerrar sessão        |");
        System.out.println("+ ------------------------------- +");
    }


    
    public void mostrarMenuAdicionarAtividades() {
        System.out.println("\n\n+ ---------------------------------- +");
        System.out.println("|  << -- Adicionar Atividades -- >>  |");
        System.out.println("+ ---------------------------------- +");
        System.out.println("|         [1] Adicionar Linha        |");
        System.out.println("|         [2] Remover Linha          |");
        System.out.println("|         [0] Voltar                 |");
        System.out.println("+ ---------------------------------- +");
    }

    private String pegarLinhaAtividade(String frase) {
        return InputHelper.lerString(frase);
    }

    public void adicionarLinha(DayOfWeek day, Treino treino) {
        treino.adicionarAtividade(day, pegarLinhaAtividade("Digite a informação aqui: "));
    }

    public void removerLinha(DayOfWeek day, Treino treino) {
        treino.removerAtividade(day, pegarLinhaAtividade("Digite a informação da linha que quer remover: "));
    }

    public void mostrarMenuOpDiaTreino() {
        System.out.println("\n\n+ ------------------------------------ +");
        System.out.println("|  <<-- Escolha um dia da semana -->>  |");
        System.out.println("+ ------------------------------------ +");
        System.out.println("|         [1] - Segunda-feira          |");
        System.out.println("|         [2] - Terça-feira            |");
        System.out.println("|         [3] - Quarta-feira           |");
        System.out.println("|         [4] - Quinta-feira           |");
        System.out.println("|         [5] - Sexta-feira            |");
        System.out.println("|         [6] - Sabado                 |");
        System.out.println("|         [7] - Domingo                |");
        System.out.println("|         [0] - Voltar                 |");
        System.out.println("+ ------------------------------------ +");
    }


    public void mostrarMenuCriarTreino() {
        System.out.println("\n\n+ ----------------------------- +");
        System.out.println("|    << -- Criar treino -->>    |");
        System.out.println("+ ----------------------------- +");
        System.out.println("|          [1] Começar          |");
        System.out.println("|          [2] Salvar           |");
        System.out.println("|          [0] Voltar           |");
        System.out.println("+ ----------------------------- +");
    }


    public void mostrarMenuModificarTreino() {
        System.out.println("\n\n+ --------------------------------- +");
        System.out.println("|    << -- Modificar treino -->>    |");
        System.out.println("+ --------------------------------- +");
        System.out.println("|           [1] Modificar           |");
        System.out.println("|           [0] Voltar              |");
        System.out.println("+ --------------------------------- +");
    }

    public void mostrarMenuOpModificarTreino() {
        System.out.println("\n\n+ --------------------------------- +");
        System.out.println("|    << -- Modificar treino -->>    |");
        System.out.println("+ --------------------------------- +");
        System.out.println("|           [1] Descrição           |");
        System.out.println("|           [2] Plano Semanal       |");
        System.out.println("|           [3] Salvar              |");
        System.out.println("|           [0] Voltar              |");
        System.out.println("+ --------------------------------- +");
    }

    public void mostrarTreinosDoProfessor(Professor professor) {
        var mapTreinoProfessor = treinoController.getTreinoByProfessorId(professor.getId());

        String tituloMenu = "<< -- Meus Treinos -- >>";

        List<String> treinosOptions = mapTreinoProfessor.entrySet().stream()
                .map(entry -> " [" + entry.getKey() + "] - " + entry.getValue().getDescricao())
                .toList();

        int maiorLinhaTreinos = tituloMenu.length();

        for(var treino : treinosOptions) {
            maiorLinhaTreinos = Math.max(maiorLinhaTreinos, treino.length());
        }

        final int maiorLinhaOpTreinos = maiorLinhaTreinos;
        int marginLado = 6;

        String linhaMenu = "+ " + "-".repeat((maiorLinhaTreinos+marginLado) - 3) + " +";

        int paddingTitulo = ((maiorLinhaTreinos - tituloMenu.length()) + marginLado) / 2;

        String tituloMenuFormatado = "|" + " ".repeat(paddingTitulo) + tituloMenu + " ".repeat(paddingTitulo) + "|";

        System.out.println("\n\n" + linhaMenu);
        System.out.println(tituloMenuFormatado);
        System.out.println(linhaMenu);
        treinosOptions.stream()
                .forEach(treino -> System.out.println("|" + treino + " ".repeat((maiorLinhaOpTreinos - treino.length()) + (marginLado -1)) + "|"));

        System.out.println(linhaMenu);
    }


    public Aluno escolherAlunoPorCpf() {
        while(true) {
            System.out.println(" >> Informe o cpf do Aluno que receberá o treino <<");
            String cpfAluno = InputHelper.pegarCpf();
            Aluno aluno = alunoController.findAlunoByCpf(cpfAluno);
            if(aluno != null) {
                return aluno;
            }else {
                System.out.println("Aluno não encontrado!!");
            }
        }
    }

//    public Treino escolherAlunoPorCpfComTreino() {
//        while(true) {
//            Aluno aluno = escolherAlunoPorCpf();
//            Treino treino = treinoController.isAlunoInTreino(aluno);
//            if(treino != null) {
//                return treino;
//            } else {
//                System.out.println("Aluno não possui um treino criado!!");
//            }
//        }
//
//    }
    public void mostrarMenuUpdate() {
        System.out.println("\n\n+ ------------------------- +");
        System.out.println("|  << -- Ações Professor -- >>  |");
        System.out.println("+ ----------------------------- +");
        System.out.println("|       [1] Alterar nome        |");
        System.out.println("|       [2] Alterar email       |");
        System.out.println("|       [3] Alterar senha       |");
        System.out.println("|       [4] Alterar telefone    |");
        System.out.println("|       [5] Alterar CPF         |");
        System.out.println("|       [6] Salvar alterações   |");
        System.out.println("|       [0] Encerrar sessão     |");
        System.out.println("+ ----------------------------- +");
    }

    public String pegarNomeTreino() {
        return InputHelper.lerString("Informe um nome ao treino: ");
    }

    public void salvarTreino() {
        treinoController.salvarTreino();
        MensagemView.mostrarSucesso("Treino salvo com sucesso!");
    }

    public void mostrarTodosTreinos() {
        var treinoMap = treinoController.getAllTreino();

        treinoMap.values().stream()
                .forEach(treino -> System.out.println(treino.toString()));

        System.out.println("\n Tamanho do map treinos => " + treinoMap.size());
    }

    public void mostrarDadosProfessor(Professor professor) {
        professor.mostrarInfoCompleta();
    }

    public Treino criarTreino(String alunoId, String professorId, String desTreino) {
       return treinoController.criarTreino(alunoId, professorId, desTreino);
    }

    public Treino pegarTreinoById(String id) {
        return treinoController.getTreinoPorID(id);
    }

    public void mostrarAlunos(Professor professor) {

    }
}
