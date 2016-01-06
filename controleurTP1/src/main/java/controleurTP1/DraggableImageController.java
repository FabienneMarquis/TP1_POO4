package controleurTP1;

/**
 * Created by fabienne on 2016-01-05.
 */
import javafx.application.Platform;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.paint.Color;
import modele_TP1.Dictionnaire;
import modele_TP1.DictionnairePrincipale;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DraggableImageController implements Initializable{



        @FXML
        private ImageView imageView;

        private Dragboard dragBoard;

        private String urlImage;

        @FXML
        void dragDetected(MouseEvent event) {

            if (dragBoard.hasImage()) {
                dragBoard = imageView.startDragAndDrop(TransferMode.COPY);

            }
            event.consume();
        }

        @FXML
        void dragDone(DragEvent  event) {
            Dragboard dragBoard = event.getDragboard();

        }

        @FXML
        void dragDropped( DragEvent event) {
            boolean success = false;
            Dragboard dragBoard =event.getDragboard();
            if (dragBoard.hasImage()) {
                addImage(dragBoard.getImage());
                event.setDropCompleted(true);
            }
            event.setDropCompleted(success);
            event.consume();
        }

        @FXML
        void dragEntered(DragEvent  event) {

            ColorAdjust colorAdjust = new ColorAdjust();
            colorAdjust.setBrightness(0.5);
            imageView.setEffect(colorAdjust);
        }

        @FXML
        void dragExited(DragEvent  event) {
            imageView.setEffect(null);
        }

        @FXML
        void dragOver(DragEvent  event) {
            Dragboard dragBoard = event.getDragboard();
            if (dragBoard.hasImage()) {
                event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
            }
            event.consume();
        }
    void addImage(Image i){
        imageView.setImage(i);
        String nomImage = "blueblue";
        URL path = getClass().getResource("/Images");
        File outputFile = new File(path+nomImage);
        WritableImage image = (WritableImage) i;
        BufferedImage bImage = SwingFXUtils.fromFXImage(image, null);

        try {
            ImageIO.write(bImage, "png", outputFile);
          urlImage = path+nomImage;

            (this.setImageURL(urlImage);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

           }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}

