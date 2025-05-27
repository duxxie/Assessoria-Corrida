package assessoria.util;

import org.mindrot.jbcrypt.BCrypt;

public class BCryptHash implements Hash{

    private static final int LOG_ROUNDS = 10;

    @Override
    public String gerarHash(String senhaPlana) {
        return BCrypt.hashpw(senhaPlana, BCrypt.gensalt(LOG_ROUNDS));
    }

    @Override
    public boolean verificarHash(String senhaPlana, String hashArmazenado) {
        return BCrypt.checkpw(senhaPlana, hashArmazenado);
    }
}
