package com.fipe.api.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ParamFipe {

    private int codigoTabelaReferencia;
    private String codigoMarca;
    private String codigoModelo;
    private int codigoTipoVeiculo;
    private int anoModelo;
    private int codigoTipoCombustivel;
    private String tipoVeiculo;
    private String modeloCodigoExterno;
    private String tipoConsulta;

}
