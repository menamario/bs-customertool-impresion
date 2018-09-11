package mx.com.bsmexico.customertool.impresion.plugin;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import mx.com.bsmexico.customertool.api.layouts.model.LayoutField;
import mx.com.bsmexico.customertool.api.layouts.model.LayoutModel;
import mx.com.bsmexico.customertool.api.layouts.model.LayoutModelType;

/**
 * @author jchr
 *
 */
@LayoutModel(type = LayoutModelType.PROPERTY_JAVABEANS, validatorClass = DispersionDefinitivaValidator.class)
public class DispersionDefinitiva {

	public static final String FIELD_TIPO_MOVIMIENTO = "TIPO_MOVIMIENTO";
	public static final String FIELD_APLICACION = "APLICACION";
	public static final String FIELD_FECHA = "FECHA";
	public static final String FIELD_TIPO_TRANSACCION = "TIPO_TRANSACCION";
	public static final String FIELD_CUENTA_CARGO = "CUENTA_CARGO";
	public static final String FIELD_TIPO_CUENTA_BENEFICIARIO = "TIPO_CUENTA_BENEFICIARIO";
	public static final String FIELD_CUENTA_ABONO = "CUENTA_ABONO";
	public static final String FIELD_TIPO_PERSONA = "TIPO_PERSONA";
	public static final String FIELD_NOMBRE_BENEFICIARIO = "NOMBRE_BENEFICIARIO";
	public static final String FIELD_RFC = "RFC";
	public static final String FIELD_CURP = "CURP";
	public static final String FIELD_DIVISA = "DIVISA";
	public static final String FIELD_IMPORTE = "IMPORTE";
	public static final String FIELD_IVA = "IVA";
	public static final String FIELD_CONCEPTO = "CONCEPTO";
	public static final String FIELD_REFERENCIA = "REFERENCIA";
	public static final String FIELD_CORREO_ELECTRONICO = "CORREO_ELECTRONICO";
	public static final String FIELD_NUMERO_CELULAR = "NUMERO_CELULAR";	
	public static final String FIELD_BANCO = "BANCO";
	public static final String FIELD_COMISION = "COMISION";
	public static final String FIELD_IVA_COMISION = "IVA_COMISION";
	public static final String FIELD_CLAVE_RASTREO = "CLAVE_RASTREO";
	public static final String FIELD_FOLIO_OPERACION = "FOLIO_OPERACION";
	public static final String FIELD_USUARIO = "USUARIO";
	public static final String FIELD_ESTADO_OPERACION = "ESTADO_OPERACION";
	
	@LayoutField(name = FIELD_TIPO_MOVIMIENTO, title = "Tipo de Movimiento", length = 1)
	private SimpleStringProperty tipoMovimiento;

	@LayoutField(name = FIELD_APLICACION, title = "Aplicación", length = 1)
	private SimpleStringProperty aplicacion;

	@LayoutField(name = FIELD_FECHA, title = "Fecha", length = 10)
	private SimpleStringProperty fecha;

	@LayoutField(name = FIELD_TIPO_TRANSACCION, title = "Tipo de transacción", length = 2)
	private SimpleStringProperty tipoTransaccion;

	@LayoutField(name = FIELD_CUENTA_CARGO, title = "Cuenta de Cargo", length = 11)
	private SimpleStringProperty cuentaCargo;

	@LayoutField(name = FIELD_TIPO_CUENTA_BENEFICIARIO, title = "Tipo Cuenta Beneficiario", length = 2)
	private SimpleStringProperty tipoCuentaBeneficiario;

	@LayoutField(name = FIELD_CUENTA_ABONO, title = "Cuenta abono", length = 18)
	private SimpleStringProperty cuentaAbono;

	@LayoutField(name = FIELD_TIPO_PERSONA, title = "Tipo Persona", length = 2)
	private SimpleStringProperty tipoPersona;

	@LayoutField(name = FIELD_NOMBRE_BENEFICIARIO, title = "Nombre Beneficiario", length = 40)
	private SimpleStringProperty nombre;

	@LayoutField(name = FIELD_RFC, title = "Rfc", length = 13)
	private SimpleStringProperty rfc;

	@LayoutField(name = FIELD_CURP, title = "Curp", length = 18)
	private SimpleStringProperty curp;

