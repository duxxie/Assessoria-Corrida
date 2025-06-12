package assessoria.view;

import assessoria.controller.AlunoController;
import assessoria.controller.ProfessorController;
import assessoria.model.entidades.Aluno;
import assessoria.model.entidades.Professor;
import assessoria.util.helpers.BCryptHash;
import assessoria.util.helpers.InputHelper;
import assessoria.util.helpers.Validador;

public class ProfessorView {

    ProfessorController professorController;
    AlunoController alunoController;

    public ProfessorView(ProfessorController professorController) {
        this.professorController = professorController;
    }
    MensagemView mensagemView = new MensagemView();
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
        mensagemView.mostrarSucesso("Seu cadastrado foi realizado com sucesso!!");
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
        System.out.println("|  << -- Ações Professor -- >>  |");
        System.out.println("+ ------------------------------- +");
        System.out.println("|     [1] Ver meus dados        |");
        System.out.println("|     [2] Ver meus treinos      |");
        System.out.println("|     [3] Ver meus alunos       |");
        System.out.println("|     [4] Criar um treino       |");
        System.out.println("|     [5] Alterar meus dados    |");
        System.out.println("|     [0] Encerrar sessão       |");
        System.out.println("+ ------------------------------- +");
    }

    public void mostrarMenuUpdate() {
        System.out.println("\n\n+ ------------------------- +");
        System.out.println("|  << -- Ações Professor -- >>  |");
        System.out.println("+ ------------------------- +");
        System.out.println("|   [1] Alterar nome      |");
        System.out.println("|   [2] Alterar email  |");
        System.out.println("|   [3] Alterar senha |");
        System.out.println("|   [4] Alterar telefone |");
        System.out.println("|   [5] Alterar CPF  |");
        System.out.println("|   [6] Salvar alterações  |");
        System.out.println("|   [0] Encerrar sessão     |");
        System.out.println("+ ------------------------- +");
    }

    //private Professor

    public void mostrarMenuCriarTreino() {
        System.out.println("|  << -- Criar treino -->>  |");
        System.out.println(" >> Informe o cpf do Aluno que receberá o treino <<");
        String cpfProfessor = InputHelper.pegarCpf();
        Aluno aluno = Validador.isCpfExiste(cpfProfessor, alunoController.pegarMapAlunos());
        if(aluno != null) {
            System.out.println("\n\nAluno encontrado");
            aluno.mostrarInfo();
        }else {
            System.out.println("Aluno não encontrado!!");
        }
    }

    public void mostrarDadosProfessor(Professor professor) {
        professor.mostrarInfoCompleta();
    }

    public ProfessorController getProfessorController() {
        return professorController;
    }
}
