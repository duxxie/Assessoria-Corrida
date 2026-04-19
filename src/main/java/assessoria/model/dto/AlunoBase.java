package assessoria.model.dto;

public record AlunoBase(
        String id,
        String nome,
        String email,
        String cpf,
        int idade,
        String telefone
) {
}
