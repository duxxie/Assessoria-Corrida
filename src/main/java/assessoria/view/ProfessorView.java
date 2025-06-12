package assessoria.view;

import assessoria.controller.AlunoController;
import assessoria.controller.ProfessorController;
import assessoria.controller.TreinoController;
import assessoria.model.entidades.Aluno;
import assessoria.model.entidades.Professor;
import assessoria.model.entidades.Treino;
import assessoria.util.helpers.BCryptHash;
import assessoria.util.helpers.InputHelper;
import assessoria.util.helpers.Validador;
import assessoria.util.log.Log;

import java.time.DayOfWeek;

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
        System.out.println("\n\n+ ---------------------------- +");
        System.out.println("|  << -- Cadastro Professor -- >>  |");
        System.out.println("+ ---------------------------- +");
    }

    public void pegarDadosProfessor() {
        BCryptHash bCryptHash = new BCryptHash();
        boolean dadosEmergencia = false;

        //Dados primários
        String nome = InputHelper.pegarNome();
        String cpf = InputHelper.pegarCpf();
        int idade = InputHelper.pegarIdade();
        String telefone = InputHelper.pegarTelefone();
        String email = InputHelper.pegarEmail();
        String senha = InputHelper.pegarSenhaToCadastro();
        String hash = bCryptHash.gerarHash(senha);

        //Dados scundários
        String condicaoMedica = InputHelper.pegarCondicaoMedica();
        String alergia = InputHelper.pegarAlergia();
        String[] medicamento = InputHelper.pegarMedicamentoEmUso();
        String medicamentoEmUso = medicamento != null ? medicamento[0] : null;
        String frequenciaMedicamento = medicamento != null ? medicamento[1] : null;
        String lesaoRecente = InputHelper.pegarLesao();
        String cirurgiaRecente = InputHelper.pegarCirurgiaRecente();
        String restricaoMedica = InputHelper.pegarRestricaoMedica();
        String tipoSanguineo = InputHelper.pegarTipoSanguineo();

        if(InputHelper.pegarEscolhaDadosContatoEmergencia()) {
            System.out.println("\n >>> Informe os dados do seu contado de emergencia abaixo <<<");
            String nomeEmergencia = InputHelper.pegarNome();
            String telefoneEmergencia = InputHelper.pegarTelefone();
            String relacao = InputHelper.pegarRelacao();
            //Criando professor com todos os atributos
            professorController.criarProfessor(nome, email, cpf, idade, telefone, senha, hash, nomeEmergencia, telefoneEmergencia, relacao, condicaoMedica, alergia, medicamentoEmUso, frequenciaMedicamento, lesaoRecente, cirurgiaRecente, restricaoMedica, tipoSanguineo);
        } else {
            //Criando professor sem os dados do contato de emergencia
            System.out.println("\n\n[<< Você pode adicionar um contado de emergência a qualquer momento dentro da sua conta!!>>]\n\n");
            professorController.criarProfessor(nome, email, cpf, idade, telefone, senha, hash, condicaoMedica, alergia, medicamentoEmUso, frequenciaMedicamento, lesaoRecente, cirurgiaRecente, restricaoMedica, tipoSanguineo);
        }
        MensagemView.mostrarSucesso("Seu cadastrado foi realizado com sucesso!!");
    }


    public void mostrarMenuLoginProfessor() {
        System.out.println("+ ------------------------- +");
        System.out.println("|  << -- Login Professor -- >>  |");
        System.out.println("+ ------------------------- +");
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
        System.out.println("|           [2] Salvar              |");
        System.out.println("|           [0] Voltar              |");
        System.out.println("+ --------------------------------- +");
    }


    public Aluno escolherAlunoPorCpf() {
        while(true) {
            System.out.println(" >> Informe o cpf do Aluno que receberá o treino <<");
            String cpfAluno = InputHelper.pegarCpf();
            Aluno aluno = Validador.isCpfExiste(cpfAluno, alunoController.pegarMapAlunos());
            if(aluno != null) {
                Log.registrar("info", "Aluno com ID(" + aluno.getId() + ") foi selecionado para receber um treino");
                return aluno;
            }else {
                System.out.println("Aluno não encontrado!!");
            }
        }
    }

    public Treino escolherAlunoPorCpfComTreino() {
        while(true) {
            Aluno aluno = escolherAlunoPorCpf();
            Treino treino = treinoController.isAlunoInTreino(aluno);
            if(treino != null) {
                Log.registrar("info", "Aluno com ID(" + aluno.getId() + ") foi selecionado para modificar o seu treino.");
                return treino;
            } else {
                System.out.println("Aluno não possui um treino criado!!");
            }
        }

    }
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


    public void salvarTreino(Treino treino) {
        treinoController.salvarTreino(treino);
    }

    public void mostrarDadosProfessor(Professor professor) {
        professor.mostrarInfoCompleta();
    }

    public String criarTreino(Aluno aluno, Professor professor) {
       return treinoController.criarTreino(aluno, professor);
    }

    public Treino pegarTreinoPorID(String id) {
        return treinoController.getTreinoPorID(id);
    }

    public void mostrarAlunos(Professor professor) {

    }

    public ProfessorController getProfessorController() {
        return professorController;
    }

    public TreinoController getTreinoController() {
        return treinoController;
    }

    public void atualizarProfessorNoMapTreino(Professor professor) {
        treinoController.atualizarProfessor(professor);
    }
}
