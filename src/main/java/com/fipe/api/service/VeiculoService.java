package com.fipe.api.service;

import com.fipe.api.model.*;

import java.util.List;

public interface VeiculoService {
    public String createClientResponse(String url, ParamFipe paramFipe);
    public List<MesReferenciaFipe> getMesReferenciaFipe();
    public List<Marca> getMarcaByCodVeiculo(int codVeiculo);
    public VeiculoFipe getVeiculoFipe(int anoModelo, String codFipe, int codigoTipoVeiculo, int codigoTipoCombustivel);
    public Modelo getModeloByIdMarca(int idMarca, int codVeiculo);
}
