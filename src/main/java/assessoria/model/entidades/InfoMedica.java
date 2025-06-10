package assessoria.model.entidades;

public class InfoMedica {

    private String descCondicaoMedica;
    private String descAlergia;
    private String quaisMedicamentoEmUso;
    private String frequenciaMedicamentoEmUso;
    private String descLesaoRecenteOuCronica;
    private String descCirurgiaRecente;
    private String descRestricaoMedica;
    private String descTipoSanguineo;

    public InfoMedica(){};

    public InfoMedica(String descCondicaoMedica, String descAlergia, String quaisMedicamentoEmUso, String frequenciaMedicamentoEmUso, String descLesaoRecenteOuCronica, String descCirurgiaRecente, String descRestricaoMedica, String descTipoSanguineo) {
        this.descCondicaoMedica = descCondicaoMedica;
        this.descAlergia = descAlergia;
        this.quaisMedicamentoEmUso = quaisMedicamentoEmUso;
        this.frequenciaMedicamentoEmUso = frequenciaMedicamentoEmUso;
        this.descLesaoRecenteOuCronica = descLesaoRecenteOuCronica;
        this.descCirurgiaRecente = descCirurgiaRecente;
        this.descRestricaoMedica = descRestricaoMedica;
        this.descTipoSanguineo = descTipoSanguineo;
    }

    public String getDescCondicaoMedica() {
        return descCondicaoMedica;
    }

    public void setDescCondicaoMedica(String descCondicaoMedica) {
        this.descCondicaoMedica = descCondicaoMedica;
    }

    public String getDescAlergia() {
        return descAlergia;
    }

    public void setDescAlergia(String descAlergia) {
        this.descAlergia = descAlergia;
    }

    public String getQuaisMedicamentoEmUso() {
        return quaisMedicamentoEmUso;
    }

    public void setQuaisMedicamentoEmUso(String quaisMedicamentoEmUso) {
        this.quaisMedicamentoEmUso = quaisMedicamentoEmUso;
    }

    public String getFrequenciaMedicamentoEmUso() {
        return frequenciaMedicamentoEmUso;
    }

    public void setFrequenciaMedicamentoEmUso(String frequenciaMedicamentoEmUso) {
        this.frequenciaMedicamentoEmUso = frequenciaMedicamentoEmUso;
    }

    public String getDescLesaoRecenteOuCronica() {
        return descLesaoRecenteOuCronica;
    }

    public void setDescLesaoRecenteOuCronica(String descLesaoRecenteOuCronica) {
        this.descLesaoRecenteOuCronica = descLesaoRecenteOuCronica;
    }

    public String getDescCirurgiaRecente() {
        return descCirurgiaRecente;
    }

    public void setDescCirurgiaRecente(String descCirurgiaRecente) {
        this.descCirurgiaRecente = descCirurgiaRecente;
    }

    public String getDescRestricaoMedica() {
        return descRestricaoMedica;
    }

    public void setDescRestricaoMedica(String descRestricaoMedica) {
        this.descRestricaoMedica = descRestricaoMedica;
    }

    public String getDescTipoSanguineo() {
        return descTipoSanguineo;
    }

    public void setDescTipoSanguineo(String descTipoSanguineo) {
        this.descTipoSanguineo = descTipoSanguineo;
    }

    public void mostrarInfoCompleta() {
        String valor;
        valor = getDescCondicaoMedica() == null ? "Nenhum" : getDescCondicaoMedica();
        System.out.println("Condição médica: " + valor);

        valor = getDescAlergia() == null ? "Nenhuma" : getDescAlergia();
        System.out.println("Alergia: " + valor);

        valor = getQuaisMedicamentoEmUso() == null ? "Nenhum" : getQuaisMedicamentoEmUso();
        System.out.println("Medicamentos em uso: " + valor);

        valor = getFrequenciaMedicamentoEmUso() == null ? "Nenhuma" : getFrequenciaMedicamentoEmUso();
        System.out.println("Frequência de uso do medicamento: " + valor);

        valor = getDescLesaoRecenteOuCronica() == null ? "Nenhuma" : getDescLesaoRecenteOuCronica();
        System.out.println("Lesão recente ou crônica: " + valor);

        valor = getDescCirurgiaRecente() == null ? "Nenhuma" : getDescCirurgiaRecente();
        System.out.println("Cirurgia recente: " + valor);

        valor = getDescRestricaoMedica() == null ? "Nenhuma" : getDescRestricaoMedica();
        System.out.println("Restrição médica: " + valor);

        valor = getDescTipoSanguineo() == null ? "Não informado" : getDescTipoSanguineo();
        System.out.println("Tipo sanguíneo: " + valor);
    }
}
