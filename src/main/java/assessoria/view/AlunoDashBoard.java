package assessoria.view;

import assessoria.model.dto.AlunoBase;
import assessoria.model.entidades.Administrador;
import assessoria.model.entidades.Aluno;

import java.util.List;
import java.util.Map;

public class AlunoDashBoard {

    public void mostrarTabela(List<AlunoBase> alunoBaseList) {

        String[] headers = {"id", "Nome", "Cpf", "Email", "Idade", "Telefone"};

        int maxId = headers[0].length();
        int maxNome = headers[1].length();
        int maxCpf = headers[2].length();
        int maxEmail = headers[3].length();
        int maxIdade = headers[4].length();
        int maxTelefone = headers[5].length();

        for(AlunoBase alunoBase : alunoBaseList) {
            maxId = Math.max(maxId, alunoBase.id().length());
            maxNome = Math.max(maxNome, alunoBase.nome().length());
            maxCpf = Math.max(maxCpf, alunoBase.cpf().length());
            maxEmail = Math.max(maxEmail, alunoBase.email().length());
            maxIdade = Math.max(maxIdade, String.valueOf(alunoBase.idade()).length());
            maxTelefone = Math.max(maxTelefone, alunoBase.telefone().length());
        }

        int widthId = maxId + 6;
        int widthNome = maxNome + 6;
        int widthCpf = maxCpf + 6;
        int widthEmail = maxEmail + 6;
        int widthIdade = maxIdade + 6;
        int widthTelefone = maxTelefone + 6;

        String linhaTitulo = "|" + campoFormatado(widthId, headers[0]) +
                "|" + campoFormatado(widthNome, headers[1]) +
                "|" + campoFormatado(widthCpf, headers[2]) +
                "|" + campoFormatado(widthEmail, headers[3]) +
                "|" + campoFormatado(widthIdade, headers[4]) +
                "|" + campoFormatado(widthTelefone, headers[5]) +
                "|";

        String tituloTabela = "DASHBOARD ALUNO";
        int marginTitulo = linhaTitulo.length() - (tituloTabela.length() + 6);
        int paddingTituloTabela = 6;
        int marginTituloTabela = marginTitulo/2;
        int bordasLateraisTituloTabela = 2;

        String linhaTituloTabela = " ".repeat(marginTituloTabela-2) + "|" + " ".repeat(paddingTituloTabela) + tituloTabela + " ".repeat(paddingTituloTabela) + "|" + " ".repeat(marginTituloTabela-2);
        String bordaTopTituloTabela = " ".repeat(marginTituloTabela-2) + "+ " + "-".repeat((tituloTabela.length() + (paddingTituloTabela*2) + bordasLateraisTituloTabela) - 4) + " +";

        String bordaTabela = "+" + "-".repeat(widthId)
                + "+" + "-".repeat(widthNome)
                + "+" + "-".repeat(widthCpf)
                + "+" + "-".repeat(widthEmail)
                + "+" + "-".repeat(widthIdade)
                + "+" + "-".repeat(widthTelefone)
                + "+";
        System.out.println("\n\n\n");
        System.out.println(bordaTopTituloTabela);
        System.out.println(linhaTituloTabela);
        System.out.println(bordaTopTituloTabela);
        System.out.println(bordaTabela);
        System.out.println(linhaTitulo);
        System.out.println(bordaTabela);

        // Dados
        alunoBaseList.stream().forEach(
                alunoBase -> System.out.println(
                        formatarCampoDadosAlunos(
                                widthId,
                                widthNome,
                                widthCpf,
                                widthEmail,
                                widthIdade,
                                widthTelefone,
                                alunoBase)
                )
        );
        System.out.println(bordaTabela);
    }

    public static String campoFormatado(int widthCampo, String valorCampo) {
        int valorPadding = widthCampo - valorCampo.length();
        String padding = " ";

        return padding + valorCampo + padding.repeat(valorPadding - 1);
    }

    private static String formatarCampoDadosAlunos(int widthId, int widthNome, int widthCpf, int widthEmail, int widthIdade, int widthTelefone, AlunoBase alunoBase) {
        return "|" + campoFormatado(widthId, alunoBase.id())
                + "|" + campoFormatado(widthNome, alunoBase.nome())
                + "|" + campoFormatado(widthCpf, alunoBase.cpf())
                + "|" + campoFormatado(widthEmail, alunoBase.email())
                + "|" + campoFormatado(widthIdade, String.valueOf(alunoBase.idade()))
                + "|" + campoFormatado(widthTelefone, alunoBase.telefone())
                + "|";
    }
}
