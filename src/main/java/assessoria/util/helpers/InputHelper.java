package assessoria.util.helpers;

import java.util.Scanner;

import assessoria.exceptions.ValidationException;
import assessoria.view.MensagemView;

public class InputHelper {

    private final static Scanner ler = new Scanner(System.in);

    public static String lerString(String frase) {
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
        while(true) {
            try{
                String telefone = lerString("Digite o telefone (XX)9xxxx-xxxx: ");
                String telefoneSemMascara = Formatador.removerMascaraTelefone(telefone);
                Validador.isTelefoneValido(telefoneSemMascara);

                return telefoneSemMascara;
            }catch (ValidationException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static String pegarRelacao() {
        return lerString("Informe qual é a relação do contato com você (Ex: cônjuge, pai/mãe, amigo, irmão): ");
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

    public static Boolean isAdicionarDadosInfoMedicaAgoraTrue() {
        char escolha = Character.toLowerCase(lerChar("Deseja adicionar as sua informações médicas agora ?"));
        return escolha == 's';

    }

    private static Boolean pegarSePossuiInfoMedica(String frase) {
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

    public static String pegarMedicamentoEmUso() {
        if(pegarSePossuiInfoMedica("Faz o uso de algum medicamento contínuo ?")) {
            return lerString("Digite o medicamento (se for mais de um, separe-os por vírgula): ")   ;
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

    public static boolean isAdicionarContatoEmergenciaAgoraTrue() {
        char opcao = Character.toLowerCase(lerChar("Deseja adicionar um contato para emergência agora ?"));
        return opcao == 's';
    }

    public static String pegarCpf() {
        while(true) {
            try{
                String cpf = lerString("Digite o CPF (xxx.xxx.xxx-xx) : ");
                String cpfSemMascara = Formatador.removerMascaraCpf(cpf);
                Validador.isCpfValido(cpfSemMascara);

                return cpfSemMascara;
            }catch (ValidationException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static String pegarCref() {
        while(true) {
            try {
                String cref = lerString("Informe o seu CREF: ");
                String crefSemMascara = Formatador.removerMascaraCref(cref);
                Validador.isCrefValido(cref);

                return crefSemMascara;
            } catch (ValidationException e) {
                MensagemView.mostrarAviso(e.getMessage());
            }
        }
    }

    public static String pegarSenhaToUpdate() {
        return pegarSenha("---> Informe uma nova senha para a sua conta <<---");
    }

    public static String pegarSenhaToCadastro() {
        return pegarSenha("-->> Informe uma senha para a sua conta <<--");
    }

    public static String pegarSenhaToLogin() {
        return pegarSenha("-->> Informe a sua senha <<--\n >> Lembrando que:");
    }

    private static String pegarSenha(String frase) {
        System.out.println("\n\n----------------------------------------------------------");
        System.out.println(frase);
        System.out.println("[A senha deve ser composta por no mínimo 8 caracteres.]\n[Ter pelo menos uma letra maiúscula e um número.]");
        System.out.println("----------------------------------------------------------");
        while(true) {
            try{
                String senha = lerString("Digite a senha: ");
                Validador.isSenhaValido(senha);

                return senha;
            }catch (ValidationException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static String pegarEmail() {
        while(true) {
            try {
                String email = lerString("Digite o email (Ex: user@gmail.com): ");
                Validador.isEmailValido(email);

                return email;
            }catch (ValidationException e) {
                System.out.println(e.getMessage());
            }
        }
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
       return lerInt(">> Escolha uma opção: ");
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
