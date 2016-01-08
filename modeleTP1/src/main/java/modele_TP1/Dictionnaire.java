package modele_TP1;

import java.util.List;
import java.util.Map;

/**
 * Created by Gabriel on 31/12/2015.
 */
public class Dictionnaire {
    private List<Mot> mots;
    private boolean isSortedByMot,isSortedByDateOfCreation,isSortedByDateOfModification,isSortedByImage;

    /**
     * Constructeur du Dictionnaire (liste de Mot)
     * @param mots
     */
    public Dictionnaire(List<Mot> mots) {
        this.mots = mots;
        isSortedByMot = false;
        isSortedByDateOfCreation = false;
        isSortedByDateOfModification = false;
        isSortedByImage = false;
    }

    /**
     * Retourne la liste de mots du dictionnaire
     * @return la liste de mots
     */
    public List<Mot> getMots() {
        return mots;
    }

    /**
     * permet l'attibution (set) de la liste de mots par une liste de mot
     * @param mots liste de mots
     */
    public void setMots(List<Mot> mots) {
        this.mots = mots;
    }

    /**
     * Cette méthode permet de classer la liste de mots en ordre du mot associé à chaque Mot
     */
    public void sortByMot(){
        if(!isSortedByMot)
            mots.sort((Mot mot1, Mot mot2) -> mot1.getMot().compareTo(mot2.getMot()));
        isSortedByMot = true;
        isSortedByImage = false;
        isSortedByDateOfModification = false;
        isSortedByDateOfCreation = false;
    }

    /**
     * Cette méthode permet de classer la liste de mots en ordre de la date de création
     */
    public void sortByDateOfCreation(){
        if(!isSortedByDateOfCreation)
            mots.sort((Mot mot1, Mot mot2) ->mot1.getCreatedAt().compareTo(mot2.getCreatedAt()));

        isSortedByMot = false;
        isSortedByImage = false;
        isSortedByDateOfModification = false;
        isSortedByDateOfCreation = true;
    }

    /**
     * Cette méthode permet de classer la liste de mots en ordre de la date de modification
     */
    public void sortByDateOfModification(){
        if(!isSortedByDateOfModification)
            mots.sort((Mot mot1, Mot mot2) ->mot1.getCreatedAt().compareTo(mot2.getCreatedAt()));

        isSortedByMot = false;
        isSortedByImage = false;
        isSortedByDateOfModification = true;
        isSortedByDateOfCreation = false;
    }

    /**
     * Cette méthode permet de classer la liste de mots selon ceux qui ont des images associés
     */
    public void sortByImage(){
        if(!isSortedByImage)
            mots.sort((Mot mot1, Mot mot2) ->mot1.getImageURL().compareTo(mot2.getImageURL()));

        isSortedByMot = false;
        isSortedByImage = true;
        isSortedByDateOfModification = false;
        isSortedByDateOfCreation = false;
    }

    /**
     * Cette méthode permets d'ajouter un mot dans la liste du dictionnaire
     * @param mot
     */
    public void addMot(Mot mot){
        mots.add(mot);
        isSortedByMot = false;
        isSortedByDateOfCreation = false;
        isSortedByDateOfModification = false;
        isSortedByImage = false;

    }

    /**
     * Cette méthode permet de supprimer un mot
     * @param mot mot à supprimer
     */
    public void supprimerMot(Mot mot){
        mots.remove(mot);
        isSortedByMot = false;
        isSortedByDateOfCreation = false;
        isSortedByDateOfModification = false;
        isSortedByImage = false;

    }


    /**
     * renvoit vrai ou faux si la liste est classer selon le paramettre mot
     * @return boolean
     */
    public boolean isSortedByMot() {
        return isSortedByMot;
    }

    /**
     * renvoit vrai ou faux si la liste est classer selon le paramettre par la date de création
     * @return boolean
     */
    public boolean isSortedByDateOfCreation() {
        return isSortedByDateOfCreation;
    }

    /**
     * renvoit vrai ou faux si la liste est classer selon le paramettre par al date de modification
     * @return boolean
     */
    public boolean isSortedByDateOfModification() {
        return isSortedByDateOfModification;
    }

    /**
     * renvoit vrai ou faux si la liste est classer selon le paramettre de l'image
     * @return boolean
     */
    public boolean isSortedByImage() {
        return isSortedByImage;
    }
}
