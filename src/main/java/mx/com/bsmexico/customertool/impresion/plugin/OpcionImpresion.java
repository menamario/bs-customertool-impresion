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
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.printing.PDFPageable;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.NodeOrientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
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
import javafx.stage.StageStyle;
import mx.com.bsmexico.customertool.api.Feature;
import mx.com.bsmexico.customertool.api.Layout;
import mx.com.bsmexico.customertool.api.NavRoute;
import mx.com.bsmexico.customertool.api.layouts.model.validation.LayoutValidatorException;
import mx.com.bsmexico.customertool.api.process.Importer;

public class OpcionImpresion extends Feature {

	DispersionDefinitivaTable t = null;
	List<DispersionDefinitiva> originalList = new ArrayList<DispersionDefinitiva>();
	Button bCerrar = new Button();
	ImageView error = new ImageView();
	ImageView check = new ImageView();

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
		ImageView cerrar = null;

		try {
			error = new ImageView(new Image(this.getImageInput("/img/error.png")));
			error.setPreserveRatio(true);
			error.setFitWidth(66);
			check = new ImageView(new Image(this.getImageInput("/img/check.png")));
			check.setPreserveRatio(true);
			check.setFitWidth(66);
			atras = new ImageView(new Image(this.getImageInput("/img/atras.png")));
			cerrar = new ImageView(new Image(this.getImageInput("/img/close.png")));
			cerrar.setPreserveRatio(true);
			cerrar.setFitWidth(25);
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

		bCerrar.setGraphic(cerrar);
		bCerrar.setStyle("-fx-background-color: transparent;");

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
				"-fx-background-color: #e25100;-fx-font-family: FranklinGothicLT-Demi;-fx-font-size: 14px; -fx-border-radius: 0 0 5 5; -fx-background-radius: 0 0 4 4;");
		headerBox1.getChildren().add(l);

		headerBox2.getChildren().add(bInstrucciones);
		headerBox2.getChildren().add(bImportarArchivo);
		headerBox2.setSpacing(100);
		HBox.setHgrow(headerBox2, Priority.ALWAYS);
		headerBox2.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
		headerBox1.getChildren().add(headerBox2);
		// headerBox1.setPadding(new Insets(0, 30, 0, 0));

		HBox hb = new HBox();
		hb.setSpacing(10);
		// hb.getChildren().addAll(lFormato, rbTxt, rbCsv);

		VBox v = new VBox();

		FlowPane fph = new FlowPane();
		// fph.setAlignment(Pos.CENTER_RIGHT);

		Label lArchivo = new Label("Nombre de archivo:");
		lArchivo.setTextFill(Color.WHITE);
		lArchivo.setPrefWidth(170);
		lArchivo.setStyle("-fx-font-family: FranklinGothicLT; -fx-font-size:18;");
		Label vArchivo = new Label();
		vArchivo.setTextFill(Color.WHITE);
		vArchivo.setPrefWidth(370);
		vArchivo.setStyle("-fx-font-family: FranklinGothicLT; -fx-font-size:18;-fx-font-weight: bold");

		Label lRegistros = new Label("Cantidad de Registros:");
		lRegistros.setTextFill(Color.WHITE);
		lRegistros.setPrefWidth(200);
		lRegistros.setStyle("-fx-font-family: FranklinGothicLT; -fx-font-size:18;");
		Label vRegistros = new Label();
		vRegistros.setTextFill(Color.WHITE);
		vRegistros.setStyle("-fx-font-family: FranklinGothicLT; -fx-font-size:18;-fx-font-weight: bold");
		vRegistros.setPrefWidth(50);

		Label lMonto = new Label("Monto Total:");
		lMonto.setTextFill(Color.WHITE);
		lMonto.setPrefWidth(150);
		lMonto.setStyle("-fx-font-family: FranklinGothicLT; -fx-font-size:18;");
		Label vMonto = new Label();
		vMonto.setPrefWidth(200);
		vMonto.setTextFill(Color.WHITE);
		vMonto.setStyle("-fx-font-family: FranklinGothicLT; -fx-font-size:18;-fx-font-weight: bold");
		vMonto.setAlignment(Pos.CENTER_RIGHT);
		fph.getChildren().addAll(lArchivo, vArchivo, lRegistros, vRegistros, lMonto, vMonto);

