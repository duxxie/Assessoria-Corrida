package assessoria.mapper;

import assessoria.model.dto.AlunoBase;
import assessoria.model.dto.DadosCadastroPessoa;
import assessoria.model.entidades.Aluno;
import assessoria.util.helpers.Formatador;

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

    public static AlunoBase toBase(Aluno aluno) {
        return new AlunoBase(
                aluno.getId(),
                aluno.getNome(),
                aluno.getEmail(),
                Formatador.formatarCpf(aluno.getCpf()),
                aluno.getIdade(),
                Formatador.formatarTelefone(aluno.getTelefone())
        );
    }
}
