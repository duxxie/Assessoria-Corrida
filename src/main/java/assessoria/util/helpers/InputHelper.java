package assessoria.util.helpers;

import java.util.Scanner;

import assessoria.view.MensagemView;

public class InputHelper {

    private final static Scanner ler = new Scanner(System.in);

    private static String lerString(String frase) {
        while(true) {
            System.out.print(frase);
            String cont = ler.nextLine();
            if(cont.isEmpty()) {
                MensagemView.mostrarErro("Esse campo é obrigatório!!");
            }else {
                return cont;
            }
        }
    }

    public static String pegarNome() {
        return lerString("Digite o nome completo: ");
    }

    public static String pegarTelefone() {
        return lerString("Digite o telefone (XX)9xxxx-xxxx: ");
    }

    public static String pegarRelacao() {
        return lerString("Informe qual é a relação do contato com você (Ex: cônjugue, pai/mãe, amigo, irmão): ");
    }

    private static Character lerChar(String frase) {
        while(true) {
            System.out.println("\n----------------------------------");
            System.out.println(frase);
            System.out.print("Digite (s) para sim e (n) para não: ");
            String entrada = ler.nextLine();

            if(entrada.length() == 1 && Character.isLetter(entrada.charAt(0))) {
                char caracter = entrada.charAt(0);
                if(Character.toLowerCase(caracter) == 's' || Character.toLowerCase(caracter) == 'n') {
                    return caracter;
                }
                System.out.println("Escolha uma opcao entre (s) e (n) !!");
            } else {
                System.out.println("Digite apenas caracteres!!");
            }
        }

    }

    public static Boolean pegarSePossuiInfoMedica(String frase) {
        char possui = Character.toLowerCase(lerChar(frase));
        return possui == 's';
    }

    public static String pegarCondicaoMedica() {
       if(pegarSePossuiInfoMedica("Possui alguma condição médica ?\nEx: Diabetes, hipertensão, problemas cardíacos, asma, epilepsia, etc.")) {
           return lerString("Digite a sua condição médica (se for mais de uma, separe elas por vírgula): ");
       } else {
           return null;
       }
    }

    public static String pegarAlergia() {
        if(pegarSePossuiInfoMedica("Possui algum tipo de alergia ?")) {
            return lerString("Digite a sua alergia (se for mais de uma, separe elas por vírgula): ");
        } else {
            return null;
        }
    }

    public static String[] pegarMedicamentoEmUso() {
        if(pegarSePossuiInfoMedica("Faz o uso de algum medicamento contínuo ?")) {
            return new String[] {lerString("Digite o medicamento (se for mais de uma, separe elas por vírgula): "), lerString("Informe com qual frequência (Ex: Todos os dias; De dois em dois dias): ")};
        } else {
            return null;
        }
    }

    public static String pegarLesao() {
        if(pegarSePossuiInfoMedica("Possui alguma lesão recente ou crônica ?")) {
            return lerString("Digite a sua lesão (se for mais de uma, separe elas por vírgula): ");
        } else {
            return null;
        }
    }

    public static String pegarCirurgiaRecente() {
        if(pegarSePossuiInfoMedica("Você realizou alguma cirurgia recente que deva ser levada em consideração ?")) {
            return lerString("Digite o nome da cirurgia que realizou (se for mais de uma, separe elas por vírgula): ");
        } else {
            return null;
        }
    }

    public static String pegarRestricaoMedica() {
        if(pegarSePossuiInfoMedica("Possui alguma restrição médica ?")) {
            return lerString("Digite a sua restrição (se for mais de uma, separe elas por vírgula): ");
        } else {
            return null;
        }
    }

    public static String pegarTipoSanguineo() {
        if(pegarSePossuiInfoMedica("Sabe qual é o seu tipo sanguíneo ?")) {
            return lerString("Digite o seu tipo sanguíneo: ");
        } else {
            return null;
        }
    }

    public static boolean pegarEscolhaDadosContatoEmergencia() {
        char opcao = Character.toLowerCase(lerChar("Deseja adicionar um contato para emergência ?"));
        if(opcao == 'n') {
           while(true) {
               System.out.println("\n------------------------------------------------------------------------");
               System.out.println("O contato de emergência é crucial em casos como: Emergência de Saúde; Acidentes Inesperados.");
               System.out.println(" >> [1] Estou ciente, mesmo assim não quero adicionar um contato de emergência");
               System.out.println(" >> [2] Estou ciente, quero adicionar um contato de emergência");
               int op = lerOpcao();
               if(op == 1) {
                   return false;
               } else if (op == 2) {
                   return true;
               } else {
                   MensagemView.mostrarErro("Escolha uma opção válida!!");
               }
           }
        }
        return true;
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

    public static String pegarSenhaToCadastro() {
        return pegarSenha("\n -->> Informe uma senha para a sua conta <<--");
    }

    public static String pegarSenhaToLogin() {
        return pegarSenha("\n -->> Informe a sua senha <<--\n >> Lembrando que:");
    }

    private static String pegarSenha(String frase) {
        String senha = "";
        boolean valido = false;
        System.out.println(frase);
        System.out.println("[A senha deve ser composta por no mínimo 8 caracteres.]\n[Ter pelo menos uma letra maiúscula e um número.]");
        System.out.println("---------------------------------");
        while(!valido) {
            try{
                System.out.print("Digite a senha: ");
                senha = ler.nextLine();
                valido = Validador.isSenhaValido(senha);
            }catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return senha;

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
                MensagemView.mostrarErro("Digite apenas número inteiro!!");
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
                MensagemView.mostrarErro("Digite apenas número decimal!!");
            }
        }

    }

    public static void encerrarInput() {
        ler.close();
    }
}