		v.getChildren().add(fph);

		FlowPane fp = new FlowPane();
		// fp.setAlignment(Pos.CENTER_RIGHT);

		// fp.setStyle("-fx-background-color: green;");
		fp.hgapProperty().bind(fp.widthProperty().multiply(0.07).multiply(0.25));
		fp.setPrefWidth(850);

		TextField tfCuentaCargo = new TextField();
		tfCuentaCargo.setPromptText("Cuenta de Cargo");
		tfCuentaCargo.setStyle("-fx-font-family: FranklinGothicLT; -fx-font-size:18;");
		tfCuentaCargo.prefWidthProperty().bind(fp.widthProperty().add(-80).divide(5));
		TextField tfCuentaAbono = new TextField();
		tfCuentaAbono.setPromptText("Cuenta Abono");
		tfCuentaAbono.setStyle("-fx-font-family: FranklinGothicLT; -fx-font-size:18;");
		tfCuentaAbono.prefWidthProperty().bind(fp.widthProperty().add(-80).divide(5));
		TextField tfImporte = new TextField();
		tfImporte.setPromptText("Importe");
		tfImporte.setStyle("-fx-font-family: FranklinGothicLT; -fx-font-size:18;");
		tfImporte.prefWidthProperty().bind(fp.widthProperty().add(-80).divide(5));
		TextField tfCveRastreo = new TextField();
		tfCveRastreo.setPromptText("Clave de Rastreo");
		tfCveRastreo.setStyle("-fx-font-family: FranklinGothicLT; -fx-font-size:18;");
		tfCveRastreo.prefWidthProperty().bind(fp.widthProperty().add(-80).divide(5));
		TextField tfReferencia = new TextField();
		tfReferencia.setPromptText("Referencia");
		tfReferencia.setStyle("-fx-font-family: FranklinGothicLT; -fx-font-size:18;");
		tfReferencia.prefWidthProperty().bind(fp.widthProperty().add(-80).multiply(0.2).add(-2));
		fp.getChildren().addAll(tfCuentaCargo, tfCuentaAbono, tfImporte, tfCveRastreo, tfReferencia);

		v.getChildren().add(fp);
		TextField tfBeneficiario = new TextField();
		tfBeneficiario.prefWidthProperty().bind(fp.widthProperty());
		tfBeneficiario.setPromptText("Nombre del Beneficiario");
		tfBeneficiario.setStyle("-fx-font-family: FranklinGothicLT; -fx-font-size:18;");

		v.getChildren().add(tfBeneficiario);
		v.setAlignment(Pos.CENTER_RIGHT);

