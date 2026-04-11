package assessoria.service;

import assessoria.exceptions.InvalidStateException;
import assessoria.exceptions.NotFoundException;
import assessoria.exceptions.OperationNotAllowedException;
import assessoria.model.dao.CodigoAdministradorDAO;
import assessoria.model.entidades.CodigoAdministrador;
import assessoria.util.log.Log;
import assessoria.view.MensagemView;

import java.util.List;
import java.util.UUID;

public class CodigoAdministradorService {

    private final CodigoAdministradorDAO dao;
    private final List<CodigoAdministrador> codigoAdministradorList;

    public CodigoAdministradorService(CodigoAdministradorDAO dao) {
        this.dao = dao;
        this.codigoAdministradorList = dao.lerDadosDoAquivo();
    }

    public void validarCodigoAdministradorParaCadastro(String codigoAdminInformado) {
        // para validar, codigo de admin tem que existir, usado tem que estar false e ativo true
        CodigoAdministrador codigoAdministrador = encontrarCodigoAdministrador(codigoAdminInformado);

        if(codigoAdministrador.isUsado()) throw new OperationNotAllowedException("Falha na validação do codigo de administrador | Motivo: o codigo informado já está sendo usado!");

        if(!codigoAdministrador.isAtivo()) throw new OperationNotAllowedException("Falha na validação do codigo de administrador | Motivo: o codigo informado não está ativo!");
    }

    public void validarCodigoAdministradorParaLogin(String codigoAdminInformado) {
        CodigoAdministrador codigoAdministrador = encontrarCodigoAdministrador(codigoAdminInformado);

        if(!codigoAdministrador.isAtivo()) throw new OperationNotAllowedException("Falha na validação do codigo de administrador | Motivo: o codigo informado não está ativo!");
    }

    public void setarCodigoAdministradorUsadoTrue(String codigoAdmin) {
        CodigoAdministrador codigoAdministrador = encontrarCodigoAdministrador(codigoAdmin);
        codigoAdministrador.setUsado(true);
        Log.registrar("Info", "Codigo administrador foi atualizado em memória | usado = true");
        atualizarListDadosArquivo();
    }

    public void setarCodigoAdminUsadoFalse(String codigoAdmin) {
        CodigoAdministrador codigoAdministrador = encontrarCodigoAdministrador(codigoAdmin);
        codigoAdministrador.setUsado(false);
        atualizarListDadosArquivo();
    }

    public void desativarCodigoAdministrador(String codigoAdmin) {
        CodigoAdministrador codigoAdministrador = encontrarCodigoAdministrador(codigoAdmin);
        if(!codigoAdministrador.isAtivo()) throw new InvalidStateException("Falha ao desativar o codigo " + codigoAdmin + " | Motivo: o codigo já está desativado!");
        codigoAdministrador.setAtivo(false);
        atualizarListDadosArquivo();
    }

    public void reativarCodigoAdministrador(String codigoAdmin) {
        CodigoAdministrador codigoAdministrador = encontrarCodigoAdministrador(codigoAdmin);
        if(codigoAdministrador.isAtivo()) throw new InvalidStateException("Falha ao reativar o codigo de administrador " + codigoAdmin + " | Motivo: codigo já está ativado!");
        codigoAdministrador.setAtivo(true);
        atualizarListDadosArquivo();
    }

    public String gerarCodigoAdministrador() {
        String codigoGerado = "ADM-" + UUID.randomUUID().toString().substring(0,8).toUpperCase();
        codigoAdministradorList.add(new CodigoAdministrador(codigoGerado, false, true));
        atualizarListDadosArquivo();
        MensagemView.mostrarSucesso("Codigo gerado com sucesso e salvo no sistema.");
        return codigoGerado;
    }

    private void atualizarListDadosArquivo() {
        dao.inserirDadosNoArquivo(getCodigoAdministradorList());
    }

    public List<CodigoAdministrador> getCodigoAdministradorList() {
        return  codigoAdministradorList;
    }

    public CodigoAdministrador encontrarCodigoAdministrador(String codigoAdmin) {
        return codigoAdministradorList.stream()
                .filter(codigoAdministrador -> codigoAdministrador.getId().equals(codigoAdmin))
                .findAny()
                .orElseThrow(() -> new NotFoundException("Falha ao encontrar codigo de administrador: " + codigoAdmin + " | Motivo: codigo não encontrado"));
    }
}
