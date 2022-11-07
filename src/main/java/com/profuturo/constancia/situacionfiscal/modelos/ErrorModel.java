package com.profuturo.constancia.situacionfiscal.modelos;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorModel {

	@JsonProperty("error")
	private Error error;
}
