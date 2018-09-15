package mx.com.bsmexico.customertool.impresion.plugin;

import java.awt.print.PrinterJob;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.NodeOrientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import mx.com.bsmexico.customertool.api.Feature;
import mx.com.bsmexico.customertool.api.Layout;
import mx.com.bsmexico.customertool.api.NavRoute;
import mx.com.bsmexico.customertool.api.layouts.model.validation.LayoutValidatorException;

public class OpcionImpresion extends Feature {

	DispersionDefinitivaTable t = null;
	List<DispersionDefinitiva> originalList = new ArrayList<DispersionDefinitiva>();

	private InputStream getImageInput(final String file) throws FileNotFoundException {
		final InputStream input = getClass().getResourceAsStream(file);
		return input;
	}

	@Override
	public Layout getLayout() {

		final NavRoute.BuilderNavRoute navRuoteBuilder = new NavRoute.BuilderNavRoute("TEST");
		NavRoute route = null;
		try {
			route = navRuoteBuilder.addNode("Impresion masiva de comprobantes", "Impresion masiva de comprobantes", 0,
					false, getImageInput("/img/impresion.png")).build();

		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new Layout.LayoutBuilder("l1").route(route).build();
	}

	@Override
	public void launch() {

		getMenuNavigator().hide();
		getDesktop().updatePleca("#e25100", null);

		Pane mainPane = new BorderPane();

		mainPane.setPadding(new Insets(0, 20, 0, 20));

		HBox headerBox1 = new HBox();
		HBox headerBox2 = new HBox();

		ImageView atras = null;
		ImageView importarArchivo = null;
		ImageView instrucciones = null;
		ImageView impresion = null;

		try {
			atras = new ImageView(new Image(this.getImageInput("/img/atras.png")));
			importarArchivo = new ImageView(new Image(this.getImageInput("/img/importarImpresion.png")));
			importarArchivo.setPreserveRatio(true);
			importarArchivo.setFitWidth(70);
			instrucciones = new ImageView(new Image(this.getImageInput("/img/instrucciones.png")));
			instrucciones.setPreserveRatio(true);
			instrucciones.setFitWidth(70);

			impresion = new ImageView(new Image(this.getImageInput("/img/impresion.png")));
			impresion.setPreserveRatio(true);
			impresion.setFitWidth(30);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Button bAtras = new Button();
		Button bInstrucciones = new Button();
		Button bImportarArchivo = new Button();
		Button bImprimir = new Button();

		bAtras.setGraphic(atras);
		bAtras.setStyle("-fx-background-color: transparent;");
		bAtras.setTooltip(new Tooltip("Regresar"));

		bImprimir.setGraphic(impresion);
		bImprimir.setStyle("-fx-background-color: transparent;");
		bImprimir.setTooltip(new Tooltip("Imprimir"));
		bImprimir.setAlignment(Pos.CENTER_RIGHT);

		bInstrucciones.setGraphic(instrucciones);
		bInstrucciones.setText("Instrucciones");
		bInstrucciones.setTextFill(Color.WHITE);
		bInstrucciones.setStyle(
				"-fx-font-family: FranklinGothicLT-Demi;-fx-font-size: 13px;-fx-background-color: transparent;");
		bInstrucciones.setContentDisplay(ContentDisplay.TOP);

		bImportarArchivo.setGraphic(importarArchivo);
		bImportarArchivo.setText("Importar Archivo");
		bImportarArchivo.setTextFill(Color.WHITE);
		bImportarArchivo.setStyle(
				"-fx-font-family: FranklinGothicLT-Demi;-fx-font-size: 13px;-fx-background-color: transparent;");
		bImportarArchivo.setContentDisplay(ContentDisplay.TOP);

		bAtras.setOnMouseClicked(evt -> {
			getMenuNavigator().show();
			getDesktop().setWorkArea(null);
			getDesktop().updatePleca("black", null);
		});

		final FileChooser fileChooser = new FileChooser();
		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Archivos de dispersion", "*D.csv",
				"*P.csv", "*D.txt", "*P.txt");
		fileChooser.getExtensionFilters().add(extFilter);

		headerBox1.getChildren().add(bAtras);
		headerBox1.setSpacing(40);
		Label l = new Label("    Impresion Masiva de Comprobantes    ");
		l.setTextFill(Color.WHITE);
		l.setStyle(
				"-fx-background-color: #e25100;-fx-font-family: FranklinGothicLT-Demi;-fx-font-size: 14px; -fx-border-radius: 0 0 10 10; -fx-background-radius: 0 0 10 10;");
		headerBox1.getChildren().add(l);

		headerBox2.getChildren().add(bInstrucciones);
		headerBox2.getChildren().add(bImportarArchivo);
		headerBox2.setSpacing(100);
		HBox.setHgrow(headerBox2, Priority.ALWAYS);
		headerBox2.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
		headerBox1.getChildren().add(headerBox2);
		//headerBox1.setPadding(new Insets(0, 30, 0, 0));

		HBox hb = new HBox();
		hb.setSpacing(10);
		// hb.getChildren().addAll(lFormato, rbTxt, rbCsv);

		VBox v = new VBox();

		FlowPane fph = new FlowPane();
		fph.setAlignment(Pos.CENTER_RIGHT);

		Label lArchivo = new Label("Nombre de archivo:");
		lArchivo.setTextFill(Color.WHITE);
		lArchivo.setPrefWidth(150);
		Label vArchivo = new Label();
		vArchivo.setTextFill(Color.WHITE);
		vArchivo.setStyle("-fx-font-weight: bold");
		vArchivo.setPrefWidth(200);

		Label lRegistros = new Label("Cantidad de Registros:");
		lRegistros.setTextFill(Color.WHITE);
		lRegistros.setPrefWidth(150);
		Label vRegistros = new Label();
		vRegistros.setTextFill(Color.WHITE);
		vRegistros.setStyle("-fx-font-weight: bold");
		vRegistros.setPrefWidth(50);

		Label lMonto = new Label("Monto Total:");
		lMonto.setTextFill(Color.WHITE);
		lMonto.setPrefWidth(150);
		Label vMonto = new Label();
		vMonto.setPrefWidth(150);
		vMonto.setTextFill(Color.WHITE);
		vMonto.setStyle("-fx-font-weight: bold");
		vMonto.setAlignment(Pos.CENTER_RIGHT);
		fph.getChildren().addAll(lArchivo, vArchivo, lRegistros, vRegistros, lMonto, vMonto);

		v.getChildren().add(fph);

		FlowPane fp = new FlowPane();
		fp.setAlignment(Pos.CENTER_RIGHT);

		// fp.setStyle("-fx-background-color: green;");
		fp.setHgap(26);
		fp.setPrefWidth(850);

		TextField tfCuentaCargo = new TextField();
		tfCuentaCargo.setPromptText("Cuenta de Cargo");
		TextField tfCuentaAbono = new TextField();
		tfCuentaAbono.setPromptText("Cuenta Abono");
		TextField tfImporte = new TextField();
		tfImporte.setPromptText("Importe");
		TextField tfCveRastreo = new TextField();
		tfCveRastreo.setPromptText("Clave de Rastreo");
		TextField tfReferencia = new TextField();
		tfReferencia.setPromptText("Referencia");
		fp.getChildren().addAll(tfCuentaCargo, tfCuentaAbono, tfImporte, tfCveRastreo, tfReferencia);

		v.getChildren().add(fp);
		TextField tfBeneficiario = new TextField();
		tfBeneficiario.setMaxWidth(850);
		tfBeneficiario.setPromptText("Nombre del Beneficiario");

		v.getChildren().add(tfBeneficiario);
		v.setAlignment(Pos.CENTER_RIGHT);

		v.setSpacing(10);
		//v.setPadding(new Insets(0, 25, 0, 0));
		// v.setStyle("-fx-background-color: blue;");

		hb.setAlignment(Pos.BOTTOM_RIGHT);

		// borderpane.setCenter(hb);
		
		// borderpane.setStyle("-fx-background-color: red;");

		Button bGuardar = new Button("Guardar");
		bGuardar.setStyle(
				"-fx-background-color: #006dff;  -fx-font-family: FranklinGothicLT-Demi;-fx-font-size: 15px;-fx-font-weight:bold");
		bGuardar.setPrefWidth(140);
		bGuardar.setTextFill(Color.WHITE);

		Button bBuscar = new Button("Buscar");
		bBuscar.setStyle(
				"-fx-background-color: #accaf3;  -fx-font-family: FranklinGothicLT;-fx-font-size: 15px;-fx-font-weight:bold");
		bBuscar.setPrefWidth(140);
		bBuscar.setTextFill(Color.BLACK);


		hb.getChildren().add(bBuscar);
		hb.getChildren().add(bGuardar);
		//hb.setPadding(new Insets(0, 25, 0, 0));

		bBuscar.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(final ActionEvent e) {
				t.getItems().clear();

				for (DispersionDefinitiva dd : originalList) {

					if ((StringUtils.isEmpty(tfCuentaCargo.getText())
							|| tfCuentaCargo.getText().equals(dd.getCuentaCargo()))
							&& (StringUtils.isEmpty(tfCuentaAbono.getText())
									|| tfCuentaAbono.getText().equals(dd.getCuentaAbono()))
							&& (StringUtils.isEmpty(tfImporte.getText()) || tfImporte.getText().equals(dd.getImporte()))
							&& (StringUtils.isEmpty(tfCveRastreo.getText())
									|| tfCveRastreo.getText().equals(dd.getClaveRastreo()))
							&& (StringUtils.isEmpty(tfReferencia.getText())
									|| tfReferencia.getText().equals(dd.getReferencia()))
							&& (StringUtils.isEmpty(tfBeneficiario.getText())
									|| dd.getNombre().contains(tfBeneficiario.getText()))) {
						t.getItems().add(dd);

					}
				}
				if (t.getItems().size()==0){
					t.setPlaceholder(new Label("No se encontraron registros que coincidan con los criterios de busqueda"));
				}
				t.refresh();

			}
		});

