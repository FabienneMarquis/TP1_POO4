package controleurTP1;

/**
 * Created by Fabienne on 2016-01-05.
 */

import javafx.application.Application;
import javafx.application.Platform;
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
import modele_TP1.Context;
import modele_TP1.LocatedImage;
import modele_TP1.Requete;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class ControleurModifierMotFXML implements Initializable {


    @FXML
    private TextField textfielMot;

    @FXML
    private TextArea textAreaDefinition;

    @FXML
    private Button btModifier;

    @FXML
    private Button btnAnnuler;
    @FXML
    private ImageView imageDuMot;

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

    @FXML
    void modifierMotConfirmation(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Avertissement");
        alert.setHeaderText("Quitter?");
        alert.setContentText("Voulez-vous quitter la modification de mot?");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.get() == ButtonType.OK){
            if(sauvegarde()){

                ((Stage)btModifier.getScene().getWindow()).close();
            }
            else {
                Alert alertErreur = new Alert(Alert.AlertType.ERROR);
                alertErreur.setTitle("ERREUR!");
                alertErreur.setHeaderText("ERREUR!");
                alertErreur.setContentText("Mot deja existant");
                alertErreur.showAndWait();
            }
        }
    }
    private boolean sauvegarde(){
        if(textfielMot.getText().compareTo(Context.getInstance().getMotCourant().getMot())!=0){
            Requete requete = new Requete(textfielMot.getText(),Context.getInstance().getDictionnaire());
            requete.recherche();
            if(requete.getResultat().size()>0){
                return false;
            }
        }
        Context.getInstance().getMotCourant().setMot(textfielMot.getText());
        Context.getInstance().getMotCourant().setDefinition(textAreaDefinition.getText());
        if(imageDuMot.getImage()!=null)
            Context.getInstance().getMotCourant().setImageURL(((LocatedImage)imageDuMot.getImage()).getURL());

        Context.getInstance().alertObservers();
        return true;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if(Context.getInstance().getMotCourant()!=null){
            textfielMot.setText(Context.getInstance().getMotCourant().getMot());
            textAreaDefinition.setText(Context.getInstance().getMotCourant().getDefinition());
            if(!Context.getInstance().getMotCourant().getImageURL().isEmpty())
                imageDuMot.setImage(new Image(Context.getInstance().getMotCourant().getImageURL()));
        }


    }
}
