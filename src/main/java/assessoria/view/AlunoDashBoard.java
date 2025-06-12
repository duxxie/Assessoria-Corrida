package assessoria.view;

import assessoria.model.entidades.Aluno;

import java.util.Map;

public class AlunoDashBoard {

    public void mostrarTabela(Map<String, Aluno> typeMap) {

        String[] headers = {"id", "Nome", "Cpf", "Email", "Idade", "Telefone"};

        int maxId = headers[0].length();
        int maxNome = headers[1].length();
        int maxCpf = headers[2].length();
        int maxEmail = headers[3].length();
        int maxIdade = headers[4].length();
        int maxTelefone = headers[5].length();

        for(Map.Entry<String, Aluno> entry : typeMap.entrySet()) {
            maxId = Math.max(maxId, String.valueOf(entry.getValue().getIdade()).length());
            maxNome = Math.max(maxNome, entry.getValue().getNome().length());
            maxCpf = Math.max(maxCpf, entry.getValue().getCpf().length());
            maxEmail = Math.max(maxEmail, entry.getValue().getEmail().length());
            maxIdade = Math.max(maxIdade, String.valueOf(entry.getValue().getIdade()).length());
            maxTelefone = Math.max(maxTelefone, entry.getValue().getTelefone().length());
        }

        int larguraId = maxId + 4;
        int larguraNome = maxNome + 4;
        int larguraCpf = maxCpf + 4;
        int larguraEmail = maxEmail + 4;
        int larguraIdade = maxIdade + 4;
        int larguraTelefone = maxTelefone + 4;

        // Linha separadora
        int totalLargura = larguraId + larguraNome + larguraCpf + larguraEmail + larguraIdade + larguraTelefone + 7; // 7 separadores "|"


        System.out.println("-".repeat(totalLargura));

        String titulo =  "|" + center("ALUNOS CADASTRADOS", totalLargura-2) + "|";

        System.out.println(titulo);

        System.out.println("-".repeat(totalLargura));

        // Imprime o cabeçalho
        String cabecalho = "|" + center("id", larguraId) + "|" + center("Nome", larguraNome) + "|" + center("Cpf", larguraCpf) + "|" +  center("Email", larguraEmail) + "|" + center("Idade", larguraIdade) + "|" + center("Telefone", larguraTelefone) + "|";
        System.out.println(cabecalho);

        System.out.println("-".repeat(totalLargura));

        // Dados
        for (Map.Entry<String,Aluno> entry : typeMap.entrySet()) {
            String linha = "|" + center(entry.getValue().getId(), larguraId) + "|" + center(entry.getValue().getNome(), larguraNome) + "|" + center(entry.getValue().getCpf(), larguraCpf) + "|" + center(entry.getValue().getEmail(), larguraEmail) + "|" + center(String.valueOf(entry.getValue().getIdade()), larguraIdade) + "|" + center(entry.getValue().getTelefone(), larguraTelefone) + "|";
            System.out.println(linha);
            System.out.println("-".repeat(totalLargura));
        }

    }

    // Método para centralizar o texto dentro de um campo
    public static String center(String text, int width) {
        int padding = width - text.length();
        int padStart = padding / 2;
        int padEnd = padding - padStart;
        return " ".repeat(padStart) + text + " ".repeat(padEnd);
    }
}