	@LayoutField(name = FIELD_DIVISA, title = "Divisa", length = 3)
	private SimpleStringProperty divisa;

	@LayoutField(name = FIELD_IMPORTE, title = "Importe", length = 15)
	private SimpleStringProperty importe;

	@LayoutField(name = FIELD_IVA, title = "Iva", length = 15)
	private SimpleStringProperty iva;

	@LayoutField(name = FIELD_CONCEPTO, title = "Concepto", length = 40)
	private SimpleStringProperty concepto;

	@LayoutField(name = FIELD_REFERENCIA, title = "Referencia", length = 20)
	private SimpleStringProperty referencia;

	@LayoutField(name = FIELD_CORREO_ELECTRONICO, title = "Correo Electronico", length = 60)
	private SimpleStringProperty correoElectronico;

	@LayoutField(name = FIELD_NUMERO_CELULAR, title = "Numero Celular", length = 10)
	private SimpleStringProperty numeroCelular;

	private String detalleOperacion;

	@LayoutField(name = FIELD_BANCO, title = "Banco", length = 30)
	private SimpleStringProperty banco;

	@LayoutField(name = FIELD_COMISION, title = "Comisión", length = 15)
	private SimpleStringProperty comision;

	@LayoutField(name = FIELD_IVA_COMISION, title = "IVA Comisión", length = 15)
	private SimpleStringProperty ivaComision;

	@LayoutField(name = FIELD_CLAVE_RASTREO, title = "Clade de rastreo", length = 18)
	private SimpleStringProperty claveRastreo;

	@LayoutField(name = FIELD_FOLIO_OPERACION, title = "Folio de la operación", length = 21)
	private SimpleStringProperty folioOperacion;

	@LayoutField(name = FIELD_USUARIO, title = "Usuario", length = 10)
	private SimpleStringProperty usuario;

	@LayoutField(name = FIELD_ESTADO_OPERACION, title = "Estado de la operación", length = 30)
	private SimpleStringProperty estadoOperacion;

	private SimpleBooleanProperty comprobante;
	/**
	 * 
	 */
	public DispersionDefinitiva() {
		tipoMovimiento = new SimpleStringProperty();
		aplicacion = new SimpleStringProperty();
		fecha = new SimpleStringProperty();
		tipoTransaccion = new SimpleStringProperty();
		cuentaCargo = new SimpleStringProperty();
		tipoCuentaBeneficiario = new SimpleStringProperty();
		cuentaAbono = new SimpleStringProperty();
		tipoPersona = new SimpleStringProperty();
		nombre = new SimpleStringProperty();
		rfc = new SimpleStringProperty();
		curp = new SimpleStringProperty();
		divisa = new SimpleStringProperty();
		importe = new SimpleStringProperty();
		iva = new SimpleStringProperty();
		concepto = new SimpleStringProperty();
		referencia = new SimpleStringProperty();
		correoElectronico = new SimpleStringProperty();
		numeroCelular = new SimpleStringProperty();
		banco = new SimpleStringProperty();
		comision = new SimpleStringProperty();
		ivaComision = new SimpleStringProperty();
		claveRastreo = new SimpleStringProperty();
		folioOperacion = new SimpleStringProperty();
		usuario = new SimpleStringProperty();
		estadoOperacion = new SimpleStringProperty();
	}

	/**
	 * @return the banco
	 */
	public String getBanco() {
		return banco.get();
	}

	/**
	 * @param banco the banco to set
	 */
	public void setBanco(String banco) {
		this.banco.set(banco);
	}

	/**
	 * @return the comision
	 */
	public String getComision() {
		return comision.get();
	}

	/**
	 * @param comision the comision to set
	 */
	public void setComision(String comision) {
		this.comision.set(comision);
	}

	/**
	 * @return the ivaComision
	 */
	public String getIvaComision() {
		return ivaComision.get();
	}

	/**
	 * @param ivaComision the ivaComision to set
	 */
	public void setIvaComision(String ivaComision) {
		this.ivaComision.set(ivaComision);
	}

	/**
	 * @return the claveRastreo
	 */
	public String getClaveRastreo() {
		return claveRastreo.get();
	}

	/**
	 * @param claveRastreo the claveRastreo to set
	 */
	public void setClaveRastreo(String claveRastreo) {
		this.claveRastreo.set(claveRastreo);
	}

