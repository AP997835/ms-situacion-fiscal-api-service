package com.profuturo.constancia.situacionfiscal.excepciones;

public class NotFoundException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NotFoundException(String messageError) {
		super(messageError);

	}

}
