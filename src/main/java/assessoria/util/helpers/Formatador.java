package assessoria.util.helpers;

public class Formatador {

    private static String somenteDigitos(String valor) {
        return valor.replaceAll("\\D", "");
    }

    public static String formatarCpf(String cpf) {
        return "";
    }

    public static String formatarTelefone(String telefone) {
        return "";
    }

    public static String formatarCref(String cref) {
        return "";
    }

    public static String removerMascaraCref(String cref) {
        return cref.replaceAll("[^A-Za-z0-9]", "").toUpperCase();
    }

}
