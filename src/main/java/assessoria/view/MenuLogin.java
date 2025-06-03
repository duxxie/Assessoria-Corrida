package assessoria.view;

public class MenuLogin {

    MensagemView mensagemView = new MensagemView();

    public void mostrarMenu() {
        System.out.println("\n\n+ ------------------------------------- +");
        System.out.println("|         << -- Fazer Login -- >>       |");
        System.out.println("+ ------------------------------------- +");
        System.out.println("|             [1] Aluno                 |");
        System.out.println("|             [2] Professor             |");
        System.out.println("|             [3] Administrador         |");
        System.out.println("|             [0] Sair                  |");
        System.out.println("+ ------------------------------------- +");
    }

    public void mostrarSaida() {
        mensagemView.mostrarMensagem("Voltando ao menu principal");
    }

    public void mostrarDefaultMenu() {
        mensagemView.mostrarErro("Opçao inválida para login!!");
    }
}
