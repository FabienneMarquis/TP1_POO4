package modele_TP1;

import javafx.scene.control.*;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.*;
import javafx.scene.image.Image;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

import java.awt.*;
import java.util.Observable;

/**
 * Created by 0940135 on 2016-01-04.
 */
public class Context extends Observable {
    private Dictionnaire dictionnaire;
    private Mot motCourant;
    private static Context context;

    private Context(){
        System.out.println("creation Context");
    }

    public static Context getInstance(){
        if(context==null)
            context = new Context();
        return context;
    }

    public Dictionnaire getDictionnaire() {
        return this.dictionnaire;
    }

    public void setDictionnaire(Dictionnaire dictionnaire) {
        this.dictionnaire = dictionnaire;
    }

    public void setMotCourant(Mot motCourant){
        this.motCourant = motCourant;
        alertObservers();
    }

    public Mot getMotCourant(){
        return this.motCourant;
    }
    public void alertObservers(){
        setChanged();
        notifyObservers();
    }

    public boolean sauvegarder(String textfielMot, String imageDuMot, String textAreaDefinition){
        System.out.print(textfielMot);
        Requete requete = new Requete(textfielMot,Context.getInstance().getDictionnaire());
        requete.recherche();
        if(requete.getResultat().size()>0){
            return false;
        }

        if(!imageDuMot.isEmpty()){
            Mot mot = new Mot(textfielMot,textAreaDefinition,imageDuMot);
            Context.getInstance().getDictionnaire().addMot(mot);
            Context.getInstance().setMotCourant(mot);
            Context.getInstance().getDictionnaire().sortByMot();
        }

        else{
            Mot mot = new Mot(textfielMot,textAreaDefinition,"");
            Context.getInstance().getDictionnaire().addMot(mot);
            Context.getInstance().setMotCourant(mot);
            Context.getInstance().getDictionnaire().sortByMot();
        }
        Context.getInstance().alertObservers();
        return true;
    }
    public boolean modifierMot( String textfielMot, String imageDuMot, String textAreaDefinition){
        if(textfielMot.compareTo(Context.getInstance().getMotCourant().getMot())!=0){
            Requete requete = new Requete(textfielMot,Context.getInstance().getDictionnaire());
            requete.recherche();
            if(requete.getResultat().size()>0){
                return false;
            }
        }
        Context.getInstance().getMotCourant().setMot(textfielMot);
        Context.getInstance().getMotCourant().setDefinition(textAreaDefinition);
        if(!imageDuMot.isEmpty())
            Context.getInstance().getMotCourant().setImageURL(imageDuMot);

        Context.getInstance().getDictionnaire().sortByMot();
        Context.getInstance().alertObservers();
        return true;
    }

}
