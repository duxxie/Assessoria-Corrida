package assessoria.model.entidades;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class Treino implements Savable {
    private String id;
    private Aluno aluno;
    private Professor professor;
    private Map<DayOfWeek, List<String>> planoSemanal;

    public Treino(){};

    public Treino(String id, Aluno aluno, Professor professor) {
        this.id = id;
        this.aluno = aluno;
        this.professor = professor;
        this.planoSemanal = new EnumMap<>(DayOfWeek.class);
    }

    public String getId() {
        return id;
    }

    public Aluno getAluno() {
        return aluno;
    }

   public Professor getProfessor() {
        return professor;
   }

   public Map<DayOfWeek, List<String>> getPlanoSemanal() {
        return planoSemanal;
   }

    public void setId(String id) {
        this.id = id;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public void setPlanoSemanal(Map<DayOfWeek, List<String>> planoSemanal) {
        this.planoSemanal = planoSemanal;
    }

    public void adicionarAtividade(DayOfWeek dia, String atividade) {
        planoSemanal.computeIfAbsent(dia, k -> new ArrayList<>()).add(atividade);
    }

    public void adicionarAtividade(DayOfWeek dia, List<String> treinoDia) {
        planoSemanal.put(dia, treinoDia);
    }

    public void removerAtividade(DayOfWeek dia, String atividade) {
        List<String> list = planoSemanal.get(dia);
        if(list != null) {
            list.remove(atividade);
            if(list.isEmpty()) {
                planoSemanal.remove(dia);
            }
        }
    }

    public void removerDia(DayOfWeek dia) {
        planoSemanal.remove(dia);
    }

}
