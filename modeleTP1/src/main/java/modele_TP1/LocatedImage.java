package modele_TP1;

import javafx.scene.image.Image;

/**
 * Created by 0940135 on 2016-01-06.
 */
public class LocatedImage extends Image {
    private final String url;

    public LocatedImage(String url) {
        super(url);
        this.url = url;
    }

    public String getURL() {
        return url;
    }
}
