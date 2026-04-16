package assessoria.util.helpers;

import assessoria.model.dto.DadosCadastroPessoa;
import assessoria.model.entidades.ContatoEmergencia;
import assessoria.model.entidades.InfoMedica;

public class CadastroViewHelper {

    public static DadosCadastroPessoa pegarDadosCadastroPessoa() {
        DadosCadastroPessoa dadosCadastroPessoa = new DadosCadastroPessoa();
        BCryptHash bCryptHash = new BCryptHash();

        //Dados primários
        dadosCadastroPessoa.setNome(InputHelper.pegarNome());
        dadosCadastroPessoa.setCpf(InputHelper.pegarCpf());
        dadosCadastroPessoa.setIdade(InputHelper.pegarIdade());
        dadosCadastroPessoa.setTelefone(InputHelper.pegarTelefone());
        dadosCadastroPessoa.setEmail(InputHelper.pegarEmail());

        String senha = InputHelper.pegarSenhaToCadastro();
        dadosCadastroPessoa.setHashProvider(bCryptHash.gerarHash(senha));

        if(InputHelper.isAdicionarDadosInfoMedicaAgoraTrue()) {
            String condicaoMedica = InputHelper.pegarCondicaoMedica();
            String alergia = InputHelper.pegarAlergia();
            String medicamentoEmUso = InputHelper.pegarMedicamentoEmUso();
            String lesaoRecente = InputHelper.pegarLesao();
            String cirurgiaRecente = InputHelper.pegarCirurgiaRecente();
            String restricaoMedica = InputHelper.pegarRestricaoMedica();
            String tipoSanguineo = InputHelper.pegarTipoSanguineo();

            InfoMedica infoMedica = new InfoMedica(condicaoMedica, alergia, medicamentoEmUso, lesaoRecente, cirurgiaRecente, restricaoMedica, tipoSanguineo);

            dadosCadastroPessoa.setInfoMedica(infoMedica);
        }

        if(InputHelper.isAdicionarContatoEmergenciaAgoraTrue()) {
            System.out.println("\n >>> Informe os dados do seu contado de emergencia abaixo <<<");
            String nomeEmergencia = InputHelper.pegarNome();
            String telefoneEmergencia = InputHelper.pegarTelefone();
            String relacao = InputHelper.pegarRelacao();

            ContatoEmergencia contatoEmergencia = new ContatoEmergencia(nomeEmergencia, telefoneEmergencia, relacao);

            dadosCadastroPessoa.setContatoEmergencia(contatoEmergencia);
        }

        return dadosCadastroPessoa;
    }
}
