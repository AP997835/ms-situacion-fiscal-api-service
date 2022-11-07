package com.profuturo.constancia.situacionfiscal.controladores;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import com.profuturo.constancia.situacionfiscal.excepciones.*;
import com.profuturo.constancia.situacionfiscal.modelos.entrada.GuardarDatosCSFIn;
import com.profuturo.constancia.situacionfiscal.modelos.salida.GuardarDatosCSFOut;
import com.profuturo.constancia.situacionfiscal.servicio.SituacionFiscalService;
import com.profuturo.constancia.situacionfiscal.modelos.salida.MovimientosOut;
import com.profuturo.constancia.situacionfiscal.util.UtilConversor;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import io.swagger.annotations.Api;

@RestController
@RequestMapping
@Api(tags = { "ActualizaciónCSF" })
public class SituacionFiscalController extends UtilConversor {

	Logger logger = LogManager.getLogger(SituacionFiscalController.class);

	@Autowired
	private SituacionFiscalService EdoCtaService;

	/*@ApiResponses(value = { @ApiResponse(code = 200, message = "OK", response = MovimientosOut.class),
			@ApiResponse(code = 400, message = "BAD REQUEST", response = ResponseError.class),
			@ApiResponse(code = 401, message = "UNAUTHORIZED", response = ResponseError.class),
			@ApiResponse(code = 404, message = "NOT FOUND", response = ResponseError.class),
			@ApiResponse(code = 500, message = "INTERNAL SERVER ERROR", response = ResponseError.class),
			@ApiResponse(code = 503, message = "SERVICE UNAVAILABLE", response = ResponseError.class) })
	@GetMapping(value = "/movimientos/{numeroCuenta}")
	public ResponseEntity<List<MovimientosOut>> getMovimientos(@PathVariable("numeroCuenta") String numeroCuenta){
		logger.info("Request: " + numeroCuenta);
		if (numeroCuenta == null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Parametro numeroCuenta es Obligatorio.");
		}
		try {
			List<MovimientosOut> responseconsultarMovimientos  = EdoCtaService.consultarMovimientos(numeroCuenta);
			logger.info("Response[200]: " + "");
			return ResponseEntity.ok(responseconsultarMovimientos);
		} catch (BadRequestException e) {
			logger.warn("Response[400]: " + e.getMessage());
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
		} catch (NoAuthException e) {
			logger.warn("Response[401]: " + e.getMessage());
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage(), e);
		} catch (NotFoundException e) {
			logger.warn("Response[404]: " + e.getMessage());
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
		} catch (ServerUnavailableException e) {
			logger.warn("Response[503]: " + e.getMessage());
			throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, e.getMessage(), e);
		} catch (Exception e) {
			logger.warn("Response[500]: " + e.getMessage());
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
		}

	}*/
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK", response = GuardarDatosCSFOut.class),
			@ApiResponse(code = 400, message = "BAD REQUEST", response = ResponseError.class),
			@ApiResponse(code = 401, message = "UNAUTHORIZED", response = ResponseError.class),
			@ApiResponse(code = 404, message = "NOT FOUND", response = ResponseError.class),
			@ApiResponse(code = 500, message = "INTERNAL SERVER ERROR", response = ResponseError.class),
			@ApiResponse(code = 503, message = "SERVICE UNAVAILABLE", response = ResponseError.class) })
	@PostMapping(value = "/guardarDatosCSF")
	public ResponseEntity<GuardarDatosCSFOut> guardarDatosCSF(@RequestBody GuardarDatosCSFIn in){
		GuardarDatosCSFOut retorno=new GuardarDatosCSFOut();
		String errors = this.validaratributosderequest(in);
		if (errors != null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, errors);
		}
		try {
			Long responseGuardar = EdoCtaService.guardarCSF(in);
			if(responseGuardar==1){
				retorno.setResultado(true);
			}else{
				retorno.setResultado(false);
			}
			logger.info("RequestID: " + in + " Response[200]: Exitoso");
			return ResponseEntity.ok(retorno);
		} catch (Exception e) {
			logger.error(" Request[409]:  -> Los datos introducidos no generan");
			logger.error(e.getMessage());
			throw new ResponseStatusException(HttpStatus.CONFLICT,
					"Ocurrió un error al almacenar la información introducida", e);
		}
	}

	private String validaratributosderequest(GuardarDatosCSFIn in) {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		Set<ConstraintViolation<GuardarDatosCSFIn>> constraintViolations = validator.validate(in);
		if (constraintViolations.size() > 0) {
			List<String> errorMessages = constraintViolations.stream().map(constrain -> constrain.getMessage())
					.collect(Collectors.toList());
			logger.warn("Response[400]: Algunos campos no cumplen con las validaciones " + errorMessages);
			return errorMessages.get(0);
		}
		return null;
	}


}
