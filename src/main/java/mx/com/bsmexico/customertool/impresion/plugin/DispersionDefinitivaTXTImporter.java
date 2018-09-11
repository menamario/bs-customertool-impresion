package mx.com.bsmexico.customertool.impresion.plugin;

import java.util.List;

import mx.com.bsmexico.customertool.api.importer.FixPositionImporter;
import mx.com.bsmexico.customertool.api.importer.ImportTarget;

/**
 * @author jchr
 *
 */
public class DispersionDefinitivaTXTImporter extends FixPositionImporter<DispersionDefinitiva> {

	public DispersionDefinitivaTXTImporter(ImportTarget<DispersionDefinitiva> target) throws IllegalArgumentException {
		super(target);
	}

	@Override
	protected DispersionDefinitiva getInstance(final List<String> record) {
		DispersionDefinitiva dispersion = null;
		if(record.get(0).startsWith("DE")) {
			final StringBuffer buffer = new StringBuffer(record.get(0));
			dispersion = new DispersionDefinitiva();
			dispersion.setTipoMovimiento(buffer.substring(2, 3));
			dispersion.setAplicacion(buffer.substring(3, 4));
			dispersion.setFecha(buffer.substring(4, 21));
			dispersion.setTipoTransaccion(buffer.substring(21, 23));
			dispersion.setCuentaCargo(buffer.substring(23, 35));
			dispersion.setTipoCuentaBeneficiario(buffer.substring(35, 37));
			dispersion.setCuentaAbono(buffer.substring(37, 56));
			dispersion.setBanco(buffer.substring(56, 87));		
			dispersion.setTipoPersona(buffer.substring(87, 89));
			dispersion.setNombre(buffer.substring(89, 130));
			dispersion.setRfc(buffer.substring(130, 0));
			dispersion.setCurp(buffer.substring(0, 0));
			dispersion.setDivisa(buffer.substring(0, 0));
			dispersion.setImporte(buffer.substring(0, 0));
			dispersion.setIva(buffer.substring(0, 0));
			dispersion.setComision(buffer.substring(0, 0));
			dispersion.setIvaComision(buffer.substring(0, 0));				
			dispersion.setConcepto(buffer.substring(0, 0));
			dispersion.setReferencia(buffer.substring(0, 0));
			dispersion.setCorreoElectronico(buffer.substring(0, 0));
			dispersion.setNumeroCelular(buffer.substring(0, 0));
			dispersion.setClaveRastreo(buffer.substring(0, 0));
			dispersion.setFolioOperacion(buffer.substring(0, 0));
			dispersion.setUsuario(buffer.substring(0, 0));
			dispersion.setEstadoOperacion(buffer.substring(0, 0));			
		}			
		return dispersion;
	}

	@Override
	protected List<FixPosition> getFixPositions() {
		return null;
	}

	

}
