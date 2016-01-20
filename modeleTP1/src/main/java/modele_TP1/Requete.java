package modele_TP1;

import com.sun.istack.internal.Nullable;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by gabriel on 2016-01-04.
 */
public class Requete {
    private String mot;
    private LocalDate avantCreation;
    private LocalDate apresCreation;
    private LocalDate avantModification;
    private LocalDate apresModification;
    private boolean posedeImage;
    private List<Mot> resultat;
    private boolean prefix;
    private Dictionnaire dictionnaire;
    private long rechercheTime;

    /**
     * requêter utiliser pour la recherche selon le mot
     * @param mot
     */
    public Requete(String mot,Dictionnaire dictionnaire) {
        this.mot = mot;
        this.dictionnaire = dictionnaire;
        this.posedeImage = false;
    }

    public Requete(boolean prefix, String mot,Dictionnaire dictionnaire) {
        this.prefix = prefix;
        this.mot = mot;
        this.dictionnaire = dictionnaire;
    }

    /**
     *requêter utiliser pour la recherche selon si le mot a une image associé ou non
     * @param posedeImage
     */
    public Requete(Boolean posedeImage,Dictionnaire dictionnaire) {
        this.posedeImage = posedeImage;
        this.dictionnaire = dictionnaire;
    }

    /**
     *requêter utiliser pour la recherche selon la date de création ou de modification
     * @param avantCreation
     * @param apresCreation
     * @param avantModification
     * @param apresModification
     */
    public Requete(@Nullable LocalDate avantCreation,@Nullable LocalDate apresCreation,@Nullable LocalDate avantModification,@Nullable LocalDate apresModification,Dictionnaire dictionnaire) {
        this.avantCreation = avantCreation;
        this.apresCreation = apresCreation;
        this.avantModification = avantModification;
        this.apresModification = apresModification;
        this.dictionnaire = dictionnaire;
    }

    /**
     *requêter utiliser pour la recherche selon la date de création ou de modification, un mot et une image
     * @param mot
     * @param avantCreation
     * @param apresCreation
     * @param avantModification
     * @param apresModification
     * @param posedeImage
     */
    public Requete(@Nullable String mot,@Nullable LocalDate avantCreation,@Nullable LocalDate apresCreation,
                   @Nullable LocalDate avantModification,@Nullable LocalDate apresModification,
                   @Nullable Boolean posedeImage,Dictionnaire dictionnaire) {
        this.mot = mot;
        this.avantCreation = avantCreation;
        this.apresCreation = apresCreation;
        this.avantModification = avantModification;
        this.apresModification = apresModification;
        this.posedeImage = posedeImage;
        this.dictionnaire = dictionnaire;
    }

    /**
     * retourne les resultats (list de mot) de la recherche
     * @return resultat liste de mots
     */
    public final List<Mot> getResultat() {
        return resultat;
    }

    /**
     * méthode utilisé par la recherche par préfix
     * @param prefix
     */
    public void setPrefix(boolean prefix) {
        this.prefix = prefix;
    }

    /**
     * méthode qui renvoit un mot
     * @return mot
     */
    public String getMot() {
        return mot;
    }

    /**
     *méthode qui renvoit la date choisi pour être le point d'avant la création
     * @return avantCreation
     */
    public LocalDate getAvantCreation() {
        return avantCreation;
    }

    /**
     *méthode qui renvoit la date choisi pour être le point après la création
     * @return apresCreation
     */
    public LocalDate getApresCreation() {
        return apresCreation;
    }

    /**
     *méthode qui renvoit la date choisi pour être le point d'avant une modification
     * @return avantModification
     */
    public LocalDate getAvantModification() {
        return avantModification;
    }

    /**
     * méthode qui renvoit la date choisi pour être le point d'après une modification
     * @return apresModification
     */
    public LocalDate getApresModification() {
        return apresModification;
    }

    /**
     * méthode qui renvoit si la présence d'une image est un critère de recherche
     * @return isPosedeImage
     */
    public boolean isPosedeImage() {
        return posedeImage;
    }

    /**
     * méthode qui renvoit si la recherche se fait avec un préfix
     * @return
     */
    public boolean isPrefix() {
        return prefix;
    }

    /**
     * Méthode de rechercher selon les critères choisis
     */
    public void recherche(){
        rechercheTime = System.currentTimeMillis();
        Recherche recherche = new Recherche(dictionnaire);
        if(resultat==null) resultat = new ArrayList<>();
        if(mot!= null){
            if(!mot.isEmpty()){
                if(mot.contains("?") || mot.contains("*")){
                    resultat = recherche.rechercheMotAvecWildcards(mot);
                }else if(prefix) {
                    resultat = recherche.rechercheMotParPrefix(mot);
                }else {
                    Mot rechercheMot = recherche.rechercheMot(mot);
                    if(rechercheMot!=null)
                        resultat.add(rechercheMot);
                }
            }
        }

        if(avantCreation!= null){
            if(resultat.size() == 0)
                resultat = dictionnaire.getMots();

            recherche = new Recherche(new Dictionnaire(resultat));
            resultat = recherche.rechercheAvantDateCreation(avantCreation);
        }

        if(apresCreation != null){
            if(resultat.size() == 0)
                resultat = dictionnaire.getMots();

            recherche = new Recherche(new Dictionnaire(resultat));
            resultat = recherche.rechercheApresDateCreation(apresCreation);
        }

        if(avantModification!=null){
            if(resultat.size() == 0)
                resultat = dictionnaire.getMots();

            recherche = new Recherche(new Dictionnaire(resultat));
            resultat = recherche.rechercheAvantDateModification(avantModification);
        }
        if(apresModification!=null){
            if(resultat.size() == 0)
                resultat = dictionnaire.getMots();

            recherche = new Recherche(new Dictionnaire(resultat));
            resultat = recherche.rechercheApresDateModification(apresModification);
        }
        if(posedeImage){
            if(resultat.size() == 0)
                resultat = dictionnaire.getMots();

            recherche = new Recherche(new Dictionnaire(resultat));
            resultat = recherche.rechercheMotAvecImage(posedeImage);
        }
        rechercheTime = System.currentTimeMillis() - rechercheTime;
    }

    /**
     * Methode qui renvoit le temps pour effectuer la recherche
     * @return
     */
    public long getRechercheTime() {
        return rechercheTime;
    }
}
