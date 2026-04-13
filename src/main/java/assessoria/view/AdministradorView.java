package assessoria.view;

import assessoria.controller.AdministradorController;
import assessoria.controller.AlunoController;
import assessoria.controller.ProfessorController;
import assessoria.controller.TreinoController;
import assessoria.model.dto.DadosCadastroPessoa;
import assessoria.model.entidades.Administrador;
import assessoria.util.helpers.BCryptHash;
import assessoria.util.helpers.CadastroViewHelper;
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
        DadosCadastroPessoa dadosCadastroPessoa = CadastroViewHelper.pegarDadosCadastroPessoa();
        String codigoAdmin = InputHelper.lerString("Informe o código de administrador: ");
        administradorController.criarAdministrador(dadosCadastroPessoa, codigoAdmin);
    }

    public void mostrarMenuLoginAdministrador() {
        System.out.println("\n\n+ --------------------------------- +");
        System.out.println("|  << -- Login Administrador -- >>  |");
        System.out.println("+ --------------------------------- +");
    }

    public Administrador pegarEtratarDadosLogin() {
        String email = InputHelper.pegarEmail();
        String senha = InputHelper.pegarSenhaToLogin();
        Administrador administrador = administradorController.validarLogin(email, senha);

        return administrador;
    }

    public void mostrarMenuAcoesAdminNormal() {
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

    public void mostrarMenuAcoesAdminRaiz() {
        System.out.println("\n\n+ ------------------------------------- +");
        System.out.println("|    << -- Ações Administrador -- >>    |");
        System.out.println("+ ------------------------------------- +");
        System.out.println("|  [1] Ver meus dados                   |");
        System.out.println("|  [2] Alterar meus dados               |");
        System.out.println("|  [3] Visualizar dados de Professores  |");
        System.out.println("|  [4] Visualizar dados de Alunos       |");
        System.out.println("|  [5] Gerar código de administrador    |");
        System.out.println("|  [6] Visualizar administradores       |");
        System.out.println("|  [7] Visualizar codigos               |");
        System.out.println("|  [8] Desativar administrador          |");
        System.out.println("|  [9] Reativar administrador           |");
        System.out.println("|  [10] Excluir administrador           |");
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

    public void mostrarAdministradores(Administrador administrador) {
        var listAdminParaExibicao = administradorController.gerarListaAdministradorParaExibicao();
        if(listAdminParaExibicao != null) AdministradorDashBoard.mostrarTabela(listAdminParaExibicao);
    }

    public void gerarCodigoAdministrador(Administrador administrador) {
        System.out.println(">> Código: " + administradorController.gerarCodigoAdministrador(administrador));
    }

    public void mostrarCodigosAdministrador(Administrador administrador) {
        CodigoAdministradorDashBoard.mostrarTabela(administradorController.pegarCodigoAdministradorList());
    }

    public void excluirAdministrador(Administrador administrador) {
        mostrarAdministradores(administrador);
        String idAdministradorInformado = InputHelper.lerString("\n >> Informe o id do administrador que deseja excluir: ");
        administradorController.excluirAdministrador(idAdministradorInformado, administrador);
    }

    public void desativarAdministrador(Administrador administrador) {
        mostrarAdministradores(administrador);
        String idAdministradorInformado = InputHelper.lerString("\n >> Infome o id do administrador que deseja desativar: ");
        administradorController.desativarAdministrador(idAdministradorInformado, administrador);
    }

    public void reativarAdministrador(Administrador administrador) {
        mostrarAdministradores(administrador);
        String idAdministradorInformado = InputHelper.lerString("\n >> Informe o id do administrador que deseja reativar: ");
        administradorController.reativarAdministrador(idAdministradorInformado, administrador);
    }
}
