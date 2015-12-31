package controleurTP1;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

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
		String path = "/vueMenuDebut.fxml";
		System.out.println(path);
		System.out.println(getClass());
		//System.out.println(getClass().getResource(
				//path).getPath());
		System.out.println("FUCK");
		root = FXMLLoader.load(getClass().getResource(
				path));
		scene = new Scene(root);


	primaryStage.setTitle("Bibliothèque");
	primaryStage.setScene(scene);
	primaryStage.show();
		
	}
	
	
	/**
	 * Methode qui réinitie la recherche ? à voir si c'est utile dans le contexte du TP1
	 */
	
	public void init() {
				
	}



	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
	
	
}
