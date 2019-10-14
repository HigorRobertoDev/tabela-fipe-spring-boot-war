package com.fipe.api.model;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class Marca {

    @SerializedName("Label")
    private String label;
    @SerializedName("Value")
    private String value;

}
