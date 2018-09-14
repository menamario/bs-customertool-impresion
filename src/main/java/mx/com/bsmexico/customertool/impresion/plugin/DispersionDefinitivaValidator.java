package mx.com.bsmexico.customertool.impresion.plugin;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.function.Predicate;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.validator.routines.EmailValidator;

import mx.com.bsmexico.customertool.api.layouts.model.validation.LayoutModelValidator;

public class DispersionDefinitivaValidator extends LayoutModelValidator<DispersionDefinitiva> {

	private SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHH:mm:ss");

	@Override
	public boolean isValidField(String fieldName, DispersionDefinitiva model) {
		boolean isValid = false;
		if (StringUtils.isNotBlank(fieldName) && model != null) {
			switch (fieldName) {
			case DispersionDefinitiva.FIELD_APLICACION:
				isValid = aplicacion().test(model);
				break;
			case DispersionDefinitiva.FIELD_CONCEPTO:
				isValid = concepto().test(model);
				break;
			case DispersionDefinitiva.FIELD_CORREO_ELECTRONICO:
				isValid = email().test(model);
				break;
			case DispersionDefinitiva.FIELD_CUENTA_ABONO:
				isValid = cuentaAbono().test(model);
				break;
			case DispersionDefinitiva.FIELD_CUENTA_CARGO:
				isValid = cuentaCargo().test(model);
				break;
			case DispersionDefinitiva.FIELD_CURP:
				isValid = curp().test(model);
				break;
			case DispersionDefinitiva.FIELD_FECHA:
				isValid = fecha().test(model);
				break;
			case DispersionDefinitiva.FIELD_IMPORTE:
				isValid = importe().test(model);
				break;
			case DispersionDefinitiva.FIELD_IVA:
				isValid = iva().test(model);
				break;
			case DispersionDefinitiva.FIELD_NOMBRE_BENEFICIARIO:
				isValid = nombreBeneficiario().test(model);
				break;
			case DispersionDefinitiva.FIELD_NUMERO_CELULAR:
				isValid = numeroTel().test(model);
				break;
			case DispersionDefinitiva.FIELD_REFERENCIA:
				isValid = referencia().test(model);
				break;
			case DispersionDefinitiva.FIELD_RFC:
				isValid = rfc().test(model);
				break;
			case DispersionDefinitiva.FIELD_TIPO_CUENTA_BENEFICIARIO:
				isValid = tipoCuentaBeneficiario().test(model);
				break;
			case DispersionDefinitiva.FIELD_TIPO_MOVIMIENTO:
				isValid = tipoMovimiento().test(model);
				break;
			case DispersionDefinitiva.FIELD_TIPO_PERSONA:
				isValid = tipoPersona().test(model);
				break;
			case DispersionDefinitiva.FIELD_TIPO_TRANSACCION:
				isValid = tipoTransaccion().test(model);
				break;
			case DispersionDefinitiva.FIELD_DIVISA:
				isValid = divisa().test(model);
				break;
			case DispersionDefinitiva.FIELD_BANCO:
				isValid = banco().test(model);
				break;
			case DispersionDefinitiva.FIELD_COMISION:
				isValid = comision().test(model);
				break;
			case DispersionDefinitiva.FIELD_IVA_COMISION:
				isValid = ivaComision().test(model);
				break;
			case DispersionDefinitiva.FIELD_CLAVE_RASTREO:
				isValid = claveRastreo().test(model);
				break;
			case DispersionDefinitiva.FIELD_FOLIO_OPERACION:
				isValid = folio().test(model);
				break;
			case DispersionDefinitiva.FIELD_USUARIO:
				isValid = usuario().test(model);
				break;
			case DispersionDefinitiva.FIELD_ESTADO_OPERACION:
				isValid = estado().test(model);
				break;
			default:
				break;
			}
		}
		return isValid;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mx.com.bsmexico.customertool.api.layouts.model.validation.
	 * LayoutModelValidator#isValid(java.lang.Object)
	 */
	@Override
	public boolean isValid(DispersionDefinitiva model) {
		boolean isValid = true;
		if (!isEmptyModel(model)) {
			isValid = model != null && aplicacion().test(model) && concepto().test(model) && email().test(model)
					&& cuentaAbono().test(model) && cuentaCargo().test(model) && curp().test(model)
					&& fecha().test(model) && importe()
							.test(model)/*
										 * && iva().test(model) && nombreBeneficiario().test(model) &&
										 * numeroTel().test(model) && referencia().test(model) && rfc().test(model)&&
										 * tipoCuentaBeneficiario().test(model) && tipoMovimiento().test(model) &&
										 * tipoPersona().test(model) && tipoTransaccion().test(model) &&
										 * divisa().test(model) && banco().test(model) && comision().test(model) &&
										 * ivaComision().test(model) && claveRastreo().test(model) &&
										 * folio().test(model) && usuario().test(model) && estado().test(model)
										 */;

		}
		return isValid;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mx.com.bsmexico.customertool.api.layouts.model.validation.
	 * LayoutModelValidator#isValid(java.util.List)
	 */
	@Override
	public boolean isValid(List<DispersionDefinitiva> models) {
		boolean isValid = true;
		if (models != null) {
			for (DispersionDefinitiva model : models) {
				if (!this.isValid(model)) {
					isValid = false;
					break;
				}
			}
		}
		return isValid;
	}

	@Override
	public String getValidationDescription(String fieldName) {
		String desc = StringUtils.EMPTY;
		if (StringUtils.isNotBlank(fieldName)) {
			switch (fieldName) {
			case DispersionDefinitiva.FIELD_APLICACION:
				desc = "";
				break;
			case DispersionDefinitiva.FIELD_CONCEPTO:
				desc = "";
				break;
			case DispersionDefinitiva.FIELD_CORREO_ELECTRONICO:
				desc = "";
				break;
			case DispersionDefinitiva.FIELD_CUENTA_ABONO:
				desc = "";
				break;
			case DispersionDefinitiva.FIELD_CUENTA_CARGO:
				desc = "";
				break;
			case DispersionDefinitiva.FIELD_CURP:
				desc = "";
				break;
			case DispersionDefinitiva.FIELD_FECHA:
				desc = "";
				break;
			case DispersionDefinitiva.FIELD_IMPORTE:
				desc = "";
				break;
			case DispersionDefinitiva.FIELD_IVA:
				desc = "";
				break;
			case DispersionDefinitiva.FIELD_NOMBRE_BENEFICIARIO:
				desc = "";
				break;
			case DispersionDefinitiva.FIELD_NUMERO_CELULAR:
				desc = "";
				break;
			case DispersionDefinitiva.FIELD_REFERENCIA:
				desc = "";
				break;
			case DispersionDefinitiva.FIELD_RFC:
				desc = "";
				break;
			case DispersionDefinitiva.FIELD_TIPO_CUENTA_BENEFICIARIO:
				desc = "";
				break;
			case DispersionDefinitiva.FIELD_TIPO_MOVIMIENTO:
				desc = "";
				break;
			case DispersionDefinitiva.FIELD_TIPO_PERSONA:
				desc = "";
				break;
			case DispersionDefinitiva.FIELD_TIPO_TRANSACCION:
				desc = "";
				break;
			case DispersionDefinitiva.FIELD_DIVISA:
				desc = "";
				break;
			case DispersionDefinitiva.FIELD_BANCO:
				desc = "";
				break;
			case DispersionDefinitiva.FIELD_COMISION:
				desc = "";
				break;
			case DispersionDefinitiva.FIELD_IVA_COMISION:
				desc = "";
				break;
			case DispersionDefinitiva.FIELD_CLAVE_RASTREO:
				desc = "";
				break;
			case DispersionDefinitiva.FIELD_FOLIO_OPERACION:
				desc = "";
				break;
			case DispersionDefinitiva.FIELD_USUARIO:
				desc = "";
				break;
			case DispersionDefinitiva.FIELD_ESTADO_OPERACION:
				desc = "";
				break;
			default:
				break;
			}
		}
		return desc;
	}

	/**
	 * @param model
	 * @return
	 */
	private boolean isEmptyModel(final DispersionDefinitiva model) {
		return StringUtils.isBlank(model.getAplicacion()) && StringUtils.isBlank(model.getConcepto())
				&& StringUtils.isBlank(model.getCorreoElectronico()) && StringUtils.isBlank(model.getCuentaAbono())
				&& StringUtils.isBlank(model.getCuentaCargo()) && StringUtils.isBlank(model.getCurp())
				&& StringUtils.isBlank(model.getFecha()) && StringUtils.isBlank(model.getImporte())
				&& StringUtils.isBlank(model.getIva()) && StringUtils.isBlank(model.getNombre())
				&& StringUtils.isBlank(model.getNumeroCelular()) && StringUtils.isBlank(model.getReferencia())
				&& StringUtils.isBlank(model.getRfc()) && StringUtils.isBlank(model.getTipoCuentaBeneficiario())
				&& StringUtils.isBlank(model.getTipoMovimiento()) && StringUtils.isBlank(model.getTipoPersona())
				&& StringUtils.isBlank(model.getTipoTransaccion()) && StringUtils.isBlank(model.getDivisa())
				&& StringUtils.isBlank(model.getBanco()) && StringUtils.isBlank(model.getComision())
				&& StringUtils.isBlank(model.getIvaComision()) && StringUtils.isBlank(model.getClaveRastreo())
				&& StringUtils.isBlank(model.getFolioOperacion()) & StringUtils.isBlank(model.getUsuario())
				&& StringUtils.isBlank(model.getEstadoOperacion());

	}

	/**
	 * @return
	 */
	public Predicate<DispersionDefinitiva> tipoMovimiento() {
		return v -> {
			return (StringUtils.isNotBlank(v.getTipoMovimiento()) && v.getTipoMovimiento().matches("[01]"));
		};
	}

	/**
	 * @return
	 */
	public Predicate<DispersionDefinitiva> aplicacion() {
		return v -> {
			return (StringUtils.isNotBlank(v.getAplicacion()) && v.getAplicacion().matches("[HP]"));
		};
	}

	/**
	 * @return
	 */
	public Predicate<DispersionDefinitiva> fecha() {
		return v -> {
			Date date = null;
			if (StringUtils.isNotBlank(v.getFecha())) {
				try {
					date = df.parse(v.getFecha());
				} catch (ParseException e) {
					// No match pattern
				}
			}
			return (StringUtils.isNotBlank(v.getFecha()) && date != null);
		};
	}

	/**
	 * @return
	 */
	public Predicate<DispersionDefinitiva> tipoTransaccion() {
		return v -> {
			return (StringUtils.isNotBlank(v.getTipoTransaccion()) && v.getTipoTransaccion().matches("00|01|02|03"));
		};
	}

	/**
	 * @return
	 */
	public Predicate<DispersionDefinitiva> cuentaCargo() {
		return v -> {
			return (StringUtils.isNotBlank(v.getCuentaCargo()) && NumberUtils.isDigits(v.getCuentaCargo())
					&& v.getCuentaCargo().length() == 11);
		};
	}

	/**
	 * @return
	 */
	public Predicate<DispersionDefinitiva> tipoCuentaBeneficiario() {
		return v -> {
			return (StringUtils.isNotBlank(v.getTipoCuentaBeneficiario())
					&& v.getTipoCuentaBeneficiario().matches("01|40|03|10"));
		};
	}

	/**
	 * @return
	 */
	public Predicate<DispersionDefinitiva> cuentaAbono() {
		return v -> {
			return (StringUtils.isNotBlank(v.getCuentaAbono()) && StringUtils.isNumeric(v.getCuentaAbono())
					&& v.getCuentaAbono().length() >= 10 && v.getCuentaAbono().length() <= 18);
		};
	}

	/**
	 * @return
	 */
	public Predicate<DispersionDefinitiva> tipoPersona() {
		return v -> {
			return (StringUtils.isBlank(v.getTipoPersona()) || v.getTipoPersona().matches("PF|PM"));
		};
	}

	/**
	 * @return
	 */
	public Predicate<DispersionDefinitiva> nombreBeneficiario() {
		return v -> {
			return (StringUtils.isNotBlank(v.getNombre()) && v.getNombre().length() <= 40);
		};
	}

	/**
	 * @return
	 */
	public Predicate<DispersionDefinitiva> rfc() {
		return v -> {
			return (("PF".equals(v.getTipoPersona()) && v.getRfc().length() == 13)
					|| ("PM".equals(v.getTipoPersona()) && v.getRfc().length() == 12))
					|| StringUtils.isBlank(v.getRfc());
		};
	}

	/**
	 * @return
	 */
	public Predicate<DispersionDefinitiva> curp() {
		return v -> {
			return (StringUtils.isBlank(v.getCurp()) || v.getCurp().length() == 18);
		};
	}

	/**
	 * @return
	 */
	public Predicate<DispersionDefinitiva> divisa() {
		return v -> {
			return (StringUtils.isNotBlank(v.getDivisa()) && v.getDivisa().matches("USD|MXN"));
		};
	}

	/**
	 * @return
	 */
	public Predicate<DispersionDefinitiva> importe() {
		return v -> {
			System.out.println(v.getImporte());
			return (StringUtils.isNotBlank(v.getImporte())
					&& NumberUtils.isCreatable(v.getImporte().replaceFirst("^0+(?!$)", ""))
					&& Double.valueOf(v.getImporte()) <= 999999999999.99);
		};
	}

	/**
	 * @return
	 */
	public Predicate<DispersionDefinitiva> iva() {
		return v -> {
			return (StringUtils.isNotBlank(v.getRfc()) && StringUtils.isNotBlank(v.getIva())
					&& NumberUtils.isCreatable(v.getIva().replaceFirst("^0+(?!$)", ""))
					&& Double.valueOf(v.getIva().replaceFirst("^0+(?!$)", "")) <= 999999999999.99)
					|| StringUtils.isBlank(v.getIva());
		};
	}

	/**
	 * @return
	 */
	public Predicate<DispersionDefinitiva> concepto() {
		return v -> {
			return (StringUtils.isNotBlank(v.getConcepto()) && v.getConcepto().length() <= 40);
		};
	}

	/**
	 * @return
	 */
	public Predicate<DispersionDefinitiva> referencia() {
		return v -> {
			return (StringUtils.isNotBlank(v.getReferencia())
					&& (v.getReferencia().length() >= 7 && v.getReferencia().length() <= 20));
		};
	}

	/**
	 * @return
	 */
	public Predicate<DispersionDefinitiva> email() {
		return v -> {
			return (StringUtils.isBlank(v.getCorreoElectronico())
					|| (EmailValidator.getInstance().isValid(v.getCorreoElectronico())
							&& v.getCorreoElectronico().length() <= 60));
		};
	}

	/**
	 * @return
	 */
	public Predicate<DispersionDefinitiva> numeroTel() {
		return v -> {
			return (StringUtils.isNumeric(v.getNumeroCelular()) && v.getNumeroCelular().length() == 10)
					|| StringUtils.isBlank(v.getNumeroCelular());
		};
	}

	/**
	 * @return
	 */
	public Predicate<DispersionDefinitiva> banco() {
		return v -> {
			return ("40".equals(v.getTipoCuentaBeneficiario()) && StringUtils.isNotBlank(v.getBanco())
					&& v.getBanco().length() <= 30) || StringUtils.isBlank(v.getBanco());
		};
	}

	/**
	 * @return
	 */
	public Predicate<DispersionDefinitiva> comision() {
		return v -> {
			/*
			 * return StringUtils.isNotBlank(v.getComision()) &&
			 * NumberUtils.isCreatable(v.getComision()) && Double.valueOf(v.getComision())
			 * <= 999999999999.99;
			 */
			return StringUtils.isBlank(v.getComision())
					|| (NumberUtils.isCreatable(v.getComision().replaceFirst("^0+(?!$)", ""))
							&& Double.valueOf(v.getComision()) <= 999999999999.99);
		};
	}

	/**
	 * @return
	 */
	public Predicate<DispersionDefinitiva> ivaComision() {
		return v -> {
			/*
			 * return StringUtils.isNotBlank(v.getIvaComision()) &&
			 * NumberUtils.isCreatable(v.getIvaComision()) &&
			 * Double.valueOf(v.getIvaComision()) <= 999999999999.99;
			 */
			return StringUtils.isBlank(v.getIvaComision())
					|| (NumberUtils.isCreatable(v.getIvaComision().replaceFirst("^0+(?!$)", ""))
							&& Double.valueOf(v.getIvaComision()) <= 999999999999.99);
		};
	}

	/**
	 * @return
	 */
	public Predicate<DispersionDefinitiva> claveRastreo() {
		return v -> {
			return (StringUtils.isNotBlank(v.getClaveRastreo()) && v.getClaveRastreo().length() == 18);
		};
	}

	/**
	 * @return
	 */
	public Predicate<DispersionDefinitiva> folio() {
		return v -> {
			return (StringUtils.isNotBlank(v.getFolioOperacion()) && NumberUtils.isDigits(v.getFolioOperacion())
					&& v.getFolioOperacion().length() == 21);
		};
	}

	/**
	 * @return
	 */
	public Predicate<DispersionDefinitiva> usuario() {
		return v -> {
			return (StringUtils.isNotBlank(v.getUsuario()) && v.getUsuario().length() <= 10);
		};
	}

	/**
	 * @return
	 */
	public Predicate<DispersionDefinitiva> estado() {
		return v -> {
			return (StringUtils.isNotBlank(v.getEstadoOperacion()) && v.getEstadoOperacion().length() <= 30);
		};
	}

	@Override
	public boolean isActive(DispersionDefinitiva model) {
		// TODO Auto-generated method stub
		return false;
	}

}
