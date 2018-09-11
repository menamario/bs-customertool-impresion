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
			dispersion.setTipoMovimiento(buffer.substring(2, 3).trim());
			dispersion.setAplicacion(buffer.substring(3, 4).trim());
			dispersion.setFecha(buffer.substring(4, 20).trim());
			dispersion.setTipoTransaccion(buffer.substring(20, 22).trim());
			dispersion.setCuentaCargo(buffer.substring(22, 34).trim());
			dispersion.setTipoCuentaBeneficiario(buffer.substring(34, 36).trim());
			dispersion.setCuentaAbono(buffer.substring(36, 54).trim());
			dispersion.setBanco(buffer.substring(56, 84).trim());
			dispersion.setTipoPersona(buffer.substring(84, 86).trim());
			dispersion.setNombre(buffer.substring(86, 126).trim());
			dispersion.setRfc(buffer.substring(126, 139).trim());
			dispersion.setCurp(buffer.substring(139, 157).trim());
			dispersion.setDivisa(buffer.substring(157, 160).trim());
			dispersion.setImporte(buffer.substring(160, 175).trim());
			dispersion.setIva(buffer.substring(175, 190).trim());
			
			dispersion.setComision(buffer.substring(190, 205).trim());
			dispersion.setIvaComision(buffer.substring(205, 220).trim());				
			dispersion.setConcepto(buffer.substring(220, 260).trim());
			dispersion.setReferencia(buffer.substring(260, 280).trim());
			dispersion.setCorreoElectronico(buffer.substring(280, 340).trim());
			
			dispersion.setNumeroCelular(buffer.substring(340, 350).trim());
			dispersion.setClaveRastreo(buffer.substring(350, 368).trim());
			dispersion.setFolioOperacion(buffer.substring(368, 389).trim());
			dispersion.setUsuario(buffer.substring(389, 399).trim());
			dispersion.setEstadoOperacion(buffer.substring(399, 428).trim());	
		}			
		return dispersion;
	}

	@Override
	protected List<FixPosition> getFixPositions() {
		return null;
	}

	

}
