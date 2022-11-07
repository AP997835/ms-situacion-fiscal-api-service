package com.profuturo.constancia.situacionfiscal.modelos.entrada;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "GuardarDatosCSFIn", description = "Es el bean GuardarDatosCSFIn Campos.")
public class GuardarDatosCSFIn {

    @ApiModelProperty(value = "nombre", required = true)
    @JsonProperty("nombre")
    public String nombreCompleto;

    @ApiModelProperty(value = "aPaterno", required = true)
    @JsonProperty("aPaterno")
    public String apellido_paterno;

    @ApiModelProperty(value = "aMaterno", required = true)
    @JsonProperty("aMaterno")
    public String apellido_materno;

    @ApiModelProperty(value = "rfc", required = true)
    @JsonProperty("rfc")
    public String rfc;

    @ApiModelProperty(value = "cp", required = true)
    @JsonProperty("cp")
    public String cp;

    @ApiModelProperty(value = "curp", required = true)
    @JsonProperty("curp")
    public String curp;
}
