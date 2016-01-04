package controleurTP1;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class ControleurRechercheFXML implements Initializable {

	@FXML
    private CheckBox checkBoxImage;

    @FXML
    private DatePicker daterechercher;

    @FXML
    private TextField motOuDefinietionRechercher;

    @FXML
    private Button btnRecherche;

    @FXML
    private ListView<?> listeViewResultatRecherche;
	@FXML
	void rechercheSelonCriteres(ActionEvent event) {
		if (motOuDefinietionRechercher == null) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Avertissement");
			alert.setHeaderText("Erreur");
			alert.setContentText("Il n'y a aucun mot ou définition à recherche");
		}
		

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		btnRecherche.setOnAction(this::rechercheSelonCriteres);
	}

}
