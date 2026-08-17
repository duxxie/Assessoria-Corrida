package assessoria.service;

import assessoria.exceptions.NotFoundException;
import assessoria.exceptions.OperationNotAllowedException;
import assessoria.exceptions.ValidationException;
import assessoria.mapper.AdministradorMapper;
import assessoria.model.dto.AdministradorDetalhado;
import assessoria.model.dto.DadosAtualizacaoPessoa;
import assessoria.model.dto.DadosCadastroPessoa;
import assessoria.model.entidades.Administrador;
import assessoria.model.entidades.CodigoAdministrador;
import assessoria.repository.CodigoAdministradorRepository;
import assessoria.repository.pessoaRepository.AdministradorRepository;
import assessoria.repository.pessoaRepository.AlunoRepository;
import assessoria.repository.pessoaRepository.ProfessorRepository;
import assessoria.util.helpers.BCryptHash;
import assessoria.util.helpers.Formatador;
import assessoria.util.log.Log;
import assessoria.view.MensagemView;

import java.util.*;

public class AdministradorService {

    private final AdministradorRepository administradorRepository;
    private final AlunoRepository alunoRepository;
    private final ProfessorRepository professorRepository;
    private final CodigoAdministradorRepository codigoAdministradorRepository;


    public AdministradorService(AdministradorRepository administradorRepository,
                                AlunoRepository alunoRepository,
                                ProfessorRepository professorRepository,
                                CodigoAdministradorRepository codigoAdministradorRepository) {
        this.administradorRepository = administradorRepository;
        this.alunoRepository = alunoRepository;
        this.professorRepository = professorRepository;
        this.codigoAdministradorRepository = codigoAdministradorRepository;
    }

    //Gerar lista para mostrar administradores cadastrados no sistema
    public List<AdministradorDetalhado> gerarListaAdministradorParaExibicao() {
        if(administradorRepository.getAll().isEmpty()) throw new NotFoundException("Falha ao gerar lista para Administrador | Motivo: nenhum administrador cadastrado");

        return administradorRepository.getAll().values().stream()
                .map(this::gerarAdministradorDetalhado)
                .toList();
    }

    public Map<String, Administrador> getMapAdministrador() {
        return administradorRepository.getAll();
    }

    //Transformar a entidade administrador em um dto de exibicao
    public AdministradorDetalhado gerarAdministradorDetalhado(Administrador administrador) {
        CodigoAdministrador codigoAdministrador = codigoAdministradorRepository.findCodigoAdministradorById(administrador.getIdCodigoAdministrador())
                .orElseThrow(() -> new NotFoundException("Falha ao encontrar codigo de administrador: " + administrador.getIdCodigoAdministrador() + " | Motivo: codigo não encontrado"));

        return AdministradorMapper.toDetalhado(administrador, codigoAdministrador);
    }

    //Validar email, senha se o codigo de admin nao esta desativado
    public Administrador validarLogin(String email, String senha) {
        Administrador administrador = administradorRepository.findByEmail(email)
                .orElseThrow(() -> new ValidationException("Falha ao validar cadastro | Motivo: email ou senha inválidos!!"));

        BCryptHash bCryptHash = new BCryptHash();

        if(!bCryptHash.verificarHash(senha, administrador.getSenhaHash()))
            throw new ValidationException("Falha ao validar cadastro | Motivo: email ou senha inválidos!!");

        CodigoAdministrador codigoAdministrador = codigoAdministradorRepository.findCodigoAdministradorById(administrador.getIdCodigoAdministrador())
                .orElseThrow(() -> new NotFoundException("Falha ao fazer login | Motivo: Seu código de administrador não existe mais no sistema!"));

        if(!codigoAdministrador.isAtivo()) throw new OperationNotAllowedException("Falha ao fazer login | Motivo: Seu código de administrador está desativado!");

        return administrador;
    }

