package com.fipe.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class VeiculoFipe {

    @JsonProperty("Valor")
    @SerializedName("Valor")
    private String valor;
    @JsonProperty("Marca")
    @SerializedName("Marca")
    private String marca;
    @JsonProperty("Modelo")
    @SerializedName("Modelo")
    private String modelo;
    @JsonProperty("AnoModelo")
    @SerializedName("AnoModelo")
    private String anoModelo;
    @JsonProperty("Combustivel")
    @SerializedName("Combustivel")
    private String combustivel;
    @JsonProperty("CodigoFipe")
    @SerializedName("CodigoFipe")
    private String codigoFipe;
    @JsonProperty("MesReferencia")
    @SerializedName("MesReferencia")
    private String mesReferencia;
    @JsonProperty("Autenticacao")
    @SerializedName("Autenticacao")
    private String autenticacao;
    @JsonProperty("TipoVeiculo")
    @SerializedName("TipoVeiculo")
    private String tipoVeiculo;
    @JsonProperty("SiglaCombustivel")
    @SerializedName("SiglaCombustivel")
    private String siglaCombustivel;
    @JsonProperty("DataConsulta")
    @SerializedName("DataConsulta")
    private String dataConsulta;

}
