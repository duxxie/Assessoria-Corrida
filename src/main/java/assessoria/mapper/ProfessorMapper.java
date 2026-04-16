package assessoria.mapper;

import assessoria.model.dto.DadosCadastroPessoa;
import assessoria.model.entidades.Professor;

public class ProfessorMapper {

    public static Professor toEntity(DadosCadastroPessoa dadosCadastroPessoa, String cref, String idGerado) {
        return new Professor.Builder()
                .id(idGerado)
                .nome(dadosCadastroPessoa.getNome())
                .email(dadosCadastroPessoa.getEmail())
                .cpf(dadosCadastroPessoa.getCpf())
                .idade(dadosCadastroPessoa.getIdade())
                .telefone(dadosCadastroPessoa.getTelefone())
                .hashProvider(dadosCadastroPessoa.getHashProvider())
                .contatoEmergencia(dadosCadastroPessoa.getContatoEmergencia())
                .infoMedica(dadosCadastroPessoa.getInfoMedica())
                .build(cref);
    }
}
