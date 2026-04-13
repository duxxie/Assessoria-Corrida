package assessoria.view;

public class MensagemView {

    public static void mostrarErro(String fraseErro) {
        System.out.println("\n❌ Erro: " + fraseErro);
    }

    public static void mostrarAviso(String fraseAviso) {
        System.out.println("\n ⚠️ " + fraseAviso);
    }

    public static void mostrarSucesso(String fraseSucesso) {
        System.out.println("\n✅ " + fraseSucesso);
    }

    public static void mostrarMensagem(String msg) {
        System.out.println(msg);
    }
}
