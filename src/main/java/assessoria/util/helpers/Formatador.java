package assessoria.util.helpers;

public class Formatador {

    private static String somenteDigitos(String valor) {
        return valor.replaceAll("\\D+", "");
    }

    public static String formatarCpf(String cpf) {
        StringBuilder sb = new StringBuilder(cpf);

        sb.insert(3, '.');
        sb.insert(7, '.');
        sb.insert(11, '-');

        return sb.toString();
    }

    public static String formatarTelefone(String telefone) {
        StringBuilder sb = new StringBuilder(telefone);

        sb.insert(0, "(");
        sb.insert(3, ")");
        sb.insert(4, " ");
        sb.insert(10, "-");

        return sb.toString();
    }

    public static String formatarCref(String cref) {
        return "";
    }

    public static String removerMascaraCref(String cref) {
        return cref.replaceAll("[^A-Za-z0-9]", "").toUpperCase();
    }

    public static String removerMascaraTelefone(String telefone) {
        return somenteDigitos(telefone);
    }

    public static String removerMascaraCpf(String cpf) {
        return somenteDigitos(cpf);
    }

}
