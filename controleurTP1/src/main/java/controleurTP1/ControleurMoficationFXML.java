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


