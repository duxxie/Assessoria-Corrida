package assessoria.controller;

import assessoria.dao.AdministradorDAO;
import assessoria.dao.AdministradorDAO;
import assessoria.model.Administrador;
import assessoria.model.Administrador;
import assessoria.util.InputHelper;
import assessoria.view.AdministradorView;
import assessoria.view.MensagemView;
import org.mindrot.jbcrypt.BCrypt;

import java.util.LinkedHashMap;
import java.util.Map;

public class AdministradorController extends PessoaController{

    private final Map<String, Administrador> mapAdministrador = new LinkedHashMap<>();
    private final AdministradorDAO administradorDAO = new AdministradorDAO();
    private final MensagemView mensagemView = new MensagemView();

    public void adicionarAdministrador() {
        int idAdministrador = pegarMapAdministrador().size() + 1;
        AdministradorView.mostrarMenuCadastroAdministrador();
        mapAdministrador.put("K" + idAdministrador, criarAdministrador(idAdministrador));
        administradorDAO.inserirAdministradorNoArquivo(pegarMapAdministrador());
        mensagemView.mostrarSucesso("Administrador adicionado!!");
    }

    private Administrador criarAdministrador(int id) {
        String nome = pegarNome();
        String email = pegarEmail();
        String cpf = pegarCpf();
        int idade = pegarIdade();
        String telefone = pegarTelefone();
        return new Administrador(id, nome, email, cpf, idade, telefone);
    }

    public Map<String,Administrador> pegarMapAdministrador() {
        return new LinkedHashMap<>(mapAdministrador);
    }
}