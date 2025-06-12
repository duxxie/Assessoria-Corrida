package assessoria.model.entidades;

public abstract class Pessoa {
    private String id;
    private String nome;
    private String email;
    private String cpf;
    private int idade;
    private String telefone;
    private String senhaHash;
    private String hashProvider;
    private ContatoEmergencia contatoEmergencia;
    private InfoMedica infoMedica;

    public Pessoa(){}

    public Pessoa(String id, String nome, String email, String cpf, int idade, String telefone, String senhaHash, String hashProvider, ContatoEmergencia contatoEmergencia, InfoMedica infoMedica) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.cpf = cpf;
        this.idade = idade;
        this.telefone = telefone;
        this.senhaHash = senhaHash;
        this.hashProvider = hashProvider;
        this.contatoEmergencia = contatoEmergencia;
        this.infoMedica = infoMedica;
    }

    public Pessoa(String id, String nome, String email, String cpf, int idade, String telefone, String senhaHash, String hashProvider, InfoMedica infoMedica) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.cpf = cpf;
        this.idade = idade;
        this.telefone = telefone;
        this.senhaHash = senhaHash;
        this.hashProvider = hashProvider;
        this.infoMedica = infoMedica;
    }

    public Pessoa(String id, String nome, String cpf, int idade) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.idade = idade;
    }

    public Pessoa(String id, String nome, String email, String cpf, int idade, String telefone) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.cpf = cpf;
        this.idade = idade;
        this.telefone = telefone;
    }

    public Pessoa(String id, String nome, String email, String cpf, int idade, String telefone, String senhaHash, String hashProvider) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.cpf = cpf;
        this.idade = idade;
        this.telefone = telefone;
        this.senhaHash = senhaHash;
        this.hashProvider = hashProvider;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getSenhaHash() {
        return senhaHash;
    }

    public String getHashProvider() {
        return hashProvider;
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

    public void setSenhaHash(String senhaHash) {
        this.senhaHash = senhaHash;
    }

    public void setHashProvider(String hashProvider) {
        this.hashProvider = hashProvider;
    }

    //    public boolean verificarSenha(String senhaPlana) {
//        return hashProvider.verificarHash(senhaPlana, this.senhaHash);
//    }

    public void mostrarInfoCompleta() {

        System.out.println("\n\n----------------------------------------");
        System.out.println(">> Id: " + getId());
        System.out.println(">> Nome: " + getNome());
        System.out.println(">> Email: " + getEmail());
        System.out.println(">> Cpf: " + getCpf());
        System.out.println(">> Idade: " + getIdade());
        System.out.println(">> Telefone: " + getTelefone());
        getInfoMedica().mostrarInfoCompleta();
        if(getContatoEmergencia() != null) {
            getContatoEmergencia().mostrarInfoCompleta();
        }

        System.out.println("----------------------------------------");
    }

    public void mostrarInfo() {
        System.out.println("Id: " + getId());
        System.out.println("Nome: " + getNome());
        System.out.println("Email: " + getEmail());
        System.out.println("Cpf: " + getCpf());
        System.out.println("Idade: " + getIdade());
        System.out.println("Telefone: " + getTelefone());
    }

}