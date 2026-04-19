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
import assessoria.util.helpers.BCryptHash;
import assessoria.util.helpers.Formatador;
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

    //Gerar lista para mostrar administradores cadastrados no sistema
    public List<AdministradorDetalhado> gerarListaAdministradorParaExibicao() {
        if(getMapAdministrador().isEmpty()) throw new NotFoundException("Falha ao gerar lista para Administrador | Motivo: nenhum administrador cadastrado");

        return getMapAdministrador().values().stream()
                .map(this::gerarAdministradorDetalhado)
                .toList();
    }

    public Map<String, Administrador> getMapAdministrador() {
        return mapAdministrador;
    }

    //Transformar a entidade administrador em um dto de exibicao
    public AdministradorDetalhado gerarAdministradorDetalhado(Administrador administrador) {
        CodigoAdministrador codigoAdministrador = codigoAdministradorService.encontrarCodigoAdministrador(administrador.getIdCodigoAdministrador());

        return AdministradorMapper.toDetalhado(administrador, codigoAdministrador);
    }

    //Validar email, senha se o codigo de admin nao esta desativado
    public Administrador validarLogin(String email, String senha) {
        Administrador administrador = Validador.isDadosLoginValido(email, senha, getMapAdministrador());
        codigoAdministradorService.validarCodigoAdministradorParaLogin(administrador.getIdCodigoAdministrador());

        return administrador;
    }

    //Encontra administrador pelo id informado ou lanca exception
    public Administrador findAdministradorPorId(String idAdministradorInformado) {
        Administrador administrador = mapAdministrador.get(idAdministradorInformado);

        if(administrador == null) throw new NotFoundException("Falha ao encontrar o administrador com o id: " + idAdministradorInformado + " | Motivo: id não encontrado");

        return administrador;
    }

    //Gera codigo administrador e salva no sistema apenas se o admin for o raiz
    public String gerarCodigoAdministrador(Administrador administrador) {
        if(!administrador.isAdiminRaiz()) throw new OperationNotAllowedException("Falha ao tentar gerar codigo administrador | Motivo: administrador nome=" + administrador.getNome() + " não tem permissão para tal ação.");
        return codigoAdministradorService.gerarCodigoAdministrador();
    }

    //Pega a lista de codigo de administrador para ser exibida, somente se o administrador for o raiz
    public List<CodigoAdministrador> pegarCodigoAdministradorList(Administrador administrador) {
        if(!administrador.isAdiminRaiz()) throw new OperationNotAllowedException("Falha ao listar codigos de administrador | Motivo: administrador nome=" + administrador.getNome() + " não tem permissão para tal ação.");
        return codigoAdministradorService.getCodigoAdministradorList();
    }

    public Map<String,Administrador> pegarCopiaMapAdministrador() {
        return new LinkedHashMap<>(mapAdministrador);
    }

    //Gera um dto de administrador para atualizacao
    public DadosAtualizacaoPessoa gerarAdministradorParaUpdate(Administrador administrador) {
        return AdministradorMapper.toDadosAtualizacao(administrador);
    }

    //Cadastra o administrador se as validacoes como cpf unico, email unico e o codigo admin informado forem verdadeiras
    public Administrador cadastrarAdministrador(DadosCadastroPessoa dadosCadastroPessoa, String codigoAdmin) {
        if(cpfAdministradorJaExiste(dadosCadastroPessoa.getCpf(), null) || alunoService.cpfJaExisteEmAluno(dadosCadastroPessoa.getCpf()))
            throw new ValidationException("Falha no cadastro do administrador | Motivo: cpf informado já está registrado no sistema");

        if(emailAdministradorJaExiste(dadosCadastroPessoa.getEmail(), null) || alunoService.emailJaExisteEmAluno(dadosCadastroPessoa.getEmail()))
            throw new ValidationException("Falha no cadastro do administrador | Motivo: email informado já está registrado no sistema");

        boolean adminRaiz = codigoAdministradorService.isCodigoAdminRaiz(codigoAdmin);
        codigoAdministradorService.validarCodigoAdministradorParaCadastro(codigoAdmin);

        Administrador administrador = AdministradorMapper.toEntity(dadosCadastroPessoa, GeradorID.gerarIdClass(Administrador.class), adminRaiz, codigoAdmin);

        salvarAdministrador(administrador);
        codigoAdministradorService.setarCodigoAdministradorUsadoTrue(codigoAdmin);
        MensagemView.mostrarSucesso("Seu cadastrado foi realizado com sucesso!!");
        Log.registrarInfo("Administrador cadastrado com sucesso. Id=" + administrador.getId() + ", Nome=" + administrador.getNome() + ", CodigoAdmin=" + administrador.getIdCodigoAdministrador());

        return administrador;
    }

    //Exclui administrador se ele nao for o administrador raiz e se o administrador for encontrado
    public void excluirAdministrador(String idAdministradorInformado, Administrador administrador) {
        if(!administrador.isAdiminRaiz())
            throw new OperationNotAllowedException("Falha ao tentar excluir administrador id=" + idAdministradorInformado + " | Motivo: administrador nome=" + administrador.getNome() + " não tem permissão para tal ação.");

        Administrador administradorSerExcluido = findAdministradorPorId(idAdministradorInformado);
        mapAdministrador.remove(administradorSerExcluido.getId(), administradorSerExcluido);
        codigoAdministradorService.setarCodigoAdminUsadoFalse(administradorSerExcluido.getIdCodigoAdministrador());
        atualizarMapAdministradorNoArquivo();
        MensagemView.mostrarSucesso("Administrador excluido com sucesso. Id=" + administrador.getId());
    }

    //Desativa administrador a partir do codigo de administrador
    public void desativarAdministrador(String idAdministradorInformado, Administrador administrador) {
        if(!administrador.isAdiminRaiz())
            throw new OperationNotAllowedException("Falha ao tentar desativar administrador id=" + idAdministradorInformado + " | Motivo: administrador nome=" + administrador.getNome() + " não tem permissão para tal ação.");

        Administrador administradorSerDesativado = findAdministradorPorId(idAdministradorInformado);
        codigoAdministradorService.desativarCodigoAdministrador(administradorSerDesativado.getIdCodigoAdministrador());
        MensagemView.mostrarSucesso("Administrador com o id: " + administrador.getId() + " foi desativado com sucesso!!");
    }

    //Reativa o administrador a partir do codigo de administrador
    public void reativarAdministrador(String idAdministradorInformado, Administrador administrador) {
        if(!administrador.isAdiminRaiz())
            throw new OperationNotAllowedException("Falha ao tentar reativar administrador id=" + idAdministradorInformado + " | Motivo: administrador nome=" + administrador.getNome() + " não tem permissão para tal ação.");

        Administrador administradorSerReativado = findAdministradorPorId(idAdministradorInformado);
        codigoAdministradorService.reativarCodigoAdministrador(administradorSerReativado.getIdCodigoAdministrador());
        MensagemView.mostrarSucesso("Administrador com o id: " + administrador.getId() + " foi reativado com sucesso!!");
    }

    //Salva o administrador validado e criado no map e depois atualiza para o arquivo
    public void salvarAdministrador(Administrador administrador) {
        salvarAdministradorMap(administrador);
        atualizarMapAdministradorNoArquivo();
    }

    //Atualiza o administrador dentro do map em memoria
    private void salvarAdministradorMap(Administrador administrador) {
        mapAdministrador.put(administrador.getId(), administrador);
    }

    //Verifica se o cpf do administrador informado ja existe dentro do map administrador, podendo ser utilizado tanto para o cadastro quando para atualizacao
    private boolean cpfAdministradorJaExiste(String cpf, String idIgnorado) {
        return mapAdministrador.values().stream()
                .filter(administrador -> idIgnorado == null || !administrador.getId().equals(idIgnorado))
                .anyMatch(administrador -> administrador.getCpf().equals(cpf));
    }

    //Verifica se o cpf de outra entidade informado ja existe dentro do map administrador, esse metodo é utilizado por outros services
    public boolean cpfJaExisteEmAdministrador(String cpf) {
        return mapAdministrador.values().stream()
                .anyMatch(administrador -> administrador.getCpf().equals(cpf));
    }

    //Verifica se o email do administrador informado ja existe dentro do map administrador, podendo ser utilizado tanto para o cadastro quanto para atualizacao
    private boolean emailAdministradorJaExiste(String email, String idIgnorado) {
        return mapAdministrador.values().stream()
                .filter(administrador -> idIgnorado == null || !administrador.getId().equals(idIgnorado))
                .anyMatch(administrador -> administrador.getEmail().equals(email));
    }

    //Verifica se o email de outra entidade informado ja existe dentro do map administrador, esse metodo é utlizado por outros services
    public boolean emailJaExisteEmAdministrador(String email) {
        return mapAdministrador.values().stream()
                .anyMatch(administrador -> administrador.getEmail().equals(email));
    }

    //Atualiza o map em memoria no arquivo
    private void atualizarMapAdministradorNoArquivo() {
        dao.inserirDadosNoArquivo(getMapAdministrador());
    }

    //Salva alteracoes do administrador se as validacoes como cpf unico e email unico forem verdadeiras.
    public void salvarAlteracoesAdministrador(DadosAtualizacaoPessoa dadosAtualizacaoPessoa) {

        if(cpfAdministradorJaExiste(dadosAtualizacaoPessoa.getCpf(), dadosAtualizacaoPessoa.getId()) || alunoService.cpfJaExisteEmAluno(dadosAtualizacaoPessoa.getCpf()))
            throw new ValidationException("Falha ao atualizar alteracoes do administrador. | Motivo: cpf informado já está registrado no sistema.");

        if(emailAdministradorJaExiste(dadosAtualizacaoPessoa.getEmail(), dadosAtualizacaoPessoa.getId()) || alunoService.emailJaExisteEmAluno(dadosAtualizacaoPessoa.getEmail()))
            throw new ValidationException("Falha ao atualizar alteracoes do administrador. | Motivo: email informado já está registrado no sistema.");

        Administrador administrador = findAdministradorPorId(dadosAtualizacaoPessoa.getId());
        BCryptHash bCryptHash = new BCryptHash();
        String cpfSemMascara = Formatador.removerMascaraCpf(dadosAtualizacaoPessoa.getCpf());
        String telefoneSemMascara = Formatador.removerMascaraTelefone(dadosAtualizacaoPessoa.getTelefone());

        Log.registrarAlteracao("Administrador", administrador.getId(), "Nome", administrador.getNome(), dadosAtualizacaoPessoa.getNome());
        Log.registrarAlteracao("Administrador", administrador.getId(), "Email", administrador.getEmail(), dadosAtualizacaoPessoa.getEmail());
        Log.registrarAlteracao("Administrador", administrador.getId(), "Cpf", administrador.getCpf(), cpfSemMascara);
        Log.registrarAlteracao("Administrador", administrador.getId(), "Telefone", administrador.getTelefone(), telefoneSemMascara);

        administrador.setNome(dadosAtualizacaoPessoa.getNome());
        administrador.setEmail(dadosAtualizacaoPessoa.getEmail());
        administrador.setCpf(cpfSemMascara);
        administrador.setTelefone(telefoneSemMascara);

        if(dadosAtualizacaoPessoa.getNovaSenha() != null && !bCryptHash.verificarHash(dadosAtualizacaoPessoa.getNovaSenha(), administrador.getHashProvider())) {
            String novoHash = bCryptHash.gerarHash(dadosAtualizacaoPessoa.getNovaSenha());
            administrador.setHashProvider(novoHash);
            Log.registrarAlteracaoSensivel("Administrador", administrador.getId(), "Senha");
        }

        Log.registrarInfo("Atualização do administrador concluída. Id=" + administrador.getId());
        atualizarMapAdministradorNoArquivo();

        MensagemView.mostrarSucesso("Dados salvos com sucesso.");

    }

}
