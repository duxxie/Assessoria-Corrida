package assessoria.util;

import java.util.Scanner;

public class InputHelper {

    private final static Scanner ler = new Scanner(System.in);

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
        System.out.print(frase);
        return Integer.parseInt(ler.nextLine());
    }

    public static float lerFloat(String frase) {
        System.out.print(frase);
        return Float.parseFloat(ler.nextLine());
    }
}
