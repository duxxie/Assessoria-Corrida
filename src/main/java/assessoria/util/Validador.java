package assessoria.util;

import assessoria.model.Usuario;

import java.util.Map;

public class Validador {

    public static boolean isCpfValido(String cpf) {

        cpf = cpf.replaceAll("[^0-9]", "");

        if(cpf.length() != 11) {
            throw new IllegalArgumentException("⚠️ --> [O cpf deve conter 11 dígitos numéricos!!]");
        }

        // Verificando se todos o números digitados são iguais
        if(cpf.matches("(\\d)\\1{10}")) {
            throw new IllegalArgumentException("⚠️ --> [O cpf com todos os números iguais não é válido!!]");
        }

        // Validação do primeiro digito verificador
        int soma = 0;
        for (int i = 0; i < cpf.length()-2; i++) {
            soma += Character.getNumericValue(cpf.charAt(i)) * (10 - i);
        }

        int primeiroDigito = 11 - (soma % 11);
        if(primeiroDigito > 9) primeiroDigito = 0;

        if(Character.getNumericValue(cpf.charAt(9)) != primeiroDigito) {
            throw new IllegalArgumentException("⚠️ --> [Formato de cpf inválido!!]");
        }

        // Validação do segundo digito verificador
        soma = 0;
        for (int i = 0; i < cpf.length() -1; i++) {
            soma += Character.getNumericValue(cpf.charAt(i)) * (11 - i);
        }

        int segundoDigito = 11 - (soma % 11);
        if(segundoDigito > 9) segundoDigito = 0;

        return Character.getNumericValue(cpf.charAt(10)) == segundoDigito;

    }

    public static boolean isEmailValido(String email) {
        if(!email.matches("^[a-z._0-9+-]+@[a-z]{3,}+\\.[a-z]{2,}$")) {
            throw new IllegalArgumentException("⚠️ --> [Formato de email inválido!!]");
        }else {
            return true;
        }
    }

    public static boolean isSenhaValido(String senha) {
        if(!senha.matches("^(?=.*[A-Z])(?=.*\\d).{8,}$")) {
            throw new IllegalArgumentException("⚠️ --> [Formato de senha inválido!!]");
        } else {
            return true;
        }
    }

    public static  <type extends Usuario> type isDadosLoginValido(String emailFornecido, String senhaFornecida, Map<String, type> map) {
        BCryptHash bCryptHash = new BCryptHash();

        for(Map.Entry<String, type> entry : map.entrySet()) {
            if(emailFornecido.equals(entry.getValue().getEmail()) && bCryptHash.verificarHash(senhaFornecida, entry.getValue().getHashProvider())) {
                return entry.getValue();
            }
        }
        throw new IllegalArgumentException("Email ou senha inválidos!!");
    }

}
