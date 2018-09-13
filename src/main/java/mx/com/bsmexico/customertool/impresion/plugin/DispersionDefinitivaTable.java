package mx.com.bsmexico.customertool.impresion.plugin;

import java.util.List;

import org.apache.commons.lang3.ArrayUtils;

import javafx.geometry.Pos;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import mx.com.bsmexico.customertool.api.layouts.control.CheckboxCell;
import mx.com.bsmexico.customertool.api.layouts.control.DefaultLayoutTable;
import mx.com.bsmexico.customertool.api.layouts.model.validation.LayoutModelValidator;
import mx.com.bsmexico.customertool.api.process.ExportSource;
import mx.com.bsmexico.customertool.api.process.ImportTarget;

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
			//final TableColumn ctSelectAll = new TableColumn();
			final CheckBox check = new CheckBox();
			final TableView<DispersionDefinitiva> table = this;
			check.setOnAction(e -> {
				boolean selected = ((CheckBox) e.getSource()).isSelected();
				List<DispersionDefinitiva> items = table.getItems();
				if (items != null && items.size() > 0) {
					for(DispersionDefinitiva item : items) {
						item.setComprobante(selected);
					}					
				}
				table.refresh();
			});
			final Callback<TableColumn<DispersionDefinitiva, Boolean>, TableCell<DispersionDefinitiva, Boolean>> booleanCellFactory = new Callback<TableColumn<DispersionDefinitiva, Boolean>, TableCell<DispersionDefinitiva, Boolean>>() {
				@Override
				public TableCell<DispersionDefinitiva, Boolean> call(TableColumn<DispersionDefinitiva, Boolean> p) {
					return new CheckboxCell();
				}
			};
			//ctSelectAll.setGraphic(check);
			ct = new TableColumn();
			final Label label = new Label("Comprobante");
			VBox box = new VBox();			
			box.setAlignment(Pos.CENTER);
			box.getChildren().add(label);
			box.getChildren().add(check);
			//ct.setGraphic(label);
			ct.setGraphic(box);
			ct.setId("Comprobante");
			ct.setPrefWidth(100);
			ct.setCellFactory(booleanCellFactory);
			//ct.setCellFactory(column -> new CheckBoxTableCell<>());
			ct.setCellValueFactory(new PropertyValueFactory<DispersionDefinitiva, Boolean>("comprobante"));
			ct.setEditable(true);
			//ct.getColumns().add(ctSelectAll);
			getColumns().add(ct);
			for (String id : ids) {
				ct = columnFactory.getColumn(id, 100);
				ct.prefWidthProperty().bind(widthProperty().multiply(0.15));
				getColumns().add(ct);
			}
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
