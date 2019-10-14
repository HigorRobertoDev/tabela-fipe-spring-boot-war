package com.fipe.api.service;

import com.fipe.api.model.Marca;
import com.fipe.api.model.MesReferenciaFipe;
import com.fipe.api.model.ParamFipe;
import com.fipe.api.model.VeiculoFipe;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@Service
public class VeiculoServiceImpl implements VeiculoService {

    public Gson gson = new GsonBuilder().serializeNulls().create();

    @Override
    public List<MesReferenciaFipe> getMesReferenciaFipe() {
        String output = createClientResponse(
            "https://veiculos.fipe.org.br/api/veiculos/ConsultarTabelaDeReferencia",
            null
        );

        Type listType = new TypeToken<ArrayList<MesReferenciaFipe>>(){}.getType();
        return gson.fromJson(output, listType);
    }

    @Override
    public List<Marca> getMarcaByCodVeiculo(int codVeiculo) {
        List<MesReferenciaFipe> mesReferenciaFipeList = getMesReferenciaFipe();

        ParamFipe paramFipe = ParamFipe.builder()
                .codigoTabelaReferencia(mesReferenciaFipeList.get(0).getCodigo())
                .codigoTipoVeiculo(codVeiculo)
                .build();

        String output = this.createClientResponse(
                "https://veiculos.fipe.org.br/api/veiculos/ConsultarMarcas",
                paramFipe
        );
        Type listType = new TypeToken<ArrayList<Marca>>(){}.getType();
        return gson.fromJson(output, listType);
    }

    @Override
    public VeiculoFipe getVeiculoFipe(int anoModelo, String codFipe, int codigoTipoVeiculo, int codigoTipoCombustivel) {
        List<MesReferenciaFipe> referenciaFipeList = getMesReferenciaFipe();

        String tipoVeiculo = "carro";
        if (codigoTipoVeiculo == 2) tipoVeiculo = "moto";
        else if(codigoTipoVeiculo == 3) tipoVeiculo = "caminhao";

        ParamFipe paramFipe = ParamFipe.builder()
                .codigoTabelaReferencia(referenciaFipeList.get(0).getCodigo())
                .codigoMarca("")
                .codigoModelo("")
                .codigoTipoVeiculo(codigoTipoVeiculo)
                .anoModelo(anoModelo)
                .codigoTipoCombustivel(codigoTipoCombustivel)
                .tipoVeiculo(tipoVeiculo)
                .modeloCodigoExterno(codFipe)
                .tipoConsulta("codigo")
                .build();

        String output = createClientResponse(
                "https://veiculos.fipe.org.br/api/veiculos/ConsultarValorComTodosParametros",
                paramFipe
        );

        VeiculoFipe veiculoFipe = gson.fromJson(output, VeiculoFipe.class);
        return veiculoFipe;
    }

    @Override
    public String createClientResponse(String url, ParamFipe paramFipe) {
        String paramFipeJson = gson.toJson(paramFipe);
        Client client = Client.create();
        WebResource webResource = client
                .resource(url);

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

        return response.getEntity(String.class);
    }

}