    //Encontra administrador pelo id informado ou lanca exception
    public Administrador findAdministradorPorId(String idAdministradorInformado) {
        return administradorRepository.findById(idAdministradorInformado)
                .orElseThrow(() -> new NotFoundException("Falha ao encontrar o administrador com o id: " + idAdministradorInformado + " | Motivo: id não encontrado"));
    }

    //Pega a lista de codigo de administrador para ser exibida, somente se o administrador for o raiz
    public List<CodigoAdministrador> pegarCodigoAdministradorList(Administrador administrador) {
        if(!administrador.isAdiminRaiz()) throw new OperationNotAllowedException("Falha ao listar codigos de administrador | Motivo: administrador nome=" + administrador.getNome() + " não tem permissão para tal ação.");
        return codigoAdministradorRepository.getAll();
    }

    //Gera um dto de administrador para atualizacao
    public DadosAtualizacaoPessoa gerarAdministradorParaUpdate(Administrador administrador) {
        return AdministradorMapper.toDadosAtualizacao(administrador);
    }

    //Cadastra o administrador se as validacoes como cpf unico, email unico e o codigo admin informado forem verdadeiras
    public Administrador cadastrarAdministrador(DadosCadastroPessoa dadosCadastroPessoa, String codigoAdmin) {
        if(administradorRepository.existsByCpf(dadosCadastroPessoa.getCpf())
            || professorRepository.existsByCpf(dadosCadastroPessoa.getCpf())
            || alunoRepository.existsByCpf(dadosCadastroPessoa.getCpf()))
            throw new ValidationException("Falha no cadastro do administrador | Motivo: cpf informado já está registrado no sistema");

        if(administradorRepository.existsByEmail(dadosCadastroPessoa.getEmail())
            || professorRepository.existsByEmail(dadosCadastroPessoa.getEmail())
            || alunoRepository.existsByEmail(dadosCadastroPessoa.getEmail()))
            throw new ValidationException("Falha no cadastro do administrador | Motivo: email informado já está registrado no sistema");

        boolean adminRaiz = codigoAdministradorRepository.isCodigoAdminRaiz(codigoAdmin);

        CodigoAdministrador codigoAdministrador = codigoAdministradorRepository.findCodigoAdministradorById(codigoAdmin)
                .orElseThrow(() -> new NotFoundException("Falha ao encontrar codigo de administrador: " + codigoAdmin + " | Motivo: codigo não encontrado"));

        if(codigoAdministrador.isUsado()) throw new ValidationException("Falha na validação do codigo de administrador | Motivo: o codigo informado já está sendo usado!");

        if(!codigoAdministrador.isAtivo()) throw new ValidationException("Falha na validação do codigo de administrador | Motivo: o codigo informado não está ativo!");

        Administrador administrador = administradorRepository.add(AdministradorMapper.toEntity(dadosCadastroPessoa, adminRaiz, codigoAdmin));
        administradorRepository.save();

        codigoAdministrador.setUsado(true);
        codigoAdministradorRepository.save();

        MensagemView.mostrarSucesso("Seu cadastrado foi realizado com sucesso!!");
        Log.registrarInfo("Administrador cadastrado com sucesso. Id=" + administrador.getId() + ", Nome=" + administrador.getNome() + ", CodigoAdmin=" + administrador.getIdCodigoAdministrador());

        return administrador;
    }

    //Exclui administrador se ele nao for o administrador raiz e se o administrador for encontrado
    public void excluirAdministrador(String idAdministradorInformado, Administrador administrador) {
        if(!administrador.isAdiminRaiz())
            throw new OperationNotAllowedException("Falha ao tentar excluir administrador id=" + idAdministradorInformado + " | Motivo: administrador nome=" + administrador.getNome() + " não tem permissão para tal ação.");

        Administrador administradorSerExcluido = findAdministradorPorId(idAdministradorInformado);
        administradorRepository.remove(administradorSerExcluido);

        CodigoAdministrador codigoAdministrador = codigoAdministradorRepository.findCodigoAdministradorById(administradorSerExcluido.getIdCodigoAdministrador())
                .orElseThrow(() -> new NotFoundException("Falha ao encontrar codigo de administrador: " + administradorSerExcluido.getIdCodigoAdministrador() + " | Motivo: codigo não encontrado"));

        codigoAdministrador.setUsado(false);

        administradorRepository.save();
        codigoAdministradorRepository.save();
    }

