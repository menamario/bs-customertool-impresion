package mx.com.bsmexico.customertool.impresion.plugin;

import java.util.List;
import java.util.function.Predicate;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import mx.com.bsmexico.customertool.api.layouts.model.validation.LayoutModelValidator;

/**
 * @author jchr
 *
 */
public class ImpresionValidator extends LayoutModelValidator<Impresion> {

	@Override
	public boolean isValidField(String fieldName, Impresion model) {
		boolean isValid = false;
		if (StringUtils.isNotBlank(fieldName) && model != null) {
			switch (fieldName) {
			case Impresion.FIELD_CUENTA_BENEFICIARIO:
				isValid = cuenta().test(model);
				break;
			case Impresion.FIELD_NUMERO_LINEA_BENEFICIARIO:
				isValid = true;
				break;
			case Impresion.FIELD_BANCO_PARTICIPANTE:
				isValid = bancoParticipante().test(model);
				break;
			case Impresion.FIELD_TIPO_CUENTA:
				isValid = tipoCuenta().test(model);
				break;
			case Impresion.FIELD_MONEDA:
				isValid = moneda().test(model);
				break;
			case Impresion.FIELD_IMPORTE_MAXIMO_PAGAR:
				isValid = importeMaximo().test(model);
				break;
			case Impresion.FIELD_TIPO_PERSONA:
				isValid = tipoPersona().test(model);
				break;
			case Impresion.FIELD_RAZON_SOCIAL:
				isValid = razonSocial().test(model);
				break;
			case Impresion.FIELD_NOMBRE:
				isValid = nombre().test(model);
				break;
			case Impresion.FIELD_APELLIDO_PATERNO:
				isValid = apellidoPaterno().test(model);
				break;
			case Impresion.FIELD_APELLIDO_MATERNO:
				isValid = apellidoMaterno().test(model);
				break;
			default:
				break;
			}
		}
		return isValid;
	}

	@Override
	public boolean isValid(Impresion model) {
		return !isActive(model) || (model != null && cuenta().test(model) && bancoParticipante().test(model)
				&& tipoCuenta().test(model) && moneda().test(model) && importeMaximo().test(model)
				&& tipoPersona().test(model) && razonSocial().test(model) && nombre().test(model)
				&& apellidoPaterno().test(model) && apellidoMaterno().test(model));
	}

	@Override
	public boolean isValid(List<Impresion> models) {
		boolean isValid = true;
		if (models != null) {
			for (Impresion model : models) {
				if (!this.isValid(model)) {
					isValid = false;
					break;
				}
			}
		}
		return isValid;
	}

	/**
	 * @return
	 */
	public Predicate<Impresion> cuenta() {
		return v -> {
			return (StringUtils.isNotBlank(v.getCuenta()) && v.getCuenta().length() <= 18
					&& StringUtils.isNumeric(v.getCuenta()));
		};
	}

	/**
	 * @return
	 */
	public Predicate<Impresion> bancoParticipante() {
		return v -> {
			return (StringUtils.isBlank(v.getBancoParticipante()) || v.getBancoParticipante().length() == 3);
		};
	}

	/**
	 * @return
	 */
	public Predicate<Impresion> tipoCuenta() {
		return v -> {
			return (StringUtils.isNotBlank(v.getTipoCuenta()) && v.getTipoCuenta().length() == 2
					&& v.getTipoCuenta().matches("00|40"));
		};
	}

	/**
	 * @return
	 */
	public Predicate<Impresion> moneda() {
		return v -> {
			return (StringUtils.isNotBlank(v.getMoneda()) && v.getMoneda().matches("USD|MXN|EUR"));
		};
	}

	/**
	 * @return
	 */
	public Predicate<Impresion> importeMaximo() {
		return v -> {
			return (StringUtils.isNotBlank(v.getImporteMaximo()) && NumberUtils.isCreatable(v.getImporteMaximo())
					&& Double.valueOf(v.getImporteMaximo()) <= 9999999999999999.99);
		};
	}

	/**
	 * @return
	 */
	public Predicate<Impresion> tipoPersona() {
		return v -> {
			return (StringUtils.isNotBlank(v.getTipoPersona()) && v.getTipoPersona().matches("00|01"));
		};
	}

	/**
	 * @return
	 */
	public Predicate<Impresion> razonSocial() {
		return v -> {
			return ("01".equals(v.getTipoPersona()) && StringUtils.isNotBlank(v.getTipoPersona()))
					|| ("00".equals(v.getTipoPersona()) && StringUtils.isBlank(v.getTipoPersona()));
		};
	}

