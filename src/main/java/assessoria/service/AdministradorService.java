package assessoria.service;

import assessoria.model.dao.AdministradorDAO;
import assessoria.model.entidades.Administrador;
import assessoria.model.entidades.ContatoEmergencia;
import assessoria.model.entidades.InfoMedica;
import assessoria.util.helpers.GeradorID;
import assessoria.util.log.Log;
import assessoria.view.MensagemView;

import java.util.LinkedHashMap;
import java.util.Map;

public class AdministradorService {
    private final AdministradorDAO dao;
    private final CodigoAdministradorService codigoAdministradorService;
    private Map<String, Administrador> mapAdministrador;

    public AdministradorService(AdministradorDAO dao, CodigoAdministradorService codigoAdministradorService) {
        this.dao = dao;
        this.codigoAdministradorService = codigoAdministradorService;
        this.mapAdministrador = this.dao.lerDadosDoArquivo();
    }

    public void criarAdministrador(String nome, String email, String cpf, int idade, String telefone, String senha, String hashSenha, String codigoAdmin, String nomeEmergencia, String telefoneEmergencia, String relacao, String condicaoMedica, String alergia, String medicamentoEmUso, String frequenciaMedicamentoEmUso, String lesaoRecente, String cirurgiaRecente, String restricaoMedica, String tipoSanguineo) {
        validarCpfUnicoAdministrador(cpf);
        boolean adminRaiz = mapAdministrador.isEmpty();
        if(!adminRaiz) codigoAdministradorService.validarCodigoUnicoAndSendoUsado(codigoAdmin);
        salvarAdministrador(new Administrador(GeradorID.gerarIdClass(Administrador.class), nome, email, cpf, idade, telefone, senha, hashSenha, codigoAdmin, adminRaiz, new ContatoEmergencia(nomeEmergencia, telefoneEmergencia, relacao), new InfoMedica(condicaoMedica, alergia, medicamentoEmUso, frequenciaMedicamentoEmUso, lesaoRecente, cirurgiaRecente, restricaoMedica, tipoSanguineo)));
        codigoAdministradorService.desativarCodigoAdministrador(codigoAdmin);
        MensagemView.mostrarSucesso("Seu cadastrado foi realizado com sucesso!!");
    }

    public void criarAdministrador(String nome, String email, String cpf, int idade, String telefone, String senha, String hashSenha, String codigoAdmin, String condicaoMedica, String alergia, String medicamentoEmUso, String frequenciaMedicamentoEmUso, String lesaoRecente, String cirurgiaRecente, String restricaoMedica, String tipoSanguineo) {
        validarCpfUnicoAdministrador(cpf);
        boolean adminRaiz = mapAdministrador.isEmpty();
        if(!adminRaiz) codigoAdministradorService.validarCodigoUnicoAndSendoUsado(codigoAdmin);
        salvarAdministrador(new Administrador(GeradorID.gerarIdClass(Administrador.class), nome, email, cpf, idade, telefone, senha, hashSenha, codigoAdmin, adminRaiz, new InfoMedica(condicaoMedica, alergia, medicamentoEmUso, frequenciaMedicamentoEmUso, lesaoRecente, cirurgiaRecente, restricaoMedica, tipoSanguineo)));
        codigoAdministradorService.desativarCodigoAdministrador(codigoAdmin);
        MensagemView.mostrarSucesso("Seu cadastrado foi realizado com sucesso!!");
    }

    public void salvarAdministrador(Administrador administrador) {
        salvarAdministradorMap(administrador);
        inserirAdministradorArquivo();
        Log.registrar("Info", "Dados do administrador (ID " + administrador.getId() + ") foi registrado no arquivo.");
    }

    private void salvarAdministradorMap(Administrador administrador) {
        mapAdministrador.put("K" + administrador.getId(), administrador);
        Log.registrar("Info", "Administrador ID " + administrador.getId() + " foi adicionado ao Map");
    }

    private void validarCpfUnicoAdministrador(String cpf) {
        mapAdministrador.values().stream()
                .filter(administrador -> administrador.getCpf().equals(cpf))
                .findAny()
                .ifPresent(administrador -> {
                    throw new IllegalArgumentException("Cpf informado já está cadastrado");
                });
    }

    private void inserirAdministradorArquivo() {
        dao.inserirDadosNoArquivo(getMapAdministrador());
    }

    public int pegarTamanhoMapAdministrador() {
        return mapAdministrador.size();
    }

    public Map<String,Administrador> pegarCopiaMapAdministrador() {
        return new LinkedHashMap<>(mapAdministrador);
    }

    public Map<String, Administrador> getMapAdministrador() {
        return mapAdministrador;
    }
}