		bImprimir.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(final ActionEvent e) {

				try {

					String currentPath = Paths.get(".").toAbsolutePath().normalize().toString();

					List<DispersionDefinitiva> list = new ArrayList<DispersionDefinitiva>();
					for (DispersionDefinitiva dd : t.getItems()) {
						if (dd.getComprobante())
							list.add(dd);
					}

					final DispersionDefinitivaPdfExport export = new DispersionDefinitivaPdfExport();
					export.setSingleDocument(true);
					export.export(new File(currentPath), list, "/img/logoSabadell.jpeg");
					
					
					PrinterJob pj = PrinterJob.getPrinterJob(); 
					pj.printDialog();
					

				} catch (Exception ex) {
					ex.printStackTrace();
				}

			}
		});

		bGuardar.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(final ActionEvent e) {

				try {

					String currentPath = Paths.get(".").toAbsolutePath().normalize().toString();
					DirectoryChooser saveFile = new DirectoryChooser();
					saveFile.setInitialDirectory(new File(currentPath));

					// Show save file dialog
					File file = saveFile.showDialog(getDesktop().getStage());

					if (file != null) {

						List<DispersionDefinitiva> list = new ArrayList<DispersionDefinitiva>();
						for (DispersionDefinitiva dd : t.getItems()) {
							if (dd.getComprobante())
								list.add(dd);
						}

						final DispersionDefinitivaPdfExport export = new DispersionDefinitivaPdfExport();
						export.export(file, list, "/img/logoSabadell.jpeg");
						
						Stage stage = new Stage();

						StackPane canvas = new StackPane();
						canvas.setPadding(new Insets(10));
						canvas.setStyle("-fx-background-color:  #a9d42c;");
						canvas.setPrefSize(512, 50);

						stage.getIcons().add(new Image(getClass().getResourceAsStream("/img/logoSabadellCircle.png")));
						stage.setTitle("Impresion de Masiva de Comprobantes - Archivos Generados");

						Label mensaje = new Label("Los archivos fueron generados\n en el directorio seleccionado");
						mensaje.setAlignment(Pos.CENTER);
						mensaje.setStyle("-fx-font-family: FranklinGothicLT-Demi;-fx-font-size: 20px;");
						mensaje.setTextFill(Color.web("#777777"));

						Button bContinuar = new Button("Continuar");
						bContinuar.setStyle(
								"-fx-background-color: #006dff;  -fx-font-family: FranklinGothicLT-Demi;-fx-font-size: 15px;");
						bContinuar.setPrefWidth(140);
						bContinuar.setTextFill(Color.WHITE);

						bContinuar.setOnMouseClicked(evt -> {
							stage.hide();
						});

						VBox vbox = new VBox();
						vbox.setSpacing(50);
						vbox.setAlignment(Pos.TOP_CENTER);
						vbox.setPrefSize(512, 275);
						vbox.getChildren().add(canvas);
						vbox.getChildren().add(mensaje);
						vbox.getChildren().add(bContinuar);

						stage.setScene(new Scene(vbox, 512, 275));
						stage.setResizable(false);
						stage.initOwner(getDesktop().getStage());
						stage.initModality(Modality.WINDOW_MODAL);
						stage.showAndWait();
						
					}
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});

		bInstrucciones.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {

				Stage stage = new Stage();

				StackPane canvas = new StackPane();
				canvas.setPadding(new Insets(10));
				canvas.setStyle("-fx-background-color: #239d45;");
				canvas.setPrefSize(800, 60);

				Label instruccionesLabel = new Label(
						"Falta Definir las instrucciones para la opcion de Impresion de Comprobantes");
				instruccionesLabel.setWrapText(true);
				instruccionesLabel.setTextAlignment(TextAlignment.JUSTIFY);
				instruccionesLabel
						.setStyle("-fx-font-family: FranklinGothicLT-Demi;-fx-font-size: 14px;-fx-font-weight: bold");
				instruccionesLabel.setTextFill(Color.WHITE);
				canvas.getChildren().add(instruccionesLabel);

				stage.getIcons().add(new Image(getClass().getResourceAsStream("/img/logoSabadellCircle.png")));
				stage.setTitle("Impresion de Masiva de Comprobantes - Instrucciones");

				TextArea textArea = new TextArea();
				textArea.setText("\n"
						+ "Falta Definir las instrucciones para la opcion de Impresion de Comprobantes");
				textArea.setEditable(false);
				textArea.setWrapText(true);

				VBox vbox = new VBox();
				textArea.prefHeightProperty().bind(vbox.prefHeightProperty().add(-60));
				vbox.setPrefSize(600, 600);
				VBox.setVgrow(vbox, Priority.ALWAYS);
				vbox.getChildren().add(canvas);
				vbox.getChildren().add(textArea);

				stage.setScene(new Scene(vbox, 600, 600));
				stage.setResizable(false);
				stage.show();
				// Hide this current window (if this is what you want)
				// ((Node)(event.getSource())).getScene().getWindow().hide();

			}
		});

		VBox vbox = new VBox(headerBox1, v, hb, bImprimir);
		vbox.setPadding(new Insets(0,25,0,0));
		vbox.setAlignment(Pos.CENTER_RIGHT);
		vbox.setSpacing(10);

		((BorderPane) mainPane).setTop(vbox);

		t = new DispersionDefinitivaTable();
		t.getStyleClass().add("tabla-impresion");

		t.prefWidthProperty().bind(mainPane.widthProperty().add(-60));

		((BorderPane) mainPane).setCenter(t);
		BorderPane.setMargin(t, new Insets(10, 25, 50, 0));

		bImportarArchivo.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(final ActionEvent e) {
				String currentPath = Paths.get(".").toAbsolutePath().normalize().toString();
				fileChooser.setInitialDirectory(new File(currentPath));
				File file = fileChooser.showOpenDialog(getDesktop().getStage());
				if (file != null) {
					DispersionDefinitivaCSVImporter benImporter = new DispersionDefinitivaCSVImporter(t);
					try {
						benImporter.importFile(file);

						double total = 0.0;
						for (DispersionDefinitiva dd : t.getItems()) {
							total += Double.valueOf(dd.getImporte());
						}

						String pattern = "###,###,###,###.00";
						DecimalFormat decimalFormat = new DecimalFormat(pattern);

						vArchivo.setText(file.getName());
						vRegistros.setText(String.valueOf(t.getItems().size()));
						vMonto.setText(decimalFormat.format(total));
						originalList.clear();
						originalList.addAll(t.getItems());
						t.refresh();
					} catch (LayoutValidatorException e1) {
						Stage stage = new Stage();

						Pane canvas = new Pane();
						canvas.setPadding(new Insets(10));
						canvas.setStyle("-fx-background-color:  #e90e5c;");
						canvas.setPrefSize(512, 50);

						stage.getIcons().add(new Image(getClass().getResourceAsStream("/img/logoSabadellCircle.png")));
						stage.setTitle("Impresion masiva de comprobantes - Formato de Archivo Incorrecto");

						Label mensaje = new Label("El archivo no tiene el formato correcto");
						mensaje.setStyle("-fx-font-family: FranklinGothicLT-Demi;-fx-font-size: 20px;");
						mensaje.setTextFill(Color.web("#777777"));

						Button bContinuar = new Button("Continuar");
						bContinuar.setStyle(
								"-fx-background-color: #006dff;  -fx-font-family: FranklinGothicLT-Demi;-fx-font-size: 15px;");
						bContinuar.setPrefWidth(140);
						bContinuar.setTextFill(Color.WHITE);

						bContinuar.setOnMouseClicked(evt -> {
							stage.hide();
						});

						VBox vbox = new VBox();
						vbox.setSpacing(50);
						vbox.setAlignment(Pos.TOP_CENTER);
						vbox.setPrefSize(512, 275);
						// VBox.setVgrow(vbox, Priority.ALWAYS);
						vbox.getChildren().add(canvas);
						vbox.getChildren().add(mensaje);
						vbox.getChildren().add(bContinuar);

						stage.setScene(new Scene(vbox, 512, 275));
						stage.setResizable(false);
						stage.initOwner(getDesktop().getStage());
						stage.initModality(Modality.WINDOW_MODAL);
						stage.showAndWait();

						e1.printStackTrace();
					} catch (Exception e1) {

						e1.printStackTrace();
					}
				}

			}
		});

		getDesktop().setWorkArea(mainPane);
	}

}
