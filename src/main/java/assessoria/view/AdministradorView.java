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
    AdministradorController administradorController;
    AlunoController alunoController;
    TreinoController treinoController;
    ProfessorController professorController;

    public AdministradorView(AdministradorController administradorController, AlunoController alunoController, TreinoController treinoController, ProfessorController professorController) {
        this.administradorController = administradorController;
        this.alunoController = alunoController;
        this.treinoController = treinoController;
        this.professorController = professorController;
    }

    public void mostrarMenuCadastrarAdministrador() {
        System.out.println("\n\n+ ---------------------------- +");
        System.out.println("|  << -- Cadastro Administrador -- >>  |");
        System.out.println("+ ---------------------------- +");
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

        //Criando administrador sem os dados do contato de emergencia
        administradorController.criarAdministrador(nome, email, cpf, idade, telefone, senha, hash);
        MensagemView.mostrarSucesso("Seu cadastrado foi realizado com sucesso!!");
    }


    public void mostrarMenuLoginAdministrador() {
        System.out.println("+ ------------------------- +");
        System.out.println("|  << -- Login Administrador -- >>  |");
        System.out.println("+ ------------------------- +");
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
        System.out.println("\n\n+ ------------------------- +");
        System.out.println("|  << -- Ações Administrador -- >>  |");
        System.out.println("+ ------------------------- +");
        System.out.println("|   [1] Ver meus dados      |");
        System.out.println("|   [2] Alterar meus dados  |");
        System.out.println("|   [3] Visualizar dados de Professores |");
        System.out.println("|   [4] Visualizar dados de Alunos |");
        System.out.println("|   [5] Visualizar Treinos  |");
        System.out.println("|   [0] Encerrar sessão     |");
        System.out.println("+ ------------------------- +");
    }

    public void mostrarMenuUpdate() {
        System.out.println("\n\n+ ------------------------- +");
        System.out.println("|  << -- Ações Administrador -- >>  |");
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

    public void mostrarDadosAdministrador(Administrador administrador) {
        administrador.mostrarInfo();
    }

    public AdministradorController getAdministradorController() {
        return administradorController;
    }
}
