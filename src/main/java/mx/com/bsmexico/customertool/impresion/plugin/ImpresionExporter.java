package mx.com.bsmexico.customertool.impresion.plugin;

import java.util.ArrayList;
import java.util.List;

import mx.com.bsmexico.customertool.api.exporter.CSVExporter;
import mx.com.bsmexico.customertool.api.exporter.ExportSource;

public class ImpresionExporter extends CSVExporter<Impresion> {

	/**
	 * @param source
	 */
	public ImpresionExporter(ExportSource<Impresion> source) {
		super(source);
	}

	@Override
	protected Object[] getRecord(final Impresion beneficiario) {
		final List<Object> record = new ArrayList<>();
		record.add(beneficiario.getCuenta());
		record.add(beneficiario.getNumLinea());
		record.add(beneficiario.getBancoParticipante());
		record.add(beneficiario.getTipoCuenta());
		record.add(beneficiario.getMoneda());
		record.add(beneficiario.getImporteMaximo());
		record.add(beneficiario.getTipoPersona());
		record.add(beneficiario.getRazonSocial());
		record.add(beneficiario.getNombre());
		record.add(beneficiario.getApellidoPaterno());
		record.add(beneficiario.getApellidoMaterno());
		return record.toArray();
	}

	@Override
	protected String[] getHeader() {
		return new String[] { "CuentaBeneficiario", "NumeroLinea", "BancoParticipante", "TipoCuenta", "Moneda",
				"ImporteMaximo", "TipoPersona", "RazonSocial", "Nombre", "ApellidoPaterno", "ApellidoMaterno" };
	}

}
