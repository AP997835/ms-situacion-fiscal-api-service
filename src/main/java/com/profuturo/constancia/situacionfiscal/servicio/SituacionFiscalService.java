package com.profuturo.constancia.situacionfiscal.servicio;

import com.profuturo.constancia.situacionfiscal.excepciones.BadRequestException;
import com.profuturo.constancia.situacionfiscal.excepciones.NoAuthException;
import com.profuturo.constancia.situacionfiscal.excepciones.NotFoundException;
import com.profuturo.constancia.situacionfiscal.excepciones.ServerUnavailableException;
import com.profuturo.constancia.situacionfiscal.modelos.entrada.GuardarDatosCSFIn;
import com.profuturo.constancia.situacionfiscal.modelos.salida.MovimientosOut;

import java.util.List;

public interface SituacionFiscalService {

	public List<MovimientosOut> consultarMovimientos(String numCuenta) throws NotFoundException, BadRequestException, NoAuthException, ServerUnavailableException, Exception;
	long guardarCSF(GuardarDatosCSFIn in) throws NotFoundException, BadRequestException, NoAuthException, ServerUnavailableException, Exception;
}
