package assessoria.controller;

import assessoria.dao.AlunoDAO;
import assessoria.model.Administrador;
import assessoria.model.Aluno;
import assessoria.util.InputHelper;
import assessoria.view.AdministradorView;
import assessoria.view.AlunoView;
import assessoria.view.MensagemView;

import java.util.LinkedHashMap;
import java.util.Map;

public class AdministradorController {
    private final Map<String, Administrador> mapAdministrador = new LinkedHashMap<>();;
    private final AlunoDAO alunoDAO = new AlunoDAO();
    private final MensagemView mensagemView = new MensagemView();

    private Administrador criarAdministrador(int id) {
        String nome = pegarNome();
        String email = pegarEmail();
        String cpf = pegarCpf();
        int idade = pegarIdade();
        String telefone = pegarTelefone();
        return new Administrador(id, nome, email, cpf, idade, telefone);
    }

    public void adicionarAdministrador() {
        int idAdministrador = pegarMapAdministrador().size() + 1;
        AdministradorView.mostrarMenuCadastroAdministrador();
        mapAdministrador.put("K" + idAdministrador, criarAdministrador(idAdministrador));
        mensagemView.mostrarSucesso("Administrador adicionado!!");
    }

    private String pegarNome() {
        return InputHelper.lerString("Digite o nome completo: ");
    }

    private int pegarIdade() {
        return InputHelper.lerInt("Digite a idade: ");
    }

    private String pegarEmail() {
        return InputHelper.lerEmail("Digite o email (Ex: user@gmail.com): ");
    }

    private String pegarCpf() {
        return InputHelper.lerCpf("Digite o CPF (xxx.xxx.xxx-xx) : ");
    }

    private String pegarTelefone() {
        return InputHelper.lerString("Digite o telefone ((DDD)9xxxx-xxxx): ");
    }

    public Map<String,Administrador> pegarMapAdministrador() {
        return new LinkedHashMap<>(mapAdministrador);
    }

}
