package assessoria.view;

import assessoria.controller.AlunoController;
import assessoria.controller.TreinoController;
import assessoria.model.entidades.Aluno;
import assessoria.model.entidades.Treino;
import assessoria.util.helpers.BCryptHash;
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

    public void pegarDadosAluno() {
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
            //Criando aluno com todos os atributos
            alunoController.criarAluno(nome, email, cpf, idade, telefone, senha, hash, nomeEmergencia, telefoneEmergencia, relacao, condicaoMedica, alergia, medicamentoEmUso, frequenciaMedicamento, lesaoRecente, cirurgiaRecente, restricaoMedica, tipoSanguineo);
        } else {
            //Criando aluno sem os dados do contato de emergencia
            System.out.println("\n\n[<< Você pode adicionar um contado de emergência a qualquer momento dentro da sua conta!!>>]\n\n");
            alunoController.criarAluno(nome, email, cpf, idade, telefone, senha, hash, condicaoMedica, alergia, medicamentoEmUso, frequenciaMedicamento, lesaoRecente, cirurgiaRecente, restricaoMedica, tipoSanguineo);
        }
        MensagemView.mostrarSucesso("Seu cadastrado foi realizado com sucesso!!");
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
            }catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void mostrarAlunosCadastrados() {
        alunoDashBoard.mostrarTabela(alunoController.pegarMapAlunos());
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
