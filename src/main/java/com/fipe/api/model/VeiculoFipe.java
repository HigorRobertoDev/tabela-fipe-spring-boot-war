package com.fipe.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

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

    public static List<VeiculoFipe> bubbleSorte(List<VeiculoFipe> values) {
        for (int i = values.size(); i >= 1; i--) {
            for (int j = 1; j < i; j++) {
                if (Integer.parseInt(values.get(j - 1).getValor().replaceAll("[^0-9]", "")) > Integer.parseInt(values.get(j).getValor().replaceAll("[^0-9]", ""))) {
                    String aux = values.get(j).getValor();
                    values.get(j).setValor(values.get(j - 1).getValor());
                    values.get(j - 1).setValor(aux);
                }
            }
        }
        return values;
    }

}