    //Desativa administrador a partir do codigo de administrador
    public void desativarAdministrador(String idAdministradorInformado, Administrador administrador) {
        if(!administrador.isAdiminRaiz())
            throw new OperationNotAllowedException("Falha ao tentar desativar administrador id=" + idAdministradorInformado + " | Motivo: administrador nome=" + administrador.getNome() + " não tem permissão para tal ação.");

        Administrador administradorSerDesativado = findAdministradorPorId(idAdministradorInformado);

        CodigoAdministrador codigoAdministrador = codigoAdministradorRepository.findCodigoAdministradorById(administradorSerDesativado.getIdCodigoAdministrador())
                        .orElseThrow(() -> new NotFoundException("Falha ao desativar administrador: " + administradorSerDesativado.getId() + " | Motivo: código de administrador não encontrado"));

        codigoAdministrador.setAtivo(false);
        codigoAdministradorRepository.save();

        Log.registrarAlteracao("Codigo administrador", codigoAdministrador.getId(), "ativo", "true", "false");
    }

    //Reativa o administrador a partir do codigo de administrador
    public void reativarAdministrador(String idAdministradorInformado, Administrador administrador) {
        if(!administrador.isAdiminRaiz())
            throw new OperationNotAllowedException("Falha ao tentar reativar administrador id=" + idAdministradorInformado + " | Motivo: administrador nome=" + administrador.getNome() + " não tem permissão para tal ação.");

        Administrador administradorSerReativado = findAdministradorPorId(idAdministradorInformado);

        CodigoAdministrador codigoAdministrador = codigoAdministradorRepository.findCodigoAdministradorById(administradorSerReativado.getIdCodigoAdministrador())
                        .orElseThrow(() -> new NotFoundException("Falha ao reativar administrador: " + administradorSerReativado.getId() + " | Motivo: código de administrador não encontrado"));

        codigoAdministrador.setAtivo(true);
        codigoAdministradorRepository.save();

        Log.registrarAlteracao("Codigo administrador", codigoAdministrador.getId(), "ativo", "false", "true");
    }

    //Salva alteracoes do administrador se as validacoes como cpf unico e email unico forem verdadeiras.
    public void salvarAlteracoesAdministrador(DadosAtualizacaoPessoa dadosAtualizacaoPessoa) {

        if(administradorRepository.existsByCpf(dadosAtualizacaoPessoa.getCpf(), dadosAtualizacaoPessoa.getId())
            || professorRepository.existsByCpf(dadosAtualizacaoPessoa.getCpf())
            || alunoRepository.existsByCpf(dadosAtualizacaoPessoa.getCpf()))
            throw new ValidationException("Falha ao atualizar alteracoes do administrador. | Motivo: cpf informado já está registrado no sistema.");

        if(administradorRepository.existsByEmail(dadosAtualizacaoPessoa.getEmail(), dadosAtualizacaoPessoa.getId())
            || professorRepository.existsByEmail(dadosAtualizacaoPessoa.getEmail())
            || alunoRepository.existsByEmail(dadosAtualizacaoPessoa.getEmail()))
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

        if(dadosAtualizacaoPessoa.getNovaSenha() != null && !bCryptHash.verificarHash(dadosAtualizacaoPessoa.getNovaSenha(), administrador.getSenhaHash())) {
            String novoHash = bCryptHash.gerarHash(dadosAtualizacaoPessoa.getNovaSenha());
            administrador.setSenhaHash(novoHash);
            Log.registrarAlteracaoSensivel("Administrador", administrador.getId(), "Senha");
        }

        administradorRepository.save();

        Log.registrarInfo("Atualização do administrador concluída. Id=" + administrador.getId());

        MensagemView.mostrarSucesso("Dados salvos com sucesso.");

    }

}
