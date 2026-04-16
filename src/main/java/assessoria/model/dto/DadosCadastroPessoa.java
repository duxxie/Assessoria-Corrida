package assessoria.model.dto;

import assessoria.model.entidades.ContatoEmergencia;
import assessoria.model.entidades.InfoMedica;

public class DadosCadastroPessoa {

    private String nome;
    private String email;
    private String cpf;
    private int idade;
    private String telefone;
    private String hashProvider;

    private ContatoEmergencia contatoEmergencia;
    private InfoMedica infoMedica;

    public DadosCadastroPessoa() {}

    public boolean isContatoEmergenciaInformado() {
        return contatoEmergencia != null;
    }

    public boolean isInfoMedicaInformada() {
        return infoMedica != null;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getHashProvider() {
        return hashProvider;
    }

    public void setHashProvider(String hashProvider) {
        this.hashProvider = hashProvider;
    }

    public ContatoEmergencia getContatoEmergencia() {
        return contatoEmergencia;
    }

    public void setContatoEmergencia(ContatoEmergencia contatoEmergencia) {
        this.contatoEmergencia = contatoEmergencia;
    }

    public InfoMedica getInfoMedica() {
        return infoMedica;
    }

    public void setInfoMedica(InfoMedica infoMedica) {
        this.infoMedica = infoMedica;
    }
}
