package com.profuturo.constancia.situacionfiscal.modelos.salida;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "GuardarDatosCSFOut", description = "Es el bean GuardarDatosCSFOut Campos.")
public class GuardarDatosCSFOut {

    @ApiModelProperty(value = "resultado")
    @JsonProperty("resultado")
    public boolean resultado;
}