	/**
	 * @return the folioOperacion
	 */
	public String getFolioOperacion() {
		return folioOperacion.get();
	}

	/**
	 * @param folioOperacion the folioOperacion to set
	 */
	public void setFolioOperacion(String folioOperacion) {
		this.folioOperacion.set(folioOperacion);
	}

	/**
	 * @return the usuario
	 */
	public String getUsuario() {
		return usuario.get();
	}

	/**
	 * @param usuario the usuario to set
	 */
	public void setUsuario(String usuario) {
		this.usuario.set(usuario);
	}

	/**
	 * @return the estadoOperacion
	 */
	public String getEstadoOperacion() {
		return estadoOperacion.get();
	}

	/**
	 * @param estadoOperacion the estadoOperacion to set
	 */
	public void setEstadoOperacion(String estadoOperacion) {
		this.estadoOperacion.set(estadoOperacion);
	}

	public String getTipoMovimiento() {
		return tipoMovimiento.get();
	}

	public void setTipoMovimiento(String tipoMovimiento) {
		this.tipoMovimiento.set(tipoMovimiento);
	}

	public String getAplicacion() {
		return aplicacion.get();
	}

	public void setAplicacion(String aplicacion) {
		this.aplicacion.set(aplicacion);
	}

	public String getFecha() {
		return fecha.get();
	}

	public void setFecha(String fecha) {
		this.fecha.set(fecha);
	}

	public String getTipoTransaccion() {
		return tipoTransaccion.get();
	}

	public void setTipoTransaccion(String tipoTransaccion) {
		this.tipoTransaccion.set(tipoTransaccion);
	}

	public String getCuentaCargo() {
		return cuentaCargo.get();
	}

	public void setCuentaCargo(String cuentaCargo) {
		this.cuentaCargo.set(cuentaCargo);
	}

	public String getTipoCuentaBeneficiario() {
		return tipoCuentaBeneficiario.get();
	}

	public void setTipoCuentaBeneficiario(String tipoCuentaBeneficiario) {
		this.tipoCuentaBeneficiario.set(tipoCuentaBeneficiario);
	}

	public String getCuentaAbono() {
		return cuentaAbono.get();
	}

	public void setCuentaAbono(String cuentaAbono) {
		this.cuentaAbono.set(cuentaAbono);
	}

	public String getTipoPersona() {
		return tipoPersona.get();
	}

	public void setTipoPersona(String tipoPersona) {
		this.tipoPersona.set(tipoPersona);
	}

	public String getNombre() {
		return nombre.get();
	}

	public void setNombre(String nombre) {
		this.nombre.set(nombre);
	}

	public String getRfc() {
		return rfc.get();
	}

	public void setRfc(String rfc) {
		this.rfc.set(rfc);
	}

	public String getCurp() {
		return curp.get();
	}

	public void setCurp(String curp) {
		this.curp.set(curp);
	}

	public String getDivisa() {
		return divisa.get();
	}

	public void setDivisa(String divisa) {
		this.divisa.set(divisa);
	}

	public String getImporte() {
		return importe.get();
	}

	public void setImporte(String importe) {
		this.importe.set(importe);
	}

	public String getIva() {
		return iva.get();
	}

	public void setIva(String iva) {
		this.iva.set(iva);
	}

	public String getConcepto() {
		return concepto.get();
	}

	public void setConcepto(String concepto) {
		this.concepto.set(concepto);
	}

	public String getReferencia() {
		return referencia.get();
	}

	public void setReferencia(String referencia) {
		this.referencia.set(referencia);
	}

	public String getCorreoElectronico() {
		return correoElectronico.get();
	}

	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico.set(correoElectronico);
	}

	public String getNumeroCelular() {
		return numeroCelular.get();
	}

	public void setNumeroCelular(String numeroCelular) {
		this.numeroCelular.set(numeroCelular);
	}

	public String getDetalleOperacion() {
		return detalleOperacion;
	}

	public void setDetalleOperacion(String detalleOperacion) {
		this.detalleOperacion = detalleOperacion;
	}

	public Boolean getComprobante() {
		return comprobante.getValue();
	}

	public void setComprobante(Boolean comprobante) {
		this.comprobante.set(comprobante);
	}

	
}
