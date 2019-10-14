package com.fipe.api.controllers;

import com.fipe.api.model.Marca;
import com.fipe.api.model.Modelo;
import com.fipe.api.model.ModeloDetalhado;
import com.fipe.api.model.VeiculoFipe;
import com.fipe.api.service.VeiculoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/fipe-veiculo")
public class VeiculoController {

    @Autowired
    VeiculoServiceImpl veiculoServiceImpl;

    @GetMapping
    public ResponseEntity<VeiculoFipe> findVeiculoByCodFipe(
            @RequestParam(value = "codFipe") String codFipe,
            @RequestParam(value = "anoModelo") int anoModelo,
            @RequestParam(value = "codigoTipoVeiculo", defaultValue = "1") int codigoTipoVeiculo,
            @RequestParam(value = "codigoTipoCombustivel", defaultValue = "1") int codigoTipoCombustivel
    ) {
        return new ResponseEntity<>(
                new VeiculoServiceImpl().getVeiculoFipe(anoModelo, codFipe, codigoTipoVeiculo, codigoTipoCombustivel),
                HttpStatus.OK
        );
    }

    /*
    * COD. VEÍCULO
    * 1 CARRO
    * 2 MOTO
    * 3 CAMINHÃO
    * */
    @GetMapping("/get-marcas/{codVeiculo}")
    public ResponseEntity<List<Marca>> findMarcaByTipoCodVeiculo(@PathVariable int codVeiculo) {
        return new ResponseEntity<>(
                veiculoServiceImpl.getMarcaByCodVeiculo(codVeiculo),
                HttpStatus.OK
        );
    }

    @GetMapping("/get-modelos-marca")
    public ResponseEntity<Modelo> findModeloByIdMarca(
            @RequestParam(value = "idMarca") int idMarca,
            @RequestParam(value = "codVeiculo", defaultValue = "1") int codVeiculo
    ) {
        return new ResponseEntity<>(
                veiculoServiceImpl.getModeloByIdMarca(idMarca, codVeiculo),
                HttpStatus.OK
        );
    }

    @GetMapping("/get-modelos-detalhado-marca")
    public ResponseEntity<ModeloDetalhado> getModelosDetalhadoByIdMarca(
            @RequestParam(value = "idMarca") int idMarca,
            @RequestParam(value = "codVeiculo", defaultValue = "1") int codVeiculo
    ) {
        return new ResponseEntity<>(
                veiculoServiceImpl.getModeloDetalhadoByIdMarca(idMarca, codVeiculo),
                HttpStatus.OK
        );
    }
}
