package com.fipe.api.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ParamFipe {

    private int codigoTabelaReferencia;
    private int codigoMarca;
    private String codigoModelo;
    private int codigoTipoVeiculo;
    private int anoModelo;
    private String ano;
    private String codigoTipoCombustivel;
    private String tipoVeiculo;
    private String modeloCodigoExterno;
    private String tipoConsulta;

}
