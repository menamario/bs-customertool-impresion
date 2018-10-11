package mx.com.bsmexico.customertool.impresion.plugin;

import mx.com.bsmexico.customertool.api.layouts.control.CheckboxCell;

public class ComprobanteCheckboxCell extends CheckboxCell<DispersionDefinitiva> {

	@Override
	public void updateItem(Boolean item, boolean empty) {
		super.updateItem(item, empty);
		if (!isEmpty()) {
			checkBox.setSelected(item);
		}
		final DispersionDefinitiva registro = (DispersionDefinitiva) this.getTableRow().getItem();
		// checkBox.setDisable((EstadoDispersion.TERMINADO.name().equals(registro.getEstadoOperacion()))
		// ? false : true);
		if (checkBox != null && registro != null) {
			final String hash = DispersionDefinitiva.generateHash(registro);
			if (!EstadoDispersion.LIQUIDADO.name().equals(registro.getEstadoOperacion()) && registro.getHash() != null
					&& registro.getHash().equals(hash)) {
				this.setGraphic(null);
			}
		}
	}
}
