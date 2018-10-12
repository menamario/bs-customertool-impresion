package mx.com.bsmexico.customertool.impresion.plugin;

import java.io.File;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.xml.bind.DatatypeConverter;

import org.apache.commons.lang.StringUtils;

import mx.com.bsmexico.customertool.api.process.CSVImporter;
import mx.com.bsmexico.customertool.api.process.ImportTarget;

/**
 * @author jchr
 *
 */
public class DispersionDefinitivaCSVImporter extends CSVImporter<DispersionDefinitiva> {

	private String cliente;

	public DispersionDefinitivaCSVImporter(ImportTarget<DispersionDefinitiva> target) throws IllegalArgumentException {
		super(target);
	}

	@Override
	public synchronized void importFile(File file) throws Exception {
		super.importFile(file);
	}

	@Override
	protected DispersionDefinitiva getInstance(final List<String> record) {
		DispersionDefinitiva dispersion = null;
		if (record != null && record.size() > 0) {
			if ("DE".equals(record.get(0))) {
				dispersion = new DispersionDefinitiva();
				dispersion.setValidChecksum(record.get(26).equals(this.generateHash(record)));				
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
				dispersion.setHash(record.get(26));
				dispersion.setCliente(cliente);
			} else {
				if (record.get(0).startsWith("HA")) {
					this.cliente = (StringUtils.isNotBlank(record.get(record.size() - 1)))
							? record.get(record.size() - 1).trim()
							: StringUtils.EMPTY;
				}
			}
		}

		return dispersion;
	}
	
	/**
	 * @param item
	 * @return
	 */
	private String generateHash(final List<String> record) {
		String hash = null;
		if (record != null && record.size() > 0) {
			final StringBuffer input = new StringBuffer();
			input.append(record.get(14)).append(record.get(9)).append(record.get(25)).append(record.get(3))
					.append(record.get(6)).append(record.get(5)).append(record.get(19)).append(record.get(7))
					.append(record.get(18)).append(record.get(23)).append(record.get(10));
			MessageDigest md = null;
			try {
				md = MessageDigest.getInstance("MD5");
				md.update(input.toString().getBytes());
				final byte[] digest = md.digest();
				hash = DatatypeConverter.printHexBinary(digest);
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			}
		}
		return hash;
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
