package assessoria.view;

import assessoria.controller.AdministradorController;
import assessoria.controller.AlunoController;
import assessoria.controller.ProfessorController;
import assessoria.controller.TreinoController;
import assessoria.model.entidades.Administrador;
import assessoria.util.helpers.BCryptHash;
import assessoria.util.helpers.InputHelper;
import assessoria.util.helpers.Validador;

public class AdministradorView {
    private final ProfessorDashBoard professorDashBoard;
    private final AlunoDashBoard alunoDashBoard;
    private final AdministradorController administradorController;
    private final AlunoController alunoController;
    private final TreinoController treinoController;
    private final ProfessorController professorController;


    public AdministradorView(AdministradorController administradorController, AlunoController alunoController, TreinoController treinoController, ProfessorController professorController) {
        this.administradorController = administradorController;
        this.alunoController = alunoController;
        this.treinoController = treinoController;
        this.professorController = professorController;
        this.professorDashBoard = new ProfessorDashBoard();
        this.alunoDashBoard = new AlunoDashBoard();
    }

    public void mostrarMenuCadastrarAdministrador() {
        System.out.println("\n\n+ ------------------------------------ +");
        System.out.println("|  << -- Cadastro Administrador -- >>  |");
        System.out.println("+ ------------------------------------ +");
    }

    public void pegarDadosAdministrador() {
        BCryptHash bCryptHash = new BCryptHash();

        //Dados primários
        String nome = InputHelper.pegarNome();
        String cpf = InputHelper.pegarCpf();
        int idade = InputHelper.pegarIdade();
        String telefone = InputHelper.pegarTelefone();
        String email = InputHelper.pegarEmail();
        String senha = InputHelper.pegarSenhaToCadastro();
        String hash = bCryptHash.gerarHash(senha);
        String codigoAdmin = InputHelper.lerString("Informe o código de administrador: ");

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
            administradorController.criarAdministrador(nome, email, cpf, idade, telefone, senha, hash, codigoAdmin, nomeEmergencia, telefoneEmergencia, relacao, condicaoMedica, alergia, medicamentoEmUso, frequenciaMedicamento, lesaoRecente, cirurgiaRecente, restricaoMedica, tipoSanguineo);
        } else {
            System.out.println("\n\n[<< Você pode adicionar um contado de emergência a qualquer momento dentro da sua conta!!>>]\n\n");
            administradorController.criarAdministrador(nome, email, cpf, idade, telefone, senha, hash, codigoAdmin, condicaoMedica, alergia, medicamentoEmUso, frequenciaMedicamento, lesaoRecente, cirurgiaRecente, restricaoMedica, tipoSanguineo);
        }
    }


    public void mostrarMenuLoginAdministrador() {
        System.out.println("\n\n+ --------------------------------- +");
        System.out.println("|  << -- Login Administrador -- >>  |");
        System.out.println("+ --------------------------------- +");
    }

    public Administrador pegarEtratarDadosLogin() {
        while(true) {
            try {
                String email = InputHelper.pegarEmail();
                String senha = InputHelper.pegarSenhaToLogin();
                return Validador.isDadosLoginValido(email, senha, administradorController.pegarMapAdministrador());
            }catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void mostrarMenuAcoes() {
        System.out.println("\n\n+ ------------------------------------- +");
        System.out.println("|    << -- Ações Administrador -- >>    |");
        System.out.println("+ ------------------------------------- +");
        System.out.println("|  [1] Ver meus dados                   |");
        System.out.println("|  [2] Alterar meus dados               |");
        System.out.println("|  [3] Visualizar dados de Professores  |");
        System.out.println("|  [4] Visualizar dados de Alunos       |");
        System.out.println("|  [0] Encerrar sessão                  |");
        System.out.println("+ ------------------------------------- +");
    }

    public void mostrarMenuUpdate() {
        System.out.println("\n\n+ --------------------------------- +");
        System.out.println("|  << -- Ações Administrador -- >>  |");
        System.out.println("+ --------------------------------- +");
        System.out.println("|     [1] Alterar nome              |");
        System.out.println("|     [2] Alterar email             |");
        System.out.println("|     [3] Alterar senha             |");
        System.out.println("|     [4] Alterar telefone          |");
        System.out.println("|     [5] Alterar CPF               |");
        System.out.println("|     [6] Salvar alterações         |");
        System.out.println("|     [0] Encerrar sessão           |");
        System.out.println("+ --------------------------------- +");
    }

    public void mostrarDadosAdministrador(Administrador administrador) {
        administrador.mostrarInfo();
    }

    public void mostrarProfessorCadastrados() {
        professorDashBoard.mostrarTabela(professorController.pegarMapProfessor());
    }

    public void mostrarAlunosCadastrados() {
        alunoDashBoard.mostrarTabela(alunoController.pegarMapAlunos());
    }
}
