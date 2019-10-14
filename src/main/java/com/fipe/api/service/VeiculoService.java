package com.fipe.api.service;

import com.fipe.api.model.Marca;
import com.fipe.api.model.MesReferenciaFipe;
import com.fipe.api.model.ParamFipe;
import com.fipe.api.model.VeiculoFipe;

import java.util.List;

public interface VeiculoService {
    public String createClientResponse(String url, ParamFipe paramFipe);
    public List<MesReferenciaFipe> getMesReferenciaFipe();
    public List<Marca> getMarcaByCodVeiculo(int codVeiculo);
    public VeiculoFipe getVeiculoFipe(int anoModelo, String codFipe, int codigoTipoVeiculo, int codigoTipoCombustivel);
}
