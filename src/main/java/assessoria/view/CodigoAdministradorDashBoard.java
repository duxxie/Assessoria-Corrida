package assessoria.view;

import assessoria.model.entidades.CodigoAdministrador;

import java.util.List;

public class CodigoAdministradorDashBoard {

    public static void mostrarTabela(List<CodigoAdministrador> codigoAdministradorList) {
        String[] headers = {"Id", "Usado", "Ativo"};

        int maiorLengthId = 0;
        int maiorLengthUsado = 0;
        int maiorLengthAtivo = 0;

        for(CodigoAdministrador codigoAdministrador : codigoAdministradorList) {
            maiorLengthId = Math.max(maiorLengthId, codigoAdministrador.getId().length());
            maiorLengthUsado = Math.max(maiorLengthUsado, String.valueOf(codigoAdministrador.isUsado()).length());
            maiorLengthAtivo = Math.max(maiorLengthAtivo, String.valueOf(codigoAdministrador.isAtivo()).length());
        }

        int widthId = maiorLengthId + 6;
        int widthUsado = maiorLengthUsado + 6;
        int widthAtivo = maiorLengthAtivo + 6;

        String linhaTitulo = "|" + campoFormatado(widthId, headers[0]) +
                "|" + campoFormatado(widthUsado, headers[1]) +
                "|" + campoFormatado(widthAtivo, headers[2]) +
                "|";

        String bordaTitulo = "+" + "-".repeat(widthId) + "+" + "-".repeat(widthUsado) + "+" + "-".repeat(widthAtivo) + "+";

        System.out.println("\n\n\n");
        System.out.println(bordaTitulo);
        System.out.println(linhaTitulo);
        System.out.println(bordaTitulo);

        for(CodigoAdministrador codigoAdministrador : codigoAdministradorList) {
            System.out.println(formatarCampoCodigoAdministrador(widthId, widthUsado, widthAtivo, codigoAdministrador));
        }
        System.out.println(bordaTitulo);
    }

    private static String campoFormatado(int widthCampo, String valorCampo) {

        int valorPadding = widthCampo - valorCampo.length();
        String padding = " ";

        return padding + valorCampo + padding.repeat(valorPadding - 1);
    }

    private static String formatarCampoCodigoAdministrador(int widthId, int widthUsado, int widthAtivo, CodigoAdministrador codigoAdministrador) {
        return "|" + campoFormatado(widthId, codigoAdministrador.getId())
                + "|" + campoFormatado(widthUsado, String.valueOf(codigoAdministrador.isUsado()))
                + "|" + campoFormatado(widthAtivo, String.valueOf(codigoAdministrador.isAtivo()))
                + "|";
    }
}
