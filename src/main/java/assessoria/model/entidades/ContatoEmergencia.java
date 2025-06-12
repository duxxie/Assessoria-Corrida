package assessoria.model.entidades;

public class ContatoEmergencia {

    private String nome;
    private String telefone;
    private String relacionamento;

    public ContatoEmergencia(){};

    public ContatoEmergencia(String nome, String telefone, String relacionamento) {
        this.nome = nome;
        this.telefone = telefone;
        this.relacionamento = relacionamento;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getRelacionamento() {
        return relacionamento;
    }

    public void setRelacionamento(String relacionamento) {
        this.relacionamento = relacionamento;
    }

    public void mostrarInfoCompleta() {
        String valor;
        valor = getNome() == null ? "Não informado" : getNome();
        System.out.println(">> Nome: " + valor);

        valor = getTelefone() == null ? "Não informado" : getTelefone();
        System.out.println(">> Telefone: " + valor);

        valor = getRelacionamento() == null ? "Não informado" : getRelacionamento();
        System.out.println(">> Relação: " + valor);
    }
}
