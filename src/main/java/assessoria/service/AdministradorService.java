package assessoria.service;

import assessoria.exceptions.NotFoundException;
import assessoria.exceptions.OperationNotAllowedException;
import assessoria.exceptions.ValidationException;
import assessoria.model.dao.AdministradorDAO;
import assessoria.model.dto.AdministradorDetalhado;
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
    private Map<String, Administrador> mapAdministrador;

    public AdministradorService(AdministradorDAO dao, CodigoAdministradorService codigoAdministradorService) {
        this.dao = dao;
        this.codigoAdministradorService = codigoAdministradorService;
        this.mapAdministrador = this.dao.lerDadosDoArquivo();
    }

    public List<AdministradorDetalhado> gerarListaAdministradorParaExibicao() {
        if(getMapAdministrador().isEmpty()) throw new NotFoundException("Falha ao gerar lista para Administrador | Motivo: nenhum administrador cadastrado");
        List<AdministradorDetalhado> administradorDetalhados = getMapAdministrador().values().stream()
                .map(this::gerarAdministradorDetalhado)
                .toList();

        return administradorDetalhados;
    }

    private AdministradorDetalhado gerarAdministradorDetalhado(Administrador administrador) {
        CodigoAdministrador codigoAdministrador = codigoAdministradorService.encontrarCodigoAdministrador(administrador.getIdCodigoAdministrador());

        return new AdministradorDetalhado(
                administrador.getId(),
                administrador.getNome(),
                administrador.getEmail(),
                administrador.getCpf(),
                administrador.getIdade(),
                administrador.getTelefone(),
                administrador.getIdCodigoAdministrador(),
                administrador.isAdiminRaiz(),
                codigoAdministrador.isAtivo(),
                codigoAdministrador.isUsado()
        );
    }

    public Administrador validarLogin(String email, String senha) {
        Administrador administrador = Validador.isDadosLoginValido(email, senha, getMapAdministrador());
        codigoAdministradorService.validarCodigoAdministradorParaLogin(administrador.getIdCodigoAdministrador());

        return administrador;
    }

    public void criarAdministrador(DadosCadastroPessoa dadosCadastroPessoa, String codigoAdmin) {
        validarCpfUnicoAdministrador(dadosCadastroPessoa.getCpf());
        boolean adminRaiz = mapAdministrador.isEmpty();
        if(!adminRaiz) codigoAdministradorService.validarCodigoAdministradorParaCadastro(codigoAdmin);

        Administrador administrador = new Administrador.Builder()
                .id(GeradorID.gerarIdClass(Administrador.class))
                .nome(dadosCadastroPessoa.getNome())
                .email(dadosCadastroPessoa.getEmail())
                .cpf(dadosCadastroPessoa.getCpf())
                .idade(dadosCadastroPessoa.getIdade())
                .telefone(dadosCadastroPessoa.getTelefone())
                .senhaHash(dadosCadastroPessoa.getSenhaHash())
                .hashProvider(dadosCadastroPessoa.getHashProvider())
                .contatoEmergencia(dadosCadastroPessoa.getContatoEmergencia())
                .infoMedica(dadosCadastroPessoa.getInfoMedica())
                .build(codigoAdmin, adminRaiz);

        salvarAdministrador(administrador);
        codigoAdministradorService.setarCodigoAdministradorUsadoTrue(codigoAdmin);
        MensagemView.mostrarSucesso("Seu cadastrado foi realizado com sucesso!!");
    }

    public void excluirAdministrador(String idAdministradorInformado) {
        Administrador administrador = findAdministradorPorId(idAdministradorInformado);
        mapAdministrador.remove(administrador.getId(), administrador);
        codigoAdministradorService.setarCodigoAdminUsadoFalse(administrador.getIdCodigoAdministrador());
        atualizarMapAdministradorNoArquivo();
        MensagemView.mostrarSucesso("Administrador com o id: " + administrador.getId() + " foi excluido com sucesso!!");
    }


    public void desativarAdministrador(String idAdministradorInformado) {
        Administrador administrador = findAdministradorPorId(idAdministradorInformado);
        codigoAdministradorService.desativarCodigoAdministrador(administrador.getIdCodigoAdministrador());
        MensagemView.mostrarSucesso("Administrador com o id: " + administrador.getId() + " foi desativado com sucesso!!");
    }

    public void reativarAdministrador(String idAdministradorInformado) {
        Administrador administrador = findAdministradorPorId(idAdministradorInformado);
        codigoAdministradorService.reativarCodigoAdministrador(administrador.getIdCodigoAdministrador());
        MensagemView.mostrarSucesso("Administrador com o id: " + administrador.getId() + " foi reativado com sucesso!!");
    }

    public String gerarCodigoAdministrador() {
        return codigoAdministradorService.gerarCodigoAdministrador();
    }

    public void salvarAdministrador(Administrador administrador) {
        salvarAdministradorMap(administrador);
        atualizarMapAdministradorNoArquivo();
        Log.registrar("Info", "Dados do administrador (ID " + administrador.getId() + ") foi registrado no arquivo.");
    }

    private void salvarAdministradorMap(Administrador administrador) {
        mapAdministrador.put(administrador.getId(), administrador);
        Log.registrar("Info", "Administrador ID " + administrador.getId() + " foi adicionado ao Map");
    }

    private void validarCpfUnicoAdministrador(String cpf) {
        mapAdministrador.values().stream()
                .filter(administrador -> administrador.getCpf().equals(cpf))
                .findAny()
                .ifPresent(administrador -> {
                    throw new ValidationException("Falha no cadastro do administrador | Motivo: cpf informado já está cadastrado");
                });
    }

    public Administrador findAdministradorPorId(String idAdministradorInformado) {
        Administrador administrador = mapAdministrador.get(idAdministradorInformado);

        if(administrador == null) throw new NotFoundException("Falha ao encontrar o administrador com o id: " + idAdministradorInformado + " | Motivo: id não encontrado");

        return administrador;
    }

    private void atualizarMapAdministradorNoArquivo() {
        dao.inserirDadosNoArquivo(getMapAdministrador());
    }

    public int pegarTamanhoMapAdministrador() {
        return mapAdministrador.size();
    }

    public List<CodigoAdministrador> pegarCodigoAdministradorList() {
        return codigoAdministradorService.getCodigoAdministradorList();
    }

    public Map<String,Administrador> pegarCopiaMapAdministrador() {
        return new LinkedHashMap<>(mapAdministrador);
    }

    public Map<String, Administrador> getMapAdministrador() {
        return mapAdministrador;
    }
}
