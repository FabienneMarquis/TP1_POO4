package modele_TP1;

import java.util.Observable;

/**
 * Created by 0940135 on 2016-01-04.
 */
public class Context extends Observable {
    private Dictionnaire dictionnaire;
    private Mot motCourant;
    private static Context context;

    private Context(){

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
        notifyObservers();
    }

    public Mot getMotCourant(){
        return this.motCourant;
    }
}
