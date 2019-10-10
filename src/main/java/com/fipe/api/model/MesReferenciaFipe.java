package com.fipe.api.model;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class MesReferenciaFipe {

    @SerializedName("Codigo")
    private int codigo;
    @SerializedName("Mes")
    private String mes;

}
