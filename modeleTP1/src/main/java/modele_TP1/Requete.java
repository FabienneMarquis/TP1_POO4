package modele_TP1;

import com.sun.istack.internal.Nullable;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 0940135 on 2016-01-04.
 */
public class Requete {
    private String mot;
    private LocalDate avantCreation;
    private LocalDate apresCreation;
    private LocalDate avantModification;
    private LocalDate apresModification;
    private Boolean posedeImage;
    private List<Mot> resultat;
    private boolean prefix;
    private Dictionnaire dictionnaire;

    /**
     *
     * @param mot
     */
    public Requete(String mot,Dictionnaire dictionnaire) {
        this.mot = mot;
        this.dictionnaire = dictionnaire;
    }

    public Requete(boolean prefix, String mot,Dictionnaire dictionnaire) {
        this.prefix = prefix;
        this.mot = mot;
        this.dictionnaire = dictionnaire;
    }

    /**
     *
     * @param posedeImage
     */
    public Requete(Boolean posedeImage,Dictionnaire dictionnaire) {
        this.posedeImage = posedeImage;
        this.dictionnaire = dictionnaire;
    }

    /**
     *
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
     *
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
     *
     * @return resultat
     */
    public final List<Mot> getResultat() {
        return resultat;
    }


    public void setPrefix(boolean prefix) {
        this.prefix = prefix;
    }

    /**
     *
     * @return mot
     */
    public String getMot() {
        return mot;
    }

    /**
     *
     * @return avantCreation
     */
    public LocalDate getAvantCreation() {
        return avantCreation;
    }

    /**
     *
     * @return apresCreation
     */
    public LocalDate getApresCreation() {
        return apresCreation;
    }

    /**
     *
     * @return avantModification
     */
    public LocalDate getAvantModification() {
        return avantModification;
    }

    /**
     *
     * @return apresModification
     */
    public LocalDate getApresModification() {
        return apresModification;
    }

    /**
     *
     * @return isPosedeImage
     */
    public boolean isPosedeImage() {
        return posedeImage;
    }

    public boolean isPrefix() {
        return prefix;
    }

    public void recherche(){
        Recherche recherche = new Recherche(dictionnaire);
        if(resultat==null) resultat = new ArrayList<>();
        if(mot!= null){
            if(!mot.isEmpty()){
                if(mot.contains("?") || mot.contains("*")){
                    resultat = recherche.rechercheMotAvecWildcards(mot);
                }else if(prefix) {
                    resultat = recherche.rechercheMotParPrefix(mot);
                }else {
                    resultat.add(recherche.rechercheMot(mot));
                }
            }
        }
        if(avantCreation!= null){
            recherche = new Recherche(new Dictionnaire(resultat));
            resultat = recherche.rechercheAvantDateCreation(avantCreation);
        }

        if(apresCreation != null){
            recherche = new Recherche(new Dictionnaire(resultat));
            resultat = recherche.rechercheApresDateCreation(apresCreation);
        }

        if(avantModification!=null){
            recherche = new Recherche(new Dictionnaire(resultat));
            resultat = recherche.rechercheAvantDateModification(apresCreation);
        }
        if(apresModification!=null){
            recherche = new Recherche(new Dictionnaire(resultat));
            resultat = recherche.rechercheApresDateModification(apresCreation);
        }
        if(posedeImage){
            recherche = new Recherche(new Dictionnaire(resultat));
            resultat = recherche.rechercheMotAvecImage(posedeImage);
        }
    }

}
