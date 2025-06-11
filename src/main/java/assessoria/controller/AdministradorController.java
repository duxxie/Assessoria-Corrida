package assessoria.controller;

import assessoria.model.entidades.Administrador;
import assessoria.service.AdministradorService;
import assessoria.util.helpers.GeradorID;

import java.util.Map;

public class AdministradorController{

    private final AdministradorService administradorService;

    public AdministradorController(AdministradorService administradorService) {
        this.administradorService = administradorService;
    }

    public void criarAdministrador(String nome, String email, String cpf, int idade, String telefone, String senha, String hashSenha) {
        administradorService.salvarAdministrador(new Administrador(GeradorID.gerarIdAdministrador(), nome, email, cpf, idade, telefone, senha, hashSenha));
    }

    public void salvarAdministradorArquivo() {
        administradorService.inserirAdministradorArquivo();
    }

    public Map<String,Administrador> pegarMapAdministrador() {
        return administradorService.getMapAdministrador();
    }
}