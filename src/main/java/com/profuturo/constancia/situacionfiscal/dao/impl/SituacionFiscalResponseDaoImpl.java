package com.profuturo.constancia.situacionfiscal.dao.impl;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.profuturo.constancia.situacionfiscal.config.YamlPropertySourceFactory;
import com.profuturo.constancia.situacionfiscal.dao.SituacionFiscalResponseDao;
import com.profuturo.constancia.situacionfiscal.excepciones.NotFoundException;
import com.profuturo.constancia.situacionfiscal.excepciones.SQLInsertarException;
import com.profuturo.constancia.situacionfiscal.modelos.entrada.ActualizarMovimientosIn;
import com.profuturo.constancia.situacionfiscal.modelos.entrada.GuardarDatosCSFIn;
import com.profuturo.constancia.situacionfiscal.modelos.entrada.GuardarMovimientosIn;
import com.profuturo.constancia.situacionfiscal.modelos.entrada.GuardarMovimientosTransitionalIn;
import com.profuturo.constancia.situacionfiscal.modelos.salida.MovimientosOut;
import com.profuturo.constancia.situacionfiscal.modelos.salida.MovimientosTransitionalOut;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@PropertySource(value = "classpath:querys.yaml", factory = YamlPropertySourceFactory.class)
public class SituacionFiscalResponseDaoImpl implements SituacionFiscalResponseDao {
	@Autowired
	@Qualifier("jdbcAut")
	private JdbcTemplate jdbcAut;

	@Value("${cfs.insertCFSResponse}")
	private String INSERT_CSF;

	@Value("${selectMovimientos}")
	private String SELECT_MOVIMIENTOS_NUMCUENTA;

	Logger logger = LogManager.getLogger(SituacionFiscalResponseDaoImpl.class);

	@Override
	public List<MovimientosOut> getMovimientosDao(String numCuenta) throws SQLInsertarException {
		List<MovimientosOut> resultado = null;
		try {
			resultado = jdbcAut.query(SELECT_MOVIMIENTOS_NUMCUENTA, new Object[] { numCuenta },  new RowMapper<MovimientosOut>()  {
				@Override
				public MovimientosOut mapRow(ResultSet rs, int rowNum) throws SQLException {
					MovimientosOut configrespuesta = new MovimientosOut();
					configrespuesta.setId_movimiento(rs.getLong("ID_MOVIMIENTO"));
					configrespuesta.setFecha_movimiento(rs.getString("FECHA_MOVIMIENTO"));
					configrespuesta.setDesc_tipo_cuenta(rs.getString("DESC_TIPO_CUENTA"));
					configrespuesta.setNumero_cuenta(rs.getString("NUMERO_CUENTA"));
					configrespuesta.setReferencia(rs.getString("REFERENCIA"));
					configrespuesta.setConcepto(rs.getString("CONCEPTO"));
					configrespuesta.setMonto(rs.getLong("MONTO"));
					configrespuesta.setDesc_periodo(rs.getString("DESC_PERIODO"));
					return configrespuesta;
				}
			});
			if (resultado == null) {
				throw new RuntimeException("No se logró encontrar movimientos asociados " + numCuenta);
			}
			return resultado;
		} catch (RuntimeException e) {
			logger.error("ERROR - Ocurrio un error al obtener la movimientos de la BD: " + e.getMessage());
			throw new SQLInsertarException("Ocurrio un error al obtener movimientos de la BD");
		}
	}
	@Transactional
	@Override
	public int insertCSFResponse(GuardarDatosCSFIn in) throws SQLInsertarException {
		int exito = 0;
		try {
			exito = jdbcAut.update(INSERT_CSF,
					new Object[] { in.getNombreCompleto(),in.getApellido_paterno(),in.getApellido_materno(), in.getCurp(),
							in.getRfc(), in.getCp()});
		} catch (RuntimeException e) {
			logger.error("ERROR - Ocurrio un error al insertar el movimientos en la BD: " + e.getMessage());
			throw new SQLInsertarException("Ocurrio un error al insertar el movimiento en la BD");
		}
		return exito;
	}

}
