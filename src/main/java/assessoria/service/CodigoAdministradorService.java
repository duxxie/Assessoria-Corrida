package assessoria.service;

import assessoria.model.dao.CodigoAdministradorDAO;
import assessoria.model.entidades.CodigoAdministrador;
import assessoria.view.MensagemView;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class CodigoAdministradorService {

    private final CodigoAdministradorDAO dao;
    private final List<CodigoAdministrador> codigoAdministradorList;

    public CodigoAdministradorService(CodigoAdministradorDAO dao) {
        this.dao = dao;
        this.codigoAdministradorList = dao.lerDadosDoAquivo();
    }

    public void validarCodigoUnicoAndSendoUsado(String codigoAdminInformado) {
        // para validar, codigo de admin tem que existir e usado tem que estar false
        codigoAdministradorList.stream()
                .filter(codigoAdministrador -> codigoAdministrador.getId().equals(codigoAdminInformado))
                .findAny()
                .ifPresentOrElse(
                        codigoAdministrador -> {
                            if(codigoAdministrador.isUsado()) throw new IllegalArgumentException("Codigo de administrador informado já está sendo usado.");
                        },
                        () -> {
                            throw new IllegalArgumentException("Codigo de administrador informado não existe");
                        }
                        );
    }

    public void desativarCodigoAdministrador(String codigoAdmin) {
        CodigoAdministrador codigoAdministrador = encontrarCodigoAdministrador(codigoAdmin);
        codigoAdministrador.setUsado(true);
        inserirDadosArquivo();
    }

    public String gerarCodigoAdministrador() {
        String codigoGerado = "ADM-" + UUID.randomUUID().toString().substring(0,8).toUpperCase();
        codigoAdministradorList.add(new CodigoAdministrador(codigoGerado, false));
        inserirDadosArquivo();
        MensagemView.mostrarSucesso("Codigo gerado com sucesso e salvo no sistema.");
        return codigoGerado;
    }

    private void salvarCodigoAdministrador() {

    }

    private void inserirDadosArquivo() {
        dao.inserirDadosNoArquivo(getCodigoAdministradorList());
    }

    public List<CodigoAdministrador> getCodigoAdministradorList() {
        return  codigoAdministradorList;
    }

    private CodigoAdministrador encontrarCodigoAdministrador(String codigoAdmin) {
        return codigoAdministradorList.stream()
                .filter(codigoAdministrador -> codigoAdministrador.getId().equals(codigoAdmin))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("Código de administrador não encontrado"));
    }
}
