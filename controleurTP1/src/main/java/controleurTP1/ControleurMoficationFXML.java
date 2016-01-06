package controleurTP1;
import java.io.IOException;
import java.net.URL;
import java.util.Observable;
import java.util.Observer;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import modele_TP1.DictionnairePrincipale;
import modele_TP1.Mot;

public class ControleurMoficationFXML implements Initializable, Observer{
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
        System.out.println("Btn Recherche");
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("/vueMenuSearch.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Recherche");
            stage.setScene(new Scene(root));
            stage.show();


        } catch (IOException e) {
            e.printStackTrace();
        }
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
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
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

    @Override
    public void update(Observable o, Object arg) {
        System.out.println(((Mot)arg).getMot());
        textfielMot.setText(((Mot)arg).getMot());
    }
}


