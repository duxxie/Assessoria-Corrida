package assessoria.view;

import assessoria.model.dto.AdministradorDetalhado;
import assessoria.model.dto.DadosAtualizacaoPessoa;
import assessoria.model.entidades.Administrador;

import java.util.*;
import java.util.stream.Collectors;

public class AdministradorDashBoard {

    public static void mostrarTabela(List<AdministradorDetalhado> administradorDetalhadoList) {

        String[] headers = {"Id", "Nome", "Cpf", "Email", "Idade", "Telefone", "CodigoAdministrador", "AdminRaiz", "CodigoAtivo", "CogidoUsado"};

        int maiorLengthId = headers[0].length();
        int maiorLengthNome = headers[1].length();
        int maiorLengthCpf = headers[2].length();
        int maiorLengthEmail = headers[3].length();
        int maiorLengthIdade = headers[4].length();
        int maiorLengthTelefone = headers[5].length();
        int maiorLengthCodigoAdmin = headers[6].length();
        int maiorLengthAdminRaiz = headers[7].length();
        int maiorLengthCodigoAtivo = headers[8].length();
        int maiorLengthCodigoUsado = headers[9].length();

        for(AdministradorDetalhado administradorDetalhado : administradorDetalhadoList) {
            maiorLengthId = Math.max(maiorLengthId, administradorDetalhado.id().length());
            maiorLengthNome = Math.max(maiorLengthNome, administradorDetalhado.nome().length());
            maiorLengthCpf = Math.max(maiorLengthCpf, administradorDetalhado.cpf().length());
            maiorLengthEmail = Math.max(maiorLengthEmail, administradorDetalhado.email().length());
            maiorLengthIdade = Math.max(maiorLengthIdade, String.valueOf(administradorDetalhado.idade()).length());
            maiorLengthTelefone = Math.max(maiorLengthTelefone, administradorDetalhado.telefone().length());
            maiorLengthCodigoAdmin = Math.max(maiorLengthCodigoAdmin, administradorDetalhado.idCodigoAdministrador().length());
            maiorLengthAdminRaiz = Math.max(maiorLengthAdminRaiz, String.valueOf(administradorDetalhado.adminRaiz()).length());
            maiorLengthCodigoAtivo = Math.max(maiorLengthCodigoAtivo, String.valueOf(administradorDetalhado.codigoAtivo()).length());
            maiorLengthCodigoUsado = Math.max(maiorLengthCodigoUsado, String.valueOf(administradorDetalhado.codigoUsado()).length());
        }

        int widthId = maiorLengthId + 6;
        int widthNome = maiorLengthNome + 6;
        int widthCpf = maiorLengthCpf + 6;
        int widthEmail = maiorLengthEmail + 6;
        int widthIdade = maiorLengthIdade + 6;
        int widthTelefone = maiorLengthTelefone + 6;
        int widthCodigoAdmin = maiorLengthCodigoAdmin + 6;
        int widthAdminRaiz = maiorLengthAdminRaiz + 6;
        int widthCodigoAtivo = maiorLengthCodigoAtivo + 6;
        int widthCodigoUsado = maiorLengthCodigoUsado + 6;

        String linhaTituloAdministrador = "|" + campoFormatado(widthId, headers[0]) +
                "|" + campoFormatado(widthNome, headers[1]) +
                "|" + campoFormatado(widthCpf, headers[2]) +
                "|" + campoFormatado(widthEmail, headers[3]) +
                "|" + campoFormatado(widthIdade, headers[4]) +
                "|" + campoFormatado(widthTelefone, headers[5]) +
                "|" + campoFormatado(widthCodigoAdmin, headers[6]) +
                "|" + campoFormatado(widthAdminRaiz, headers[7]) +
                "|" + campoFormatado(widthCodigoAtivo, headers[8]) +
                "|" + campoFormatado(widthCodigoUsado, headers[9]) +
                "|";

        String tituloTabela = "DASHBOARD ADMINISTRADOR";
        int marginTitulo = linhaTituloAdministrador.length() - (tituloTabela.length() + 6);
        int paddingTituloTabela = 6;
        int marginTituloTabela = marginTitulo/2;
        int bordasLateraisTituloTabela = 2;
        String linhaTituloTabela = " ".repeat(marginTituloTabela-2) + "|" + " ".repeat(paddingTituloTabela) + tituloTabela + " ".repeat(paddingTituloTabela) + "|" + " ".repeat(marginTituloTabela-2);
        String bordaTopTituloTabela = " ".repeat(marginTituloTabela-2) + "+ " + "-".repeat((tituloTabela.length() + (paddingTituloTabela*2) + bordasLateraisTituloTabela) - 4) + " +";

        String bordaTabela = "+" + "-".repeat(widthId)
                + "+" + "-".repeat(widthNome)
                + "+" + "-".repeat(widthCpf)
                + "+" + "-".repeat(widthEmail)
                + "+" + "-".repeat(widthIdade)
                + "+" + "-".repeat(widthTelefone)
                + "+" + "-".repeat(widthCodigoAdmin)
                + "+" + "-".repeat(widthAdminRaiz)
                + "+" + "-".repeat(widthCodigoAtivo)
                + "+" + "-".repeat(widthCodigoUsado)
                + "+";

        System.out.println(bordaTopTituloTabela);
        System.out.println(linhaTituloTabela);
        System.out.println(bordaTopTituloTabela);
        System.out.println(bordaTabela);
        System.out.println(linhaTituloAdministrador);
        System.out.println(bordaTabela);

        administradorDetalhadoList.stream()
                .forEach(administradorDetalhado -> System.out.println(
                        formatarCampoDadosAdministrador(
                                widthId,
                                widthNome,
                                widthCpf,
                                widthEmail,
                                widthIdade,
                                widthTelefone,
                                widthCodigoAdmin,
                                widthAdminRaiz,
                                widthCodigoAtivo,
                                widthCodigoUsado,
                                administradorDetalhado)));

        System.out.println(bordaTabela);
    }

