package assessoria.model.entidades;

public class InfoMedica {

    private String condicaoMedica;
    private String alergia;
    private String medicamentoEmUso;
    private String lesao;
    private String cirurgiaRecente;
    private String restricaoMedica;
    private String tipoSanguineo;

    public InfoMedica(){};

    public InfoMedica(String condicaoMedica, String alergia, String medicamentoEmUso, String lesao, String cirurgiaRecente, String restricaoMedica, String tipoSanguineo) {
        this.condicaoMedica = condicaoMedica;
        this.alergia = alergia;
        this.medicamentoEmUso = medicamentoEmUso;
        this.lesao = lesao;
        this.cirurgiaRecente = cirurgiaRecente;
        this.restricaoMedica = restricaoMedica;
        this.tipoSanguineo = tipoSanguineo;
    }

    public String getCondicaoMedica() {
        return condicaoMedica;
    }

    public void setCondicaoMedica(String condicaoMedica) {
        this.condicaoMedica = condicaoMedica;
    }

    public String getAlergia() {
        return alergia;
    }

    public void setalergia(String alergia) {
        this.alergia = alergia;
    }

    public String getMedicamentoEmUso() {
        return medicamentoEmUso;
    }

    public void setMedicamentoEmUso(String medicamentoEmUso) {
        this.medicamentoEmUso = medicamentoEmUso;
    }

    public String getLesao() {
        return lesao;
    }

    public void setLesao(String lesao) {
        this.lesao = lesao;
    }

    public String getCirurgiaRecente() {
        return cirurgiaRecente;
    }

    public void setCirurgiaRecente(String cirurgiaRecente) {
        this.cirurgiaRecente = cirurgiaRecente;
    }

    public String getRestricaoMedica() {
        return restricaoMedica;
    }

    public void setRestricaoMedica(String restricaoMedica) {
        this.restricaoMedica = restricaoMedica;
    }

    public String getTipoSanguineo() {
        return tipoSanguineo;
    }

    public void setTipoSanguineo(String tipoSanguineo) {
        this.tipoSanguineo = tipoSanguineo;
    }

    public void mostrarInfoCompleta() {
        String valor;
        valor = getCondicaoMedica() == null ? "Nenhum" : getCondicaoMedica();
        System.out.println(">> Condição médica: " + valor);

        valor = getAlergia() == null ? "Nenhuma" : getAlergia();
        System.out.println(">> Alergia: " + valor);

        valor = getMedicamentoEmUso() == null ? "Nenhum" : getMedicamentoEmUso();
        System.out.println(">> Medicamentos em uso: " + valor);

        valor = getLesao() == null ? "Nenhuma" : getLesao();
        System.out.println(">> Lesão recente ou crônica: " + valor);

        valor = getCirurgiaRecente() == null ? "Nenhuma" : getCirurgiaRecente();
        System.out.println(">> Cirurgia recente: " + valor);

        valor = getRestricaoMedica() == null ? "Nenhuma" : getRestricaoMedica();
        System.out.println(">> Restrição médica: " + valor);

        valor = getTipoSanguineo() == null ? "Não informado" : getTipoSanguineo();
        System.out.println(">> Tipo sanguíneo: " + valor);
    }
}
