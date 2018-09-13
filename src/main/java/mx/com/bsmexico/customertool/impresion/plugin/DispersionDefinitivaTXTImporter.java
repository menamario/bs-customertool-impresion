package mx.com.bsmexico.customertool.impresion.plugin;

import java.util.ArrayList;
import java.util.List;

import mx.com.bsmexico.customertool.api.process.FixPositionImporter;
import mx.com.bsmexico.customertool.api.process.ImportTarget;
import mx.com.bsmexico.customertool.api.process.RecordPosition;

/**
 * @author jchr
 *
 */
public class DispersionDefinitivaTXTImporter extends FixPositionImporter<DispersionDefinitiva> {

	public DispersionDefinitivaTXTImporter(ImportTarget<DispersionDefinitiva> target) throws IllegalArgumentException {
		super(target);
		withTrim(true);
	}

	@Override
	protected DispersionDefinitiva getInstance(final List<String> record) {
		DispersionDefinitiva dispersion = null;
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
	protected List<RecordPosition> getFixPositions() {
		List<RecordPosition> positions = new ArrayList<>();
		positions.add(new RecordPosition(0, 2));
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
		positions.add(new RecordPosition(399, 428));
		return positions;
	}

}
