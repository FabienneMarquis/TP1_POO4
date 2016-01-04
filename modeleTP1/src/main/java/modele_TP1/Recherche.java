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

	//méthode de recherche selon le texte du mot
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

    public Mot rechercheMot(String recherche){
        int pos = recherchePosMot(recherche);
        return pos>=0?dictionnaire.getMots().get(pos):null;
    }

    public List<Mot> rechercheMotParPrefix(String prefix){
        List<Mot> mots = new ArrayList<>();

        prefix = prefix.toLowerCase();
        char[] max = prefix.toCharArray();
        max[max.length-1] = (char) (max[max.length-1]+1);

        int pos = recherchePosMot(prefix);

        while (pos >= 0 && pos < dictionnaire.getMots().size()){
            if(dictionnaire.getMots().get(pos).getMot().compareTo(String.valueOf(max))<=0){
                mots.add(dictionnaire.getMots().get(pos));
            }else{
                return mots;
            }
            pos++;
        }
        return mots;
    }

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

    private int rechercheParDateDeCreation(LocalDate date){

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

    private int rechercheParDateDeModification(LocalDate date){
        dictionnaire.sortByMot();

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

    public List<Mot> rechercheMotContenant(String lettres){
        List<Mot> out = new ArrayList<>();
        dictionnaire.getMots().forEach((Mot mot)->{
            if(mot.getMot().contains(lettres))
                out.add(mot);
        });
        return out;
    }

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
    public List<Mot> rechercheMotAvecWildcards(String wildcard){

        String regex = getRegexFromWildCardString(wildcard);
        return rechercheMotParRegex(regex);
    }

    public static void main(String[] args) {
        MotFactory motFactory = MotFactory.getInstance();
        Dictionnaire dictionnaire = new Dictionnaire(motFactory.getMots());
        Recherche recherche = new Recherche(dictionnaire);
        long startTime;
        long stopTime;
//        long startTime = System.currentTimeMillis();
//        Mot mot = recherche.rechercheMot("a");
//        long stopTime = System.currentTimeMillis();
//
//
//        System.out.println(mot.getMot());
//        System.out.println("time for recherche: "+(stopTime-startTime)+"ms");
//
//        startTime = System.currentTimeMillis();
//        List<Mot> mots = recherche.rechercheMotParPrefix("ab");
//        stopTime = System.currentTimeMillis();
//        for(Mot mot1: mots){
//            System.out.println(mot1.getMot()+"-"+mot1.getCreatedAt());
//        }
//        System.out.println("time for recherche: "+(stopTime-startTime)+"ms");

//        startTime = System.currentTimeMillis();
//        List<Mot> motsParDateCreation = recherche.rechercheApresDateCreation(LocalDate.of(2015,12,31));
//        stopTime = System.currentTimeMillis();
//        int i = 0;
//        for(Mot mot1: motsParDateCreation){
//            i++;
//            System.out.println(mot1.getMot() + "-" + mot1.getCreatedAt());
//        }
//        System.out.println("time for recherche: "+(stopTime-startTime)+"ms #result: "+i);
//
//        startTime = System.currentTimeMillis();
//        List<Mot> mots = recherche.rechercheMotAvecImage(true);
//        stopTime = System.currentTimeMillis();
//        int i = 0;
//        for(Mot mot: mots){
//            i++;
//            System.out.println(mot.getMot() + "-" + mot.getCreatedAt());
//        }
//        System.out.println("time for recherche: "+(stopTime-startTime)+"ms #result: "+i);

////
//        startTime = System.currentTimeMillis();
//        List<Mot> mots = recherche.rechercheMotContenant("sa");
//        stopTime = System.currentTimeMillis();
//        int i = 0;
//        for(Mot mot: mots){
//            i++;
//            System.out.println(mot.getMot() + "-" + mot.getCreatedAt());
//        }
//        System.out.println("time for recherche: "+(stopTime-startTime)+"ms #result: "+i);
        startTime = System.currentTimeMillis();
        List<Mot> mots = recherche.rechercheMotAvecWildcards("av*r?");
        stopTime = System.currentTimeMillis();
        int i = 0;
        for(Mot mot1: mots){
            i++;
            System.out.println(mot1.getMot()+"-"+mot1.getCreatedAt());
        }
        System.out.println("time for recherche: "+(stopTime-startTime)+"ms #result: "+i);
    }


}
