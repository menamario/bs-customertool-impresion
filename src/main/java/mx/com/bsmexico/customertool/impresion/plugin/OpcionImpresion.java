package mx.com.bsmexico.customertool.impresion.plugin;

import java.awt.print.PrinterJob;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.printing.PDFPageable;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.NodeOrientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
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
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;
import javafx.scene.web.WebView;
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
	double xOffset = 0;
	double yOffset = 0;
	Label lb = null;
	Stage stage = null;
	String pattern = "###########0.00";
	DecimalFormat decimalFormat = new DecimalFormat(pattern);

	private InputStream getImageInput(final String file) throws FileNotFoundException {
		final InputStream input = getClass().getResourceAsStream(file);
		return input;
	}

	@Override
	public Layout getLayout() {

		final NavRoute.BuilderNavRoute navRuoteBuilder = new NavRoute.BuilderNavRoute("TEST");
		NavRoute route = null;
		try {
			route = navRuoteBuilder.addNode("Impresión masiva de comprobantes", "Impresión masiva de comprobantes", 0,
					false, getClass().getResourceAsStream("/img/impresion.svg")).build();

		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new Layout.LayoutBuilder("l1").route(route).build();
	}

	@Override
	public void launch() {

		getMenuNavigator().hide();
		getDesktop().updatePleca("#000000", null);
		originalList = new ArrayList<DispersionDefinitiva>();

		Pane mainPane = new BorderPane();

		mainPane.setPadding(new Insets(0, 20, 0, 20));

		HBox headerBox1 = new HBox();
		HBox headerBox2 = new HBox();

		ImageView atras = null;
		ImageView importarArchivo = null;
		ImageView instrucciones = null;
		ImageView impresion = null;
		ImageView cerrar = null;

		WebView importarArchivoWv = null;
		WebView instruccionesWv = null;
		WebView regresarWv = null;
		WebView imprimirWv = null;

		try {
			error = new ImageView(new Image(this.getImageInput("/img/error.png")));
			error.setPreserveRatio(true);
			error.setFitWidth(66);
			check = new ImageView(new Image(this.getImageInput("/img/check.png")));
			check.setPreserveRatio(true);
			check.setFitWidth(66);
//			atras = new ImageView(new Image(this.getImageInput("/img/atras.png")));
//			atras.setPreserveRatio(true);
//			atras.setFitWidth(40);
			cerrar = new ImageView(new Image(this.getImageInput("/img/close.png")));
			cerrar.setPreserveRatio(true);
			cerrar.setFitWidth(25);
//			importarArchivo = new ImageView(new Image(this.getImageInput("/img/importarImpresion.png")));
//			importarArchivo.setPreserveRatio(true);
//			importarArchivo.setFitWidth(70);
//			instrucciones = new ImageView(new Image(this.getImageInput("/img/instrucciones.png")));
//			instrucciones.setPreserveRatio(true);
//			instrucciones.setFitWidth(70);

//			impresion = new ImageView(new Image(this.getImageInput("/img/impresionSmall.png")));
//			impresion.setPreserveRatio(true);
//			impresion.setFitWidth(30);

			String htmlImportarArchivo = null;
			String htmlInstrucciones = null;
			String htmlRegresar = null;
			String htmlImprimir = null;

			try {
				htmlImportarArchivo = this.getHtml(65,
						readFile(getClass().getResourceAsStream("/img/importarArchivo.svg"), Charset.defaultCharset()));
				htmlInstrucciones = this.getHtml(65,
						readFile(getClass().getResourceAsStream("/img/instrucciones.svg"), Charset.defaultCharset()));
				htmlRegresar = this.getHtml(40,
						readFile(getClass().getResourceAsStream("/img/atras.svg"), Charset.defaultCharset()));
				htmlImprimir = this.getHtml(30,
						readFile(getClass().getResourceAsStream("/img/impresionSmall.svg"), Charset.defaultCharset()));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			importarArchivoWv = new WebView();
			importarArchivoWv.setContextMenuEnabled(false);
			importarArchivoWv.getEngine().loadContent(htmlImportarArchivo);
			importarArchivoWv.getStyleClass().add("browser");
			importarArchivoWv.setMaxSize(85, 85);
			importarArchivoWv.setMouseTransparent(true);

			instruccionesWv = new WebView();
			instruccionesWv.setContextMenuEnabled(false);
			instruccionesWv.getEngine().loadContent(htmlInstrucciones);
			instruccionesWv.getStyleClass().add("browser");
			instruccionesWv.setMaxSize(85, 85);
			instruccionesWv.setMouseTransparent(true);

			regresarWv = new WebView();
			regresarWv.setContextMenuEnabled(false);
			regresarWv.getEngine().loadContent(htmlRegresar);
			regresarWv.getStyleClass().add("browser");
			regresarWv.setMaxSize(60, 60);
			regresarWv.setMouseTransparent(true);
			
			imprimirWv = new WebView();
			imprimirWv.setContextMenuEnabled(false);
			imprimirWv.getEngine().loadContent(htmlImprimir);
			imprimirWv.getStyleClass().add("browser");
			imprimirWv.setMaxSize(50, 50);
			imprimirWv.setMouseTransparent(true);

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

		bAtras.setGraphic(regresarWv);
		bAtras.setStyle("-fx-background-color: transparent;-fx-padding:0;");
		bAtras.setTooltip(new Tooltip("Regresar"));
		bAtras.setMaxSize(80, 80);
		bAtras.setGraphicTextGap(0);

		bImprimir.setGraphic(imprimirWv);
		bImprimir.setStyle("-fx-background-color: transparent;");
		bImprimir.setTooltip(new Tooltip("Imprimir"));
		bImprimir.setAlignment(Pos.CENTER_RIGHT);
		bImprimir.setMaxSize(55,55);
		bImprimir.setPadding(new Insets(0,0,0,0));

		bInstrucciones.setGraphic(instruccionesWv);
		bInstrucciones.setText("Instrucciones");
		bInstrucciones.setTextFill(Color.BLACK);
		bInstrucciones.setStyle(
				"-fx-font-family: FranklinGothicLT-Demi;-fx-font-size: 13px;-fx-background-color: transparent;-fx-padding:0");
		bInstrucciones.setContentDisplay(ContentDisplay.TOP);
		bInstrucciones.setMaxSize(150, 105);
		bInstrucciones.setGraphicTextGap(0);

		bImportarArchivo.setGraphic(importarArchivoWv);
		bImportarArchivo.setText("Importar archivo");
		bImportarArchivo.setTextFill(Color.BLACK);
		bImportarArchivo.setStyle(
				"-fx-font-family: FranklinGothicLT-Demi;-fx-font-size: 13px;-fx-background-color: transparent; -fx-padding:0");
		bImportarArchivo.setContentDisplay(ContentDisplay.TOP);
		bImportarArchivo.setMaxSize(150, 105);
		bImportarArchivo.setGraphicTextGap(0);

		bAtras.setOnMouseClicked(evt -> {
			getMenuNavigator().show();
			getDesktop().setWorkArea(null);
			getDesktop().updatePleca("white", null);
		});

		final FileChooser fileChooser = new FileChooser();
		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Archivos de dispersion", "*D.csv",
				"*P.csv", "*D.txt", "*P.txt");
		fileChooser.getExtensionFilters().add(extFilter);

		headerBox1.getChildren().add(bAtras);
		headerBox1.setSpacing(40);
		Label l = new Label("    Impresión masiva de comprobantes    ");
		l.setTextFill(Color.WHITE);
		l.setStyle(
				"-fx-background-color: black;-fx-font-family: FranklinGothicLT-Demi;-fx-font-size: 14px; -fx-border-radius: 0 0 5 5; -fx-background-radius: 0 0 4 4;");
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
		lArchivo.setTextFill(Color.BLACK);
		lArchivo.setPrefWidth(170);
		lArchivo.setStyle("-fx-font-family: FranklinGothicLT; -fx-font-size:18;");
		Label vArchivo = new Label();
		vArchivo.setTextFill(Color.BLACK);
		vArchivo.setPrefWidth(360);
		vArchivo.setStyle("-fx-font-family: FranklinGothicLT; -fx-font-size:16;-fx-font-weight: bold");

		Label lRegistros = new Label("Cantidad de registros:");
		lRegistros.setTextFill(Color.BLACK);
		lRegistros.setPrefWidth(200);
		lRegistros.setStyle("-fx-font-family: FranklinGothicLT; -fx-font-size:18;");
		Label vRegistros = new Label();
		vRegistros.setTextFill(Color.BLACK);
		vRegistros.setStyle("-fx-font-family: FranklinGothicLT; -fx-font-size:16;-fx-font-weight: bold");
		vRegistros.setPrefWidth(50);

		Label lMonto = new Label("Monto total:");
		lMonto.setTextFill(Color.BLACK);
		lMonto.setPrefWidth(150);
		lMonto.setStyle("-fx-font-family: FranklinGothicLT; -fx-font-size:18;");
		Label vMonto = new Label();
		vMonto.setPrefWidth(200);
		vMonto.setTextFill(Color.BLACK);
		vMonto.setStyle("-fx-font-family: FranklinGothicLT; -fx-font-size:16;-fx-font-weight: bold");
		vMonto.setAlignment(Pos.CENTER_RIGHT);
		fph.getChildren().addAll(lArchivo, vArchivo, lRegistros, vRegistros, lMonto, vMonto);

		v.getChildren().add(fph);

		FlowPane fp = new FlowPane();
		// fp.setAlignment(Pos.CENTER_RIGHT);

		// fp.setStyle("-fx-background-color: green;");
		fp.hgapProperty().bind(fp.widthProperty().multiply(0.07).multiply(0.25));
		fp.setPrefWidth(850);

		TextField tfCuentaCargo = new TextField();
		tfCuentaCargo.setPromptText("Cuenta de cargo");
		tfCuentaCargo.setStyle("-fx-font-family: FranklinGothicLT; -fx-font-size:18; -fx-background-color:#e5f0ff;");
		tfCuentaCargo.prefWidthProperty().bind(fp.widthProperty().add(-80).divide(5));
		tfCuentaCargo.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (!newValue.matches("\\d*")) {
					tfCuentaCargo.setText(newValue.replaceAll("[^\\d]", ""));
				}
				if (newValue.length() > 11) {
					tfCuentaCargo.setText(newValue.substring(0, 11));
				}
			}
		});
		TextField tfCuentaAbono = new TextField();
		tfCuentaAbono.setPromptText("Cuenta abono");
		tfCuentaAbono.setStyle("-fx-font-family: FranklinGothicLT; -fx-font-size:18; -fx-background-color:#e5f0ff;");
		tfCuentaAbono.prefWidthProperty().bind(fp.widthProperty().add(-80).divide(5));
		tfCuentaAbono.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (!newValue.matches("\\d*")) {
					tfCuentaAbono.setText(newValue.replaceAll("[^\\d]", ""));
				}
				if (newValue.length() > 18) {
					tfCuentaAbono.setText(newValue.substring(0, 18));
				}
			}
		});

		TextField tfImporte = new TextField();
		tfImporte.setPromptText("Importe");
		tfImporte.setStyle("-fx-font-family: FranklinGothicLT; -fx-font-size:18; -fx-background-color:#e5f0ff;");
		tfImporte.prefWidthProperty().bind(fp.widthProperty().add(-80).divide(5));
		tfImporte.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (!newValue.matches("\\d{0,12}([\\.]\\d{0,2})?")) {
					tfImporte.setText(oldValue);
				}
				if (newValue.length() > 15) {
					tfImporte.setText(newValue.substring(0, 15));
				}
			}
		});
		TextField tfCveRastreo = new TextField();
		tfCveRastreo.setPromptText("Clave de rastreo");
		tfCveRastreo.setStyle("-fx-font-family: FranklinGothicLT; -fx-font-size:18; -fx-background-color:#e5f0ff;");
		tfCveRastreo.prefWidthProperty().bind(fp.widthProperty().add(-80).divide(5));
		tfCveRastreo.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (newValue.length() > 18) {
					tfCveRastreo.setText(newValue.substring(0, 18));
				}
			}
		});
		TextField tfReferencia = new TextField();
		tfReferencia.setPromptText("Referencia");
		tfReferencia.setStyle("-fx-font-family: FranklinGothicLT; -fx-font-size:18; -fx-background-color:#e5f0ff;");
		tfReferencia.prefWidthProperty().bind(fp.widthProperty().add(-80).multiply(0.2).add(-2));
		tfReferencia.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (!newValue.matches("\\d*")) {
					tfReferencia.setText(newValue.replaceAll("[^\\d]", ""));
				}
				if (newValue.length() > 20) {
					tfReferencia.setText(newValue.substring(0, 20));
				}
			}
		});
		fp.getChildren().addAll(tfCuentaCargo, tfCuentaAbono, tfImporte, tfCveRastreo, tfReferencia);

		v.getChildren().add(fp);
		TextField tfBeneficiario = new TextField();
		tfBeneficiario.prefWidthProperty().bind(fp.widthProperty());
		tfBeneficiario.setPromptText("Nombre del beneficiario");
		tfBeneficiario.setStyle("-fx-font-family: FranklinGothicLT; -fx-font-size:18; -fx-background-color:#e5f0ff;");
		tfBeneficiario.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (newValue.length() > 40) {
					tfBeneficiario.setText(newValue.substring(0, 40));
				}
			}
		});

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
				"-fx-background-color: #4c4c4c;  -fx-font-family: FranklinGothicLT;-fx-font-size: 15px;-fx-font-weight:bold;-fx-border-color:#4c4c4c;-fx-border-width:2px;");
		bGuardar.setPrefWidth(140);
		bGuardar.setTextFill(Color.WHITE);
		bGuardar.setAlignment(Pos.BOTTOM_CENTER);

		Button bBuscar = new Button("Buscar");
		bBuscar.setStyle(
				"-fx-background-color: white;  -fx-font-family: FranklinGothicLT;-fx-font-size: 15px;-fx-font-weight:bold;-fx-border-color:#006dff;-fx-border-width:2px;");
		bBuscar.setPrefWidth(140);
		bBuscar.setTextFill(Color.BLACK);
		bBuscar.setAlignment(Pos.BOTTOM_CENTER);

		hb.getChildren().add(bBuscar);
		hb.getChildren().add(bGuardar);
		// hb.setPadding(new Insets(0, 25, 0, 0));

		bBuscar.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(final ActionEvent e) {
				if (NumberUtils.isCreatable(StringUtils.stripStart(tfImporte.getText(), "0")))
					tfImporte.setText(
							decimalFormat.format(new BigDecimal(StringUtils.stripStart(tfImporte.getText(), "0"))));
				t.getItems().clear();
				lb.setVisible(false);

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
							new Label("No se encontraron registros que coincidan con los criterios de búsqueda"));
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
					canvas.setStyle("-fx-background-color: #ff5120;");
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
					vbox.setSpacing(25);
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
					stage.setX(getDesktop().getStage().getX() + ((getDesktop().getStage().getWidth() - 512) / 2));
					stage.setY(getDesktop().getStage().getY() + ((getDesktop().getStage().getHeight() - 345) / 2));
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
							canvas.setStyle("-fx-background-color:  #006dff;");
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
							mensaje.setStyle(
									"-fx-font-family: FranklinGothicLT;-fx-font-size: 20px;-fx-font-weight:bold;");
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
							stage.setX(
									getDesktop().getStage().getX() + ((getDesktop().getStage().getWidth() - 512) / 2));
							stage.setY(
									getDesktop().getStage().getY() + ((getDesktop().getStage().getHeight() - 345) / 2));
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

				if (stage == null) {
					stage = new Stage(StageStyle.UNDECORATED);

					StackPane canvas = new StackPane();
					canvas.setPadding(new Insets(10));
					canvas.setStyle("-fx-background-color: #66a7ff;");
					canvas.setPrefSize(800, 60);
					canvas.setMinHeight(54);
					canvas.setOnMousePressed(e -> {
						xOffset = e.getSceneX();
						yOffset = e.getSceneY();

					});

					canvas.setOnMouseDragged(e -> {
						stage.setX(e.getScreenX() - xOffset);
						stage.setY(e.getScreenY() - yOffset - 20);

					});

					canvas.getChildren().add(bCerrar);
					StackPane.setAlignment(bCerrar, Pos.TOP_RIGHT);

					bCerrar.setOnMouseClicked(ev -> {
						stage.hide();
					});

					Label instruccionesLabel = new Label(
							"Banco Sabadell agradece su preferencia, a continuación se detallan los pasos que debes seguir para\ndescargar tus comprobantes de pagos realizados a través de dispersión de pagos.");
					instruccionesLabel.setWrapText(true);
					instruccionesLabel.setTextAlignment(TextAlignment.CENTER);
					instruccionesLabel.setStyle(
							"-fx-font-family: FranklinGothicLT-Demi;-fx-font-size: 14px;-fx-font-family: 'Franklin Gothic Demi'");
					instruccionesLabel.setTextFill(Color.web("#828488"));
					instruccionesLabel.setMinHeight(40);
					StackPane p = new StackPane();
					p.setPadding(new Insets(20, 0, 20, 0));
					p.setStyle("-fx-background-color: white");
					p.getChildren().add(instruccionesLabel);

					stage.getIcons().add(new Image(getClass().getResourceAsStream("/img/logoSabadellCircle.png")));
					stage.setTitle("Impresion de Comprobantes - Instrucciones");

					TextFlow flow = new TextFlow();

					Text t1 = new Text("1.");
					Text t19 = new Text(
							" Accede a Banca en Línea y descarga los archivos \"Previos\", (se identifican con la letra \"P\" al final de su nombre) o\n    \"Definitivos\", (se identifican con la letra \"D\" al final de su nombre) de \"Dispersión de Pagos\" que se encuentran\n    alojados en la opción de \"Históricos de Archivos Procesados\". Esta descarga la puedes realizar en la carpeta de tu\n    preferencia.\n");
					t1.setStyle("-fx-fill: black;-fx-font-family: 'Franklin Gothic Demi'");
					Text t2 = new Text("2.");
					Text t29 = new Text(
							" Oprime el icono \"Importar archivo\" y selecciona de la carpeta que designaste, el archivo Previo o Definitivo del que\n    deseas imprimir sus comprobantes.\n");
					t2.setStyle("-fx-fill: black;-fx-font-family: 'Franklin Gothic Demi'");
					Text t3 = new Text("3.");
					Text t39 = new Text(
							" Podrás llevar a cabo la busqueda de los comprobantes que requieras imprimir de forma específica por medio de los\n    siguientes filtros:\n\n");
					t3.setStyle("-fx-fill: black;-fx-font-family: 'Franklin Gothic Demi'");
					Text t4 = new Text("      - Cuenta de cargo\n");
					t4.setStyle("-fx-fill: black");
					Text t5 = new Text("      - Cuenta de abono\n");
					t5.setStyle("-fx-fill: black");
					Text t6 = new Text("      - Importe\n");
					t6.setStyle("-fx-fill: black");
					Text t7 = new Text("      - Clave de rastreo\n");
					t7.setStyle("-fx-fill: black");
					Text t8 = new Text("      - Referencia\n");
					t8.setStyle("-fx-fill: black");
					Text t9 = new Text("      - Nombre del beneficiario\n\n");
					t9.setStyle("-fx-fill: black");
					Text t10 = new Text("4.");
					Text t109 = new Text(
							" Podrás seleccionar uno o varios checkbox del lado izquierdo de la pantalla, para imprimir o guardar los comprobantes\n    que desees.\n");
					t10.setStyle("-fx-fill: black;-fx-font-family: 'Franklin Gothic Demi'");
					Text t11 = new Text("5.");
					Text t119 = new Text(
							" En caso de que desees guardar los comprobantes, selecciona el icono \"Guardar\" y la aplicación los guardará con la\n    siguiente nomenclatura (Fecha_referencia_importe). Si deseas imprimir, selecciona el icono \"Imprimir\".\n\n\n\n\n");
					t11.setStyle("-fx-fill: black;-fx-font-family: 'Franklin Gothic Demi'");
					flow.getChildren().addAll(t1, t19, t2, t29, t3, t39, t4, t5, t6, t7, t8, t9, t10, t109, t11, t119);
					flow.setStyle(
							"-fx-background-color:white;-fx-font-size: 14px;-fx-fill:black;-fx-border-width:0;-fx-border-color:white;-fx-effect:null");
					flow.getStyleClass().add("flowFrank");
					flow.setMinWidth(756);
					flow.setTextAlignment(TextAlignment.JUSTIFY);

					ScrollPane scrollPaneGenerales = new ScrollPane();
					scrollPaneGenerales.setPrefSize(800, 600);
					scrollPaneGenerales.setVbarPolicy(ScrollBarPolicy.AS_NEEDED);
					scrollPaneGenerales.setHbarPolicy(ScrollBarPolicy.AS_NEEDED);
					scrollPaneGenerales.setContent(flow);
					scrollPaneGenerales.setPadding(new Insets(35, 30, 0, 30));
					scrollPaneGenerales.setStyle("-fx-background-color:white;");

					TabPane tabPane = new TabPane();
					Tab tabInstrucciones = new Tab("    Instrucciones    ");

					tabInstrucciones.setContent(scrollPaneGenerales);
					tabInstrucciones.setClosable(false);

					tabPane.getTabs().addAll(tabInstrucciones);

					VBox vbox = new VBox();
					vbox.setPrefSize(820, 600);
					VBox.setVgrow(vbox, Priority.ALWAYS);
					vbox.getChildren().add(canvas);
					vbox.getChildren().add(p);
					vbox.getChildren().add(tabPane);
					vbox.setStyle("-fx-background-color:white;-fx-border-color:lightgray; -fx-border-width:2px;");

					Scene scene = new Scene(vbox, 820, 600);
					scene.getStylesheets().add(getClass().getResource("/css/application.css").toExternalForm());

					stage.setScene(scene);
					stage.setResizable(false);
					stage.show();

				} else {
					stage.show();
					stage.toFront();
				}
			}
		});

		hb.setPadding(new Insets(10,0,0,0));
		v.setPadding(new Insets(10,0,0,0));
		VBox vbox = new VBox(headerBox1, v, hb, bImprimir);
		vbox.setPadding(new Insets(0, 25, 0, 0));
		vbox.setAlignment(Pos.CENTER_RIGHT);
		vbox.setSpacing(0);

		((BorderPane) mainPane).setTop(vbox);

		t = new DispersionDefinitivaTable();

		StackPane impresionsp = new StackPane();
		HBox b = new HBox();
		b.setAlignment(Pos.CENTER);
		lb = new Label("Por favor, importe un archivo de dispersión.");
		lb.setStyle(
				"-fx-background-color:transparent;-fx-font-family: FranklinGothicLT;-fx-font-size: 20px;-fx-text-fill:#828488;-fx-font-weight:bold");
		b.getChildren().add(lb);

		t.prefWidthProperty().bind(mainPane.widthProperty().add(-60));

		impresionsp.getChildren().addAll(t, lb);

		((BorderPane) mainPane).setCenter(impresionsp);
		BorderPane.setMargin(impresionsp, new Insets(0, 25, 50, 0));

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

						((HBox) t.getColumns().get(0).getGraphic()).getChildren().get(0)
								.setStyle("-fx-text-fill:black !important;");
						((CheckBox) ((HBox) t.getColumns().get(0).getGraphic()).getChildren().get(1)).setDisable(false);
						((CheckBox) ((HBox) t.getColumns().get(0).getGraphic()).getChildren().get(1))
								.setStyle("-fx-background-color:white;");
						t.getColumns().get(0).setStyle(
								"-fx-background-color: white !important;-fx-border-color: white !important;-fx-alignment: CENTER-RIGHT;-fx-padding:0 5 0 0");
						lb.setVisible(false);
						t.refresh();
					} catch (LayoutValidatorException e1) {
						getDesktop().opacar();
						Stage stage = new Stage(StageStyle.UNDECORATED);

						StackPane canvas = new StackPane();
						canvas.setPadding(new Insets(5));
						canvas.setStyle("-fx-background-color: #ff5120;");
						canvas.setPrefSize(512, 54);

						canvas.getChildren().add(bCerrar);
						StackPane.setAlignment(bCerrar, Pos.TOP_RIGHT);

						bCerrar.setOnMouseClicked(ev -> {
							stage.hide();
						});

						stage.getIcons().add(new Image(getClass().getResourceAsStream("/img/logoSabadellCircle.png")));
						stage.setTitle("Impresion masiva de comprobantes - Formato de Archivo Incorrecto");

						Label mensaje = new Label("El archivo no tiene el formato correcto");
						mensaje.setStyle("-fx-font-family: FranklinGothicLT;-fx-font-size: 20px;-fx-font-weight:bold;");
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
						stage.setX(getDesktop().getStage().getX() + ((getDesktop().getStage().getWidth() - 512) / 2));
						stage.setY(getDesktop().getStage().getY() + ((getDesktop().getStage().getHeight() - 345) / 2));
						stage.addEventHandler(KeyEvent.KEY_RELEASED, (KeyEvent event) -> {
							if (KeyCode.ESCAPE == event.getCode()) {
								stage.close();
							}
						});
						stage.showAndWait();
						getDesktop().desOpacar();

						e1.printStackTrace();
					} catch (Exception e1) {
						getDesktop().opacar();
						Stage stage = new Stage(StageStyle.UNDECORATED);

						StackPane canvas = new StackPane();
						canvas.setPadding(new Insets(5));
						canvas.setStyle("-fx-background-color: #ff5120;");
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
						stage.setX(getDesktop().getStage().getX() + ((getDesktop().getStage().getWidth() - 512) / 2));
						stage.setY(getDesktop().getStage().getY() + ((getDesktop().getStage().getHeight() - 345) / 2));
						stage.addEventHandler(KeyEvent.KEY_RELEASED, (KeyEvent event) -> {
							if (KeyCode.ESCAPE == event.getCode()) {
								stage.close();
							}
						});
						stage.showAndWait();
						getDesktop().desOpacar();

						e1.printStackTrace();

					}
				}

			}
		});

		getDesktop().setWorkArea(mainPane);
	}

	static String readFile(InputStream in, Charset encoding) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(in));
		StringBuffer response = new StringBuffer();
		for (String line; (line = reader.readLine()) != null; response.append(line))
			;
		return response.toString();
	}

	private String getHtml(int circle, int image, String circleColor, String svg) {
		StringBuffer sb = new StringBuffer();
		int radio = circle / 2;
		sb.append(String.format(
				"<html><head></head><body><svg  width='%d' height='%d'><circle cx='%d' cy='%d' r='%d' fill='%s'></circle><svg>",
				circle, circle, radio, radio, radio, circleColor));
		int desplazamientoImagen = (circle - image) / 2;
		sb.append(String.format("<svg x='%d' y='%d' width='%d' height='%d' style='fill:white'>", desplazamientoImagen,
				desplazamientoImagen, image, image));
		sb.append(svg);
		sb.append("</svg></body></html>");
		return sb.toString();
	}

	private String getHtml(int image, String svg) {
		StringBuffer sb = new StringBuffer();
		sb.append(String.format("<html><head></head><body>"));

		sb.append(String.format("<svg width='%d' height='%d'>", image, image));
		sb.append(svg);
		sb.append("</svg></body></html>");
		return sb.toString();
	}

	@Override
	public int getOrder() {
		return 1;
	}

}
