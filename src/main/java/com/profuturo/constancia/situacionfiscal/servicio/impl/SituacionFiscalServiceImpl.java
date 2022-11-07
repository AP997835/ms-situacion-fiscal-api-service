package com.profuturo.constancia.situacionfiscal.servicio.impl;

import java.util.ArrayList;
import java.util.List;

import com.profuturo.constancia.situacionfiscal.dao.SituacionFiscalResponseDao;
import com.profuturo.constancia.situacionfiscal.modelos.entrada.GuardarDatosCSFIn;
import com.profuturo.constancia.situacionfiscal.servicio.SituacionFiscalService;
import com.profuturo.constancia.situacionfiscal.excepciones.BadRequestException;
import com.profuturo.constancia.situacionfiscal.excepciones.NoAuthException;
import com.profuturo.constancia.situacionfiscal.excepciones.NotFoundException;
import com.profuturo.constancia.situacionfiscal.excepciones.ServerUnavailableException;
import com.profuturo.constancia.situacionfiscal.modelos.entrada.GuardarMovimientosIn;
import com.profuturo.constancia.situacionfiscal.modelos.salida.MovimientosOut;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import feign.RetryableException;

@Service
public class SituacionFiscalServiceImpl implements SituacionFiscalService {

	Logger logger = LogManager.getLogger(SituacionFiscalServiceImpl.class);

	@Autowired
	private SituacionFiscalResponseDao CSFResponseDao;
	@Autowired
	private SituacionFiscalService intBitacoraServicio;
	@Autowired
	private ObjectMapper objectMapper;

	@Override
	public List<MovimientosOut> consultarMovimientos(String numCuenta) throws NotFoundException, BadRequestException, NoAuthException, ServerUnavailableException, Exception {
		logger.info("consultarMovimientos Service Impl"+numCuenta);
		List<MovimientosOut> retorno=new ArrayList<MovimientosOut>();
		try {

			retorno=CSFResponseDao.getMovimientosDao(numCuenta);
			if(retorno.size()==0){
				throw new NotFoundException("No se encontró resultado");
			}
		} catch (Exception e) {


		}
		/*} catch (NotFoundException e) {

			throw new NotFoundException("No se encontró el recurso solicitado");
		} catch (BadRequestException e) {

			throw new BadRequestException(
					"El cuerpo de la solicitud al servicio Movimientos no es el esperado");
		} catch (NoAuthException e) {

			throw new NoAuthException(
					"No se tiene autorizacion para consumir el servicio Movimientos");
		} catch (Throwable e) {
			if (e.getClass() == RetryableException.class) {
				throw new ServerUnavailableException(
						"No se logró establecer conexion con el servicio Movimientos");
			} else {
				throw new Exception("Hubo un error interno: " + e.getCause().getMessage());
			}
		} finally {
			logger.info("Fin Servicio Movimientos Impl");
		}*/

		return retorno;

	}

	@Override
	public long guardarCSF(GuardarDatosCSFIn in) throws NotFoundException, BadRequestException, NoAuthException, ServerUnavailableException, Exception {
		long retorno=0;
		try {

				retorno=CSFResponseDao.insertCSFResponse(in);

		} catch (NotFoundException e) {
			throw new NotFoundException("No se encontró el recurso solicitado");
		} catch (BadRequestException e) {
			throw new BadRequestException(
					"El cuerpo de la solicitud al servicio Guardar Movimientos no es el esperado");
		} catch (NoAuthException e) {
			throw new NoAuthException(
					"No se tiene autorizacion para consumir el servicio Guardar Movimientos");
		} catch (Throwable e) {
			if (e.getClass() == RetryableException.class) {
				throw new ServerUnavailableException(
						"No se logró establecer conexion con el servicio Movimientos");
			} else {
				throw new Exception("Hubo un error interno: " + e.getCause().getMessage());
			}
		} finally {
			logger.info("Fin Servicio Movimientos Impl");
		}

		return retorno;
	}

}
