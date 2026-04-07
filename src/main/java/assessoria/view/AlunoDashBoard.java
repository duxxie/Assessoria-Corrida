package assessoria.view;

import assessoria.model.entidades.Administrador;
import assessoria.model.entidades.Aluno;

import java.util.Map;

public class AlunoDashBoard {

    public void mostrarTabela(Map<String, Aluno> typeMap) {

        String[] headers = {"id", "Nome", "Cpf", "Email", "Idade", "Telefone"};

        int maxId = 0;
        int maxNome = 0;
        int maxCpf = 0;
        int maxEmail = 0;
        int maxIdade = 0;
        int maxTelefone = 0;

        for(Map.Entry<String, Aluno> entry : typeMap.entrySet()) {
            maxId = Math.max(maxId, String.valueOf(entry.getValue().getIdade()).length());
            maxNome = Math.max(maxNome, entry.getValue().getNome().length());
            maxCpf = Math.max(maxCpf, entry.getValue().getCpf().length());
            maxEmail = Math.max(maxEmail, entry.getValue().getEmail().length());
            maxIdade = Math.max(maxIdade, String.valueOf(entry.getValue().getIdade()).length());
            maxTelefone = Math.max(maxTelefone, entry.getValue().getTelefone().length());
        }

        int widthId = maxId + 6;
        int widthNome = maxNome + 6;
        int widthCpf = maxCpf + 6;
        int widthEmail = maxEmail + 6;
        int widthIdade = maxIdade + 6;
        int widthTelefone = maxTelefone + 6;

        String linhaTitulo = "|" + campoFormatado(widthId, headers[0]) +
                "|" + campoFormatado(widthNome, headers[1]) +
                "|" + campoFormatado(widthCpf, headers[2]) +
                "|" + campoFormatado(widthEmail, headers[3]) +
                "|" + campoFormatado(widthIdade, headers[4]) +
                "|" + campoFormatado(widthTelefone, headers[5]) +
                "|";

        String tituloTabela = "DASHBOARD ALUNO";
        int marginTitulo = linhaTitulo.length() - (tituloTabela.length() + 6);
        int paddingTituloTabela = 6;
        int marginTituloTabela = marginTitulo/2;
        int bordasLateraisTituloTabela = 2;

        String linhaTituloTabela = " ".repeat(marginTituloTabela-2) + "|" + " ".repeat(paddingTituloTabela) + tituloTabela + " ".repeat(paddingTituloTabela) + "|" + " ".repeat(marginTituloTabela-2);
        String bordaTopTituloTabela = " ".repeat(marginTituloTabela-2) + "+ " + "-".repeat((tituloTabela.length() + (paddingTituloTabela*2) + bordasLateraisTituloTabela) - 4) + " +";

        String bordaTabela = "+" + "-".repeat(widthId)
                + "+" + "-".repeat(widthNome)
                + "+" + "-".repeat(widthCpf)
                + "+" + "-".repeat(widthEmail)
                + "+" + "-".repeat(widthIdade)
                + "+" + "-".repeat(widthTelefone)
                + "+";
        System.out.println("\n\n\n");
        System.out.println(bordaTopTituloTabela);
        System.out.println(linhaTituloTabela);
        System.out.println(bordaTopTituloTabela);
        System.out.println(bordaTabela);
        System.out.println(linhaTitulo);
        System.out.println(bordaTabela);

        // Dados
        for (Aluno aluno : typeMap.values()) {
            System.out.println(formatarCampoDadosAlunos(widthId, widthNome, widthCpf, widthEmail, widthIdade, widthTelefone, aluno));
        }
        System.out.println(bordaTabela);
    }

    public static String campoFormatado(int widthCampo, String valorCampo) {
        int valorPadding = widthCampo - valorCampo.length();
        String padding = " ";

        return padding + valorCampo + padding.repeat(valorPadding - 1);
    }

    private static String formatarCampoDadosAlunos(int widthId, int widthNome, int widthCpf, int widthEmail, int widthIdade, int widthTelefone, Aluno aluno) {
        return "|" + campoFormatado(widthId, aluno.getId())
                + "|" + campoFormatado(widthNome, aluno.getNome())
                + "|" + campoFormatado(widthCpf, aluno.getCpf())
                + "|" + campoFormatado(widthEmail, aluno.getEmail())
                + "|" + campoFormatado(widthIdade, String.valueOf(aluno.getIdade()))
                + "|" + campoFormatado(widthTelefone, aluno.getTelefone())
                + "|";
    }
}
