package controleurTP1;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class ControleurMoficationFXML implements Initializable {
	@FXML
    private Button btMotduJour;

    @FXML
    private Button btAjouterMot;

    @FXML
    private Button btModifier;

    @FXML
    private Button btSupprimer;

    @FXML
    private Button btRecherche;

    @FXML
    private Button btnQuitter;

    @FXML
    private ImageView imageDuMot;

    @FXML
    private TextField textfielMot;

    @FXML
    private TextArea textAreaDefinition;

    @FXML
    void ajoutImageparDrag(ActionEvent event) {

    }

    @FXML
    void ajouterMot(ActionEvent event) {

    }

    @FXML
    void lancerVueRecherche(ActionEvent event) {

    }

    @FXML
    void modifierMot(ActionEvent event) {

    }

    @FXML
    void motdujourRandom(ActionEvent event) {

    }

    @FXML
    void supprimerMot(ActionEvent event) {

    }

    @FXML
    void quitterProgramme(ActionEvent event) {
        Alert alert = new Alert(AlertType.Confirmation);
        alert.setTitle("Avertissement");
        alert.setHeaderText("Quitter?");
        alert.setContentText("Voulez-vous quitter le dictionnaire");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.get() == ButtonType.OK){
            Platform.exit();
            System.exit(0);
        }
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		//btnRecherche.setOnAction(this::rechercheSelonCriteres);
		btAjouterMot.setOnAction(this::ajouterMot);
		btModifier.setOnAction(this::modifierMot);
		btMotduJour.setOnAction(this::motdujourRandom);
		btRecherche.setOnAction(this::lancerVueRecherche);
		
	}

	}