		v.setSpacing(10);
		// v.setPadding(new Insets(0, 25, 0, 0));
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
		// hb.setPadding(new Insets(0, 25, 0, 0));

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
									|| StringUtils.containsIgnoreCase(dd.getNombre(), tfBeneficiario.getText()))) {
						t.getItems().add(dd);

					}
				}
				if (t.getItems().size() == 0) {
					t.setPlaceholder(
							new Label("No se encontraron registros que coincidan con los criterios de busqueda"));
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

					if (list.size() > 0) {

						final DispersionDefinitivaPdfExport export = new DispersionDefinitivaPdfExport();
						export.setSingleDocument(true);
						export.export(new File(currentPath), list, "/img/logoSabadellByn.png");

						File folder = new File(currentPath);
						File[] listOfFiles = folder.listFiles();

						File pdfToPrint = null;

						for (File file : listOfFiles) {
							if (file.isFile()) {
								if (file.getName().startsWith("DPTmp")) {
									pdfToPrint = file;
									break;
								}

							}
						}

						PDDocument document = PDDocument.load(pdfToPrint);

						// PrintService myPrintService = findPrintService("My
						// Windows printer Name");

						PrinterJob job = PrinterJob.getPrinterJob();
						if (job.printDialog()) {
							job.setPageable(new PDFPageable(document));
							job.print();
						}

						pdfToPrint.delete();
					}

				} catch (Exception ex) {
					ex.printStackTrace();
					getDesktop().opacar();
					Stage stage = new Stage(StageStyle.UNDECORATED);

					StackPane canvas = new StackPane();
					canvas.setPadding(new Insets(5));
					canvas.setStyle("-fx-background-color:  #e90e5c;");
					canvas.setPrefSize(512, 54);

					canvas.getChildren().add(bCerrar);
					StackPane.setAlignment(bCerrar, Pos.TOP_RIGHT);

					bCerrar.setOnMouseClicked(ev -> {
						stage.hide();
					});

					stage.getIcons().add(new Image(getClass().getResourceAsStream("/img/logoSabadellCircle.png")));
					stage.setTitle("Impresion de Masiva de Comprobantes - Impresion");

					Label mensaje = new Label("No fue posible imprimir en la impresora seleccionada");
					mensaje.setAlignment(Pos.CENTER);
					mensaje.setStyle("-fx-font-family: FranklinGothicLT-Demi;-fx-font-size: 20px;");
					mensaje.setTextFill(Color.web("#777777"));

					Button bContinuar = new Button("Continuar");
					bContinuar.setStyle(
							"-fx-font-family: FranklinGothicLT;	-fx-font-size: 12.0px;	-fx-border-radius: 8.0px;	-fx-background-color: #006dff;	-fx-border-width: 1.0px;	-fx-border-color: #979797;	-fx-font-weight:bold;	-fx-background-radius: 8.0px;");
					bContinuar.setPrefSize(140, 40);
					bContinuar.setTextFill(Color.WHITE);

					bContinuar.setOnMouseClicked(evt -> {
						stage.hide();
					});

					VBox vbox = new VBox();
					vbox.setSpacing(50);
					vbox.setAlignment(Pos.TOP_CENTER);
					vbox.setPrefSize(512, 345);
					vbox.getChildren().add(canvas);
					vbox.getChildren().add(error);
					mensaje.setPadding(new Insets(0, 0, 35, 0));
					vbox.getChildren().add(mensaje);
					vbox.getChildren().add(bContinuar);

					stage.setScene(new Scene(vbox, 512, 345));
					stage.setResizable(false);
					stage.initOwner(getDesktop().getStage());
					stage.initModality(Modality.WINDOW_MODAL);
					stage.setX(getDesktop().getStage().getX()+((getDesktop().getStage().getWidth()-512)/2));
					stage.setY(getDesktop().getStage().getY()+((getDesktop().getStage().getHeight()-345)/2));
					stage.addEventHandler(KeyEvent.KEY_RELEASED, (KeyEvent event) -> {
				        if (KeyCode.ESCAPE == event.getCode()) {
				            stage.close();
				        }
				    });
					stage.showAndWait();
					getDesktop().desOpacar();
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

					List<DispersionDefinitiva> list = new ArrayList<DispersionDefinitiva>();
					for (DispersionDefinitiva dd : t.getItems()) {
						if (dd.getComprobante())
							list.add(dd);
					}

					if (list.size() > 0) {
						// Show save file dialog
						File file = saveFile.showDialog(getDesktop().getStage());

						if (file != null) {
							getDesktop().opacar();

							final DispersionDefinitivaPdfExport export = new DispersionDefinitivaPdfExport();
							export.export(file, list, "/img/logoSabadellByn.png");

							Stage stage = new Stage(StageStyle.UNDECORATED);

							StackPane canvas = new StackPane();
							canvas.setPadding(new Insets(5));
							canvas.setStyle("-fx-background-color:  #a9d42c;");
							canvas.setPrefSize(512, 54);

							canvas.getChildren().add(bCerrar);
							StackPane.setAlignment(bCerrar, Pos.TOP_RIGHT);

							bCerrar.setOnMouseClicked(ev -> {
								stage.hide();
							});

							stage.getIcons()
									.add(new Image(getClass().getResourceAsStream("/img/logoSabadellCircle.png")));
							stage.setTitle("Impresion de Masiva de Comprobantes - Archivos Generados");

							Label mensaje = new Label("Los archivos fueron generados.");
							mensaje.setAlignment(Pos.CENTER);
							mensaje.setStyle("-fx-font-family: FranklinGothicLT-Demi;-fx-font-size: 20px;");
							mensaje.setTextFill(Color.web("#777777"));

							Button bContinuar = new Button("Continuar");
							bContinuar.setStyle(
									"-fx-font-family: FranklinGothicLT;	-fx-font-size: 12.0px;	-fx-border-radius: 8.0px;	-fx-background-color: #006dff;	-fx-border-width: 1.0px;	-fx-border-color: #979797;	-fx-font-weight:bold;	-fx-background-radius: 8.0px;");
							bContinuar.setPrefSize(140, 40);
							bContinuar.setTextFill(Color.WHITE);

							bContinuar.setOnMouseClicked(evt -> {
								stage.hide();
							});

							VBox vbox = new VBox();
							vbox.setSpacing(25);
							vbox.setAlignment(Pos.TOP_CENTER);
							vbox.setPrefSize(512, 345);
							vbox.getChildren().add(canvas);
							vbox.getChildren().add(check);
							mensaje.setPadding(new Insets(0, 0, 35, 0));
							vbox.getChildren().add(mensaje);
							vbox.getChildren().add(bContinuar);

							stage.setScene(new Scene(vbox, 512, 345));
							stage.setResizable(false);
							stage.initOwner(getDesktop().getStage());
							stage.initModality(Modality.WINDOW_MODAL);
							stage.setX(getDesktop().getStage().getX()+((getDesktop().getStage().getWidth()-512)/2));
							stage.setY(getDesktop().getStage().getY()+((getDesktop().getStage().getHeight()-345)/2));
							stage.addEventHandler(KeyEvent.KEY_RELEASED, (KeyEvent event) -> {
						        if (KeyCode.ESCAPE == event.getCode()) {
						            stage.close();
						        }
						    });
							stage.showAndWait();
							getDesktop().desOpacar();
						}
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
				canvas.setPrefSize(1000, 60);
				canvas.setMinHeight(54);

				Label instruccionesLabel = new Label(
						"Banco Sabadell agradece su preferencia, acontinuación detallamos los pasos que debes seguir para\ndescargar tus comprobantes de pagos realizados a través Dispersión de Pagos.");
				instruccionesLabel.setWrapText(true);
				instruccionesLabel.setTextAlignment(TextAlignment.CENTER);
				instruccionesLabel
						.setStyle("-fx-font-family: FranklinGothicLT-Demi;-fx-font-size: 14px;-fx-font-weight: bold");
				instruccionesLabel.setTextFill(Color.web("#828488"));
				instruccionesLabel.setMinHeight(40);
				StackPane p = new StackPane();
				p.setPadding(new Insets(20,0,20,0));
				p.setStyle("-fx-background-color: #d9d9d9");
				p.getChildren().add(instruccionesLabel);

				stage.getIcons().add(new Image(getClass().getResourceAsStream("/img/logoSabadellCircle.png")));
				stage.setTitle("Impresion de Comprobantes - Instrucciones");

				ImageView insGIv = null;

				try {
					insGIv = new ImageView(new Image(getImageInput("/img/instruccionesImpresion.png")));
					insGIv.setPreserveRatio(true);
					insGIv.setFitWidth(1000);
					insGIv.setSmooth(true);
					
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				ScrollPane scrollPaneGenerales = new ScrollPane();
				scrollPaneGenerales.setPrefSize(800, 600);
				scrollPaneGenerales.setVbarPolicy(ScrollBarPolicy.AS_NEEDED);
				scrollPaneGenerales.setHbarPolicy(ScrollBarPolicy.AS_NEEDED);
				scrollPaneGenerales.setContent(insGIv);
				
				
				TabPane tabPane = new TabPane();
				Tab tabInstrucciones = new Tab("Instrucciones");
				
				tabInstrucciones.setContent(scrollPaneGenerales);
				
				tabPane.getTabs().addAll(tabInstrucciones);

				VBox vbox = new VBox();
				vbox.setPrefSize(1020, 600);
				VBox.setVgrow(vbox, Priority.ALWAYS);
				vbox.getChildren().add(canvas);
				vbox.getChildren().add(p);
				vbox.getChildren().add(tabPane);
				

				stage.setScene(new Scene(vbox, 1020, 600));
				stage.setResizable(false);
				stage.show();
			}
		});

		VBox vbox = new VBox(headerBox1, v, hb, bImprimir);
		vbox.setPadding(new Insets(0, 25, 0, 0));
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
					Importer ddImporter = null;
					if (file.getName().toUpperCase().endsWith("CSV")) {
						ddImporter = new DispersionDefinitivaCSVImporter(t);
					} else {
						ddImporter = new DispersionDefinitivaTXTImporter(t);
					}

					try {
						ddImporter.importFile(file);

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
						getDesktop().opacar();
						Stage stage = new Stage(StageStyle.UNDECORATED);

						Pane canvas = new Pane();
						canvas.setPadding(new Insets(5));
						canvas.setStyle("-fx-background-color:  #e90e5c;");
						canvas.setPrefSize(512, 54);

						canvas.getChildren().add(bCerrar);
						StackPane.setAlignment(bCerrar, Pos.TOP_RIGHT);

						bCerrar.setOnMouseClicked(ev -> {
							stage.hide();
						});

						stage.getIcons().add(new Image(getClass().getResourceAsStream("/img/logoSabadellCircle.png")));
						stage.setTitle("Impresion masiva de comprobantes - Formato de Archivo Incorrecto");

						Label mensaje = new Label("El archivo no tiene el formato correcto");
						mensaje.setStyle("-fx-font-family: FranklinGothicLT-Demi;-fx-font-size: 20px;");
						mensaje.setTextFill(Color.web("#777777"));

						Button bContinuar = new Button("Continuar");
						bContinuar.setStyle(
								"-fx-font-family: FranklinGothicLT;	-fx-font-size: 12.0px;	-fx-border-radius: 8.0px;	-fx-background-color: #006dff;	-fx-border-width: 1.0px;	-fx-border-color: #979797;	-fx-font-weight:bold;	-fx-background-radius: 8.0px;");
						bContinuar.setPrefSize(140, 40);
						bContinuar.setTextFill(Color.WHITE);

						bContinuar.setOnMouseClicked(evt -> {
							stage.hide();
						});

						VBox vbox = new VBox();
						vbox.setSpacing(25);
						vbox.setAlignment(Pos.TOP_CENTER);
						vbox.setPrefSize(512, 345);
						// VBox.setVgrow(vbox, Priority.ALWAYS);
						vbox.getChildren().add(canvas);
						vbox.getChildren().add(error);
						mensaje.setPadding(new Insets(0, 0, 35, 0));
						vbox.getChildren().add(mensaje);
						vbox.getChildren().add(bContinuar);

						stage.setScene(new Scene(vbox, 512, 345));
						stage.setResizable(false);
						stage.initOwner(getDesktop().getStage());
						stage.initModality(Modality.WINDOW_MODAL);
						stage.setX(getDesktop().getStage().getX()+((getDesktop().getStage().getWidth()-512)/2));
						stage.setY(getDesktop().getStage().getY()+((getDesktop().getStage().getHeight()-345)/2));
						stage.addEventHandler(KeyEvent.KEY_RELEASED, (KeyEvent event) -> {
					        if (KeyCode.ESCAPE == event.getCode()) {
					            stage.close();
					        }
					    });
						stage.showAndWait();
						getDesktop().desOpacar();

						e1.printStackTrace();
					} catch (Exception e1) {

						e1.printStackTrace();
					}
				}

			}
		});

		getDesktop().setWorkArea(mainPane);
	}

	@Override
	public int getOrder() {
		return 1;
	}

}
