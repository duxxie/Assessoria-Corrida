package assessoria.view;

import assessoria.model.entidades.Treino;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TreinoDashBoard {

    public static void mostrarAtividades(Treino treino) {

        String[] headers = {"Segunda", "Terça", "Quarta", "Quinta", "Sexta", "Sábado", "Domingo"};

        /*
            Estrutura tabela

                                    + ------------- +
                                    |  Nome treino  |
            + ------------------------------------------------------------ +
            | Segunda | Terça | Quarta | Quinta | Sexta | Sábado | Domingo |
            + ------------------------------------------------------------ +
            | treino  |treino | treino | treino | treino| treino | treino  |
            |--------------------------------------------------------------|
            | treino  |treino | treino | treino | treino| treino | treino  |
            |--------------------------------------------------------------|
            | treino  |treino | treino | treino | treino| treino | treino  |
            + ------------------------------------------------------------ +

            - Cada coluna de dia da semana tera o mesmo tamanho de comprimento
            - O valor do comprimento da coluna sera definido da seguinte forma:
                - O map de plano semanal do treino sera percorrido
                - Para cada dia da semana (key) sera percorrido a list de treino
                - Para cada treino na list sera calculado o treino que tiver o maior comprimento de string
                - Resultara nos valores das maiores strings de cada dia da semana
                - Desses valores sera pego o maior valor
                - Definindo assim o valor padrao do comprimento de cada coluna

        * */


        var planoSemanal = treino.getPlanoSemanal();
        List<Integer> maioresComprimentoTreinos = new ArrayList<>();

        for(var entry : planoSemanal.entrySet()) {
            var maiorLengh = entry.getValue().stream()
                    .mapToInt(plano -> plano.length())
                    .max().orElse(0);

            maioresComprimentoTreinos.add(maiorLengh);
        }


    }
//
//    // Método para centralizar o texto dentro de um campo
//    public static String center(String text, int width) {
//        int padding = width - text.length();
//        int padStart = padding / 2;
//        int padEnd = padding - padStart;
//        return " ".repeat(padStart) + text + " ".repeat(padEnd);
//    }

}
