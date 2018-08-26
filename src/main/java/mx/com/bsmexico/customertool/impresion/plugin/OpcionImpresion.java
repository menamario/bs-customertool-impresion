package mx.com.bsmexico.customertool.impresion.plugin;

import java.io.FileNotFoundException;
import java.io.InputStream;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import mx.com.bsmexico.customertool.api.Feature;
import mx.com.bsmexico.customertool.api.Layout;
import mx.com.bsmexico.customertool.api.NavRoute;

public class OpcionImpresion extends Feature {

	public String getNombreMenu() {
		// TODO Auto-generated method stub
		return "Alta de Beneficiarios";
	}

	public void ejecutar() {
       System.out.println("Se ejecuto el alta de Beneficiarios");

	}

	public String getImagenMenu() {
		return "/img/beneficiarios.svg";

	}
	
	@SuppressWarnings("unused")
	private InputStream getImageInput(final String file, double h, double w) throws FileNotFoundException {
		final InputStream input = getClass().getResourceAsStream(file);		
		return input;
		
	}

	@Override
	public Layout getLayout() {

		final NavRoute.BuilderNavRoute navRuoteBuilder = new NavRoute.BuilderNavRoute("TEST");
		NavRoute route = null;
		try {
			route = navRuoteBuilder.addNode("Impresion masiva de Comprobantes", "Impresion masiva de \n   Comprobantes",0,false,getImageInput("/img/impresion.png",100,100)).build();
					
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
		Pane pane = new Pane();
		
		pane.setStyle("-fx-background-color: black");
		pane.setMinSize(500, getMenuNavigator().getDesktop().getMinHeight());
		Label label = new Label("Estoy en la opcion de impresion");
		label.setTextFill(Color.WHITE);
		Button hideMenu = new Button("Hide Menu");
		hideMenu.setOnMouseClicked(evt -> {
			getMenuNavigator().hide();
		});
		Button showMenu = new Button("Show Menu");
		showMenu.setOnMouseClicked(evt -> {
			getMenuNavigator().show();
		});
		HBox box = new HBox(label,hideMenu,showMenu);
		pane.getChildren().add(box);
		getDesktop().setWorkArea(pane);
	}

}
