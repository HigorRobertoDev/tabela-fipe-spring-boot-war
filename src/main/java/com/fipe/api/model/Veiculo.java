package com.fipe.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Veiculo {

    @JsonProperty("MesReferencia")
    private String mesReferencia;

    @JsonProperty("CodigoFipe")
    private String codigoFipe;

    @JsonProperty("Marca")
    private String marca;

    @JsonProperty("Modelo")
    private String modelo;

    @JsonProperty("AnoModelo")
    private String anoModelo;

    @JsonProperty("Autenticacao")
    private String autenticacao;

    @JsonProperty("DataConsulta")
    private String dataConsulta;

    @JsonProperty("Valor")
    public String Valor;

}
