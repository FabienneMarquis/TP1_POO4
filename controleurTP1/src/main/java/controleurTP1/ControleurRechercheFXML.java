package controleurTP1;

import java.awt.event.KeyEvent;
import java.net.URL;
import java.time.LocalDate;
import java.util.Observable;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.text.Text;
import javafx.util.Callback;
import modele_TP1.DictionnairePrincipale;
import modele_TP1.Mot;
import modele_TP1.Requete;

public class ControleurRechercheFXML extends Observable implements Initializable{

	@FXML
	ChoiceBox<String> critereRechercheparDate;

	@FXML
	ObservableList<String> choixRechercheDate = FXCollections
			.observableArrayList("Avant la date de saisie",
					"Après la date de saisie", "Avant la date de modification",
					"Après la date de modification");

	@FXML
	private CheckBox checkBoxMotsContenant;

	@FXML
	private TextField motOuDefinietionRechercher;

	@FXML
	private ListView<Mot> listeViewResultatRecherche;

	@FXML
	private Button btnRecherche;

	@FXML
	private CheckBox checkBoxImage;

	@FXML
	private DatePicker dateApresModification;

	@FXML
	private DatePicker dateApresEntree;

	@FXML
	private DatePicker dateAvantModification;

	@FXML
	private DatePicker dateAvantEntree;

	@FXML
	private Text statDeRecherche;

	private ObservableList<Mot> observableList = FXCollections
			.synchronizedObservableList(FXCollections.observableArrayList());
	@FXML
	void rechercheSelonCriteres(ActionEvent event) {
		if (motOuDefinietionRechercher.getText().isEmpty()) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Avertissement");
			alert.setHeaderText("Erreur");
			alert.setContentText("Il n'y a aucun mot ou définition à recherche");
			alert.show();
		}else{
			recherche(false);
		}

	}
	@FXML
	void rechercheModifier(ActionEvent event) {
		recherche(true);
	}
	public void recherche(boolean prefix){
		String recherche = motOuDefinietionRechercher.getText();
		if(checkBoxMotsContenant.isSelected()){
			recherche = "*"+recherche+"*";
		}
		Requete requete = new Requete(recherche,dateAvantEntree.getValue()
				,dateApresEntree.getValue(),dateAvantModification.getValue(),dateApresModification.getValue()
				,checkBoxImage.isSelected(),DictionnairePrincipale.getDictionnaire());
		requete.setPrefix(prefix);
		requete.recherche();

		observableList.clear();
		observableList.addAll(requete.getResultat());
		listeViewResultatRecherche.setItems(observableList);
		listeViewResultatRecherche.setCellFactory(new Callback<ListView<Mot>, ListCell<Mot>>() {
			@Override
			public ListCell<Mot> call(
					ListView<Mot> param) {
				return new ListCell<Mot>() {
					@Override
					protected void updateItem(Mot t,
											  boolean bln) {
						super.updateItem(t, bln);
						if (t != null) {
							Platform.runLater(() -> setText(t.getMot()));
						}
					}
				};
			}
		});
		statDeRecherche.setText("Resultats: "+requete.getResultat().size()+ " Temps: " + requete.getRechercheTime());
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		motOuDefinietionRechercher.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue)->{
			recherche(true);
		});
		listeViewResultatRecherche.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Mot>() {
			@Override
			public void changed(ObservableValue<? extends Mot> observable, Mot oldValue, Mot newValue) {
				notifyObservers(newValue);
			}
		});
	}

}
