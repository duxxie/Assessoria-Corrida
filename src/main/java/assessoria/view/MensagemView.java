package assessoria.view;

public class MensagemView {

    public void mostrarErro(String fraseErro) {
        System.out.println("\n❌ Erro: " + fraseErro);
    }

    public void mostrarSucesso(String fraseSucesso) {
        System.out.println("\n✅ " + fraseSucesso);
    }

    public void mostrarMensagem(String msg) {
        System.out.println(msg);
    }
}
