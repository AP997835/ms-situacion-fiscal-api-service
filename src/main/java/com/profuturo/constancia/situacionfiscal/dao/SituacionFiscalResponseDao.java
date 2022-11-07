package com.profuturo.constancia.situacionfiscal.dao;

import com.profuturo.constancia.situacionfiscal.modelos.entrada.GuardarDatosCSFIn;
import com.profuturo.constancia.situacionfiscal.modelos.entrada.GuardarMovimientosTransitionalIn;
import com.profuturo.constancia.situacionfiscal.excepciones.SQLInsertarException;
import com.profuturo.constancia.situacionfiscal.modelos.entrada.ActualizarMovimientosIn;
import com.profuturo.constancia.situacionfiscal.modelos.entrada.GuardarMovimientosIn;
import com.profuturo.constancia.situacionfiscal.modelos.salida.MovimientosOut;
import com.profuturo.constancia.situacionfiscal.modelos.salida.MovimientosTransitionalOut;

import java.util.List;

public interface SituacionFiscalResponseDao {
	List<MovimientosOut> getMovimientosDao(String numCuenta) throws SQLInsertarException;
	public int insertCSFResponse(GuardarDatosCSFIn in) throws SQLInsertarException;
}
