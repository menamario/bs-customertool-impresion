package mx.com.bsmexico.customertool.impresion.plugin;

import java.util.List;

import mx.com.bsmexico.customertool.api.exporter.CSVImporter;
import mx.com.bsmexico.customertool.api.exporter.ImportTarget;

/**
 * @author jchr
 *
 */
public class ImpresionImporter extends CSVImporter<Impresion> {
	
	public ImpresionImporter(ImportTarget<Impresion> target) throws IllegalArgumentException {
		super(target);	
	}

	@Override
	protected Impresion getInstance(final List<String> record) {
		final Impresion beneficiario = new Impresion();		
		beneficiario.setCuenta(record.get(0));
		//beneficiario.setEstatus("cuenta", validator.cuenta().test(beneficiario.getCuenta()));
		beneficiario.setNumLinea(record.get(1));
		//beneficiario.setEstatus("numLinea", validator.numLinea().test(beneficiario.getNumLinea()));
		beneficiario.setBancoParticipante(record.get(2));
		//beneficiario.setEstatus("bancoParticipante",
		//		validator.bancoParticipante().test(beneficiario.getBancoParticipante()));
		beneficiario.setTipoCuenta(record.get(3));
		//beneficiario.setEstatus("tipoCuenta", validator.tipoCuenta().test(beneficiario.getTipoCuenta()));
		beneficiario.setMoneda(record.get(4));
		//beneficiario.setEstatus("moneda", validator.moneda().test(beneficiario.getMoneda()));
		beneficiario.setImporteMaximo(record.get(5));
		//beneficiario.setEstatus("importeMaximo", validator.importeMaximo().test(beneficiario.getImporteMaximo()));
		beneficiario.setTipoPersona(record.get(6));
		//beneficiario.setEstatus("tipoPersona", validator.tipoPersona().test(beneficiario.getTipoPersona()));
		beneficiario.setRazonSocial(record.get(7));
		//beneficiario.setEstatus("razonSocial", validator.razonSocial().test(beneficiario.getRazonSocial()));
		beneficiario.setNombre(record.get(8));
		//beneficiario.setEstatus("nombre", validator.nombre().test(beneficiario.getNombre()));
		beneficiario.setApellidoMaterno(record.get(9));
		//beneficiario.setEstatus("apellidoMaterno", validator.apellidoMaterno().test(beneficiario.getApellidoMaterno()));
		beneficiario.setApellidoPaterno(record.get(10));
		//beneficiario.setEstatus("apellidoPaterno", validator.apellidoPaterno().test(beneficiario.getApellidoPaterno()));
		return beneficiario;
	}

	@Override
	protected String[] getHeader() {
		return null;/*new String[] { "CuentaBeneficiario", "NumeroLinea", "BancoParticipante", "TipoCuenta", "Moneda",
				"ImporteMaximo", "TipoPersona", "RazonSocial", "Nombre", "ApellidoPaterno", "ApellidoMaterno" };*/
	}

}
