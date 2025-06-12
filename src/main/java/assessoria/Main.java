package assessoria;
import assessoria.app.Aplicacao;
import assessoria.util.log.Log;

public class Main {

    public static void main(String[] args) {
        Aplicacao app = new Aplicacao();
        Log.registrar("info", "Sistema iniciado.");
        app.inicializarDados();
        app.executarPrograma();
        app.salvarDados();
        Log.registrar("Info", "Sistema encerrado.");
    }
}