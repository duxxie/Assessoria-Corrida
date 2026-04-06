package assessoria.view;

import assessoria.model.entidades.Administrador;

import java.util.Map;

public class AdministradorDashBoard {

    public static void mostrarTabela(Map<String, Administrador> produtos) {

        String[] headers = {"Id", "Nome", "Cpf", "Email", "Idade", "Telefone", "CodigoAdministrador", "AdminRaiz"};

        int maiorLengthId = 0;
        int maiorLengthNome = 0;
        int maiorLengthCpf = 0;
        int maiorLengthEmail = 0;
        int maiorLengthIdade = 0;
        int maiorLengthTelefone = 0;
        int maiorLengthCodigoAdmin = 0;
        int maiorLengthAdminRaiz = 0;

        for(Map.Entry<String, Administrador> entry : produtos.entrySet()) {
            maiorLengthId = Math.max(maiorLengthId, entry.getValue().getId().length());
            maiorLengthNome = Math.max(maiorLengthNome, entry.getValue().getNome().length());
            maiorLengthCpf = Math.max(maiorLengthCpf, entry.getValue().getCpf().length());
            maiorLengthEmail = Math.max(maiorLengthEmail, entry.getValue().getEmail().length());
            maiorLengthIdade = Math.max(maiorLengthIdade, String.valueOf(entry.getValue().getIdade()).length());
            maiorLengthTelefone = Math.max(maiorLengthTelefone, entry.getValue().getTelefone().length());
            maiorLengthCodigoAdmin = Math.max(maiorLengthCodigoAdmin, entry.getValue().getIdCodigoAdministrador().length());
            maiorLengthAdminRaiz = Math.max(maiorLengthAdminRaiz, String.valueOf(entry.getValue().isAdiminRaiz()).length());
        }

        int widthId = maiorLengthId + 6;
        int widthNome = maiorLengthNome + 6;
        int widthCpf = maiorLengthCpf + 6;
        int widthEmail = maiorLengthEmail + 6;
        int widthIdade = maiorLengthIdade + 6;
        int widthTelefone = maiorLengthTelefone + 6;
        int widthCodigoAdmin = maiorLengthCodigoAdmin + 6;
        int widthAdminRaiz = maiorLengthAdminRaiz + 6;

        String linhaTituloAdministrador = "|" + campoFormatado(widthId, headers[0]) +
                "|" + campoFormatado(widthNome, headers[1]) +
                "|" + campoFormatado(widthCpf, headers[2]) +
                "|" + campoFormatado(widthEmail, headers[3]) +
                "|" + campoFormatado(widthIdade, headers[4]) +
                "|" + campoFormatado(widthTelefone, headers[5]) +
                "|" + campoFormatado(widthCodigoAdmin, headers[6]) +
                "|" + campoFormatado(widthAdminRaiz, headers[7]) +
                "|";

        String tituloTabela = "DASHBOARD ADMINISTRADOR";
        int marginTitulo = linhaTituloAdministrador.length() - (tituloTabela.length() + 6);
        int paddingTituloTabela = 6;
        int marginTituloTabela = marginTitulo/2;
        int bordasLateraisTituloTabela = 2;
        String linhaTituloTabela = " ".repeat(marginTituloTabela-2) + "|" + " ".repeat(paddingTituloTabela) + tituloTabela + " ".repeat(paddingTituloTabela) + "|" + " ".repeat(marginTituloTabela-2);
        String bordaTopTituloTabela = " ".repeat(marginTituloTabela-2) + "+ " + "-".repeat((tituloTabela.length() + (paddingTituloTabela*2) + bordasLateraisTituloTabela) - 4) + " +";

        String bordaTabela = "+ " + "-".repeat(linhaTituloAdministrador.length()-4) + " +";
        String linhaDivisoriaProdutos = "-".repeat(linhaTituloAdministrador.length());
        String linhaVazia = "|" +
                " ".repeat(widthId) + "|" +
                " ".repeat(widthNome) + "|" +
                " ".repeat(widthCpf) + "|" +
                " ".repeat(widthEmail) + "|" +
                " ".repeat(widthIdade) + "|" +
                " ".repeat(widthTelefone) + "|" +
                " ".repeat(widthCodigoAdmin) + "|" +
                " ".repeat(widthAdminRaiz) +
                "|";

        System.out.println(bordaTopTituloTabela);
        System.out.println(linhaTituloTabela);
        System.out.println(bordaTabela);
        System.out.println(linhaTituloAdministrador);

        for(Map.Entry<String,Administrador> entry : produtos.entrySet()) {
            System.out.println(linhaDivisoriaProdutos);
            System.out.println(linhaVazia);
            System.out.println(formatarCampoDadosProduto(widthId, widthNome, widthCpf, widthEmail, widthIdade, widthTelefone, widthCodigoAdmin, widthAdminRaiz,entry.getValue()));
            System.out.println(linhaVazia);
        }
        System.out.println(bordaTabela);
    }

    private static String campoFormatado(int widthCampo, String valorCampo) {

        int valorPadding = widthCampo - valorCampo.length();
        String padding = " ";

        return padding + valorCampo + padding.repeat(valorPadding - 1);
    }

    private static String formatarCampoDadosProduto(int widthId, int widthNome, int widthCpf, int widthEmail, int widthIdade, int widthTelefone, int widthCodigoAdmin, int widthAdminRaiz, Administrador administrador) {
        return "|" + campoFormatado(widthId, administrador.getId())
                + "|" + campoFormatado(widthNome, administrador.getNome())
                + "|" + campoFormatado(widthCpf, administrador.getCpf())
                + "|" + campoFormatado(widthEmail, administrador.getEmail())
                + "|" + campoFormatado(widthIdade, String.valueOf(administrador.getIdade()))
                + "|" + campoFormatado(widthTelefone, administrador.getTelefone())
                + "|" + campoFormatado(widthCodigoAdmin, administrador.getIdCodigoAdministrador())
                + "|" + campoFormatado(widthAdminRaiz, String.valueOf(administrador.isAdiminRaiz()))
                + "|";
    }
}