    private static String campoFormatado(int widthCampo, String valorCampo) {

        int valorPadding = widthCampo - valorCampo.length();
        String padding = " ";

        return padding + valorCampo + padding.repeat(valorPadding - 1);
    }

    private static String formatarCampoDadosAdministrador(int widthId, int widthNome, int widthCpf, int widthEmail, int widthIdade, int widthTelefone, int widthCodigoAdmin, int widthAdminRaiz, int widthCodigoAtivo, int widthCodigoUsado, AdministradorDetalhado administradorDetalhado) {
        return "|" + campoFormatado(widthId, administradorDetalhado.id())
                + "|" + campoFormatado(widthNome, administradorDetalhado.nome())
                + "|" + campoFormatado(widthCpf, administradorDetalhado.cpf())
                + "|" + campoFormatado(widthEmail, administradorDetalhado.email())
                + "|" + campoFormatado(widthIdade, String.valueOf(administradorDetalhado.idade()))
                + "|" + campoFormatado(widthTelefone, administradorDetalhado.telefone())
                + "|" + campoFormatado(widthCodigoAdmin, administradorDetalhado.idCodigoAdministrador())
                + "|" + campoFormatado(widthAdminRaiz, String.valueOf(administradorDetalhado.adminRaiz()))
                + "|" + campoFormatado(widthCodigoAtivo, String.valueOf(administradorDetalhado.codigoAtivo()))
                + "|" + campoFormatado(widthCodigoUsado, String.valueOf(administradorDetalhado.codigoUsado()))
                + "|";
    }

