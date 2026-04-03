package assessoria.controller;

import assessoria.model.entidades.Administrador;
import assessoria.service.AdministradorService;
import assessoria.view.MensagemView;

import java.util.Map;

public class AdministradorController{

    private final AdministradorService administradorService;

    public AdministradorController(AdministradorService administradorService) {
        this.administradorService = administradorService;
    }

    private void executeActionWithErrorHandler(Runnable action) {
        try {
            action.run();
        } catch (IllegalArgumentException e) {
            MensagemView.mostrarErro("Erro ao fazer cadastro do administrador | Motivo: " + e.getMessage());
        }
    }

    public void criarAdministrador(String nome, String email, String cpf, int idade, String telefone, String senha, String hashSenha, String codigoAdmin, String nomeEmergencia, String telefoneEmergencia, String relacao, String condicaoMedica, String alergia, String medicamentoEmUso, String frequenciaMedicamentoEmUso, String lesaoRecente, String cirurgiaRecente, String restricaoMedica, String tipoSanguineo) {
        executeActionWithErrorHandler(() -> administradorService.criarAdministrador(nome, email, cpf, idade, telefone, senha, hashSenha, codigoAdmin, nomeEmergencia, telefoneEmergencia, relacao, condicaoMedica, alergia, medicamentoEmUso, frequenciaMedicamentoEmUso, lesaoRecente, cirurgiaRecente, restricaoMedica, tipoSanguineo));
    }

    public void criarAdministrador(String nome, String email, String cpf, int idade, String telefone, String senha, String hashSenha, String codigoAdmin, String condicaoMedica, String alergia, String medicamentoEmUso, String frequenciaMedicamentoEmUso, String lesaoRecente, String cirurgiaRecente, String restricaoMedica, String tipoSanguineo) {
        executeActionWithErrorHandler(() -> administradorService.criarAdministrador(nome, email, cpf, idade, telefone, senha, hashSenha, codigoAdmin, condicaoMedica, alergia, medicamentoEmUso, frequenciaMedicamentoEmUso, lesaoRecente, cirurgiaRecente, restricaoMedica, tipoSanguineo));
    }

    public void salvarAdministrador(Administrador administrador) {
        administradorService.salvarAdministrador(administrador);
    }

    public Map<String,Administrador> pegarMapAdministrador() {
        return administradorService.getMapAdministrador();
    }
}