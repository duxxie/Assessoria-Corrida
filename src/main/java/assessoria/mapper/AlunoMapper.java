package assessoria.mapper;

import assessoria.model.dto.DadosCadastroPessoa;
import assessoria.model.entidades.Aluno;

public class AlunoMapper {

    public static Aluno toEntity(DadosCadastroPessoa dadosCadastroPessoa, String idGerado) {
        return new Aluno.Builder()
                .id(idGerado)
                .nome(dadosCadastroPessoa.getNome())
                .email(dadosCadastroPessoa.getEmail())
                .cpf(dadosCadastroPessoa.getCpf())
                .idade(dadosCadastroPessoa.getIdade())
                .telefone(dadosCadastroPessoa.getTelefone())
                .hashProvider(dadosCadastroPessoa.getHashProvider())
                .contatoEmergencia(dadosCadastroPessoa.getContatoEmergencia())
                .infoMedica(dadosCadastroPessoa.getInfoMedica())
                .build();
    }
}
