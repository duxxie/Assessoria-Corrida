package assessoria.model.dto;

public record AdministradorDetalhado (
        String id,
        String nome,
        String email,
        String cpf,
        int idade,
        String telefone,
        String idCodigoAdministrador,
        boolean adminRaiz,
        boolean codigoAtivo,
        boolean codigoUsado
) {
}
