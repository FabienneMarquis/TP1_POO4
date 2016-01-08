package modele_TP1;

import java.time.LocalDate;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Recherche {

    private Dictionnaire dictionnaire;

	public Recherche(Dictionnaire dictionnaire){
        this.dictionnaire = dictionnaire;
    }

    public void recherche(Requete requete){


    }

    /**
     * Méthode qui fait une recherche binaire de la position d'une mot selon
     * @param recherche
     * @return
     */
    private int recherchePosMot(String recherche){
        int pos;
        dictionnaire.sortByMot();

        if (dictionnaire.getMots().isEmpty()){
            return -1;
        }
        int low = 0;
        int high = dictionnaire.getMots().size()-1;
        int middle = -1;
        while (low <= high){
            middle = (low+high)/2;

            if(recherche.compareTo(dictionnaire.getMots().get(middle).getMot()) > 0 ){
                low = middle + 1;
            }else if(recherche.compareTo(dictionnaire.getMots().get(middle).getMot())<0){
                high = middle -1;
            }
            else {
                return middle;
            }
        }
        return middle;
    }

    /**
     * Méthode qui fait une recherche par le mot
     * @param recherche
     * @return
     */
    public Mot rechercheMot(String recherche){
        int pos = recherchePosMot(recherche);
        Mot mot;
        if(pos>=0){
            mot = dictionnaire.getMots().get(pos);
            if(mot.getMot().compareTo(recherche)==0){
                return mot;
            }
        }
        return null;
    }

    /**
     *Méthode qui fait une recherche par préfix
     * @param prefix
     * @return
     */
    public List<Mot> rechercheMotParPrefix(String prefix){
        List<Mot> mots = new ArrayList<>();

        prefix = prefix.toLowerCase();
        char[] max = prefix.toCharArray();
        max[max.length-1] = (char) (max[max.length-1]+1);

        int pos = recherchePosMot(prefix);

        while (pos >= 0 && pos < dictionnaire.getMots().size()){
            if(dictionnaire.getMots().get(pos).getMot().compareTo(String.valueOf(max))<0 ){
                if(dictionnaire.getMots().get(pos).getMot().compareTo(prefix)>=0)
                    mots.add(dictionnaire.getMots().get(pos));
            }else{
                return mots;
            }
            pos++;
        }
        return mots;
    }

    /**
     * Méthode qui fait une recherche par après la date de création
     * @param date
     * @return
     */
    public List<Mot> rechercheApresDateCreation(LocalDate date){

        List<Mot> mots = new ArrayList<>();

        int max = rechercheParDateDeCreation(date);
        int pos = 0;

        while (max >= 0 && pos < dictionnaire.getMots().size()){
            if(dictionnaire.getMots().get(max).getUpdatedAt().compareTo(date)>=0){
                mots.add(dictionnaire.getMots().get(pos));
            }else{
                return mots;
            }
            pos++;
        }
        return mots;
    }

    /**
     * Méthode qui fait une recherche par avant la date de création
     * @param date
     * @return
     */
    public List<Mot> rechercheAvantDateCreation(LocalDate date){
        List<Mot> mots = new ArrayList<>();

        int max = rechercheParDateDeCreation(date);
        int pos = 0;

        while (max >= 0 && pos < dictionnaire.getMots().size()){
            if(dictionnaire.getMots().get(max).getUpdatedAt().compareTo(date)<0){
                mots.add(dictionnaire.getMots().get(pos));
            }else{
                return mots;
            }
            pos++;
        }
        return mots;
    }

    /**
     * Méthode qui fait une recherche par après la date de modification
     * @param date
     * @return
     */
    public List<Mot> rechercheApresDateModification(LocalDate date){
        List<Mot> mots = new ArrayList<>();

        int max = rechercheParDateDeModification(date);
        int pos = 0;

        while (max >= 0 && pos < dictionnaire.getMots().size()){
            if(dictionnaire.getMots().get(max).getUpdatedAt().compareTo(date)>=0){
                mots.add(dictionnaire.getMots().get(pos));
            }else{
                return mots;
            }
            pos++;
        }
        return mots;
    }

    /**
     * Méthode qui fait une recherche par avant la date de modification
     * @param date
     * @return
     */
    public List<Mot> rechercheAvantDateModification(LocalDate date){
        List<Mot> mots = new ArrayList<>();

        int max = rechercheParDateDeModification(date);
        int pos = 0;

        while (max >= 0 && pos < dictionnaire.getMots().size()){
            if(dictionnaire.getMots().get(max).getUpdatedAt().compareTo(date)<0){
                mots.add(dictionnaire.getMots().get(pos));
            }else{
                return mots;
            }
            pos++;
        }
        return mots;
    }

    /**
     *  Méthode qui fait une recherche par la date de création
     * @param date
     * @return
     */
    private int rechercheParDateDeCreation(LocalDate date){

        int pos;
        dictionnaire.sortByDateOfCreation();

        if (dictionnaire.getMots().isEmpty()){
            return -1;
        }
        int low = 0;
        int high = dictionnaire.getMots().size()-1;
        int middle = -1;
        while (low <= high){
            middle = (low+high)/2;

            if(date.compareTo(dictionnaire.getMots().get(middle).getCreatedAt()) > 0 ){
                low = middle + 1;
            }else if(date.compareTo(dictionnaire.getMots().get(middle).getCreatedAt())<0){
                high = middle -1;
            }
            else {
                return middle;
            }
        }
        return middle;
    }

    /**
     * Méthode qui fait une recherche par la date de modification
     * @return
     */
    private int rechercheParDateDeModification(LocalDate date){
        dictionnaire.sortByDateOfModification();

        int pos;

        if (dictionnaire.getMots().isEmpty()){
            return -1;
        }
        int low = 0;
        int high = dictionnaire.getMots().size()-1;
        int middle = -1;
        while (low <= high){
            middle = (low+high)/2;

            if(date.compareTo(dictionnaire.getMots().get(middle).getUpdatedAt()) > 0 ){
                low = middle + 1;
            }else if(date.compareTo(dictionnaire.getMots().get(middle).getUpdatedAt())<0){
                high = middle -1;
            }
            else {
                return middle;
            }
        }
        return middle;
    }

    /**
     * Méthode qui fait une recherche pour un mots avec une image
     * @param image
     * @return
     */
    public List<Mot> rechercheMotAvecImage(boolean image){
        dictionnaire.sortByImage();

        List<Mot> mots = new ArrayList<>();
        if(image){
            int pos = dictionnaire.getMots().size()-1;
            while (pos >= 0 ){
                if(!dictionnaire.getMots().get(pos).getImageURL().isEmpty())
                    mots.add(dictionnaire.getMots().get(pos));
                else return mots;
                pos--;
            }
        }else {
            int pos = 0;
            while (pos < dictionnaire.getMots().size() ){
                if(dictionnaire.getMots().get(pos).getImageURL().isEmpty())
                    mots.add(dictionnaire.getMots().get(pos));
                else return mots;
                pos++;
            }
        }
        return mots;
    }

    /**
     * Méthode qui fait une recherche contenant les lettres
     * @param lettres
     * @return
     */
    public List<Mot> rechercheMotContenant(String lettres){
        List<Mot> out = new ArrayList<>();
        dictionnaire.getMots().forEach((Mot mot)->{
            if(mot.getMot().contains(lettres))
                out.add(mot);
        });
        return out;
    }

    /**
     * permet de créer une expression régulière pour être utiliser lors d'une recherche par wildcard
     * @return
     */
    private String getRegexFromWildCardString(String wildcard){
        String regex = "";
        for(char c : wildcard.toCharArray()){
            if(c == '*')
                regex += ".*";
            else if(c == '?')
                regex += ".";
            else regex += c;
        }
        return regex;
    }

    /**
     *Méthode qui fait une recherche avec un regex
     * @param regex
     * @return
     */
    private List<Mot> rechercheMotParRegex(String regex){

        Pattern p = Pattern.compile(regex);

        List<Mot> out = new ArrayList<>();
        dictionnaire.getMots().forEach((Mot mot)->{

            Matcher m = p.matcher(mot.getMot());
            if(m.matches()){
                out.add(mot);
            }
        });
        return out;
    }

    /**
     * Méthode qui fait une recherche avec une wildcard
     * @param wildcard
     * @return
     */
    public List<Mot> rechercheMotAvecWildcards(String wildcard){

        String regex = getRegexFromWildCardString(wildcard);
        return rechercheMotParRegex(regex);
    }


}
