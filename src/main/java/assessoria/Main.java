package assessoria;
import assessoria.app.Aplicacao;
import assessoria.util.log.Log;

public class Main {

    public static void main(String[] args) {
        Aplicacao app = new Aplicacao();
        Log.registrarInfo( "Sistema iniciado.");
        app.inicializarIds();
        app.executarPrograma();
        app.salvarDados();
        Log.registrarInfo("Sistema encerrado.");
    }
}