	/**
	 * @return
	 */
	public Predicate<Impresion> nombre() {
		return v -> {
			return ("00".equals(v.getTipoPersona()) && StringUtils.isNotBlank(v.getNombre())
					&& v.getNombre().length() <= 25)
					|| ("01".equals(v.getTipoPersona()) && StringUtils.isBlank(v.getNombre()));
		};
	}

	/**
	 * @return
	 */
	public Predicate<Impresion> apellidoPaterno() {
		return v -> {
			return ("00".equals(v.getTipoPersona()) && StringUtils.isNotBlank(v.getApellidoPaterno())
					&& v.getApellidoPaterno().length() <= 30)
					|| ("01".equals(v.getTipoPersona()) && StringUtils.isBlank(v.getApellidoPaterno()));
		};
	}

	/**
	 * @return
	 */
	public Predicate<Impresion> apellidoMaterno() {
		return v -> {
			return ("00".equals(v.getTipoPersona()) && StringUtils.isNotBlank(v.getApellidoMaterno())
					&& v.getApellidoMaterno().length() <= 30)
					|| ("01".equals(v.getTipoPersona()) && StringUtils.isBlank(v.getApellidoMaterno()));
		};
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mx.com.bsmexico.customertool.api.layouts.model.validation.
	 * LayoutModelValidator#getValidationDescription(java.lang.String)
	 */
	@Override
	public String getValidationDescription(String fieldName) {
		String desc = StringUtils.EMPTY;
		if (StringUtils.isNotBlank(fieldName)) {
			switch (fieldName) {
			case Impresion.FIELD_CUENTA_BENEFICIARIO:
				desc = "Valor numérico de la cuenta de Sabadell Banco o CLABE(otros bancos)";
				break;
			case Impresion.FIELD_NUMERO_LINEA_BENEFICIARIO:
				desc = "Valor numérico";
				break;
			case Impresion.FIELD_BANCO_PARTICIPANTE:
				desc = "Campo vacío (Cuenta Sabadell) - Tres primeros dígitos de la cuenta CALBE (otros bancos)";
				break;
			case Impresion.FIELD_TIPO_CUENTA:
				desc = "00(cuenta Sabadell) -  004 (otros bancos)";
				break;
			case Impresion.FIELD_MONEDA:
				desc = "MXN (pesos mexicanos) - USD (dolares americanos)- EUR (euros)";
				break;
			case Impresion.FIELD_IMPORTE_MAXIMO_PAGAR:
				desc = "Valor numérico cuyo máximo es  9999999999999999.99";
				break;
			case Impresion.FIELD_TIPO_PERSONA:
				desc = "00 (persona física) - 01 (persona moral)";
				break;
			case Impresion.FIELD_RAZON_SOCIAL:
				desc = "Campo vacio si es persona física";
				break;
			case Impresion.FIELD_NOMBRE:
				desc = "Campo vacio  si es persona moral";
				break;
			case Impresion.FIELD_APELLIDO_PATERNO:
				desc = "Campo vacio  si es persona moral";
				break;
			case Impresion.FIELD_APELLIDO_MATERNO:
				desc = "Campo vacio si es persona moral";
				break;
			default:
				break;
			}
		}
		return desc;
	}

	@Override
	public boolean isActive(Impresion model) {
		if (model != null) {
			if (model.getCuenta() != null && model.getCuenta().length() > 0)
				return true;
			if (model.getNumLinea() != null && model.getNumLinea().length() > 0)
				return true;
			if (model.getBancoParticipante() != null && model.getBancoParticipante().length() > 0)
				return true;
			if (model.getTipoCuenta() != null && model.getTipoCuenta().length() > 0)
				return true;
			if (model.getMoneda() != null && model.getMoneda().length() > 0)
				return true;
			if (model.getImporteMaximo() != null && model.getImporteMaximo().length() > 0)
				return true;
			if (model.getTipoPersona() != null && model.getTipoPersona().length() > 0)
				return true;
			if (model.getRazonSocial() != null && model.getRazonSocial().length() > 0)
				return true;
			if (model.getNombre() != null && model.getNombre().length() > 0)
				return true;
			if (model.getApellidoPaterno() != null && model.getApellidoPaterno().length() > 0)
				return true;
			if (model.getApellidoMaterno() != null && model.getApellidoMaterno().length() > 0)
				return true;
		}
		return false;
	}

}
