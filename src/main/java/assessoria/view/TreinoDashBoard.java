package assessoria.view;

import assessoria.model.entidades.Treino;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TreinoDashBoard {

    public static void mostrarPlanoSemanal(Treino treino) {

        int paddingLeftTitulo = 20;
        var planoSemanal = treino.getPlanoSemanal();
        String tituloTreino = treino.getDescricao();

        String linhaTituloTreino = "".repeat(paddingLeftTitulo) + "║" + " ".repeat(3) + tituloTreino + " ".repeat(3) + "║";

        int linhaBorder = linhaTituloTreino.length() - (paddingLeftTitulo + 2);

        String bordaTopTituloTreino = " ".repeat(paddingLeftTitulo) + "╔" + "═" .repeat(linhaBorder) + "╗";
        String bordaBottomTituloTreino = " ".repeat(paddingLeftTitulo) + "╚" + "═".repeat(linhaBorder) + "╝";

        System.out.println(bordaTopTituloTreino);
        System.out.println(linhaTituloTreino);
        System.out.println(bordaBottomTituloTreino);
        System.out.println("\n");

        for(var entry : planoSemanal.entrySet()) {
            formatarCampoDiaSemana(treino.traduzirDia(entry.getKey()), entry.getValue());
            System.out.println("\n");
        }
    }

    public static void formatarCampoDiaSemana(String diaSemana, List<String> treinos) {
        String linhaTopTabela = "╔"
                + "═".repeat(6)
                + " "
                + diaSemana
                + " "
                + "═".repeat(31)
                + " [ " + treinos.size() + (treinos.size() == 1 ? " Exercício" : " Exercícios")
                + " ] "
                + "═".repeat(6)
                + "╗";

        String linhaVazia = "║" + " ".repeat(linhaTopTabela.length() - 2) + "║";
        String bordaTabelaVazia = "╚" + "═".repeat(linhaTopTabela.length() - 2) + "╝";

        System.out.println(linhaTopTabela);
        System.out.println(linhaVazia);

        int marcadorLinha = 1;

        for(var treino : treinos) {
            var paddingLeftLinha = 3;
            var textoTreino = "0" + marcadorLinha++ + ". " + treino;
            String linha = "║"
                    + " ".repeat(paddingLeftLinha)
                    + textoTreino
                    + " ".repeat(linhaTopTabela.length() - (paddingLeftLinha + 2 + textoTreino.length()))
                    + "║";

            System.out.println(linha);
        }

        System.out.println(linhaVazia);
        System.out.println(bordaTabelaVazia);
    }

}
