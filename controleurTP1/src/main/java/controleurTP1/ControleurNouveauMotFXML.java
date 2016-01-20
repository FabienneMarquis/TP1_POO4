
package controleurTP1;
/**
 * @author Fabienne et Gabriel
 */

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import modele_TP1.*;

import java.io.IOException;
import java.net.URL;
import java.util.Observable;
import java.util.Observer;
import java.util.Optional;
import java.util.ResourceBundle;


public class ControleurNouveauMotFXML implements Initializable, Observer{

    @FXML
    private Button btNouveauMot;

    @FXML
    private Button btnAnnuler;

    @FXML
    private ImageView imageDuMot;

    @FXML
    private TextField textfielMot;

    @FXML
    private TextArea textAreaDefinition;
    /**
     * lorsque l'utilisateur appuit sur le bouton annuler il enclanche cet méthode qui demande la confirmation avant
     * de fermer ou non la fenêtre
     * @param event
     */
    @FXML
    void annuler(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Avertissement");
        alert.setHeaderText("Quitter?");
        alert.setContentText("Voulez-vous quitter la modification de mot?");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.get() == ButtonType.OK){
            ((Stage)btnAnnuler.getScene().getWindow()).close();
        }
    }

    /**
     * lorsque l'utilisateur appuit sur le bouton ajouter, cette méthdoe test si il y a un mot d'entrer dans le champs
     * d'entré mot ou si le mot existe déjà ou non dans le dictionnaire.
      * @param event
     */
    @FXML
    void creerNouveauMot(ActionEvent event) {
        if(textfielMot.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Avertissement");
            alert.setHeaderText("Erreur");
            alert.setContentText("Il n'y a aucun mot à ajouter.");
        } else if(Context.getInstance().sauvegarder(textfielMot.getText(),
                ((LocatedImage)imageDuMot.getImage())!=null?((LocatedImage)imageDuMot.getImage()).getURL():"", textAreaDefinition.getText())) {
             Context.getInstance().getDictionnaire().sortByMot();
            ((Stage) btNouveauMot.getScene().getWindow()).close();
        }
        else {
            Alert alertErreur = new Alert(Alert.AlertType.ERROR);
            alertErreur.setTitle("ERREUR!");
            alertErreur.setHeaderText("ERREUR!");
            alertErreur.setContentText("Mot deja existant");
            alertErreur.showAndWait();
        }
    }


    @Override
    public void update(Observable o, Object arg) {
        textfielMot.setText(((Mot)arg).getMot());
    }
    /**
     * Cette méthode est utilisé pour s'assurer que l'image respecte le cadre
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        imageDuMot.setPreserveRatio(true);
        imageDuMot.setFitWidth(150);
    }
}


