package assessoria.controller;

import assessoria.dao.ProfessorDAO;
import assessoria.model.Professor;
import assessoria.view.ProfessorView;
import assessoria.view.MensagemView;

import java.util.LinkedHashMap;
import java.util.Map;

public class ProfessorController extends PessoaController{

    private final Map<String, Professor> mapProfessor = new LinkedHashMap<>();
    private final ProfessorDAO professorDAO = new ProfessorDAO();
    private final MensagemView mensagemView = new MensagemView();

    private Professor criarProfessor(int id) {
        String nome = pegarNome();
        String email = pegarEmail();
        String cpf = pegarCpf();
        int idade = pegarIdade();
        String telefone = pegarTelefone();
        return new Professor(id, nome, email, cpf, idade, telefone);
    }

    public void adicionarProfessor() {
        int idProfessor = pegarMapProfessor().size() + 1;
        ProfessorView.mostrarMenuCadastrarProfessor();
        mapProfessor.put("K" + idProfessor, criarProfessor(idProfessor));
        professorDAO.inserirProfessorNoArquivo(pegarMapProfessor());
        mensagemView.mostrarSucesso("Professor adicionado!!");
    }

    public Map<String, Professor> pegarMapProfessor() {
        return new LinkedHashMap<>(mapProfessor);
    }

    private int pegarId() {
        int id = 0;
        for(Map.Entry<String,Professor> entry : mapProfessor.entrySet()) {
            id = entry.getValue().getId();
        }
        return id;
    }

}
