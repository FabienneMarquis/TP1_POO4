package controleurTP1;
/**
 * @author Créer par Team Gabriel Fabienne
 */

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import modele_TP1.Dictionnaire;
import modele_TP1.Context;
import modele_TP1.MotFactory;

public class Controleur extends Application implements Initializable{
	@FXML
	private GridPane root;

	@FXML
	private Scene scene;
	
	/**
	 * Methode qui démarre le programme
	 * @param args
	 */
	public static void main(String[] args) {
		launch(args);
	}


	
	/**
	 * Méthode qui va gérer la vue (l'interface graphique du programme)
	 * @param primaryStage
	 * @throws Exception
	 */
	public void start(Stage primaryStage) throws Exception {
		String path = "/vueMenuModification.fxml";
		root = FXMLLoader.load(getClass().getResource(
				path));
		scene = new Scene(root);
		Context.getInstance().setDictionnaire(new Dictionnaire(MotFactory.getInstance().getMots()));

	primaryStage.setTitle("Bibliothèque");
	primaryStage.setScene(scene);
	primaryStage.show();
		
	}

	/**
	 * Methode vide (garder au cas où nous aurions besoin de
 	 * @param arg0
	 * @param arg1
     */

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {


	}
	
	
}
