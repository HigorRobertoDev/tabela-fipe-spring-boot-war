package com.fipe.api.controllers;

import com.fipe.api.model.Veiculo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/fipe-veiculo")
public class VeiculoController {

    @GetMapping("/get-veiculos-teste")
    public ResponseEntity<List<Veiculo>> getVeiculosTeste() {
        List<Veiculo> veiculos = new ArrayList<>();

        Veiculo veiculo = new Veiculo();
        veiculo.setMesReferencia("outubro de 2019");
        veiculo.setCodigoFipe("004083-5");
        veiculo.setMarca("GM - Chevrolet");
        veiculo.setModelo("Astra Sedan 2.0/CD/ GLS/ Adv. 2.0 16V 4p");
        veiculo.setAnoModelo("2002 Gasolina");
        veiculo.setAutenticacao("g8xhv9w2wfp");
        veiculo.setDataConsulta("quinta-feira, 3 de outubro de 2019 12:00");
        veiculo.setPrecoMedio("R$ 13.099,00");
        veiculos.add(veiculo);

        Veiculo veiculo2 = new Veiculo();
        veiculo2.setMesReferencia("outubro de 2019");
        veiculo2.setCodigoFipe("004066-5");
        veiculo2.setMarca("GM - Chevrolet");
        veiculo2.setModelo("Vectra GLS/Expres.2.2/ 2.0 e 2.0 CD 8V");
        veiculo2.setAnoModelo("2002 Gasolina");
        veiculo2.setAutenticacao("jrbynjqr7rrl");
        veiculo2.setDataConsulta("quinta-feira, 3 de outubro de 2019 12:02");
        veiculo2.setPrecoMedio("R$ 16.974,00");
        veiculos.add(veiculo);

        return new ResponseEntity<>(
                veiculos,
                HttpStatus.OK
        );
    }

}
