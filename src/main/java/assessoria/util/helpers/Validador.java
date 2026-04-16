package assessoria.util.helpers;

import assessoria.exceptions.ValidationException;
import assessoria.model.entidades.Aluno;
import assessoria.model.entidades.Usuario;

import java.util.Map;

public class Validador {

    public static void isCpfValido(String cpf) {

        if(cpf.length() != 11) {
            throw new ValidationException("⚠️ --> [O cpf deve conter 11 dígitos numéricos!!]");
        }

        // Verificando se todos o números digitados são iguais
        if(cpf.matches("(\\d)\\1{10}")) {
            throw new ValidationException("⚠️ --> [O cpf com todos os números iguais não é válido!!]");
        }

        // Validação do primeiro digito verificador
        int soma = 0;
        for (int i = 0; i < cpf.length()-2; i++) {
            soma += Character.getNumericValue(cpf.charAt(i)) * (10 - i);
        }

        int primeiroDigito = 11 - (soma % 11);
        if(primeiroDigito > 9) primeiroDigito = 0;

        if(Character.getNumericValue(cpf.charAt(9)) != primeiroDigito) {
            throw new ValidationException("⚠️ --> [Formato de cpf inválido!!]");
        }

        // Validação do segundo digito verificador
        soma = 0;
        for (int i = 0; i < cpf.length() -1; i++) {
            soma += Character.getNumericValue(cpf.charAt(i)) * (11 - i);
        }

        int segundoDigito = 11 - (soma % 11);
        if(segundoDigito > 9) segundoDigito = 0;

        if(Character.getNumericValue(cpf.charAt(10)) != segundoDigito)
            throw new ValidationException("⚠️ --> [Formato de cpf inválido!!]");

    }

    public static void isCrefValido(String crefInformado) {

        //if(crefInformado.length() != 9) throw new ValidationException("Formato do cref inválido!");

        if(!crefInformado.matches("\\d{6}[GP][A-Z]{2}"))
            throw new ValidationException("Formato do cref inválido!");
    }

    public static void isEmailValido(String email) {
        if(!email.matches("^[a-z._0-9+-]+@[a-z]{3,}+\\.[a-z]{2,}$"))
            throw new ValidationException("⚠️ --> [Formato de email inválido!!]");

    }

    public static void isSenhaValido(String senha) {
        if(!senha.matches("^(?=.*[A-Z])(?=.*\\d).{8,}$"))
            throw new ValidationException("⚠️ --> [Formato de senha inválido!!]");
    }

    public static void isTelefoneValido(String telefone) {
        if(telefone.length() != 11)
            throw new ValidationException("Falha ao validar telefone | Motivo: tamanho inválido!");

        if(!telefone.matches("^[0-9]{2}9[0-9]{8}$"))
            throw new ValidationException("Falha ao validar telefone | Motivo: formato inválido!");
    }

    public static  <type extends Usuario> type isDadosLoginValido(String emailFornecido, String senhaFornecida, Map<String, type> map) {
        BCryptHash bCryptHash = new BCryptHash();

        for(Map.Entry<String, type> entry : map.entrySet()) {
            if(emailFornecido.equals(entry.getValue().getEmail()) && bCryptHash.verificarHash(senhaFornecida, entry.getValue().getHashProvider())) {
                return entry.getValue();
            }
        }
        throw new ValidationException("Falha ao validar cadastro | Motivo: email ou senha inválidos!!");
    }

    public static Aluno isCpfExiste(String cpf, Map<String, Aluno> map) {
        for(Map.Entry<String, Aluno> entry : map.entrySet()) {
            if(cpf.equals(entry.getValue().getCpf())) {
                return entry.getValue();
            }
        }
        return null;
    }

}
