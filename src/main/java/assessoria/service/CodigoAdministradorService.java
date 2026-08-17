package assessoria.service;

import assessoria.exceptions.InvalidStateException;
import assessoria.exceptions.NotFoundException;
import assessoria.exceptions.OperationNotAllowedException;
import assessoria.model.dao.CodigoAdministradorDAO;
import assessoria.model.entidades.Administrador;
import assessoria.model.entidades.CodigoAdministrador;
import assessoria.repository.CodigoAdministradorRepository;
import assessoria.util.log.Log;
import assessoria.view.MensagemView;

import java.util.List;
import java.util.UUID;

public class CodigoAdministradorService {

    private final CodigoAdministradorRepository codigoAdministradorRepository;

    public CodigoAdministradorService(CodigoAdministradorRepository codigoAdministradorRepository) {
        this.codigoAdministradorRepository = codigoAdministradorRepository;
    }

    public void validarCodigoAdministradorParaCadastro(String codigoAdminInformado) {
        // para validar, codigo de admin tem que existir, usado tem que estar false e ativo true
        CodigoAdministrador codigoAdministrador = encontrarCodigoAdministradorOrElseThrowNotFound(codigoAdminInformado);

        if(codigoAdministrador.isUsado()) throw new OperationNotAllowedException("Falha na validação do codigo de administrador | Motivo: o codigo informado já está sendo usado!");

        if(!codigoAdministrador.isAtivo()) throw new OperationNotAllowedException("Falha na validação do codigo de administrador | Motivo: o codigo informado não está ativo!");
    }

    public void validarCodigoAdministradorParaLogin(String codigoAdminInformado) {
        CodigoAdministrador codigoAdministrador = encontrarCodigoAdministradorOrElseThrowNotFound(codigoAdminInformado);

        if(!codigoAdministrador.isAtivo()) throw new OperationNotAllowedException("Falha na validação do codigo de administrador | Motivo: o codigo informado não está ativo!");
    }

    public void setarCodigoAdministradorUsadoTrue(String codigoAdmin) {
        CodigoAdministrador codigoAdministrador = encontrarCodigoAdministradorOrElseThrowNotFound(codigoAdmin);
        codigoAdministrador.setUsado(true);
        codigoAdministradorRepository.save();
        Log.registrarInfo("Codigo administrador atualizado. Id=" + codigoAdministrador.getId() + " | Campo=usado | Antes=false | Depois=" + codigoAdministrador.isUsado());
    }

    public void setarCodigoAdminUsadoFalse(String codigoAdmin) {
        CodigoAdministrador codigoAdministrador = encontrarCodigoAdministradorOrElseThrowNotFound(codigoAdmin);
        codigoAdministrador.setUsado(false);
        codigoAdministradorRepository.save();
        Log.registrarInfo("Codigo administrador atualizado. Id=" + codigoAdministrador.getId() + " | Campo=usado | Antes=true | Depois=" + codigoAdministrador.isUsado());
    }

    public void desativarCodigoAdministrador(String codigoAdmin) {
        CodigoAdministrador codigoAdministrador = encontrarCodigoAdministradorOrElseThrowNotFound(codigoAdmin);
        if(!codigoAdministrador.isAtivo()) throw new InvalidStateException("Falha ao desativar o codigo " + codigoAdmin + " | Motivo: o codigo já está desativado!");
        boolean campoAntigo = codigoAdministrador.isAtivo();
        codigoAdministrador.setAtivo(false);
        codigoAdministradorRepository.save();
        Log.registrarInfo("Codigo administrador desativado. Id=" + codigoAdmin + " | Campo=ativo | Antes="+ campoAntigo +" | Depois=" + codigoAdministrador.isAtivo());
    }

    public void reativarCodigoAdministrador(String codigoAdmin) {
        CodigoAdministrador codigoAdministrador = encontrarCodigoAdministradorOrElseThrowNotFound(codigoAdmin);
        if(codigoAdministrador.isAtivo()) throw new InvalidStateException("Falha ao reativar o codigo de administrador " + codigoAdmin + " | Motivo: codigo já está ativado!");
        boolean campoAntigo = codigoAdministrador.isAtivo();
        codigoAdministrador.setAtivo(true);
        codigoAdministradorRepository.save();
        Log.registrarInfo("Codigo administrador reativado. Id=" + codigoAdmin + " | Campo=ativo | Antes="+ campoAntigo +" | Depois=" + codigoAdministrador.isAtivo());
    }

    public String gerarCodigoAdministrador(Administrador administrador) {
        if(!administrador.isAdiminRaiz()) throw new OperationNotAllowedException("Falha ao tentar gerar codigo administrador | Motivo: administrador nome=" + administrador.getNome() + " não tem permissão para tal ação.");
        return codigoAdministradorRepository.gerarCodigoAdministradorAndSalvar();
    }

    public boolean isCodigoAdminRaiz(String codigoInformado) {
        return codigoAdministradorRepository.isCodigoAdminRaiz(codigoInformado);
    }

    public List<CodigoAdministrador> getCodigoAdministradorList() {
        return  codigoAdministradorRepository.getAll();
    }

    public CodigoAdministrador encontrarCodigoAdministrador(String codigoAdmin) {
        return encontrarCodigoAdministradorOrElseThrowNotFound(codigoAdmin);
    }

    private CodigoAdministrador encontrarCodigoAdministradorOrElseThrowNotFound(String codigoAdmin) {
        return codigoAdministradorRepository.findCodigoAdministradorById(codigoAdmin)
                .orElseThrow(() -> new NotFoundException("Falha ao encontrar codigo de administrador: " + codigoAdmin + " | Motivo: codigo não encontrado"));
    }
}
