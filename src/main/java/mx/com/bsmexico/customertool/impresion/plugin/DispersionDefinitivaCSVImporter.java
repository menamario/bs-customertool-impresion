package mx.com.bsmexico.customertool.impresion.plugin;

import java.util.List;

import mx.com.bsmexico.customertool.api.process.CSVImporter;
import mx.com.bsmexico.customertool.api.process.ImportTarget;

/**
 * @author jchr
 *
 */
public class DispersionDefinitivaCSVImporter extends CSVImporter<DispersionDefinitiva> {

	public DispersionDefinitivaCSVImporter(ImportTarget<DispersionDefinitiva> target) throws IllegalArgumentException {
		super(target);
	}

	@Override
	protected DispersionDefinitiva getInstance(final List<String> record) {
		DispersionDefinitiva dispersion = null;		
		if("DE".equals(record.get(0))) {
			dispersion = new DispersionDefinitiva();
			dispersion.setTipoMovimiento(record.get(1));
			dispersion.setAplicacion(record.get(2));
			dispersion.setFecha(record.get(3));
			dispersion.setTipoTransaccion(record.get(4));
			dispersion.setCuentaCargo(record.get(5));
			dispersion.setTipoCuentaBeneficiario(record.get(6));
			dispersion.setCuentaAbono(record.get(7));
			dispersion.setBanco(record.get(8));		
			dispersion.setTipoPersona(record.get(9));
			dispersion.setNombre(record.get(10));
			dispersion.setRfc(record.get(11));
			dispersion.setCurp(record.get(12));
			dispersion.setDivisa(record.get(13));
			dispersion.setImporte(record.get(14));
			dispersion.setIva(record.get(15));
			dispersion.setComision(record.get(16));
			dispersion.setIvaComision(record.get(17));				
			dispersion.setConcepto(record.get(18));
			dispersion.setReferencia(record.get(19));
			dispersion.setCorreoElectronico(record.get(20));
			dispersion.setNumeroCelular(record.get(21));		
			dispersion.setClaveRastreo(record.get(22));
			dispersion.setFolioOperacion(record.get(23));
			dispersion.setUsuario(record.get(24));
			dispersion.setEstadoOperacion(record.get(25));
		}			
		return dispersion;
	}

	@Override
	protected String[] getHeader() {
		return null;
	}
	
	@Override
	protected Character getCustomDelimiter() {
		return '|';
	}

}
