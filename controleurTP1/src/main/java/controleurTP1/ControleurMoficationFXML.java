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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import modele_TP1.Context;
import modele_TP1.Dictionnaire;
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
    void ajouterMot(ActionEvent event) {

    }

    private Stage popUp;

    @FXML
    void lancerVueRecherche(ActionEvent event) {
        System.out.println("Btn Recherche");

        Parent root;
        if(popUp == null) {
            popUp = new Stage();
        }
        if(!popUp.isShowing()){

            try {
                root = FXMLLoader.load(getClass().getResource("/vueMenuSearch.fxml"));
                popUp = new Stage();
                popUp.setTitle("Recherche");
                popUp.setScene(new Scene(root));
                popUp.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    @FXML
    void modifierMot(ActionEvent event) {
        if(Context.getInstance().getMotCourant()!=null){
            if(popUp == null) {
                popUp = new Stage();
            }
            if(!popUp.isShowing()) {

                Parent root;
                try {
                    root = FXMLLoader.load(getClass().getResource("/vueMenuModifierMot.fxml"));
                    popUp.setTitle("Modification");
                    popUp.setScene(new Scene(root));
                    popUp.show();


                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Avertissement");
            alert.setHeaderText("Erreur");
            alert.setContentText("Pour modifier un mot il faut un mot, non?");
            alert.showAndWait();
        }
    }

    @FXML
    void motdujourRandom(ActionEvent event) {

    }

    @FXML
    void supprimerMot(ActionEvent event) {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Avertissement");
        alert.setHeaderText("Suppression");
        alert.setContentText("Voulez-vous vraiment supprimer ce mot du dictionnaire?");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.get() == ButtonType.OK){

        }
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
        Context.getInstance().addObserver(this);
	}

    @Override
    public void update(Observable o, Object arg) {
        textfielMot.setText(Context.getInstance().getMotCourant().getMot());
        textAreaDefinition.setText(Context.getInstance().getMotCourant().getDefinition());
        if(!Context.getInstance().getMotCourant().getImageURL().isEmpty())
            imageDuMot.setImage(new Image(Context.getInstance().getMotCourant().getImageURL()));
        else imageDuMot.setImage(null);
    }
}


