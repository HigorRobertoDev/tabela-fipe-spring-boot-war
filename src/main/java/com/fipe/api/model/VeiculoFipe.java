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
                    VeiculoFipe veiculoFipeAux = values.get(j);

                    values.get(j).setValor(values.get(j - 1).getValor());
                    values.get(j).setMarca(values.get(j - 1).getMarca());
                    values.get(j).setModelo(values.get(j - 1).getModelo());
                    values.get(j).setAnoModelo(values.get(j - 1).getAnoModelo());
                    values.get(j).setCombustivel(values.get(j - 1).getCombustivel());
                    values.get(j).setCodigoFipe(values.get(j - 1).getCodigoFipe());
                    values.get(j).setMesReferencia(values.get(j - 1).getMesReferencia());
                    values.get(j).setAutenticacao(values.get(j - 1).getAutenticacao());
                    values.get(j).setTipoVeiculo(values.get(j - 1).getTipoVeiculo());
                    values.get(j).setSiglaCombustivel(values.get(j - 1).getSiglaCombustivel());
                    values.get(j).setDataConsulta(values.get(j - 1).getDataConsulta());

                    values.get(j - 1).setValor(veiculoFipeAux.getValor());
                    values.get(j - 1).setMarca(veiculoFipeAux.getMarca());
                    values.get(j - 1).setModelo(veiculoFipeAux.getModelo());
                    values.get(j - 1).setAnoModelo(veiculoFipeAux.getAnoModelo());
                    values.get(j - 1).setCombustivel(veiculoFipeAux.getCombustivel());
                    values.get(j - 1).setCodigoFipe(veiculoFipeAux.getCodigoFipe());
                    values.get(j - 1).setMesReferencia(veiculoFipeAux.getMesReferencia());
                    values.get(j - 1).setAutenticacao(veiculoFipeAux.getAutenticacao());
                    values.get(j - 1).setTipoVeiculo(veiculoFipeAux.getTipoVeiculo());
                    values.get(j - 1).setSiglaCombustivel(veiculoFipeAux.getSiglaCombustivel());
                    values.get(j - 1).setDataConsulta(veiculoFipeAux.getDataConsulta());
                }
            }
        }
        return values;
    }

}
