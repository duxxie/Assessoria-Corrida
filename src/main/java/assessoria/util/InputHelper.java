package assessoria.util;

import java.util.Scanner;
import assessoria.view.MensagemView;

public class InputHelper {

    private final static Scanner ler = new Scanner(System.in);
    private final static MensagemView mensagemView = new MensagemView();

    public static String lerString(String frase) {
        System.out.print(frase);
        return ler.nextLine();
    }

    public static String lerCpf(String frase) {
        String cpf = "";
        boolean valido = false;
        while(!valido) {
            try{
                System.out.print(frase);
                cpf = ler.nextLine();
                valido = Validador.isCpfValido(cpf);
            }catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return cpf;
    }

    public static String lerEmail(String frase) {
        String email = "";
        boolean valido = false;
        while(!valido) {
            try {
                System.out.print(frase);
                email = ler.nextLine();
                valido = Validador.isEmailValido(email);
            }catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return email;
    }

    public static int lerInt(String frase) {
        while(true) {
            try{
                System.out.print(frase);
                return Integer.parseInt(ler.nextLine());
            } catch (Exception e) {
                mensagemView.mostrarErro("Digite apenas número inteiro!!");
            }
        }
    }

    public static int lerOpcao() {
        System.out.print("Escolha uma opção: ");
        return Integer.parseInt(ler.nextLine());
    }

    public static float lerFloat(String frase) {
        while(true) {
            try{
                System.out.print(frase);
                return Float.parseFloat(ler.nextLine());
            } catch (Exception e) {
                mensagemView.mostrarErro("Digite apenas número decimal!!");
            }
        }

    }

    public static void encerrarInput() {
        ler.close();
    }
}
