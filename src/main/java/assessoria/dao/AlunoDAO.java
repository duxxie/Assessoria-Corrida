package assessoria.dao;

import assessoria.model.Aluno;
import assessoria.view.MensagemView;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.opencsv.CSVWriter;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AlunoDAO {

    //private final String arquivoUsuarios = "src/main/java/assessoria/util/users/alunos.json";
    private final String arquivoUsuarios = "src/main/java/assessoria/util/treino/alunos.csv";

    public void inserirAlunoNoCsv(Map<String,Aluno> alunoMap) {
        try{
            MensagemView mensagemView = new MensagemView();
            FileWriter fileWriter = new FileWriter(arquivoUsuarios, StandardCharsets.ISO_8859_1);
            CSVWriter csvWriter = new CSVWriter(fileWriter, ';', CSVWriter.NO_QUOTE_CHARACTER, CSVWriter.DEFAULT_ESCAPE_CHARACTER, CSVWriter.DEFAULT_LINE_END);

            List<String[]> dados = new ArrayList<>();
            String[] header = {"Id", "Nome", "Email", "Cpf", "Idade", "Telefone"};
            dados.add(header);

            for(Map.Entry<String,Aluno> entry : alunoMap.entrySet()) {
                dados.add(new String[] {String.valueOf(entry.getValue().getId()), entry.getValue().getNome(), entry.getValue().getEmail(), entry.getValue().getCpf(), String.valueOf(entry.getValue().getIdade()), entry.getValue().getTelefone()});
            }

            csvWriter.writeAll(dados);
            csvWriter.close();
            fileWriter.close();

            mensagemView.mostrarSucesso("Arquivo escrito com sucesso!!");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
//    public void inserirAlunoNoArquivo(Map<String, Aluno> alunoMap) {
//        ObjectMapper objectMapper = new ObjectMapper();
//        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
//        try{
//            objectMapper.writeValue(new File(arquivoUsuarios), alunoMap);
//            System.out.println("Arquivo escrito com sucesso");
//        } catch (IOException e) {
//            System.out.println("Erro ao escrever no arquivo");
//            System.out.println(e.getMessage());
//        }
//    }

}
