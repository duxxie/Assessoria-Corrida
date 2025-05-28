package assessoria.controller;

import assessoria.dao.PessoaDAO;
import assessoria.model.Pessoa;
import assessoria.util.InputHelper;
import assessoria.view.PessoaView;
import assessoria.view.MensagemView;

import java.util.LinkedHashMap;
import java.util.Map;

public abstract class PessoaController {
    private final Map<String, Pessoa> mapPessoa = new LinkedHashMap<>();
    private final PessoaDAO administradorDAO = new PessoaDAO();
    private final MensagemView mensagemView = new MensagemView();

    private Pessoa criarPessoa(int id) {
        String nome = pegarNome();
        String email = pegarEmail();
        String cpf = pegarCpf();
        int idade = pegarIdade();
        String telefone = pegarTelefone();
        return new Pessoa(id, nome, email, cpf, idade, telefone);
    }

    public void adicionarPessoa() {
        int idPessoa = pegarMapPessoa().size() + 1;
        PessoaView.mostrarMenuCadastroPessoa();
        mapPessoa.put("K" + idPessoa, criarPessoa(idPessoa));
        administradorDAO.inserirPessoaNoArquivo(pegarMapPessoa());
        mensagemView.mostrarSucesso("Pessoa adicionado!!");
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

    public Map<String,Pessoa> pegarMapPessoa() {
        return new LinkedHashMap<>(mapPessoa);
    }
}