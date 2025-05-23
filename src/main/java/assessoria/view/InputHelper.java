package assessoria.view;

import java.util.Scanner;

public class InputHelper {

    private final static Scanner ler = new Scanner(System.in);

    public static String lerString(String frase) {
        System.out.print(frase);
        return ler.nextLine();
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
