package controleurTP1;

/**
 * Created by fabienne on 2016-01-05.
 */
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.paint.Color;

import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.util.ResourceBundle;

public class DraggableImageController implements Initializable{



        @FXML
        private ImageView imageView;



        private Dragboard dragBoard ;

        @FXML
        void dragDetected(MouseEvent event) {
            dragBoard = imageView.startDragAndDrop(TransferMode.COPY);

        }

        @FXML
        void dragDone(DragEvent  event) {

        }

        @FXML
        void dragDropped( DragEvent event) {
            boolean success = false;
            if (dragBoard.hasImage()) {
                System.out.println("Dropped: " + dragBoard.getImage());
                success = true;
            }
            event.setDropCompleted(success);
            event.consume();
        }

        @FXML
        void dragEntered(DragEvent  event) {
            ColorAdjust colorAdjust = new ColorAdjust();
            colorAdjust.setBrightness(05);
            imageView.setEffect(colorAdjust);
        }

        @FXML
        void dragExited(DragEvent  event) {

        }

        @FXML
        void dragOver(DragEvent  event) {

            if (dragBoard.hasImage()) {
                event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
            }
            event.consume();
        }
    void addImage(Image i){

        imageView = new ImageView();
        imageView.setImage(i);
           }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}

