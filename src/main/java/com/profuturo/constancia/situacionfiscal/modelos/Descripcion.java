package com.profuturo.constancia.situacionfiscal.modelos;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Descripcion {

	@ApiModelProperty(position = 1)
	@JsonProperty("validationFailureDetail")
	private ValidationFailureDetail validationFailureDetail;

}
