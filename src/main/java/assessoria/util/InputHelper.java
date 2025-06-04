package assessoria.util;

import java.util.Scanner;
import assessoria.view.MensagemView;

public class InputHelper {

    private final static Scanner ler = new Scanner(System.in);
    private final static MensagemView mensagemView = new MensagemView();

    private static String lerString(String frase) {
        System.out.print(frase);
        return ler.nextLine();
    }

    public static String pegarNome() {
        return lerString("Digite o nome completo: ");
    }

    public static String pegarTelefone() {
        return lerString("Digite o telefone (XX)9xxxx-xxxx: ");
    }

    public static String pegarCpf() {
        String cpf = "";
        boolean valido = false;
        while(!valido) {
            try{
                System.out.print("Digite o CPF (xxx.xxx.xxx-xx) : ");
                cpf = ler.nextLine();
                valido = Validador.isCpfValido(cpf);
            }catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return cpf;
    }

    public static String pegarEmail() {
        String email = "";
        boolean valido = false;
        while(!valido) {
            try {
                System.out.print("Digite o email (Ex: user@gmail.com): ");
                email = ler.nextLine();
                valido = Validador.isEmailValido(email);
            }catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return email;
    }

    private static int lerInt(String frase) {
        while(true) {
            try{
                System.out.print(frase);
                return Integer.parseInt(ler.nextLine());
            } catch (Exception e) {
                mensagemView.mostrarErro("Digite apenas número inteiro!!");
            }
        }
    }

    public static int pegarIdade() {
       return lerInt("Digite a idade: ");
    }

    public static int lerOpcao() {
       return lerInt("Escolha uma opção: ");
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
