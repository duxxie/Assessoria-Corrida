package assessoria.util.helpers;

public interface Hash {
    String gerarHash(String senhaPlana);

    boolean verificarHash(String senhaPlana, String hashArmazenado);
}
