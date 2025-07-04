package assessoria.model.dao;

import assessoria.model.entidades.Aluno;

import java.util.Map;

public class AlunoDAO extends GenericDAO<Aluno> {

    private final String caminhoArquivo = "users/alunos/alunos.json";

    public AlunoDAO() {
        super(Aluno.class);
    }

    @Override
    public void inserirDadosNoArquivo(Map<String, Aluno> alunoMap) {
        super.inserirDadosNoArquivo(alunoMap);
    }
    @Override
    public Map<String,Aluno> lerDadosDoArquivo() {
       return super.lerDadosDoArquivo();
    }

    @Override
    public String getCaminhoArquivo() {
        return caminhoArquivo;
    }

    //private final String arquivoUsuarios = "src/main/java/assessoria/util/treino/alunos.csv";

//    public void inserirAlunoNoCsv(Map<String,Aluno> alunoMap) {
//
//        try{
//            MensagemView mensagemView = new MensagemView();
//            FileWriter fileWriter = new FileWriter(arquivoUsuarios, StandardCharsets.ISO_8859_1);
//            CSVWriter csvWriter = new CSVWriter(fileWriter, ';', CSVWriter.NO_QUOTE_CHARACTER, CSVWriter.DEFAULT_ESCAPE_CHARACTER, CSVWriter.DEFAULT_LINE_END);
//
//            List<String[]> dados = new ArrayList<>();
//            String[] header = {"Id", "Nome", "Email", "Cpf", "Idade", "Telefone"};
//            dados.add(header);
//
//            for(Map.Entry<String,Aluno> entry : alunoMap.entrySet()) {
//                dados.add(new String[] {String.valueOf(entry.getValue().getId()), entry.getValue().getNome(), entry.getValue().getEmail(), entry.getValue().getCpf(), String.valueOf(entry.getValue().getIdade()), entry.getValue().getTelefone()});
//            }
//
//            csvWriter.writeAll(dados);
//            csvWriter.close();
//            fileWriter.close();
//
//            mensagemView.mostrarSucesso("Arquivo escrito com sucesso!!");
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
}

