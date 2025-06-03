package assessoria.view;

public class MenuPrincipal {

    MensagemView mensagemView = new MensagemView();

    public void mostrarMenuPrincipal() {
        System.out.println("\n\n+ ------------------------------------- +");
        System.out.println("|     << -- Assessoria Corrida -- >>    |");
        System.out.println("+ ------------------------------------- +");
        System.out.println("|       [1] Fazer cadastro              |");
        System.out.println("|       [2] Já tenho cadastro           |");
        System.out.println("|       [0] Sair                        |");
        System.out.println("+ ------------------------------------- +");
    }

    public void mostrarSaida() {
        mensagemView.mostrarMensagem("Encerrando o programa...");
    }

    public void mostrarDefaultMenu() {
        mensagemView.mostrarErro("Ecolha uma opção válida!!");
    }

}