    public static void mostrarDadosAdminUpdate(DadosAtualizacaoPessoa dadosAtualizacaoPessoa) {

        Map<String, String> campos = new LinkedHashMap<>();
        campos.put("Nome", dadosAtualizacaoPessoa.getNome());
        campos.put("Email", dadosAtualizacaoPessoa.getEmail());
        campos.put("Cpf", dadosAtualizacaoPessoa.getCpf());
        campos.put("Telefone", dadosAtualizacaoPessoa.getTelefone());

        int widthPadraoValores = campos.values().stream()
                .map(valor -> valor == null ? "" : valor)
                .mapToInt(valor -> valor.length() + 6)
                .max()
                .orElse(0);

        int widthPadraoCampo = campos.keySet().stream()
                .mapToInt(campo -> campo.length() + 6)
                .max()
                .orElse(0);

        List<String> arrayCamposValores = new ArrayList<>();

        for (Map.Entry<String, String> entry : campos.entrySet()) {
            String valor = entry.getValue() == null ? "" : entry.getValue();
            arrayCamposValores.add(
                    formatarCamposDadosAdminExibicao(entry.getKey(), valor, widthPadraoCampo, widthPadraoValores)
            );
        }

        String primeiraLinha = arrayCamposValores.getFirst();
        String bordaTabela = "+" + "-".repeat(primeiraLinha.length() - 2) + "+";

        String titulo = "Alterar meus dados";
        int larguraInterna = primeiraLinha.length() - 2;
        int espacosTotais = larguraInterna - titulo.length();
        int paddingEsquerda = espacosTotais / 2;
        int paddingDireita = espacosTotais - paddingEsquerda;

        String linhaTitulo = "|" + " ".repeat(paddingEsquerda) + titulo + " ".repeat(paddingDireita) + "|";

        System.out.println("\n\n");
        System.out.println(bordaTabela);
        System.out.println(linhaTitulo);
        System.out.println(bordaTabela);
        arrayCamposValores.forEach(System.out::println);
        System.out.println(bordaTabela);
    }

    public static void mostrarDadosAdmin(AdministradorDetalhado administradorDetalhado) {
        Map<String, String> campos = new LinkedHashMap<>();
        campos.put("Nome", administradorDetalhado.nome());
        campos.put("Email", administradorDetalhado.email());
        campos.put("Cpf", administradorDetalhado.cpf());
        campos.put("Idade", String.valueOf(administradorDetalhado.idade()));
        campos.put("Telefone", administradorDetalhado.telefone());
        campos.put("IdCodigoAdministrador", administradorDetalhado.idCodigoAdministrador());
        campos.put("AdminRaiz", String.valueOf(administradorDetalhado.adminRaiz()));
        campos.put("CodigoAtivo", String.valueOf(administradorDetalhado.codigoAtivo()));
        campos.put("CodigoUsado", String.valueOf(administradorDetalhado.codigoUsado()));

        int widthPadraoValores = campos.values().stream()
                .map(valor -> valor == null ? "" : valor)
                .mapToInt(valor -> valor.length() + 6)
                .max()
                .orElse(0);

        int widthPadraoCampo = campos.keySet().stream()
                .mapToInt(campo -> campo.length() + 6)
                .max()
                .orElse(0);


        List<String> arrayCamposValores = new ArrayList<>();

        for (Map.Entry<String, String> entry : campos.entrySet()) {
            String valor = entry.getValue() == null ? "" : entry.getValue();
            arrayCamposValores.add(
                    formatarCamposDadosAdminExibicao(entry.getKey(), valor, widthPadraoCampo, widthPadraoValores)
            );
        }

        String primeiraLinha = arrayCamposValores.getFirst();
        String bordaTabela = "+" + "-".repeat(primeiraLinha.length() - 2) + "+";

        String titulo = "Meus dados";
        int larguraInterna = primeiraLinha.length() - 2;
        int espacosTotais = larguraInterna - titulo.length();
        int paddingEsquerda = espacosTotais / 2;
        int paddingDireita = espacosTotais - paddingEsquerda;

        String linhaTitulo = "|" + " ".repeat(paddingEsquerda) + titulo + " ".repeat(paddingDireita) + "|";

        System.out.println("\n\n");
        System.out.println(bordaTabela);
        System.out.println(linhaTitulo);
        System.out.println(bordaTabela);
        arrayCamposValores.forEach(System.out::println);
        System.out.println(bordaTabela);

    }

    private static String formatarCamposDadosAdminExibicao(String nomeCampo, String valor, int widthPadraoCampo, int widthPadraoValor) {
        return "|" + campoFormatado(widthPadraoCampo, nomeCampo)
                + "|" + campoFormatado(widthPadraoValor, valor)
                + "|";
    }

}
