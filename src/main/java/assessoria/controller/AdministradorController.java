package assessoria.controller;

import assessoria.exceptions.InvalidStateException;
import assessoria.exceptions.NotFoundException;
import assessoria.exceptions.OperationNotAllowedException;
import assessoria.exceptions.ValidationException;
import assessoria.model.dto.AdministradorDetalhado;
import assessoria.model.dto.DadosCadastroPessoa;
import assessoria.model.entidades.Administrador;
import assessoria.model.entidades.CodigoAdministrador;
import assessoria.service.AdministradorService;
import assessoria.view.MensagemView;

import java.io.IOException;
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
        } catch (NotFoundException e) {
            MensagemView.mostrarErro(e.getMessage());
        } catch (OperationNotAllowedException e) {
            e.getMessage();
        } catch (InvalidStateException e) {
            e.getMessage();
        } catch (IOException e) {
            e.getMessage();
        }
    }

    public void criarAdministrador(DadosCadastroPessoa dadosCadastroPessoa, String codigoAdmin) {
        executeActionWithErrorHandler(() -> administradorService.criarAdministrador(dadosCadastroPessoa, codigoAdmin));
    }

    public void excluirAdministrador(String idAdministradorInformado) {
        executeActionWithErrorHandler(() -> administradorService.excluirAdministrador(idAdministradorInformado));
    }

    public void desativarAdministrador(String idAdministradorInformado) {
        executeActionWithErrorHandler(() -> administradorService.desativarAdministrador(idAdministradorInformado));
    }

    public void reativarAdministrador(String idAdministradorInformado) {
        executeActionWithErrorHandler(() -> administradorService.reativarAdministrador(idAdministradorInformado));
    }

    public void salvarAdministrador(Administrador administrador) {
        executeActionWithErrorHandler(() -> administradorService.salvarAdministrador(administrador));
    }

    private <T> T executeActionWithErrorHandlerWithReturn(Supplier<T> action) {
        try {
            return action.get();
        } catch (ValidationException e) {
            MensagemView.mostrarErro(e.getMessage());
            return null;
        } catch (OperationNotAllowedException e) {
            MensagemView.mostrarErro(e.getMessage());
            return null;
        }
    }

    public Administrador validarLogin(String email, String senha) {
      return executeActionWithErrorHandlerWithReturn(() -> administradorService.validarLogin(email, senha));
    }

    public List<AdministradorDetalhado> gerarListaAdministradorParaExibicao() {
        return executeActionWithErrorHandlerWithReturn(() -> administradorService.gerarListaAdministradorParaExibicao());
    }

    public String gerarCodigoAdministrador() {
        return administradorService.gerarCodigoAdministrador();
    }

    public Map<String,Administrador> pegarMapAdministrador() {
        return administradorService.getMapAdministrador();
    }

    public List<CodigoAdministrador> pegarCodigoAdministradorList() {
        return administradorService.pegarCodigoAdministradorList();
    }

}