package assessoria.service;

import assessoria.exceptions.NotFoundException;
import assessoria.exceptions.OperationNotAllowedException;
import assessoria.exceptions.ValidationException;
import assessoria.mapper.AdministradorMapper;
import assessoria.model.dao.AdministradorDAO;
import assessoria.model.dto.AdministradorDetalhado;
import assessoria.model.dto.DadosAtualizacaoPessoa;
import assessoria.model.dto.DadosCadastroPessoa;
import assessoria.model.entidades.Administrador;
import assessoria.model.entidades.CodigoAdministrador;
import assessoria.model.entidades.ContatoEmergencia;
import assessoria.model.entidades.InfoMedica;
import assessoria.util.helpers.GeradorID;
import assessoria.util.helpers.Validador;
import assessoria.util.log.Log;
import assessoria.view.MensagemView;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class AdministradorService {
    private final AdministradorDAO dao;
    private final CodigoAdministradorService codigoAdministradorService;
    private final AlunoService alunoService;
    private final Map<String, Administrador> mapAdministrador;

    public AdministradorService(AdministradorDAO dao, CodigoAdministradorService codigoAdministradorService, AlunoService alunoService) {
        this.dao = dao;
        this.mapAdministrador = this.dao.lerDadosDoArquivo();
        this.codigoAdministradorService = codigoAdministradorService;
        this.alunoService = alunoService;
    }

    public List<AdministradorDetalhado> gerarListaAdministradorParaExibicao() {
        if(getMapAdministrador().isEmpty()) throw new NotFoundException("Falha ao gerar lista para Administrador | Motivo: nenhum administrador cadastrado");

        return getMapAdministrador().values().stream()
                .map(this::gerarAdministradorDetalhado)
                .toList();
    }

    public Map<String, Administrador> getMapAdministrador() {
        return mapAdministrador;
    }

    private AdministradorDetalhado gerarAdministradorDetalhado(Administrador administrador) {
        CodigoAdministrador codigoAdministrador = codigoAdministradorService.encontrarCodigoAdministrador(administrador.getIdCodigoAdministrador());

        return AdministradorMapper.toDetalhado(administrador, codigoAdministrador);
    }

    public Administrador validarLogin(String email, String senha) {
        Administrador administrador = Validador.isDadosLoginValido(email, senha, getMapAdministrador());
        codigoAdministradorService.validarCodigoAdministradorParaLogin(administrador.getIdCodigoAdministrador());

        return administrador;
    }

    public Administrador findAdministradorPorId(String idAdministradorInformado) {
        Administrador administrador = mapAdministrador.get(idAdministradorInformado);

        if(administrador == null) throw new NotFoundException("Falha ao encontrar o administrador com o id: " + idAdministradorInformado + " | Motivo: id não encontrado");

        return administrador;
    }

    public String gerarCodigoAdministrador(Administrador administrador) {
        if(!administrador.isAdiminRaiz()) throw new OperationNotAllowedException("Falha ao tentar gerar codigo administrador | Motivo: administrador nome=" + administrador.getNome() + " não tem permissão para tal ação.");
        return codigoAdministradorService.gerarCodigoAdministrador();
    }

    public List<CodigoAdministrador> pegarCodigoAdministradorList() {
        return codigoAdministradorService.getCodigoAdministradorList();
    }

    public Map<String,Administrador> pegarCopiaMapAdministrador() {
        return new LinkedHashMap<>(mapAdministrador);
    }

    public DadosAtualizacaoPessoa gerarAdministradorParaUpdate(Administrador administrador) {
        return AdministradorMapper.toDadosAtualizacao(administrador);
    }

    public void criarAdministrador(DadosCadastroPessoa dadosCadastroPessoa, String codigoAdmin) {
        if(cpfAdministradorJaExiste(dadosCadastroPessoa.getCpf(), null))
            throw new ValidationException("Falha no cadastro do administrador | Motivo: cpf informado já está cadastrado");
        boolean adminRaiz = mapAdministrador.isEmpty();
        if(!adminRaiz) codigoAdministradorService.validarCodigoAdministradorParaCadastro(codigoAdmin);

        Administrador administrador = AdministradorMapper.toEntity(dadosCadastroPessoa, GeradorID.gerarIdClass(Administrador.class), adminRaiz, codigoAdmin);

        salvarAdministrador(administrador);
        codigoAdministradorService.setarCodigoAdministradorUsadoTrue(codigoAdmin);
        MensagemView.mostrarSucesso("Seu cadastrado foi realizado com sucesso!!");
    }

    public void excluirAdministrador(String idAdministradorInformado, Administrador administrador) {
        if(!administrador.isAdiminRaiz()) throw new OperationNotAllowedException("Falha ao tentar excluir administrador id=" + idAdministradorInformado + " | Motivo: administrador nome=" + administrador.getNome() + " não tem permissão para tal ação.");
        Administrador administradorSerExcluido = findAdministradorPorId(idAdministradorInformado);
        mapAdministrador.remove(administradorSerExcluido.getId(), administradorSerExcluido);
        codigoAdministradorService.setarCodigoAdminUsadoFalse(administradorSerExcluido.getIdCodigoAdministrador());
        atualizarMapAdministradorNoArquivo();
        MensagemView.mostrarSucesso("Administrador excluido com sucesso. Id=" + administrador.getId());
    }

    public void desativarAdministrador(String idAdministradorInformado, Administrador administrador) {
        if(!administrador.isAdiminRaiz()) throw new OperationNotAllowedException("Falha ao tentar desativar administrador id=" + idAdministradorInformado + " | Motivo: administrador nome=" + administrador.getNome() + " não tem permissão para tal ação.");
        Administrador administradorSerDesativado = findAdministradorPorId(idAdministradorInformado);
        codigoAdministradorService.desativarCodigoAdministrador(administradorSerDesativado.getIdCodigoAdministrador());
        MensagemView.mostrarSucesso("Administrador com o id: " + administrador.getId() + " foi desativado com sucesso!!");
    }

    public void reativarAdministrador(String idAdministradorInformado, Administrador administrador) {
        if(!administrador.isAdiminRaiz()) throw new OperationNotAllowedException("Falha ao tentar reativar administrador id=" + idAdministradorInformado + " | Motivo: administrador nome=" + administrador.getNome() + " não tem permissão para tal ação.");
        Administrador administradorSerReativado = findAdministradorPorId(idAdministradorInformado);
        codigoAdministradorService.reativarCodigoAdministrador(administradorSerReativado.getIdCodigoAdministrador());
        MensagemView.mostrarSucesso("Administrador com o id: " + administrador.getId() + " foi reativado com sucesso!!");
    }


    public void salvarAdministrador(Administrador administrador) {
        salvarAdministradorMap(administrador);
        atualizarMapAdministradorNoArquivo();
        Log.registrarInfo("Administrador cadastrado com sucesso. Id=" + administrador.getId() + ", Nome=" + administrador.getNome() + ", CodigoAdmin=" + administrador.getIdCodigoAdministrador());
    }

    private void salvarAdministradorMap(Administrador administrador) {
        mapAdministrador.put(administrador.getId(), administrador);
    }

    private boolean cpfAdministradorJaExiste(String cpf, String idIgnorado) {
        return mapAdministrador.values().stream()
                .filter(administrador -> idIgnorado == null || !administrador.getId().equals(idIgnorado))
                .anyMatch(administrador -> administrador.getCpf().equals(cpf));
    }

    private boolean emailAdministradorJaExiste(String email, String idIgnorado) {
        return mapAdministrador.values().stream()
                .filter(administrador -> idIgnorado == null || !administrador.getId().equals(idIgnorado))
                .anyMatch(administrador -> administrador.getEmail().equals(email));
    }

    private void atualizarMapAdministradorNoArquivo() {
        dao.inserirDadosNoArquivo(getMapAdministrador());
    }

    public void salvarAlteracoesAdministrador(DadosAtualizacaoPessoa dadosAtualizacaoPessoa) {
        if(cpfAdministradorJaExiste(dadosAtualizacaoPessoa.getCpf(), dadosAtualizacaoPessoa.getId()) || alunoService.cpfJaExisteEmAluno(dadosAtualizacaoPessoa.getCpf()))
            throw new ValidationException("Falha ao atualizar alteracoes do administrador. | Motivo: cpf informado já está registrado no sistema.");

        if(emailAdministradorJaExiste(dadosAtualizacaoPessoa.getEmail(), dadosAtualizacaoPessoa.getId()) || alunoService.emailJaExisteEmAluno(dadosAtualizacaoPessoa.getEmail()))
            throw new ValidationException("Falha ao atualizar alteracoes do administrador. | Motivo: email informado já está registrado no sistema.");

        Administrador administrador = findAdministradorPorId(dadosAtualizacaoPessoa.getId());

        administrador.setNome(dadosAtualizacaoPessoa.getNome());
        administrador.setEmail(dadosAtualizacaoPessoa.getEmail());
        administrador.setCpf(dadosAtualizacaoPessoa.getCpf());
        administrador.setTelefone(dadosAtualizacaoPessoa.getTelefone());
        administrador.setHashProvider(dadosAtualizacaoPessoa.getNovaSenha());

        atualizarMapAdministradorNoArquivo();
    }


}
