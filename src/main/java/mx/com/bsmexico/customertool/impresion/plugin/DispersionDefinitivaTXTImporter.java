package mx.com.bsmexico.customertool.impresion.plugin;

import java.io.File;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import mx.com.bsmexico.customertool.api.process.FixPositionImporter;
import mx.com.bsmexico.customertool.api.process.ImportTarget;
import mx.com.bsmexico.customertool.api.process.RecordPosition;

/**
 * @author jchr
 *
 */
public class DispersionDefinitivaTXTImporter extends FixPositionImporter<DispersionDefinitiva> {

	private String cliente;
	String pattern = "############.00";
	DecimalFormat decimalFormat = new DecimalFormat(pattern);

	public DispersionDefinitivaTXTImporter(ImportTarget<DispersionDefinitiva> target) throws IllegalArgumentException {
		super(target);
		withTrim(true);
	}

	@Override
	public synchronized void importFile(File file) throws Exception {
		super.importFile(file);
	}

	@Override
	protected DispersionDefinitiva getInstance(final List<String> record) {
		DispersionDefinitiva dispersion = null;
		if (record != null && record.size() > 0) {
			if (record.get(0).startsWith("DE")) {
				dispersion = new DispersionDefinitiva();
				dispersion.setTipoMovimiento(record.get(1));
				dispersion.setAplicacion(record.get(2));
				// se realiza el replace para que el formato del txt (yyyymmdd hhmmss) coincida
				// con el formato en
				// el csv (yyyymmddhhmmss)
				dispersion.setFecha(record.get(3).replace(" ", ""));
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
				dispersion.setImporte(StringUtils.isNotEmpty(record.get(14))
						? decimalFormat.format(Double.parseDouble(record.get(14)))
						: "");
				dispersion.setIva(StringUtils.isNotEmpty(record.get(15))
						? decimalFormat.format(Double.parseDouble(record.get(15)))
						: "");
				dispersion.setComision(StringUtils.isNotEmpty(record.get(16))
						? decimalFormat.format(Double.parseDouble(record.get(16)))
						: "");
				dispersion.setIvaComision(StringUtils.isNotEmpty(record.get(17))
						? decimalFormat.format(Double.parseDouble(record.get(17)))
						: "");
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
					this.cliente = this.extractClientName(record);
				}
			}
		}
		return dispersion;
	}

	@Override
	protected List<RecordPosition> getFixPositions() {
		List<RecordPosition> positions = new ArrayList<>();
		positions.add(new RecordPosition(0, 2));
		positions.add(new RecordPosition(2, 3));
		positions.add(new RecordPosition(3, 4));
		positions.add(new RecordPosition(4, 19));
		positions.add(new RecordPosition(19, 21));
		positions.add(new RecordPosition(21, 32));
		positions.add(new RecordPosition(32, 34));
		positions.add(new RecordPosition(34, 52));
		positions.add(new RecordPosition(52, 82));
		positions.add(new RecordPosition(82, 84));
		positions.add(new RecordPosition(84, 124));
		positions.add(new RecordPosition(124, 137));
		positions.add(new RecordPosition(137, 155));
		positions.add(new RecordPosition(155, 158));
		positions.add(new RecordPosition(158, 173));
		positions.add(new RecordPosition(173, 188));
		positions.add(new RecordPosition(188, 203));
		positions.add(new RecordPosition(203, 218));
		positions.add(new RecordPosition(218, 258));
		positions.add(new RecordPosition(258, 278));
		positions.add(new RecordPosition(278, 338));
		positions.add(new RecordPosition(338, 348));
		positions.add(new RecordPosition(348, 366));
		positions.add(new RecordPosition(366, 387));
		positions.add(new RecordPosition(387, 397));
		positions.add(new RecordPosition(397, 426));
		positions.add(new RecordPosition(426, 459));
		return positions;
	}

	/**
	 * @param record
	 * @return
	 */
	private String extractClientName(final List<String> record) {
		String name = StringUtils.EMPTY;
		StringBuffer buffer = new StringBuffer();
		for (int index = 0; index < record.size(); index++) {
			buffer.append(record.get(index));
		}
		final StringBuffer client = new StringBuffer();
		for (int index = buffer.length() - 1; index >= 0; index--) {
			char elem = buffer.charAt(index);
			if (Character.isDigit(elem)) {
				break;
			}
			client.append(elem);
		}
		name = (client.length() > 0) ? this.cliente = client.reverse().toString().trim() : StringUtils.EMPTY;
		return name;
	}

}
