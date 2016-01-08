package controleurTP1;
import java.io.IOException;
import java.net.URL;

import java.util.*;

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
import modele_TP1.LocatedImage;
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

    private Random random = new Random();

    private Stage popUp;

    @FXML
    void ajouterMot(ActionEvent event) {

        Parent root;
        if(popUp == null) {
            popUp = new Stage();
        }
        if(!popUp.isShowing()){

            try {

            root = FXMLLoader.load(getClass().getResource("/vueMenuNouveauMot.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Nouveau Mot");
            stage.setScene(new Scene(root));
            stage.show();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    }



    @FXML
    void lancerVueRecherche(ActionEvent event){
            System.out.println("Btn Recherche");

            Parent root;
            if (popUp == null) {
                popUp = new Stage();
            }
            if (!popUp.isShowing()) {

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
        int index = random.nextInt(Context.getInstance().getDictionnaire().getMots().size()-1);
        System.out.println("\nIndex :" + index );
        Mot mot = Context.getInstance().getDictionnaire().getMots().get(index);

       if (!mot.getImageURL().isEmpty() ) imageDuMot.setImage( new Image( mot.getImageURL()));
        else imageDuMot.setImage(null);
        textfielMot.setText(mot.getMot());
        textAreaDefinition.setText(mot.getDefinition());
        Context.getInstance().setMotCourant(mot);
    }

    @FXML
    void supprimerMot(ActionEvent event) {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Avertissement");
        alert.setHeaderText("Suppression");
        alert.setContentText("Voulez-vous vraiment supprimer ce mot du dictionnaire?");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.get() == ButtonType.OK){
            Context.getInstance().getDictionnaire().supprimerMot(Context.getInstance().getMotCourant());
            Context.getInstance().getDictionnaire().sortByMot();
            textAreaDefinition.setText("");
            textfielMot.setText("");
            if (imageDuMot.getImage()!=null) imageDuMot.setImage(null);
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

    /**
     * Méthode qui permet de lien le contrôleur (observateur) à la classe Context qui est observer
     * permet le lien entre les contrôleurs
     * @param o
     * @param arg
     */
    @Override
    public void update(Observable o, Object arg) {
        textfielMot.setText(Context.getInstance().getMotCourant().getMot());
        textAreaDefinition.setText(Context.getInstance().getMotCourant().getDefinition());
        if(!Context.getInstance().getMotCourant().getImageURL().isEmpty())
            imageDuMot.setImage(new Image(Context.getInstance().getMotCourant().getImageURL()));
        else imageDuMot.setImage(null);


    }
}



