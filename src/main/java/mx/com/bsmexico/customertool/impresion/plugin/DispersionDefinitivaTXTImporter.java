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
				dispersion.setImporte(StringUtils.isNotEmpty(record.get(14))?decimalFormat.format(Double.parseDouble(record.get(14))):"");
				dispersion.setIva(StringUtils.isNotEmpty(record.get(15))?decimalFormat.format(Double.parseDouble(record.get(15))):"");
				dispersion.setComision(StringUtils.isNotEmpty(record.get(16))?decimalFormat.format(Double.parseDouble(record.get(16))):"");
				dispersion.setIvaComision(StringUtils.isNotEmpty(record.get(17))?decimalFormat.format(Double.parseDouble(record.get(17))):"");
				dispersion.setConcepto(record.get(18));
				dispersion.setReferencia(record.get(19));
				dispersion.setCorreoElectronico(record.get(20));
				dispersion.setNumeroCelular(record.get(21));
				dispersion.setClaveRastreo(record.get(22));
				dispersion.setFolioOperacion(record.get(23));
				dispersion.setUsuario(record.get(24));
				dispersion.setEstadoOperacion(record.get(25));
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
		/*positions.add(new RecordPosition(0, 2));
		positions.add(new RecordPosition(2, 3));
		positions.add(new RecordPosition(3, 4));
		positions.add(new RecordPosition(4, 20));
		positions.add(new RecordPosition(20, 22));
		positions.add(new RecordPosition(22, 34));
		positions.add(new RecordPosition(34, 36));
		positions.add(new RecordPosition(36, 54));
		positions.add(new RecordPosition(54, 84));
		positions.add(new RecordPosition(84, 86));
		positions.add(new RecordPosition(86, 126));
		positions.add(new RecordPosition(126, 139));
		positions.add(new RecordPosition(139, 157));
		positions.add(new RecordPosition(157, 160));
		positions.add(new RecordPosition(160, 175));
		positions.add(new RecordPosition(175, 190));
		positions.add(new RecordPosition(190, 205));
		positions.add(new RecordPosition(205, 220));
		positions.add(new RecordPosition(220, 260));
		positions.add(new RecordPosition(260, 280));
		positions.add(new RecordPosition(280, 340));
		positions.add(new RecordPosition(340, 350));
		positions.add(new RecordPosition(350, 368));
		positions.add(new RecordPosition(368, 389));
		positions.add(new RecordPosition(389, 399));
		positions.add(new RecordPosition(399, 428));*/
		positions.add(new RecordPosition(0, 2));
		positions.add(new RecordPosition(2, 3));
		positions.add(new RecordPosition(3, 4));
		positions.add(new RecordPosition(4, 20));
		positions.add(new RecordPosition(20, 22));
		positions.add(new RecordPosition(22, 33));
		positions.add(new RecordPosition(33, 35));
		positions.add(new RecordPosition(35, 53));
		positions.add(new RecordPosition(53, 83));
		positions.add(new RecordPosition(83, 85));
		positions.add(new RecordPosition(85, 125));
		positions.add(new RecordPosition(125, 138));
		positions.add(new RecordPosition(138, 156));
		positions.add(new RecordPosition(156, 159));
		positions.add(new RecordPosition(159, 174));
		positions.add(new RecordPosition(174, 189));
		positions.add(new RecordPosition(189, 204));
		positions.add(new RecordPosition(204, 219));
		positions.add(new RecordPosition(219, 259));
		positions.add(new RecordPosition(259, 279));
		positions.add(new RecordPosition(279, 339));
		positions.add(new RecordPosition(339, 349));
		positions.add(new RecordPosition(349, 367));
		positions.add(new RecordPosition(367, 388));
		positions.add(new RecordPosition(388, 398));
		positions.add(new RecordPosition(398, 427));
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
