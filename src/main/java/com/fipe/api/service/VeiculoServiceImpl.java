package com.fipe.api.service;

import com.fipe.api.model.*;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
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
                .codigoModelo("")
                .codigoTipoVeiculo(codigoTipoVeiculo)
                .anoModelo(anoModelo)
                .codigoTipoCombustivel(String.valueOf(codigoTipoCombustivel))
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
    public Modelo getModeloByIdMarca(int idMarca, int codVeiculo) {
        List<MesReferenciaFipe> mesReferenciaFipeList = getMesReferenciaFipe();

        ParamFipe paramFipe = ParamFipe.builder()
                .codigoTipoVeiculo(codVeiculo)
                .codigoTabelaReferencia(mesReferenciaFipeList.get(0).getCodigo())
                .codigoMarca(idMarca)
                .build();

        String output = this.createClientResponse(
                "https://veiculos.fipe.org.br/api/veiculos/ConsultarModelos",
                paramFipe
        );

        Modelo modelo = gson.fromJson(output, Modelo.class);
        return modelo;
    }

    @Override
    public ModeloDetalhado getModeloDetalhadoByIdMarca(int idMarca, int codVeiculo) {
        Modelo modeloList = this.getModeloByIdMarca(idMarca, codVeiculo);
        List<MesReferenciaFipe> mesReferenciaFipeList = getMesReferenciaFipe();
        List<VeiculoFipe> veiculoFipeList = new ArrayList<>();

        modeloList.getModelos().stream()
                .forEach(marca -> {
                    List<Marca> anoModelo = this.consultarAnoModelo(codVeiculo, mesReferenciaFipeList.get(0).getCodigo(), marca.getValue(), idMarca);

                    List<String> anoVeiculoAndTipoCombustivel = Arrays.asList(anoModelo.get(0).getValue().split("-"));

                    String tipoVeiculo = "carro";
                    if (codVeiculo == 2) tipoVeiculo = "moto";
                    else if(codVeiculo == 3) tipoVeiculo = "caminhao";

                    ParamFipe paramFipe = ParamFipe.builder()
                            .codigoTabelaReferencia(mesReferenciaFipeList.get(0).getCodigo())
                            .codigoMarca(idMarca)
                            .codigoModelo(marca.getValue())
                            .codigoTipoVeiculo(codVeiculo)
                            .anoModelo(Integer.parseInt(anoVeiculoAndTipoCombustivel.get(0)))
                            .codigoTipoCombustivel(anoVeiculoAndTipoCombustivel.get(1))
                            .tipoVeiculo(tipoVeiculo)
                            .tipoConsulta("tradicional")
                            .build();

                    String output = this.createClientResponse(
                            "https://veiculos.fipe.org.br/api/veiculos/ConsultarValorComTodosParametros",
                            paramFipe
                    );

                    VeiculoFipe veiculoFipe = gson.fromJson(output, VeiculoFipe.class);
                    veiculoFipeList.add(veiculoFipe);
                });
        ModeloDetalhado modeloDetalhado = new ModeloDetalhado();
        modeloDetalhado.setModeloDetalhado(VeiculoFipe.bubbleSorte(veiculoFipeList));
        return modeloDetalhado;
    }

    @Override
    public List<Marca> consultarAnoModelo(int codigoTipoVeiculo, int codigoTabelaReferencia, String codigoModelo, int codigoMarca) {
        ParamFipe paramFipe = ParamFipe.builder()
                .codigoTipoVeiculo(codigoTipoVeiculo)
                .codigoTabelaReferencia(codigoTabelaReferencia)
                .codigoModelo(codigoModelo)
                .codigoMarca(codigoMarca)
                .build();

        String output = this.createClientResponse(
                "https://veiculos.fipe.org.br/api/veiculos/ConsultarAnoModelo",
                paramFipe
        );

        Type listType = new TypeToken<ArrayList<Marca>>(){}.getType();
        return gson.fromJson(output, listType);
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
