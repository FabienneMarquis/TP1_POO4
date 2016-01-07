package controleurTP1;

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
import modele_TP1.Context;
import modele_TP1.Dictionnaire;
import modele_TP1.Mot;

import java.io.IOException;
import java.net.URL;
import java.util.Observable;
import java.util.Observer;
import java.util.Optional;
import java.util.ResourceBundle;


public class ControleurNouveauMotFXML implements Initializable, Observer{
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

    private Dictionnaire dictionnaire;
    private Mot mot;

    @FXML
    void annuler(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Avertissement");
        alert.setHeaderText("Quitter?");
        alert.setContentText("Voulez-vous quitter la modification de mot?");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.get() == ButtonType.OK){

        }
    }

    @FXML
    void creerNouveauMot(ActionEvent event) {
        if(textfielMot.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Avertissement");
            alert.setHeaderText("Erreur");
            alert.setContentText("Il n'y a aucun mot à ajouter.");
        }else {
            if (imageDuMot.getImage().isError()&&textAreaDefinition.getText().isEmpty()){
                mot.setMot(textfielMot.getText());

            }else if(textAreaDefinition.getText().isEmpty()) {
                mot.setMot(textfielMot.getText());
                //mot.setImageURL(imageDuMot.getImage().);
            } else if (imageDuMot.getImage().isError()){
                mot.setMot(textfielMot.getText());
                mot.setDefinition(textAreaDefinition.getText());
            }
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        System.out.println(((Mot)arg).getMot());
        textfielMot.setText(((Mot)arg).getMot());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        dictionnaire = Context.getInstance().getDictionnaire();
    }
}


