package modele_TP1;

import java.util.List;
import java.util.Map;

/**
 * Created by Gabriel on 31/12/2015.
 */
public class Dictionnaire {
    private List<Mot> mots;
    private boolean isSortedByMot,isSortedByDateOfCreation,isSortedByDateOfModification,isSortedByImage;

    public Dictionnaire(List<Mot> mots) {
        this.mots = mots;
        isSortedByMot = false;
        isSortedByDateOfCreation = false;
        isSortedByDateOfModification = false;
        isSortedByImage = false;
    }

    public List<Mot> getMots() {
        return mots;
    }

    public void setMots(List<Mot> mots) {
        this.mots = mots;
    }

    public void sortByMot(){
        if(!isSortedByMot)
            mots.sort((Mot mot1, Mot mot2) -> mot1.getMot().compareTo(mot2.getMot()));
        isSortedByMot = true;
        isSortedByImage = false;
        isSortedByDateOfModification = false;
        isSortedByDateOfCreation = false;
    }
    public void sortByDateOfCreation(){
        if(!isSortedByDateOfCreation)
            mots.sort((Mot mot1, Mot mot2) ->mot1.getCreatedAt().compareTo(mot2.getCreatedAt()));

        isSortedByMot = false;
        isSortedByImage = false;
        isSortedByDateOfModification = false;
        isSortedByDateOfCreation = true;
    }
    public void sortByDateOfModification(){
        if(!isSortedByDateOfModification)
            mots.sort((Mot mot1, Mot mot2) ->mot1.getCreatedAt().compareTo(mot2.getCreatedAt()));

        isSortedByMot = false;
        isSortedByImage = false;
        isSortedByDateOfModification = true;
        isSortedByDateOfCreation = false;
    }
    public void sortByImage(){
        if(!isSortedByImage)
            mots.sort((Mot mot1, Mot mot2) ->mot1.getImageURL().compareTo(mot2.getImageURL()));

        isSortedByMot = false;
        isSortedByImage = true;
        isSortedByDateOfModification = false;
        isSortedByDateOfCreation = false;
    }
}