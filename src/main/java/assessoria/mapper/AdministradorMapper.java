package assessoria.mapper;

import assessoria.model.dto.AdministradorDetalhado;
import assessoria.model.dto.DadosAtualizacaoPessoa;
import assessoria.model.dto.DadosCadastroPessoa;
import assessoria.model.entidades.Administrador;
import assessoria.model.entidades.CodigoAdministrador;
import assessoria.util.helpers.Formatador;

public class AdministradorMapper {

    public static Administrador toEntity(DadosCadastroPessoa dadosCadastroPessoa, String idGerado, boolean adminRaiz, String codigoAdmin) {
        return new Administrador.Builder()
                .id(idGerado)
                .nome(dadosCadastroPessoa.getNome())
                .email(dadosCadastroPessoa.getEmail())
                .cpf(dadosCadastroPessoa.getCpf())
                .idade(dadosCadastroPessoa.getIdade())
                .telefone(dadosCadastroPessoa.getTelefone())
                .hashProvider(dadosCadastroPessoa.getHashProvider())
                .contatoEmergencia(dadosCadastroPessoa.getContatoEmergencia())
                .infoMedica(dadosCadastroPessoa.getInfoMedica())
                .build(codigoAdmin, adminRaiz);
    }

    public static AdministradorDetalhado toDetalhado(Administrador administrador, CodigoAdministrador codigoAdministrador) {
        return new AdministradorDetalhado(
                administrador.getId(),
                administrador.getNome(),
                administrador.getEmail(),
                Formatador.formatarCpf(administrador.getCpf()),
                administrador.getIdade(),
                Formatador.formatarTelefone(administrador.getTelefone()),
                administrador.getIdCodigoAdministrador(),
                administrador.isAdiminRaiz(),
                codigoAdministrador.isAtivo(),
                codigoAdministrador.isUsado()
        );
    }

    public static DadosAtualizacaoPessoa toDadosAtualizacao(Administrador administrador) {
        return new DadosAtualizacaoPessoa(
                administrador.getId(),
                administrador.getNome(),
                administrador.getEmail(),
                Formatador.formatarCpf(administrador.getCpf()),
                Formatador.formatarTelefone(administrador.getTelefone()),
                administrador.getHashProvider()
        );
    }
}
