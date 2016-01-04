package controleurTP1;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ControleurFXML implements Initializable{




	@FXML
	public void recherche_mot(){
		
	}
	@FXML
	public void lancerVueRecherche(){
		System.out.println("Btn Recherche");
		Parent root;
		try {
			root = FXMLLoader.load(getClass().getResource("/vueMenuModification.fxml"));
			Stage stage = new Stage();
			stage.setTitle("My New Stage Title");
			stage.setScene(new Scene(root, 450, 450));
			stage.show();


		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@FXML
	public void ajoutImageparDrag(){

	}
	@FXML
	public void importation_dictionnaire(){
		
	}
	@FXML
	public void enregistrer_nouveau_mots(){
		
	}
	@FXML
	public void effacer_un_mot(){
		
	}
	@FXML
	public void modifier_un_mot(){
		
	}



	@Override
	public void initialize(URL location, ResourceBundle resources) {


	}
	
}
