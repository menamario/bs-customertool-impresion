package mx.com.bsmexico.customertool.impresion.plugin;

import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import mx.com.bsmexico.customertool.api.exporter.ExportSource;
import mx.com.bsmexico.customertool.api.exporter.ImportTarget;
import mx.com.bsmexico.customertool.api.layouts.control.EditableLayoutTable;
import mx.com.bsmexico.customertool.api.layouts.model.validation.LayoutModelValidator;

public class ImpresionTable extends EditableLayoutTable<Impresion>
		implements ImportTarget<Impresion>, ExportSource<Impresion> {

	private final int INITIAL_CAPACITY = 50;

	public ImpresionTable() throws IllegalArgumentException, InstantiationError {
		super(Impresion.class);

	}

	protected void polulate() {
		for (int idx = 0; idx <= INITIAL_CAPACITY; idx++) {
			this.data.add(new Impresion());
		}
	}

	@Override
	protected void addRow() {
		getItems().add(new Impresion());

	}

	@Override
	public void setData(List<Impresion> data) {
		ObservableList<Impresion> observableList = FXCollections.observableList(data);
		setItems(observableList);

	}

	@Override
	public List<Impresion> getData() {
		List<Impresion> exportList = new ArrayList<Impresion>();
		try{
			for(Impresion r:getItems()){
				if(isActiveModel(r)){
					exportList.add(r);
				}
			}
		}catch (Exception e) {
			// TODO: handle exception
		}
		return exportList;
	}

	@Override
	protected String[] getFieldOrder() {
		return new String[] { Impresion.FIELD_CUENTA_BENEFICIARIO, Impresion.FIELD_NUMERO_LINEA_BENEFICIARIO,
				Impresion.FIELD_BANCO_PARTICIPANTE, Impresion.FIELD_TIPO_CUENTA, Impresion.FIELD_MONEDA,
				Impresion.FIELD_IMPORTE_MAXIMO_PAGAR, Impresion.FIELD_TIPO_PERSONA,
				Impresion.FIELD_RAZON_SOCIAL, Impresion.FIELD_NOMBRE, Impresion.FIELD_APELLIDO_PATERNO,
				Impresion.FIELD_APELLIDO_MATERNO };
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean validateTable() throws Exception {
		boolean isValid = true;
		if (this.metamodel.getValidator() != null) {
			this.validated = true;
			isValid = ((LayoutModelValidator<Impresion>) this.metamodel.getValidator()).isValid(getData());
			if (!isValid) {
				refresh();
			}
		}
		return isValid;
	}

	@Override
	public boolean validateModel(Impresion model) throws Exception {
		boolean isValid = true;
		if (this.metamodel.getValidator() != null) {
			isValid = ((LayoutModelValidator<Impresion>) this.metamodel.getValidator()).isValid(model);
			if (!isValid) {
				refresh();
			}
		}
		return isValid;
	}

	@Override
	public boolean isActiveModel(Impresion model) throws Exception {
		boolean isValid = true;
		if (this.metamodel.getValidator() != null) {
			isValid = ((LayoutModelValidator<Impresion>) this.metamodel.getValidator()).isActive(model);
			if (!isValid) {
				refresh();
			}
		}
		return isValid;
	}

}
