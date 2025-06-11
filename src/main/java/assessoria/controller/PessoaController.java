package assessoria.controller;

import assessoria.model.entidades.Pessoa;
import assessoria.model.entidades.Pessoa;
import assessoria.model.entidades.ContatoEmergencia;
import assessoria.model.entidades.InfoMedica;
import assessoria.service.PessoaService;
import assessoria.util.helpers.GeradorID;

import java.util.Map;

public abstract class PessoaController {

    private final PessoaService pessoaService;

    public PessoaController(PessoaService pessoaService) {
        this.pessoaService = pessoaService;
    }

    public void criarPessoa(String nome, String email, String cpf, int idade, String telefone, String senha, String hashSenha, String nomeEmergencia, String telefoneEmergencia, String relacao, String condicaoMedica, String alergia, String medicamentoEmUso, String frequenciaMedicamentoEmUso, String lesaoRecente, String cirurgiaRecente, String restricaoMedica, String tipoSanguineo) {
        pessoaService.salvarPessoa(new Pessoa(GeradorID.gerarIdPessoa(), nome, email, cpf, idade, telefone, senha, hashSenha, new ContatoEmergencia(nomeEmergencia, telefoneEmergencia, relacao), new InfoMedica(condicaoMedica, alergia, medicamentoEmUso, frequenciaMedicamentoEmUso, lesaoRecente, cirurgiaRecente, restricaoMedica, tipoSanguineo)));
    }

    public void criarPessoa(String nome, String email, String cpf, int idade, String telefone, String senha, String hashSenha, String condicaoMedica, String alergia, String medicamentoEmUso, String frequenciaMedicamentoEmUso, String lesaoRecente, String cirurgiaRecente, String restricaoMedica, String tipoSanguineo) {
        pessoaService.salvarPessoa(new Pessoa(GeradorID.gerarIdPessoa(),nome, email, cpf, idade, telefone, senha, hashSenha, new InfoMedica(condicaoMedica, alergia, medicamentoEmUso, frequenciaMedicamentoEmUso, lesaoRecente, cirurgiaRecente, restricaoMedica, tipoSanguineo)));
    }

    public void criarPessoa(String nome, String email, String cpf, int idade, String telefone, String senha, String hashSenha, String condicaoMedica, String alergia, String medicamentoEmUso, String frequenciaMedicamentoEmUso, String lesaoRecente, String cirurgiaRecente, String restricaoMedica, String tipoSanguineo) {
        pessoaService.salvarPessoa(new Pessoa(GeradorID.gerarIdPessoa(),nome, email, cpf, idade, telefone, senha, hashSenha));
    }

    public void salvarPessoasArquivo() {
        pessoaService.inserirPessoasArquivo();
    }

    public Map<String,Pessoa> pegarMapPessoas() {
        return pessoaService.getMapPessoa();
    }


}