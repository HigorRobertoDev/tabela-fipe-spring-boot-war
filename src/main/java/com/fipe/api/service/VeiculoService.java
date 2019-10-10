package com.fipe.api.service;

import com.fipe.api.model.ParamFipe;
import com.fipe.api.model.VeiculoFipe;
import com.google.gson.*;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class VeiculoService {

    public VeiculoFipe getVeiculoFipe(int anoModelo, String codFipe) {

        ParamFipe paramFipe = ParamFipe.builder()
                .codigoTabelaReferencia(247)
                .codigoMarca("")
                .codigoModelo("")
                .codigoTipoVeiculo(1)
                .anoModelo(anoModelo)
                .codigoTipoCombustivel(1)
                .tipoVeiculo("carro")
                .modeloCodigoExterno(codFipe)
                .tipoConsulta("codigo")
                .build();

        String paramFipeJson = new Gson().toJson(paramFipe);
        Client client = Client.create();
        WebResource webResource = client
                .resource("https://veiculos.fipe.org.br/api/veiculos/ConsultarValorComTodosParametros");

        ClientResponse response = webResource
                .type("application/json")
                .header("Accept", "application/json, text/javascript, */*; q=0.01")
                .header("Accept-Encoding", "gzip, deflate, br")
                .header("Accept-Language", "pt-BR,pt;q=0.9,en-US;q=0.8,en;q=0.7")
                .header("Connection" , "keep-alive")
                .header("Host", "veiculos.fipe.org.br")
                .header("Origin", "https://veiculos.fipe.org.br")
                .header("Referer", "https://veiculos.fipe.org.br/")
                .header("Sec-Fetch-Mode", "cors")
                .header("Sec-Fetch-Site", "same-origin")
                .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/77.0.3865.90 Safari/537.36")
                .header("X-Requested-With", "XMLHttpRequest")
                .post(ClientResponse.class, paramFipeJson);

        if (response.getStatus() != 200) {
            throw new RuntimeException("Failed : Http error code: "
                + response.getStatus());
        }

        String output = response.getEntity(String.class);
        Gson gson = new GsonBuilder().serializeNulls().create();
        VeiculoFipe veiculoFipe = gson.fromJson(output, VeiculoFipe.class);

        return veiculoFipe;
    }

}
