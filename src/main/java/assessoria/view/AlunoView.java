package assessoria.view;

import assessoria.controller.AlunoController;
import assessoria.util.InputHelper;

public class AlunoView {

    AlunoController alunoController = new AlunoController();
    MensagemView mensagemView = new MensagemView();

    public void mostrarMenuCadastrarAluno() {
        System.out.println("\n\n+ ---------------------------- +");
        System.out.println("|  << -- Cadastro Aluno -- >>  |");
        System.out.println("+ ---------------------------- +");
    }

    public void pegarDadosAluno() {
        String nome = InputHelper.pegarNome();
        String email = InputHelper.pegarEmail();
        String cpf = InputHelper.pegarCpf();
        int idade = InputHelper.pegarIdade();
        String telefone = InputHelper.pegarTelefone();
        alunoController.criarAluno(nome, email, cpf, idade, telefone);
        mensagemView.mostrarSucesso("Aluno cadastrado com sucesso!!");
    }
}
