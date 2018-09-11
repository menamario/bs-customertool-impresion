package mx.com.bsmexico.customertool.impresion.plugin;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.nio.file.Paths;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.NodeOrientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
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
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import mx.com.bsmexico.customertool.api.Feature;
import mx.com.bsmexico.customertool.api.Layout;
import mx.com.bsmexico.customertool.api.NavRoute;
import mx.com.bsmexico.customertool.api.report.ContextReport;
import mx.com.bsmexico.customertool.api.report.ReportDataSourceFactory;
import mx.com.bsmexico.customertool.api.report.ReportGenerator;

public class OpcionImpresion extends Feature {

	DispersionDefinitivaTable t = null;

	private InputStream getImageInput(final String file) throws FileNotFoundException {
		final InputStream input = getClass().getResourceAsStream(file);
		return input;
	}

	@Override
	public Layout getLayout() {

		final NavRoute.BuilderNavRoute navRuoteBuilder = new NavRoute.BuilderNavRoute("TEST");
		NavRoute route = null;
		try {
			route = navRuoteBuilder.addNode("Impresion masiva de Comprobantes", "Impresion masiva de \n   Comprobantes",0,false,getImageInput("/img/impresion.png")).build();
					
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

		Pane mainPane = new BorderPane();

		mainPane.setPadding(new Insets(0, 20, 0, 20));

		HBox headerBox1 = new HBox();
		HBox headerBox2 = new HBox();

		ImageView atras = null;
		ImageView importarArchivo = null;
		ImageView instrucciones = null;

		try {
			atras = new ImageView(new Image(this.getImageInput("/img/atras.png")));
			importarArchivo = new ImageView(new Image(this.getImageInput("/img/importarArchivo.png")));
			importarArchivo.setPreserveRatio(true);
			importarArchivo.setFitWidth(70);
			instrucciones = new ImageView(new Image(this.getImageInput("/img/instrucciones.png")));
			instrucciones.setPreserveRatio(true);
			instrucciones.setFitWidth(70);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Button bAtras = new Button();
		Button bInstrucciones = new Button();
		Button bImportarArchivo = new Button();

		bAtras.setGraphic(atras);
		bAtras.setStyle("-fx-background-color: transparent;");
		bAtras.setTooltip(new Tooltip("Regresar"));
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
		});

		final FileChooser fileChooser = new FileChooser();
		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Excel and Csv files (*.xls,*.csv)", "*.csv","*.xls");
		fileChooser.getExtensionFilters().add(extFilter);

		headerBox1.getChildren().add(bAtras);
		
		
		
		headerBox2.getChildren().add(bInstrucciones);
		headerBox2.getChildren().add(bImportarArchivo);
		headerBox2.setSpacing(30);
		HBox.setHgrow(headerBox2, Priority.ALWAYS);
		headerBox2.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
		headerBox1.getChildren().add(headerBox2);

		BorderPane borderpane = new BorderPane();
		borderpane.setPadding(new Insets(0, 20, 0, 20));
		Label lFormato = new Label("Formato");
		lFormato.setTextFill(Color.WHITE);
		RadioButton rbTxt = new RadioButton("txt");
		rbTxt.setTextFill(Color.WHITE);
		RadioButton rbCsv = new RadioButton("csv");
		rbCsv.setSelected(true);
		rbCsv.setTextFill(Color.WHITE);
		ToggleGroup tgFormato = new ToggleGroup();
		rbCsv.setToggleGroup(tgFormato);
		rbTxt.setToggleGroup(tgFormato);
		rbTxt.setDisable(true);

		HBox hb = new HBox();
		hb.setSpacing(10);
		// hb.getChildren().addAll(lFormato, rbTxt, rbCsv);
		
		
		VBox v = new VBox();
		
		FlowPane fp = new FlowPane();
		fp.setHgap(20);
		
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
		fp.getChildren().addAll(tfCuentaCargo,tfCuentaAbono,tfImporte,tfCveRastreo,tfReferencia);

		v.getChildren().add(fp);
		
		
		
		hb.getChildren().add(v);
		hb.setAlignment(Pos.CENTER_RIGHT);

		borderpane.setCenter(hb);

		Button bGuardar = new Button("Guardar");
		bGuardar.setStyle(
				"-fx-background-color: #006dff;  -fx-font-family: FranklinGothicLT-Demi;-fx-font-size: 15px;");
		bGuardar.setPrefWidth(140);
		bGuardar.setTextFill(Color.WHITE);
		borderpane.setRight(bGuardar);

		bGuardar.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(final ActionEvent e) {
				
				
				try {
					
					
					String currentPath = Paths.get(".").toAbsolutePath().normalize().toString();
					FileChooser saveFile = new FileChooser();
					saveFile.setInitialDirectory(new File(currentPath));
					FileChooser.ExtensionFilter sfFilter = new FileChooser.ExtensionFilter("PDF files (*.PDF)", "*.pdf");
					saveFile.getExtensionFilters().add(sfFilter);
					

					// Show save file dialog
					File file = saveFile.showSaveDialog(getDesktop().getStage());

					if (file != null) {
						final FileOutputStream fout = new FileOutputStream(file);
						file.createNewFile();
						
						final ContextReport context = new ContextReport();
						context.addParameter("cliente", "");
						context.addImageParameter("logo",  getImageInput("/img/impresion.png"));
						ReportGenerator.generateFromCompiledReport("/reports/ComprobanteDispersionPago.jasper", context,
								ReportDataSourceFactory.getBeanDataSource(t.getItems()), fout);
						fout.close();
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
						"Banco Sabadell agradece su preferencia, a continuacion detallamos los pasos que debe seguir para capturar los datos de alta de beneficiario.");
				instruccionesLabel.setWrapText(true);
				instruccionesLabel.setTextAlignment(TextAlignment.JUSTIFY);
				instruccionesLabel
						.setStyle("-fx-font-family: FranklinGothicLT-Demi;-fx-font-size: 14px;-fx-font-weight: bold");
				instruccionesLabel.setTextFill(Color.WHITE);
				canvas.getChildren().add(instruccionesLabel);

				stage.getIcons().add(new Image(getClass().getResourceAsStream("/img/logoSabadellCircle.png")));
				stage.setTitle("Archivos Bantotal - Beneficiarios - Instrucciones");

				TextArea textArea = new TextArea();
				textArea.setText(
						"\n"
					  + "1) Revise que la configuracion regional de su sistema operativo este en Español (México)."
					  + "\n\n2) Los datos que se capturan deben estar en mayusculas y sin caracteres especiales."
					  + "\n\n3) Finalmente le pedimos validar que los datos marcados como obligatorios se encuentren con la información requerida."
					  + "\n\n4) Al concluir la captura de beneficiarios, dar un click en el boton de Guardar, en seguida se abrira una ventana donde usted podrá guardar el archivo en la ruta que indique y con el nombre que desee."
					  + "\n\n5) Al concluir el guardado correcto del archivo de Beneficiarios el siguiente paso es ingresar a su banca en linea de Banco Sabadell, para iniciar el proceso de Alta de Beneficiarios."
					  + "\n\n6) Los Beneficiarios que se dan de alta estarán disponibles para transaccionar despues de 30 minutos."
					  );
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

		VBox vbox = new VBox(headerBox1, borderpane);
		vbox.setSpacing(20);

		((BorderPane) mainPane).setTop(vbox);

		t = new DispersionDefinitivaTable();		

		t.prefWidthProperty().bind(mainPane.widthProperty().add(-60));

		((BorderPane) mainPane).setCenter(t);
		BorderPane.setMargin(t, new Insets(25, 0, 0, 0));

		bImportarArchivo.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(final ActionEvent e) {
				String currentPath = Paths.get(".").toAbsolutePath().normalize().toString();
				fileChooser.setInitialDirectory(new File(currentPath));
				File file = fileChooser.showOpenDialog(getDesktop().getStage());
				DispersionDefinitivaCSVImporter benImporter = new DispersionDefinitivaCSVImporter(t);
				try {
					System.out.println("voy a importar");
					benImporter.importFile(file);
					t.refresh();
					System.out.println("ya termine");
				} catch (Exception e1) {
					System.out.println("Exception");
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		getDesktop().setWorkArea(mainPane);
	}

}
