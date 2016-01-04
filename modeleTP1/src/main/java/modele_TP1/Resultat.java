package modele_TP1;

import java.util.List;

/**
 * Created by 0940135 on 2016-01-04.
 */
public class Resultat {
    private List<Mot> mots;

    public Resultat(){}
    public Resultat(List<Mot> mots) {
        this.mots = mots;
    }

    public List<Mot> getMots() {
        return mots;
    }

}
