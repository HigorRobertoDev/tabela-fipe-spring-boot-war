package com.fipe.api.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Veiculo {

    private String mesReferencia;
    private String codigoFipe;
    private String marca;
    private String modelo;
    private String anoModelo;
    private String autenticacao;
    private String dataConsulta;
    private String precoMedio;

}
