package assessoria.controller;

import assessoria.util.InputHelper;

public abstract class PessoaController {


    protected String pegarNome() {
        return InputHelper.lerString("Digite o nome completo: ");
    }

    protected int pegarIdade() {
        return InputHelper.lerInt("Digite a idade: ");
    }

    protected String pegarEmail() {
        return InputHelper.lerEmail("Digite o email (Ex: user@gmail.com): ");
    }

    protected String pegarCpf() {
        return InputHelper.lerCpf("Digite o CPF (xxx.xxx.xxx-xx) : ");
    }

    protected String pegarTelefone() {
        return InputHelper.lerString("Digite o telefone ((DDD)9xxxx-xxxx): ");
    }
}