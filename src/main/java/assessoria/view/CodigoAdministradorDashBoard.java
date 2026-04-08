package assessoria.view;

import assessoria.model.entidades.Administrador;
import assessoria.model.entidades.CodigoAdministrador;

import java.util.List;

public class CodigoAdministradorDashBoard {

    public static void mostrarTabela(List<CodigoAdministrador> codigoAdministradorList) {
        String[] headers = {"Id", "Usado"};

        int maiorLengthId = 0;
        int maiorLengthUsado = 0;

        for(CodigoAdministrador codigoAdministrador : codigoAdministradorList) {
            maiorLengthId = Math.max(maiorLengthId, codigoAdministrador.getId().length());
            maiorLengthUsado = Math.max(maiorLengthUsado, String.valueOf(codigoAdministrador.isUsado()).length());
        }

        int widthId = maiorLengthId + 6;
        int widthUsado = maiorLengthUsado + 6;

        String linhaTitulo = "|" + campoFormatado(widthId, headers[0]) +
                "|" + campoFormatado(widthUsado, headers[1]) +
                "|";

        String bordaTitulo = "+" + "-".repeat(widthId) + "+" + "-".repeat(widthUsado) + "+";

        System.out.println("\n\n\n");
        System.out.println(bordaTitulo);
        System.out.println(linhaTitulo);
        System.out.println(bordaTitulo);

        for(CodigoAdministrador codigoAdministrador : codigoAdministradorList) {
            System.out.println(formatarCampoCodigoAdministrador(widthId, widthUsado, codigoAdministrador));
        }
        System.out.println(bordaTitulo);
    }

    private static String campoFormatado(int widthCampo, String valorCampo) {

        int valorPadding = widthCampo - valorCampo.length();
        String padding = " ";

        return padding + valorCampo + padding.repeat(valorPadding - 1);
    }

    private static String formatarCampoCodigoAdministrador(int widthId, int widthUsado, CodigoAdministrador codigoAdministrador) {
        return "|" + campoFormatado(widthId, codigoAdministrador.getId())
                + "|" + campoFormatado(widthUsado, String.valueOf(codigoAdministrador.isUsado()))
                + "|";
    }
}
