package mx.com.bsmexico.customertool.impresion.plugin;

import java.util.List;

import org.apache.commons.lang3.ArrayUtils;

import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import mx.com.bsmexico.customertool.api.exporter.ExportSource;
import mx.com.bsmexico.customertool.api.importer.ImportTarget;
import mx.com.bsmexico.customertool.api.layouts.control.DefaultLayoutTable;
import mx.com.bsmexico.customertool.api.layouts.model.validation.LayoutModelValidator;

/**
 * @author jchr
 *
 */
public class DispersionDefinitivaTable extends DefaultLayoutTable<DispersionDefinitiva>
		implements ImportTarget<DispersionDefinitiva>, ExportSource<DispersionDefinitiva> {

	protected DispersionDefinitivaTable(final Class<DispersionDefinitiva> type) {
		super(type);
	}
	
	public DispersionDefinitivaTable() throws IllegalArgumentException, InstantiationError {
		super(DispersionDefinitiva.class);

	}

	/**
	 * @throws Exception
	 */
	@Override
	@SuppressWarnings({ "rawtypes", "unchecked" })
	protected void setColumns() throws Exception {
		String[] ids = getFieldOrder();
		if (!ArrayUtils.isEmpty(ids)) {
			TableColumn ct = null;
			for (String id : ids) {
				ct = columnFactory.getColumn(id, 100);
				ct.prefWidthProperty().bind(widthProperty().multiply(0.15));
				getColumns().add(ct);
			}
			final TableColumn ctSelectAll = new TableColumn();
			final CheckBox check = new CheckBox();
			final TableView<DispersionDefinitiva> table = this;
			check.setOnAction(e -> {
				boolean selected = ((CheckBox) e.getSource()).isSelected();
				List<DispersionDefinitiva> items = table.getItems();
				if (items != null && items.size() > 0) {
					items.forEach(i -> i.setComprobante(selected));
				}

			});
			ctSelectAll.setGraphic(check);
			ct = new TableColumn();
			final Label firstNameLabel = new Label("Comprobante");
			ct.setGraphic(firstNameLabel);
			ct.setId("Comprobante");
			ct.setPrefWidth(40);
			ct.setCellFactory(column -> new CheckBoxTableCell<>());
			ct.setCellValueFactory(new PropertyValueFactory<DispersionDefinitiva, Boolean>("comprobante"));
			ct.setEditable(true);
			ct.getColumns().add(ctSelectAll);
			getColumns().add(ct);
		}

	}

	@Override
	protected String[] getFieldOrder() {
		return new String[] { DispersionDefinitiva.FIELD_CUENTA_CARGO, DispersionDefinitiva.FIELD_CUENTA_ABONO,
				DispersionDefinitiva.FIELD_DIVISA, DispersionDefinitiva.FIELD_IMPORTE,
				DispersionDefinitiva.FIELD_CONCEPTO, DispersionDefinitiva.FIELD_NOMBRE_BENEFICIARIO,
				DispersionDefinitiva.FIELD_TIPO_MOVIMIENTO, DispersionDefinitiva.FIELD_ESTADO_OPERACION };
	}

	@Override
	public boolean validateTable() throws Exception {
		return true;
	}

	@Override
	public boolean validateModel(DispersionDefinitiva model) throws Exception {
		return true;
	}

	@Override
	public boolean isActiveModel(DispersionDefinitiva model) throws Exception {
		return true;
	}

	@Override
	protected void polulate() {
		// No implement
	}

	@Override
	protected void addRow() {
		// No implement
	}

	@Override
	public List<DispersionDefinitiva> getData() {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void setData(List<DispersionDefinitiva> data) {
		if (data != null) {
			getItems().addAll(data);
			try {
				final LayoutModelValidator<DispersionDefinitiva> validator = (LayoutModelValidator<DispersionDefinitiva>) this.metamodel
						.getValidator();
				if (validator != null) {
					if (validator.isValid(data)) {
						getItems().addAll(data);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
