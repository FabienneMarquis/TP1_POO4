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
        import javafx.scene.image.ImageView;
        import javafx.stage.Stage;

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
    void modifierMotConfirmation(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Avertissement");
        alert.setHeaderText("Quitter?");
        alert.setContentText("Voulez-vous quitter la modification de mot?");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.get() == ButtonType.OK){

        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
