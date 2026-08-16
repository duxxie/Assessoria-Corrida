package assessoria.controller;

import assessoria.exceptions.*;
import assessoria.model.dto.AdministradorDetalhado;
import assessoria.model.dto.DadosAtualizacaoPessoa;
import assessoria.model.dto.DadosCadastroPessoa;
import assessoria.model.entidades.Administrador;
import assessoria.model.entidades.CodigoAdministrador;
import assessoria.service.AdministradorService;
import assessoria.util.log.Log;
import assessoria.view.MensagemView;

import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public class AdministradorController{

    private final AdministradorService administradorService;

    public AdministradorController(AdministradorService administradorService) {
        this.administradorService = administradorService;
    }

    private void executeActionWithErrorHandler(Runnable action) {
        try {
            action.run();
        } catch (ValidationException e) {
            MensagemView.mostrarErro(e.getMessage());
            Log.registrarAviso(e.getMessage());
        } catch (NotFoundException e) {
            MensagemView.mostrarErro(e.getMessage());
        } catch (OperationNotAllowedException e) {
            Log.registrarAviso(e.getMessage());
            MensagemView.mostrarAviso(e.getMessage());
        } catch (InvalidStateException e) {
            e.getMessage();
        } catch (PersistenciaException e) {
            Log.registrarErro(e.getMessage(), e);
        }
    }


    public void excluirAdministrador(String idAdministradorInformado, Administrador administrador) {
        executeActionWithErrorHandler(() -> administradorService.excluirAdministrador(idAdministradorInformado, administrador));
    }

    public void desativarAdministrador(String idAdministradorInformado, Administrador administrador) {
        executeActionWithErrorHandler(() -> administradorService.desativarAdministrador(idAdministradorInformado, administrador));
    }

    public void reativarAdministrador(String idAdministradorInformado, Administrador administrador) {
        executeActionWithErrorHandler(() -> administradorService.reativarAdministrador(idAdministradorInformado, administrador));
    }

    public void salvarAdministrador(Administrador administrador) {
        executeActionWithErrorHandler(() -> administradorService.salvarAdministrador(administrador));
    }

    public void salvarAlteracoesAdministrador(DadosAtualizacaoPessoa dadosAtualizacaoPessoa) {
        executeActionWithErrorHandler(() -> administradorService.salvarAlteracoesAdministrador(dadosAtualizacaoPessoa));
    }

    private <T> T executeActionWithErrorHandlerWithReturn(Supplier<T> action) {
        try {
            return action.get();
        } catch (ValidationException e) {
            MensagemView.mostrarErro(e.getMessage());
            return null;
        } catch (OperationNotAllowedException e) {
            MensagemView.mostrarAviso(e.getMessage());
            Log.registrarAviso(e.getMessage());
            return null;
        }
    }

    public Administrador cadastrarAdministrador(DadosCadastroPessoa dadosCadastroPessoa, String codigoAdmin) {
        return executeActionWithErrorHandlerWithReturn(() -> administradorService.cadastrarAdministrador(dadosCadastroPessoa, codigoAdmin));
    }

    public Administrador validarLogin(String email, String senha) {
        return executeActionWithErrorHandlerWithReturn(() -> administradorService.validarLogin(email, senha));
    }

    public List<AdministradorDetalhado> gerarListaAdministradorParaExibicao() {
        return executeActionWithErrorHandlerWithReturn(() -> administradorService.gerarListaAdministradorParaExibicao());
    }

    public String gerarCodigoAdministrador(Administrador administrador) {
        return administradorService.gerarCodigoAdministrador(administrador);
    }

    public Map<String,Administrador> pegarMapAdministrador() {
        return administradorService.getMapAdministrador();
    }

    public List<CodigoAdministrador> pegarCodigoAdministradorList(Administrador administrador) {
        return executeActionWithErrorHandlerWithReturn(() -> administradorService.pegarCodigoAdministradorList(administrador));
    }

    public DadosAtualizacaoPessoa gerarAdministradorParaUpdate(Administrador administrador) {
        return administradorService.gerarAdministradorParaUpdate(administrador);
    }

    public AdministradorDetalhado gerarAdministradorParaExibicao(Administrador administrador) {
        return administradorService.gerarAdministradorDetalhado(administrador);
    }

}