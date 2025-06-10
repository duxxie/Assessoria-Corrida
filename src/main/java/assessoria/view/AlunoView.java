package assessoria.view;

import assessoria.controller.AlunoController;
import assessoria.model.entidades.Aluno;
import assessoria.util.helpers.BCryptHash;
import assessoria.util.helpers.InputHelper;
import assessoria.util.helpers.Validador;

public class AlunoView {

    AlunoController alunoController;

    public AlunoView(AlunoController alunoController) {
        this.alunoController = alunoController;
    }
    MensagemView mensagemView = new MensagemView();
    DashBoardView dashBoardView = new DashBoardView();

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
        String senha = InputHelper.pegarSenha();
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
        mensagemView.mostrarSucesso("Seu cadastrado foi realizado com sucesso!!");
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
                String senha = InputHelper.pegarSenha();
                return Validador.isDadosLoginValido(email, senha, alunoController.pegarMapAlunos());
            }catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void mostrarAlunosCadastrados() {
        dashBoardView.mostrarTabela(alunoController.pegarMapAlunos());
    }

    public void mostrarMenuAcoes() {
        System.out.println("\n\n+ ------------------------- +");
        System.out.println("|  << -- Ações Aluno -- >>  |");
        System.out.println("+ ------------------------- +");
        System.out.println("|   [1] Ver meus dados      |");
        System.out.println("|   [2] Ver meus treinos    |");
        System.out.println("|   [3] Alterar meus dados  |");
        System.out.println("|   [0] Encerrar sessão     |");
        System.out.println("+ ------------------------- +");

    }

    public void mostrarDadosAluno(Aluno aluno) {
        aluno.mostrarInfoCompleta();
    }


}
