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
 * Created by Gabriel et Fabienne on 2016-01-04.
 */
public class Context extends Observable {
    private Dictionnaire dictionnaire;
    private Mot motCourant;
    private static Context context;

    private Context(){
        System.out.println("creation Context");
    }

    /**
     *retour le la classe Context  qui renvoit le dictionnaire(liste de mot) et le mot Courant
     * @return context
     */
    public static Context getInstance(){
        if(context==null)
            context = new Context();
        return context;
    }

    /**
     * Retourne le dictionnaire (liste de mots)
     * @return dictionnaire
     */
    public Dictionnaire getDictionnaire() {
        return this.dictionnaire;
    }

    /**
     * Permets d'ajouter le dictionnaire créer dans la Classe dictionnaire à la classe context qui est observer par
     * les contrôleurs
     * @param dictionnaire
     */
    public void setDictionnaire(Dictionnaire dictionnaire) {
        this.dictionnaire = dictionnaire;
    }

    /**
     * Permets d'ajouter le mot qui a été choisi soi lors de la recherche ou du mot du jour (random)
     * à la classe context qui est observer pa les contrôleurs
     * @param motCourant
     */
    public void setMotCourant(Mot motCourant){
        this.motCourant = motCourant;
        alertObservers();
    }

    /**
     * Renvoit le Mot qui a été placer à la position mot Courant
     * @return
     */
    public Mot getMotCourant(){
        return this.motCourant;
    }
    public void alertObservers(){
        setChanged();
        notifyObservers();
    }

    /**
     * Permets la sauvegarde d'un nouveau mot grâce au paramettre envoyer
     * @param textfielMot nouveau mot
     * @param imageDuMot image du nouveau mot (string de URL)
     * @param textAreaDefinition
     * @return
     */
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

    /**
     *Permet la modification d'un mots selon les parametres suivant
     * @param textfielMot Mot
     * @param imageDuMot nouvelle image ou non du mots (String de l'URL)
     * @param textAreaDefinition
     * @return
     */
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
        Context.getInstance().getMotCourant().setUpdatedAt();
        if(!imageDuMot.isEmpty())
            Context.getInstance().getMotCourant().setImageURL(imageDuMot);

        Context.getInstance().getDictionnaire().sortByMot();
        Context.getInstance().alertObservers();
        return true;
    }

}
