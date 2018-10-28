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
		if (checkBox != null && registro != null) {
			if (!(EstadoDispersion.LIQUIDADO.name().equals(registro.getEstadoOperacion())
					/*&& registro.isValidChecksum()*/)) {
				this.setGraphic(null);
			}
		}
	}
}
